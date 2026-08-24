package com.vmp.xxlJob;

import com.alibaba.fastjson.JSONObject;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.service.ShopifyOrderService;
import com.vmp.service.ShopifyProductService;
import com.vmp.service.ShopifySessionReportService;
import com.vmp.utils.DateUtil;
import com.vmp.utils.JsonUtils;
import com.vmp.utils.StringTools;
import com.vmp.utils.VerifyUtils;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


/**
 * XxlJob（Bean模式）
 * <p>
 * 注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * <p>
 * 任务参数：以Json字符串传递任务执行参数
 */
@Component
public class ShopifyXxlJobHandler {

    private static final Logger logger = LoggerFactory.getLogger(ShopifyXxlJobHandler.class);

    @Resource
    private ShopifyProductService shopifyProductService;

    @Resource
    private ShopifyOrderService shopifyOrderService;

    @Resource
    private ShopifySessionReportService shopifySessionReportService;


    /**
     * 同步Shopify商品数据-子体
     * <p>
     * jobParams示例:
     * {"host":"vantruedashcam", "first":100}
     */
    @XxlJob("syncShopifyProducts")
    public void syncShopifyProducts() {
        String jobParams = XxlJobHelper.getJobParam();
        JSONObject jobParamsObject = null;
        String host = null;
        Integer first = null;

        try {
            // 解析JSON
            jobParamsObject = JsonUtils.convertJson2Obj(jobParams, JSONObject.class);
            host = jobParamsObject.getString("host");
            first = jobParamsObject.getInteger("first");

            // 业务参数校验
            if (StringTools.isEmpty(host) || null == first || first <= 0 || first > 100) {
                String errorMsg = "Xxl-syncShopifyProducts: 任务参数非法。host=" + host + ", first=" + first + " (有效范围: 1-100)";
                XxlJobHelper.log(errorMsg);
                XxlJobHelper.handleFail(errorMsg);
                return;
            }
        } catch (Exception e) {
            // 捕获JSON解析或任何其他参数处理异常
            String errorMsg = "Xxl-syncShopifyProducts: 任务参数处理失败。原始参数: " + jobParams;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.handleFail(errorMsg);
            return;
        }

        // 执行业务逻辑
        try {
            this.shopifyProductService.syncShopifyProducts(host, first, null);
            XxlJobHelper.log("Xxl-syncShopifyProducts: 任务执行成功。host=" + host + ", first=" + first);
        } catch (Exception e) {
            // 捕获业务异常
            String errorMsg = "Xxl-syncShopifyProducts: 业务执行失败。host=" + host + ", first=" + first;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail(errorMsg);
        }
    }

    /**
     * 同步官网订单数据
     * <p>
     * jobParams示例:
     * {"host":"vantruedashcam", "first":100}
     * {"host":"vantruedashcam", "first":100, "startDate":"2026-10-01", "endDate":"2026-10-01"}
     */
    @XxlJob("syncShopifyOrders")
    public void syncShopifyOrders() {
        String jobParams = XxlJobHelper.getJobParam();
        JSONObject jobParamsObject = null;
        String host = null;
        Integer first = null;
        String startDateStr = null;
        String endDateStr = null;
        Date startDate = null;
        Date endDate = null;

        try {
            // 解析JSON
            jobParamsObject = JsonUtils.convertJson2Obj(jobParams, JSONObject.class);
            host = jobParamsObject.getString("host");
            first = jobParamsObject.getInteger("first");
            Date curDate = new Date();
            startDateStr = jobParamsObject.containsKey("startDate") ? jobParamsObject.getString("startDate") : DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());
            endDateStr = jobParamsObject.containsKey("endDate") ? jobParamsObject.getString("endDate") : DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());

