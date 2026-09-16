package com.vmp.xxlJob;

import com.alibaba.fastjson.JSONObject;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.service.MetaAdsCampaignInfoService;
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
public class MetaAdsXxlJobHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaAdsXxlJobHandler.class);

    @Resource
    private MetaAdsCampaignInfoService metaAdsCampaignInfoService;

    /**
     * 同步Meta广告系列表现
     * <p>
     * jobParams示例:
     * {"dateRangeType":"LAST_7_DAYS"}
     * {"dateRangeType":"CUSTOM", "since":"2026-09-07", "until":"2026-09-07"}
     */
    @XxlJob("syncMetaAdsCampaignInsight")
    public void syncMetaAdsCampaignInsight() {
        String jobParams = XxlJobHelper.getJobParam();
        JSONObject jobParamsObject = null;
        String dateRangeType = null;
        String since = null;
        String until = null;

        try {
            // 解析JSON
            jobParamsObject = JsonUtils.convertJson2Obj(jobParams, JSONObject.class);
            dateRangeType = jobParamsObject.getString("dateRangeType");
            if ("LAST_7_DAYS".equals(dateRangeType)) {
                Date curDate = new Date();
                until = DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());
                since = DateUtil.format(DateUtil.getAfterDate(-6), DateTimePatternEnum.YYYY_MM_DD.getPattern());
            } else if ("CUSTOM".equals(dateRangeType)) {
                since = jobParamsObject.getString("since");
                until = jobParamsObject.getString("until");
            }

            // 业务参数校验
            if (StringTools.isEmpty(dateRangeType) || !VerifyUtils.verify(VerifyRegexEnum.YYYY_MM_DD, since) || !VerifyUtils.verify(VerifyRegexEnum.YYYY_MM_DD, until)) {
                String errorMsg = "Xxl-syncMetaAdsCampaignInsights: 任务参数非法。dateRangeType=" + dateRangeType + ", since=" + since + ", until=" + until;
                XxlJobHelper.log(errorMsg);
                XxlJobHelper.handleFail(errorMsg);
                return;
            }
        } catch (Exception e) {
            // 捕获JSON解析或任何其他参数处理异常
            String errorMsg = "Xxl-syncMetaAdsCampaignInsights: 任务参数处理失败。原始参数: " + jobParams;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.handleFail(errorMsg);
            return;
        }

        // 执行业务逻辑
        try {
            this.metaAdsCampaignInfoService.syncMetaAdsCampaignInsight(since, until);
            XxlJobHelper.log("Xxl-syncMetaAdsCampaignInsights: 任务执行成功。dateRangeType=" + dateRangeType + ", since=" + since + ", until=" + until);
        } catch (Exception e) {
            // 捕获业务异常
            String errorMsg = "Xxl-syncMetaAdsCampaignInsights: 业务执行失败。dateRangeType=" + dateRangeType + ", since=" + since + ", until=" + until;
            XxlJobHelper.log(errorMsg + ", 错误详情: " + e.getMessage());
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail(errorMsg);
        }
    }

}
