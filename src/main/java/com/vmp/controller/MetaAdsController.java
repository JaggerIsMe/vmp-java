package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.AdsPlatformEnum;
import com.vmp.entity.po.ProductsAdsMappingInfo;
import com.vmp.entity.query.MetaAdsCampaignCommonQuery;
import com.vmp.entity.query.MetaAdsCampaignInfoQuery;
import com.vmp.entity.query.ProductsAdsMappingInfoQuery;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.service.MetaAdsCampaignInfoService;
import com.vmp.service.ProductsAdsMappingInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController("metaAdsController")
@RequestMapping("/metaAds")
public class MetaAdsController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(MetaAdsController.class);

    @Resource
    private MetaAdsCampaignInfoService metaAdsCampaignInfoService;

    @Resource
    private ProductsAdsMappingInfoService productsAdsMappingInfoService;

    /**
     * 查询Meta广告系列--分页
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @RequestMapping("/loadMetaAdsCampaignInsights")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO loadMetaAdsCampaignInsights(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeStart,
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeEnd,
                                                  MetaAdsCampaignCommonQuery query) {
        return getSuccessResponseVO(metaAdsCampaignInfoService.loadMetaAdsCampaignInsightsByPage(dateRangeStart, dateRangeEnd, query));
    }

    /**
     * 获取Meta广告系列数据表现
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @RequestMapping("/getMetaAdsCampaignInsightsDailyPerformance")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO getMetaAdsCampaignInsightsDailyPerformance(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeStart,
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeEnd,
                                                                 MetaAdsCampaignCommonQuery query) {
        return getSuccessResponseVO(metaAdsCampaignInfoService.getMetaAdsCampaignInsightsDailyPerformance(dateRangeStart, dateRangeEnd, query));
    }

    /**
     * 分配Meta广告投手
     *
     * @param newPersonInCharge
     * @param param
     * @return
     */
    @RequestMapping("/allocatePerson")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO allocatePerson(String newPersonInCharge, MetaAdsCampaignInfoQuery param) {
        metaAdsCampaignInfoService.allocatePerson(newPersonInCharge, param);
        return getSuccessResponseVO(null);
    }

    /**
     * 谷歌广告绑定产品
     *
     * @param newMappingProductUid
     * @param param
     * @return
     */
    @RequestMapping("/linkProduct")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO linkProduct(String newMappingProductUid,
                                  ProductsAdsMappingInfoQuery param) {
        if (Constants.CANCEL_MARK.equals(newMappingProductUid)) {
            newMappingProductUid = "";
        }
        param.setAdsPlatform(AdsPlatformEnum.META.getPlatform());
        ProductsAdsMappingInfo updateInfo = new ProductsAdsMappingInfo();
        updateInfo.setMappingProductUid(newMappingProductUid);
        productsAdsMappingInfoService.updateByParam(updateInfo, param);
        return getSuccessResponseVO(null);
    }

}