            startDate = DateUtil.parse(startDateStr, DateTimePatternEnum.YYYY_MM_DD.getPattern());
            endDate = DateUtil.parse(endDateStr, DateTimePatternEnum.YYYY_MM_DD.getPattern());
            // 业务参数校验
            if (StringTools.isEmpty(host) || null == first || first <= 0 || first > 100 || !VerifyUtils.verify(VerifyRegexEnum.YYYY_MM_DD, startDateStr) || !VerifyUtils.verify(VerifyRegexEnum.YYYY_MM_DD, endDateStr) || startDate.compareTo(endDate) > 0) {
                String errorMsg = "Xxl-syncShopifyOrders: 任务参数非法。host=" + host + ", first=" + first + " (有效范围: 1-100)" + ", startDate=" + startDateStr + ", endDate=" + endDateStr;
                XxlJobHelper.log(errorMsg);
                XxlJobHelper.handleFail(errorMsg);
                return;
            }
        } catch (Exception e) {
            // 捕获JSON解析或任何其他参数处理异常
            String errorMsg = "Xxl-syncShopifyOrders: 任务参数处理失败。原始参数: " + jobParams;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.handleFail(errorMsg);
            return;
        }

        // 执行业务逻辑
        try {
            this.shopifyOrderService.syncShopifyOrders(host, first, null, startDate, endDate);
            XxlJobHelper.log("Xxl-syncShopifyOrders: 任务执行成功。host=" + host + ", first=" + first + ", startDate=" + startDateStr + ", endDate=" + endDateStr);
        } catch (Exception e) {
            // 捕获业务异常
            String errorMsg = "Xxl-syncShopifyOrders: 业务执行失败。host=" + host + ", first=" + first + ", startDate=" + startDateStr + ", endDate=" + endDateStr;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail(errorMsg);
        }
    }

    /**
     * 分析官网流量Sessions
     *
     * jobParams示例:
     * {"host":"vantruedashcam", "type":"Product"}
     * {"host":"vantruedashcam", "type":"Product", "day":"2026-10-01"}
     */
    @XxlJob("writeShopifySessionsReport")
    public void writeShopifySessionsReport() {
        String jobParams = XxlJobHelper.getJobParam();
        JSONObject jobParamsObject = null;
        String host = null;
        String type = null;
        String dayStr = null;

        try {
            // 解析JSON
            jobParamsObject = JsonUtils.convertJson2Obj(jobParams, JSONObject.class);
            host = jobParamsObject.getString("host");
            type = jobParamsObject.containsKey("type") ? jobParamsObject.getString("type") : Constants.SHOPIFY_SESSION_PRODUCT_TYPE;
            Date curDate = new Date();
            dayStr = jobParamsObject.containsKey("day") ? jobParamsObject.getString("day") : DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());

            // 业务参数校验
            if (StringTools.isEmpty(host) || !VerifyUtils.verify(VerifyRegexEnum.YYYY_MM_DD, dayStr)) {
                String errorMsg = "Xxl-syncShopifyOrders: 任务参数非法。host=" + host + ", type=" + type + ", day=" + dayStr;
                XxlJobHelper.log(errorMsg);
                XxlJobHelper.handleFail(errorMsg);
                return;
            }
        } catch (Exception e) {
            // 捕获JSON解析或任何其他参数处理异常
            String errorMsg = "Xxl-syncShopifyOrders: 任务参数处理失败。原始参数: " + jobParams;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.handleFail(errorMsg);
            return;
        }

        // 执行业务逻辑
        try {
            Date day = DateUtil.parse(dayStr, DateTimePatternEnum.YYYY_MM_DD.getPattern());
            this.shopifySessionReportService.getShopifySessionsReport(host, type, day);
            XxlJobHelper.log("Xxl-syncShopifyOrders: 任务执行成功。host=" + host + ", type=" + type + ", day=" + dayStr);
        } catch (Exception e) {
            // 捕获业务异常
            String errorMsg = "Xxl-syncShopifyOrders: 业务执行失败。host=" + host + ", type=" + type + ", day=" + dayStr;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail(errorMsg);
        }
    }

}
