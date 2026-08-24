package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.AdsPlatformEnum;
import com.vmp.entity.po.ProductsAdsMappingInfo;
import com.vmp.entity.query.GoogleAdsCampaignCommonQuery;
import com.vmp.entity.query.GoogleCampaignInfoQuery;
import com.vmp.entity.query.ProductsAdsMappingInfoQuery;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.service.GoogleAdsApiService;
import com.vmp.service.GoogleCampaignInfoService;
import com.vmp.service.ProductsAdsMappingInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController("googleAdsController")
@RequestMapping("/googleAds")
public class GoogleAdsController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(GoogleAdsController.class);

    @Resource
    private GoogleCampaignInfoService googleCampaignInfoService;

    @Resource
    private ProductsAdsMappingInfoService productsAdsMappingInfoService;

    /**
     * 查询谷歌广告系列--分页
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @RequestMapping("/loadGoogleAdsCampaignMetrics")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO loadGoogleAdsCampaignMetrics(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeStart,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeEnd,
                                                   GoogleAdsCampaignCommonQuery query) {
        return getSuccessResponseVO(googleCampaignInfoService.loadGoogleAdsCampaignMetricsByPage(dateRangeStart, dateRangeEnd, query));
    }

    /**
     * 获取谷歌广告系列数据表现
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @RequestMapping("/getGoogleAdsCampaignMetricsDailyPerformance")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO getGoogleAdsCampaignMetricsDailyPerformance(@RequestParam(value = "dateRangeStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeStart,
                                                                  @RequestParam(value = "dateRangeEnd") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRangeEnd,
                                                                  GoogleAdsCampaignCommonQuery query) {
        return getSuccessResponseVO(googleCampaignInfoService.getGoogleAdsCampaignMetricsDailyPerformance(dateRangeStart, dateRangeEnd, query));
    }

    /**
     * 分配谷歌广告投手
     *
     * @param newPersonInCharge
     * @param param
     * @return
     */
    @RequestMapping("/allocatePerson")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO allocatePerson(@RequestParam(value = "newPersonInCharge") String newPersonInCharge, GoogleCampaignInfoQuery param) {
        googleCampaignInfoService.allocatePerson(newPersonInCharge, param);
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
    public ResponseVO linkProduct(@RequestParam(value = "newMappingProductUid") String newMappingProductUid,
                                  ProductsAdsMappingInfoQuery param) {
        if (Constants.CANCEL_MARK.equals(newMappingProductUid)) {
            newMappingProductUid = "";
        }
        param.setAdsPlatform(AdsPlatformEnum.GOOGLE.getPlatform());
        ProductsAdsMappingInfo updateInfo = new ProductsAdsMappingInfo();
        updateInfo.setMappingProductUid(newMappingProductUid);
        productsAdsMappingInfoService.updateByParam(updateInfo, param);
        return getSuccessResponseVO(null);
    }

}
