package com.vmp.xxlJob;


import com.alibaba.fastjson.JSONObject;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.service.GoogleCampaignInfoService;
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

@Component
public class GoogleAdsXxlJobHandler {

    private static final Logger logger = LoggerFactory.getLogger(GoogleAdsXxlJobHandler.class);

    @Resource
    private GoogleCampaignInfoService googleCampaignInfoService;

    /**
     * 同步谷歌广告系列表现
     * <p>
     * jobParams示例:
     * {"dateRangeType":"TODAY"}
     * {"dateRangeType":"YESTERDAY"}
     * {"dateRangeType":"CUSTOM", "date":"2026-10-01"}
     */
    @XxlJob("syncGoogleAdsCampaignMetrics")
    public void syncGoogleAdsCampaignMetrics() {
        String jobParams = XxlJobHelper.getJobParam();
        JSONObject jobParamsObject = null;
        String dateRangeType = null;
        String date = null;

        try {
            // 解析JSON
            jobParamsObject = JsonUtils.convertJson2Obj(jobParams, JSONObject.class);
            dateRangeType = jobParamsObject.getString("dateRangeType");
            if ("TODAY".equals(dateRangeType)) {
                Date curDate = new Date();
                date = DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());
            } else if ("YESTERDAY".equals(dateRangeType)) {
                Date yesterdayDate = DateUtil.getAfterDate(-1);
                date = DateUtil.format(yesterdayDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());
            } else if ("CUSTOM".equals(dateRangeType)) {
                date = jobParamsObject.containsKey("date") ? jobParamsObject.getString("date") : DateUtil.format(new Date(), DateTimePatternEnum.YYYY_MM_DD.getPattern());
            }

            // 业务参数校验
            if (StringTools.isEmpty(dateRangeType) || !VerifyUtils.verify(VerifyRegexEnum.YYYY_MM_DD, date)) {
                String errorMsg = "Xxl-syncGoogleAdsCampaignMetrics: 任务参数非法。dateRangeType=" + dateRangeType + ", date=" + date;
                XxlJobHelper.log(errorMsg);
                XxlJobHelper.handleFail(errorMsg);
                return;
            }
        } catch (Exception e) {
            // 捕获JSON解析或任何其他参数处理异常
            String errorMsg = "Xxl-syncGoogleAdsCampaignMetrics: 任务参数处理失败。原始参数: " + jobParams;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.handleFail(errorMsg);
            return;
        }

        // 执行业务逻辑
        try {
            this.googleCampaignInfoService.syncGoogleAdsCampaignMetrics(DateUtil.parse(
                    DateUtil.format(
                            DateUtil.parse(
                                    date, DateTimePatternEnum.YYYY_MM_DD.getPattern()),
                            DateTimePatternEnum.YYYYMMDD.getPattern()),
                    DateTimePatternEnum.YYYYMMDD.getPattern()));
            XxlJobHelper.log("Xxl-syncGoogleAdsCampaignMetrics: 任务执行成功。dateRangeType=" + dateRangeType + ", date=" + date);
        } catch (Exception e) {
            // 捕获业务异常
            String errorMsg = "Xxl-syncGoogleAdsCampaignMetrics: 业务执行失败。dateRangeType=" + dateRangeType + ", date=" + date;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail(errorMsg);
        }
    }

}
