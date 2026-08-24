package com.vmp.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.vmp.entity.apidto.GoogleAdsCampaignMetricsApiDto;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dashboardVo.GoogleAdsCampaignMetricDailyPerformanceVO;
import com.vmp.entity.enums.AdsPlatformEnum;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.entity.po.CrossPlatformProductCommonInfo;
import com.vmp.entity.po.GoogleCampaignMetric;
import com.vmp.entity.po.ProductsAdsMappingInfo;
import com.vmp.entity.query.*;
import com.vmp.entity.vo.CrossPlatformProductCommonInfoVO;
import com.vmp.entity.vo.GoogleAdsCampaignMetricVO;
import com.vmp.service.*;
import com.vmp.utils.CopyTools;
import com.vmp.utils.DateUtil;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.po.GoogleCampaignInfo;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.mappers.GoogleCampaignInfoMapper;
import com.vmp.utils.StringTools;


/**
 * 谷歌广告系列 业务接口实现
 */
@Service("googleCampaignInfoService")
public class GoogleCampaignInfoServiceImpl implements GoogleCampaignInfoService {

    @Resource
    private GoogleCampaignInfoMapper<GoogleCampaignInfo, GoogleCampaignInfoQuery> googleCampaignInfoMapper;

    @Resource
    private GoogleAdsApiService googleAdsApiService;

    @Resource
    private GoogleCampaignMetricService googleCampaignMetricService;

    @Resource
    private ProductsAdsMappingInfoService productsAdsMappingInfoService;

    @Resource
    private CrossPlatformProductCommonInfoService crossPlatformProductCommonInfoService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<GoogleCampaignInfo> findListByParam(GoogleCampaignInfoQuery param) {
        return this.googleCampaignInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(GoogleCampaignInfoQuery param) {
        return this.googleCampaignInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<GoogleCampaignInfo> findListByPage(GoogleCampaignInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<GoogleCampaignInfo> list = this.findListByParam(param);
        PaginationResultVO<GoogleCampaignInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(GoogleCampaignInfo bean) {
        return this.googleCampaignInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<GoogleCampaignInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.googleCampaignInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<GoogleCampaignInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.googleCampaignInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(GoogleCampaignInfo bean, GoogleCampaignInfoQuery param) {
        StringTools.checkParam(param);
        return this.googleCampaignInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(GoogleCampaignInfoQuery param) {
        StringTools.checkParam(param);
        return this.googleCampaignInfoMapper.deleteByParam(param);
    }

    /**
     * 根据CampaignId获取对象
     */
    @Override
    public GoogleCampaignInfo getGoogleCampaignInfoByCampaignId(String campaignId) {
        return this.googleCampaignInfoMapper.selectByCampaignId(campaignId);
    }

    /**
     * 根据CampaignId修改
     */
    @Override
    public Integer updateGoogleCampaignInfoByCampaignId(GoogleCampaignInfo bean, String campaignId) {
        return this.googleCampaignInfoMapper.updateByCampaignId(bean, campaignId);
    }

    /**
     * 根据CampaignId删除
     */
    @Override
    public Integer deleteGoogleCampaignInfoByCampaignId(String campaignId) {
        return this.googleCampaignInfoMapper.deleteByCampaignId(campaignId);
    }

    /**
     * 同步谷歌广告系列表现
     *
     * @param segmentsDate
     */
    @Override
    public void syncGoogleAdsCampaignMetrics(Date segmentsDate) {
        List<GoogleAdsCampaignMetricsApiDto> adsCampaignMetricsDtoList = this.googleAdsApiService.getGoogleAdsCampaignMetrics(segmentsDate);

        List<GoogleCampaignInfo> campaignInfos = new ArrayList<>();
        List<GoogleCampaignMetric> campaignMetrics = new ArrayList<>();
        List<ProductsAdsMappingInfo> productsAdsMappings = new ArrayList<>();

        adsCampaignMetricsDtoList.forEach(adsCampaignMetricsDto -> {
            // CampaignInfo表
            GoogleCampaignInfo googleCampaignInfo = new GoogleCampaignInfo();
            googleCampaignInfo.setCampaignId(adsCampaignMetricsDto.getCampaign().getId());
            googleCampaignInfo.setCampaignName(adsCampaignMetricsDto.getCampaign().getName());
            googleCampaignInfo.setCurrencyCode(adsCampaignMetricsDto.getCustomer().getCurrencyCode());
            googleCampaignInfo.setBudgetAmount(BigDecimal.valueOf(adsCampaignMetricsDto.getCampaignBudget().getAmountMicros()).movePointLeft(6));
            googleCampaignInfo.setStatus(adsCampaignMetricsDto.getCampaign().getStatus());
            googleCampaignInfo.setAdvertisingChannelType(adsCampaignMetricsDto.getCampaign().getAdvertisingChannelType());
            googleCampaignInfo.setStartDateTime(adsCampaignMetricsDto.getCampaign().getStartDateTime());
            googleCampaignInfo.setEndDateTime(adsCampaignMetricsDto.getCampaign().getEndDateTime());
            campaignInfos.add(googleCampaignInfo);
            // Metric表
            GoogleCampaignMetric campaignMetric = new GoogleCampaignMetric();
            campaignMetric.setSegmentDate(segmentsDate);
            campaignMetric.setCampaignId(adsCampaignMetricsDto.getCampaign().getId());
            campaignMetric.setCurrencyCode(adsCampaignMetricsDto.getCustomer().getCurrencyCode());
            campaignMetric.setCostAmount(BigDecimal.valueOf(adsCampaignMetricsDto.getMetrics().getCostMicros()).movePointLeft(6));
            campaignMetric.setImpressions(adsCampaignMetricsDto.getMetrics().getImpressions());
            campaignMetric.setClicks(adsCampaignMetricsDto.getMetrics().getClicks());
            campaignMetric.setConversions(adsCampaignMetricsDto.getMetrics().getConversions());
            campaignMetric.setConversionsValue(adsCampaignMetricsDto.getMetrics().getConversionsValue());
            campaignMetrics.add(campaignMetric);
            // Mapping表
            ProductsAdsMappingInfo mapping = new ProductsAdsMappingInfo();
            mapping.setAdsPlatform(AdsPlatformEnum.GOOGLE.getPlatform());
            mapping.setAdsCampaignId(adsCampaignMetricsDto.getCampaign().getId());
            productsAdsMappings.add(mapping);
        });

        addOrUpdateBatch(campaignInfos);
        this.googleCampaignMetricService.addOrUpdateBatch(campaignMetrics);
        this.productsAdsMappingInfoService.addOrUpdateBatch(productsAdsMappings);
    }

    /**
     * 查询谷歌广告系列--分页
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @Override
    public PaginationResultVO<GoogleAdsCampaignMetricVO> loadGoogleAdsCampaignMetricsByPage(Date dateRangeStart, Date dateRangeEnd, GoogleAdsCampaignCommonQuery query) {
        List<String> adsCampaignIdList = new ArrayList<>();

        // 先获取campaign info
        // 从Mapping表里查询 campaign id列表
        ProductsAdsMappingInfoQuery mappingQuery = new ProductsAdsMappingInfoQuery();
        mappingQuery.setMappingProductUid(query.getMappingProductUid());
        mappingQuery.setAdsPlatform(AdsPlatformEnum.GOOGLE.getPlatform());
        this.productsAdsMappingInfoService.findListByParam(mappingQuery).forEach(productsAdsMappingInfo -> adsCampaignIdList.add(productsAdsMappingInfo.getAdsCampaignId()));

        GoogleCampaignInfoQuery campaignInfoQuery = new GoogleCampaignInfoQuery();
        campaignInfoQuery.setPageNo(query.getPageNo());
        campaignInfoQuery.setPageSize(query.getPageSize());
        campaignInfoQuery.setCampaignIdList(adsCampaignIdList);
        campaignInfoQuery.setCampaignNameFuzzy(query.getCampaignNameFuzzy());
        campaignInfoQuery.setStatus(query.getStatus());
        campaignInfoQuery.setPersonInCharge(query.getPersonInCharge());
        campaignInfoQuery.setOrderBy("start_date_time desc");

        // 查询分页
        int count = this.findCountByParam(campaignInfoQuery);
        int pageSize = campaignInfoQuery.getPageSize() == null ? PageSize.SIZE15.getSize() : campaignInfoQuery.getPageSize();

        SimplePage page = new SimplePage(campaignInfoQuery.getPageNo(), count, pageSize);
        campaignInfoQuery.setSimplePage(page);

        List<GoogleCampaignInfo> googleCampaignInfoList = findListByParam(campaignInfoQuery);
        List<GoogleAdsCampaignMetricVO> googleAdsCampaignMetricVOList = CopyTools.copyList(googleCampaignInfoList, GoogleAdsCampaignMetricVO.class);
        // 再获取计算campaign metrics 组装VO
        googleAdsCampaignMetricVOList.forEach(vo -> {
            vo.setCostAmount(BigDecimal.valueOf(0));
            vo.setImpressions(0L);
            vo.setClicks(0L);
            vo.setConversions(BigDecimal.valueOf(0));
            vo.setConversionsValue(BigDecimal.valueOf(0));
            GoogleCampaignMetricQuery metricQuery = new GoogleCampaignMetricQuery();
            metricQuery.setCampaignId(vo.getCampaignId());
            metricQuery.setSegmentDateStart(DateUtil.format(dateRangeStart, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
            metricQuery.setSegmentDateEnd(DateUtil.format(dateRangeEnd, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
            List<GoogleCampaignMetric> metricList = this.googleCampaignMetricService.findListByParam(metricQuery);
            metricList.forEach(metric -> {
                vo.setCostAmount(vo.getCostAmount().add(metric.getCostAmount()));
                vo.setImpressions(vo.getImpressions() + metric.getImpressions());
                vo.setClicks(vo.getClicks() + metric.getClicks());
                vo.setConversions(vo.getConversions().add(metric.getConversions()));
                vo.setConversionsValue(vo.getConversionsValue().add(metric.getConversionsValue()));
            });
            if (vo.getImpressions() > 0) {
                vo.setCtr((BigDecimal.valueOf(vo.getClicks()).divide(BigDecimal.valueOf(vo.getImpressions()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (vo.getClicks() > 0 && vo.getCostAmount() != null) {
                vo.setCpc(vo.getCostAmount().divide(BigDecimal.valueOf(vo.getClicks()), 2, RoundingMode.HALF_UP));
            }
            if (vo.getClicks() > 0 && vo.getConversions() != null) {
                vo.setCvr((vo.getConversions().divide(BigDecimal.valueOf(vo.getClicks()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (vo.getConversionsValue() != null && vo.getCostAmount() != null && vo.getCostAmount().compareTo(BigDecimal.ZERO) != 0) {
                vo.setRoas(vo.getConversionsValue().divide(vo.getCostAmount(), 2, RoundingMode.HALF_UP).doubleValue());
            }
            if (vo.getCostAmount() != null && vo.getConversions() != null && vo.getConversions().compareTo(BigDecimal.ZERO) != 0) {
                vo.setCpa(vo.getCostAmount().divide(vo.getConversions(), 2, RoundingMode.HALF_UP));
            }

            // 组装mapping product common info
            ProductsAdsMappingInfo mappingInfo = this.productsAdsMappingInfoService.getProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(AdsPlatformEnum.GOOGLE.getPlatform(), vo.getCampaignId());
            if (!StringTools.isEmpty(mappingInfo.getMappingProductUid())) {
                vo.setCrossPlatformProductCommonInfoVO(CopyTools.copy(this.crossPlatformProductCommonInfoService.getCrossPlatformProductCommonInfoByUid(mappingInfo.getMappingProductUid()), CrossPlatformProductCommonInfoVO.class));
            }
        });

        PaginationResultVO<GoogleAdsCampaignMetricVO> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), googleAdsCampaignMetricVOList);
        return result;
    }

    /**
     * 获取谷歌广告系列数据表现
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @Override
    public List<GoogleAdsCampaignMetricDailyPerformanceVO> getGoogleAdsCampaignMetricsDailyPerformance(Date dateRangeStart, Date dateRangeEnd, GoogleAdsCampaignCommonQuery query) {
        List<GoogleAdsCampaignMetricDailyPerformanceVO> result = new ArrayList<>();
        List<String> adsCampaignIdList = new ArrayList<>();

        // 从Mapping表里查询 campaign id列表
        ProductsAdsMappingInfoQuery mappingQuery = new ProductsAdsMappingInfoQuery();
        mappingQuery.setMappingProductUid(query.getMappingProductUid());
        mappingQuery.setAdsPlatform(AdsPlatformEnum.GOOGLE.getPlatform());
        this.productsAdsMappingInfoService.findListByParam(mappingQuery).forEach(productsAdsMappingInfo -> adsCampaignIdList.add(productsAdsMappingInfo.getAdsCampaignId()));

        GoogleCampaignInfoQuery campaignInfoQuery = new GoogleCampaignInfoQuery();
        campaignInfoQuery.setCampaignIdList(adsCampaignIdList);
        campaignInfoQuery.setCampaignNameFuzzy(query.getCampaignNameFuzzy());
        campaignInfoQuery.setStatus(query.getStatus());
        campaignInfoQuery.setPersonInCharge(query.getPersonInCharge());
        campaignInfoQuery.setOrderBy("start_date_time desc");

        List<GoogleCampaignInfo> googleCampaignInfoList = findListByParam(campaignInfoQuery);
        List<String> finalCampaignIdList = new ArrayList<>();
        googleCampaignInfoList.forEach(campaignInfo -> {
            finalCampaignIdList.add(campaignInfo.getCampaignId());
        });
        GoogleCampaignMetricQuery metricsQuery = new GoogleCampaignMetricQuery();
        metricsQuery.setCampaignIdList(finalCampaignIdList);
        List<String> reportDateList = DateUtil.getDateList(DateUtil.format(dateRangeStart, DateTimePatternEnum.YYYY_MM_DD.getPattern()), DateUtil.format(dateRangeEnd, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
        reportDateList.forEach(reportDate -> {
            GoogleAdsCampaignMetricDailyPerformanceVO dailyPerformanceVO = new GoogleAdsCampaignMetricDailyPerformanceVO();
            dailyPerformanceVO.setReportDate(reportDate);
            dailyPerformanceVO.setCostAmount(BigDecimal.valueOf(0));
            dailyPerformanceVO.setImpressions(0L);
            dailyPerformanceVO.setClicks(0L);
            dailyPerformanceVO.setConversions(BigDecimal.valueOf(0));
            dailyPerformanceVO.setConversionsValue(BigDecimal.valueOf(0));

            metricsQuery.setSegmentDate(reportDate);
            this.googleCampaignMetricService.findListByParam(metricsQuery).forEach(metrics -> {
                dailyPerformanceVO.setCostAmount(dailyPerformanceVO.getCostAmount().add(metrics.getCostAmount()));
                dailyPerformanceVO.setImpressions(dailyPerformanceVO.getImpressions() + metrics.getImpressions());
                dailyPerformanceVO.setClicks(dailyPerformanceVO.getClicks() + metrics.getClicks());
                dailyPerformanceVO.setConversions(dailyPerformanceVO.getConversions().add(metrics.getConversions()));
                dailyPerformanceVO.setConversionsValue(dailyPerformanceVO.getConversionsValue().add(metrics.getConversionsValue()));
            });
            if (dailyPerformanceVO.getImpressions() > 0) {
                dailyPerformanceVO.setCtr((BigDecimal.valueOf(dailyPerformanceVO.getClicks()).divide(BigDecimal.valueOf(dailyPerformanceVO.getImpressions()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (dailyPerformanceVO.getClicks() > 0 && dailyPerformanceVO.getCostAmount() != null) {
                dailyPerformanceVO.setCpc(dailyPerformanceVO.getCostAmount().divide(BigDecimal.valueOf(dailyPerformanceVO.getClicks()), 2, RoundingMode.HALF_UP));
            }
            if (dailyPerformanceVO.getClicks() > 0 && dailyPerformanceVO.getConversions() != null) {
                dailyPerformanceVO.setCvr((dailyPerformanceVO.getConversions().divide(BigDecimal.valueOf(dailyPerformanceVO.getClicks()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (dailyPerformanceVO.getConversionsValue() != null && dailyPerformanceVO.getCostAmount() != null && dailyPerformanceVO.getCostAmount().compareTo(BigDecimal.ZERO) != 0) {
                dailyPerformanceVO.setRoas(dailyPerformanceVO.getConversionsValue().divide(dailyPerformanceVO.getCostAmount(), 2, RoundingMode.HALF_UP).doubleValue());
            }
            if (dailyPerformanceVO.getCostAmount() != null && dailyPerformanceVO.getConversions() != null && dailyPerformanceVO.getConversions().compareTo(BigDecimal.ZERO) != 0) {
                dailyPerformanceVO.setCpa(dailyPerformanceVO.getCostAmount().divide(dailyPerformanceVO.getConversions(), 2, RoundingMode.HALF_UP));
            }

            result.add(dailyPerformanceVO);
        });

        return result;
    }

    /**
     * 分配谷歌广告投手
     *
     * @param newPersonInCharge
     * @param param
     * @return
     */
    @Override
    public void allocatePerson(String newPersonInCharge, GoogleCampaignInfoQuery param) {
        GoogleCampaignInfo updateInfo = new GoogleCampaignInfo();
        if (Constants.CANCEL_MARK.equals(newPersonInCharge)) {
            newPersonInCharge = "";
        }
        updateInfo.setPersonInCharge(newPersonInCharge);
        updateByParam(updateInfo, param);
    }
}