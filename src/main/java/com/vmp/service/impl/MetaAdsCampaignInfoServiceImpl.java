package com.vmp.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.vmp.entity.apidto.MetaAdsAccountApiDto;
import com.vmp.entity.apidto.MetaAdsApiResult;
import com.vmp.entity.apidto.MetaAdsCampaignApiDto;
import com.vmp.entity.apidto.MetaAdsCampaignInsightApiDto;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dashboardVo.GoogleAdsCampaignMetricDailyPerformanceVO;
import com.vmp.entity.dashboardVo.MetaAdsCampaignInsightDailyPerformanceVO;
import com.vmp.entity.enums.AdsPlatformEnum;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.entity.po.GoogleCampaignMetric;
import com.vmp.entity.po.MetaCampaignInsight;
import com.vmp.entity.po.ProductsAdsMappingInfo;
import com.vmp.entity.query.*;
import com.vmp.entity.vo.CrossPlatformProductCommonInfoVO;
import com.vmp.entity.vo.MetaCampaignInsightVO;
import com.vmp.service.*;
import com.vmp.utils.CopyTools;
import com.vmp.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.po.MetaAdsCampaignInfo;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.mappers.MetaAdsCampaignInfoMapper;
import com.vmp.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * meta广告系列信息 业务接口实现
 */
@Service("metaAdsCampaignInfoService")
public class MetaAdsCampaignInfoServiceImpl implements MetaAdsCampaignInfoService {

    @Resource
    private MetaAdsCampaignInfoMapper<MetaAdsCampaignInfo, MetaAdsCampaignInfoQuery> metaAdsCampaignInfoMapper;

    @Resource
    private MetaAdsApiService metaAdsApiService;

    @Resource
    private ProductsAdsMappingInfoService productsAdsMappingInfoService;

    @Resource
    private MetaCampaignInsightService metaCampaignInsightService;

    @Resource
    private CrossPlatformProductCommonInfoService crossPlatformProductCommonInfoService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<MetaAdsCampaignInfo> findListByParam(MetaAdsCampaignInfoQuery param) {
        return this.metaAdsCampaignInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(MetaAdsCampaignInfoQuery param) {
        return this.metaAdsCampaignInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<MetaAdsCampaignInfo> findListByPage(MetaAdsCampaignInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<MetaAdsCampaignInfo> list = this.findListByParam(param);
        PaginationResultVO<MetaAdsCampaignInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(MetaAdsCampaignInfo bean) {
        return this.metaAdsCampaignInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<MetaAdsCampaignInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.metaAdsCampaignInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<MetaAdsCampaignInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.metaAdsCampaignInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(MetaAdsCampaignInfo bean, MetaAdsCampaignInfoQuery param) {
        StringTools.checkParam(param);
        return this.metaAdsCampaignInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(MetaAdsCampaignInfoQuery param) {
        StringTools.checkParam(param);
        return this.metaAdsCampaignInfoMapper.deleteByParam(param);
    }

    /**
     * 根据Uid获取对象
     */
    @Override
    public MetaAdsCampaignInfo getMetaAdsCampaignInfoByUid(String uid) {
        return this.metaAdsCampaignInfoMapper.selectByUid(uid);
    }

    /**
     * 根据Uid修改
     */
    @Override
    public Integer updateMetaAdsCampaignInfoByUid(MetaAdsCampaignInfo bean, String uid) {
        return this.metaAdsCampaignInfoMapper.updateByUid(bean, uid);
    }

    /**
     * 根据Uid删除
     */
    @Override
    public Integer deleteMetaAdsCampaignInfoByUid(String uid) {
        return this.metaAdsCampaignInfoMapper.deleteByUid(uid);
    }

    /**
     * 根据CampaignIdAndAdsAccountId获取对象
     */
    @Override
    public MetaAdsCampaignInfo getMetaAdsCampaignInfoByCampaignIdAndAdsAccountId(String campaignId, String adsAccountId) {
        return this.metaAdsCampaignInfoMapper.selectByCampaignIdAndAdsAccountId(campaignId, adsAccountId);
    }

    /**
     * 根据CampaignIdAndAdsAccountId修改
     */
    @Override
    public Integer updateMetaAdsCampaignInfoByCampaignIdAndAdsAccountId(MetaAdsCampaignInfo bean, String campaignId, String adsAccountId) {
        return this.metaAdsCampaignInfoMapper.updateByCampaignIdAndAdsAccountId(bean, campaignId, adsAccountId);
    }

    /**
     * 根据CampaignIdAndAdsAccountId删除
     */
    @Override
    public Integer deleteMetaAdsCampaignInfoByCampaignIdAndAdsAccountId(String campaignId, String adsAccountId) {
        return this.metaAdsCampaignInfoMapper.deleteByCampaignIdAndAdsAccountId(campaignId, adsAccountId);
    }

    public String getMetaCampaignUid(String accountId, String campaignId) {
        return campaignId + accountId;
    }

    /**
     * 同步Meta广告系列表现
     *
     * @param since
     * @param until
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncMetaAdsCampaignInsight(String since, String until) {
        // 获取所有广告账户
        List<MetaAdsAccountApiDto> accountList = new ArrayList<>();
        String accountNext = null;
        String accountAfter = null;
        do {
            MetaAdsApiResult<MetaAdsAccountApiDto> adsAccountApiResult = this.metaAdsApiService.getAdsAccountList(accountAfter);
            adsAccountApiResult.getData().forEach(data -> {
                MetaAdsAccountApiDto accountApiDto = new MetaAdsAccountApiDto();
                accountApiDto.setId(data.getId());
                accountApiDto.setName(data.getName());
                accountApiDto.setAccountStatus(data.getAccountStatus());
                accountApiDto.setCurrency(data.getCurrency());
                accountApiDto.setTimezoneName(data.getTimezoneName());
                accountList.add(accountApiDto);
            });
            accountNext = getNextPage(adsAccountApiResult);
            accountAfter = getAfterCursor(adsAccountApiResult);
        } while (null != accountNext);

        List<MetaAdsCampaignInfo> campaignList = new ArrayList<>();
        List<ProductsAdsMappingInfo> mappingInfoList = new ArrayList<>();
        List<MetaCampaignInsight> insightList = new ArrayList<>();

        // 遍历广告账户获取所属广告系列信息
        accountList.forEach(account -> {
            String campaignNext = null;
            String campaignAfter = null;
            do {
                MetaAdsApiResult<MetaAdsCampaignApiDto> adsCampaignApiResult = this.metaAdsApiService.getCampaignList(account.getId(), campaignAfter);
                adsCampaignApiResult.getData().forEach(campaignApiDto -> {
                    // Info表
                    MetaAdsCampaignInfo campaignInfo = new MetaAdsCampaignInfo();
                    campaignInfo.setUid(getMetaCampaignUid(account.getId(), campaignApiDto.getId()));
                    campaignInfo.setCampaignId(campaignApiDto.getId());
                    campaignInfo.setCampaignName(campaignApiDto.getName());
                    campaignInfo.setAdsAccountId(account.getId());
                    campaignInfo.setAdsAccountName(account.getName());
                    campaignInfo.setAdsAccountStatus(account.getAccountStatus());
                    campaignInfo.setCurrencyCode(account.getCurrency());
                    campaignInfo.setEffectiveStatus(campaignApiDto.getEffectiveStatus());
                    campaignInfo.setStartTime(campaignApiDto.getStartTime());
                    campaignInfo.setStopTime(campaignApiDto.getStopTime());
                    campaignList.add(campaignInfo);

                    // Mapping表
                    ProductsAdsMappingInfo mappingInfo = new ProductsAdsMappingInfo();
                    mappingInfo.setAdsPlatform(AdsPlatformEnum.META.getPlatform());
                    mappingInfo.setAdsCampaignId(getMetaCampaignUid(account.getId(), campaignApiDto.getId()));
                    mappingInfoList.add(mappingInfo);
                });
                campaignNext = getNextPage(adsCampaignApiResult);
                campaignAfter = getAfterCursor(adsCampaignApiResult);
            } while (null != campaignNext);
        });

        // 遍历广告账户获取广告系列Insights
        accountList.forEach(account -> {
            String insightNext = null;
            String insightAfter = null;
            do {
                MetaAdsApiResult<MetaAdsCampaignInsightApiDto> campaignInsightApiResult = this.metaAdsApiService.getCampaignInsights(account.getId(), insightAfter, since, until);
                campaignInsightApiResult.getData().forEach(insightApiDto -> {
                    // Insight表
                    MetaCampaignInsight insight = new MetaCampaignInsight();
                    insight.setSegmentDate(insightApiDto.getDataStart());
                    insight.setCampaignUid(getMetaCampaignUid(account.getId(), insightApiDto.getCampaignId()));
                    insight.setCurrencyCode(account.getCurrency());
                    insight.setCostAmount(insightApiDto.getSpend());
                    insight.setImpressions(insightApiDto.getImpressions().longValue());
                    insight.setClicks(insightApiDto.getClicks().longValue());

                    long conversions = 0L;
                    BigDecimal conversionsValue = BigDecimal.ZERO;
                    // 遍历查找purchase
                    if (insightApiDto.getActions() != null) {
                        for (MetaAdsCampaignInsightApiDto.Action action : insightApiDto.getActions()) {
                            if ("purchase".equals(action.getActionType())) {
                                conversions = action.getValue().longValue();
                                break;
                            }
                        }
                    }
                    if (insightApiDto.getActionValues() != null) {
                        for (MetaAdsCampaignInsightApiDto.ActionValue actionValue : insightApiDto.getActionValues()) {
                            if ("purchase".equals(actionValue.getActionType())) {
                                conversionsValue = actionValue.getValue();
                                break;
                            }
                        }
                    }
                    insight.setConversions(conversions);
                    insight.setConversionsValue(conversionsValue);
                    insightList.add(insight);
                });
                insightNext = getNextPage(campaignInsightApiResult);
                insightAfter = getAfterCursor(campaignInsightApiResult);
            } while (null != insightNext);
        });

        this.metaAdsCampaignInfoMapper.insertOrUpdateBatch(campaignList);
        this.productsAdsMappingInfoService.addOrUpdateBatch(mappingInfoList);
        this.metaCampaignInsightService.addOrUpdateBatch(insightList);
    }

    private static String getNextPage(MetaAdsApiResult<?> apiResult) {
        if (apiResult == null || apiResult.getPaging() == null) {
            return null;
        }
        return apiResult.getPaging().getNext();
    }

    private static String getAfterCursor(MetaAdsApiResult<?> apiResult) {
        if (apiResult == null
                || apiResult.getPaging() == null
                || apiResult.getPaging().getCursors() == null) {
            return null;
        }
        return apiResult.getPaging().getCursors().getAfter();
    }

    /**
     * 查询Meta广告系列--分页
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @Override
    public PaginationResultVO<MetaCampaignInsightVO> loadMetaAdsCampaignInsightsByPage(Date dateRangeStart, Date dateRangeEnd, MetaAdsCampaignCommonQuery query) {
        List<String> adsCampaignIdList = new ArrayList<>();

        // 先获取campaign info
        // 从Mapping表里查询 campaign id列表
        ProductsAdsMappingInfoQuery mappingQuery = new ProductsAdsMappingInfoQuery();
        mappingQuery.setMappingProductUid(query.getMappingProductUid());
        mappingQuery.setAdsPlatform(AdsPlatformEnum.META.getPlatform());
        this.productsAdsMappingInfoService.findListByParam(mappingQuery).forEach(productsAdsMappingInfo -> adsCampaignIdList.add(productsAdsMappingInfo.getAdsCampaignId()));

        MetaAdsCampaignInfoQuery campaignInfoQuery = new MetaAdsCampaignInfoQuery();
        campaignInfoQuery.setPageNo(query.getPageNo());
        campaignInfoQuery.setPageSize(query.getPageSize());
        campaignInfoQuery.setUidList(adsCampaignIdList);
        campaignInfoQuery.setCampaignNameFuzzy(query.getCampaignNameFuzzy());
        campaignInfoQuery.setEffectiveStatus(query.getEffectiveStatus());
        campaignInfoQuery.setPersonInCharge(query.getPersonInCharge());
        campaignInfoQuery.setOrderBy("start_time desc");

        // 查询分页
        int count = this.findCountByParam(campaignInfoQuery);
        int pageSize = campaignInfoQuery.getPageSize() == null ? PageSize.SIZE15.getSize() : campaignInfoQuery.getPageSize();

        SimplePage page = new SimplePage(campaignInfoQuery.getPageNo(), count, pageSize);
        campaignInfoQuery.setSimplePage(page);

        List<MetaAdsCampaignInfo> metaAdsCampaignInfoList = findListByParam(campaignInfoQuery);
        List<MetaCampaignInsightVO> metaCampaignInsightVOList = CopyTools.copyList(metaAdsCampaignInfoList, MetaCampaignInsightVO.class);

        // 再获取计算campaign metrics 组装VO
        metaCampaignInsightVOList.forEach(vo -> {
            vo.setCostAmount(BigDecimal.valueOf(0));
            vo.setImpressions(0L);
            vo.setClicks(0L);
            vo.setConversions(0L);
            vo.setConversionsValue(BigDecimal.valueOf(0));
            MetaCampaignInsightQuery insightQuery = new MetaCampaignInsightQuery();
            insightQuery.setCampaignUid(vo.getUid());
            insightQuery.setSegmentDateStart(DateUtil.format(dateRangeStart, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
            insightQuery.setSegmentDateEnd(DateUtil.format(dateRangeEnd, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
            List<MetaCampaignInsight> insightList = this.metaCampaignInsightService.findListByParam(insightQuery);
            insightList.forEach(insight -> {
                vo.setCostAmount(vo.getCostAmount().add(insight.getCostAmount()));
                vo.setImpressions(vo.getImpressions() + insight.getImpressions());
                vo.setClicks(vo.getClicks() + insight.getClicks());
                vo.setConversions(vo.getConversions() + insight.getConversions());
                vo.setConversionsValue(vo.getConversionsValue().add(insight.getConversionsValue()));
            });
            if (vo.getImpressions() > 0) {
                vo.setCtr((BigDecimal.valueOf(vo.getClicks()).divide(BigDecimal.valueOf(vo.getImpressions()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (vo.getClicks() > 0 && vo.getCostAmount() != null) {
                vo.setCpc(vo.getCostAmount().divide(BigDecimal.valueOf(vo.getClicks()), 2, RoundingMode.HALF_UP));
            }
            if (vo.getClicks() > 0 && vo.getConversions() != null) {
                vo.setCvr((BigDecimal.valueOf(vo.getConversions()).divide(BigDecimal.valueOf(vo.getClicks()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (vo.getConversionsValue() != null && vo.getCostAmount() != null && vo.getCostAmount().compareTo(BigDecimal.ZERO) != 0) {
                vo.setRoas(vo.getConversionsValue().divide(vo.getCostAmount(), 2, RoundingMode.HALF_UP).doubleValue());
            }
            if (vo.getCostAmount() != null && vo.getConversions() != null && vo.getConversions() != 0) {
                vo.setCpa(vo.getCostAmount().divide(BigDecimal.valueOf(vo.getConversions()), 2, RoundingMode.HALF_UP));
            }

            // 组装mapping product common info
            ProductsAdsMappingInfo mappingInfo = this.productsAdsMappingInfoService.getProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(AdsPlatformEnum.META.getPlatform(), vo.getUid());
            if (!StringTools.isEmpty(mappingInfo.getMappingProductUid())) {
                vo.setCrossPlatformProductCommonInfoVO(CopyTools.copy(this.crossPlatformProductCommonInfoService.getCrossPlatformProductCommonInfoByUid(mappingInfo.getMappingProductUid()), CrossPlatformProductCommonInfoVO.class));
            }
        });

        PaginationResultVO<MetaCampaignInsightVO> result = new PaginationResultVO<>(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), metaCampaignInsightVOList);
        return result;
    }

    /**
     * 获取Meta广告系列数据表现
     *
     * @param dateRangeStart
     * @param dateRangeEnd
     * @param query
     * @return
     */
    @Override
    public List<MetaAdsCampaignInsightDailyPerformanceVO> getMetaAdsCampaignInsightsDailyPerformance(Date dateRangeStart, Date dateRangeEnd, MetaAdsCampaignCommonQuery query) {
        List<MetaAdsCampaignInsightDailyPerformanceVO> result = new ArrayList<>();
        List<String> adsCampaignIdList = new ArrayList<>();

        // 从Mapping表里查询 campaign id列表
        ProductsAdsMappingInfoQuery mappingQuery = new ProductsAdsMappingInfoQuery();
        mappingQuery.setMappingProductUid(query.getMappingProductUid());
        mappingQuery.setAdsPlatform(AdsPlatformEnum.META.getPlatform());
        this.productsAdsMappingInfoService.findListByParam(mappingQuery).forEach(productsAdsMappingInfo -> adsCampaignIdList.add(productsAdsMappingInfo.getAdsCampaignId()));

        MetaAdsCampaignInfoQuery campaignInfoQuery = new MetaAdsCampaignInfoQuery();
        campaignInfoQuery.setUidList(adsCampaignIdList);
        campaignInfoQuery.setCampaignNameFuzzy(query.getCampaignNameFuzzy());
        campaignInfoQuery.setEffectiveStatus(query.getEffectiveStatus());
        campaignInfoQuery.setPersonInCharge(query.getPersonInCharge());
        campaignInfoQuery.setOrderBy("start_time desc");

        List<MetaAdsCampaignInfo> metaAdsCampaignInfoList = findListByParam(campaignInfoQuery);
        List<String> finalCampaignIdList = new ArrayList<>();
        metaAdsCampaignInfoList.forEach(campaignInfo -> finalCampaignIdList.add(campaignInfo.getUid()));
        MetaCampaignInsightQuery insightQuery = new MetaCampaignInsightQuery();
        insightQuery.setCampaignUidList(finalCampaignIdList);
        List<String> reportDateList = DateUtil.getDateList(DateUtil.format(dateRangeStart, DateTimePatternEnum.YYYY_MM_DD.getPattern()), DateUtil.format(dateRangeEnd, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
        reportDateList.forEach(reportDate -> {
            MetaAdsCampaignInsightDailyPerformanceVO dailyPerformanceVO = new MetaAdsCampaignInsightDailyPerformanceVO();
            dailyPerformanceVO.setReportDate(reportDate);
            dailyPerformanceVO.setCostAmount(BigDecimal.valueOf(0));
            dailyPerformanceVO.setImpressions(0L);
            dailyPerformanceVO.setClicks(0L);
            dailyPerformanceVO.setConversions(0L);
            dailyPerformanceVO.setConversionsValue(BigDecimal.valueOf(0));

            insightQuery.setSegmentDate(reportDate);
            this.metaCampaignInsightService.findListByParam(insightQuery).forEach(insight -> {
                dailyPerformanceVO.setCostAmount(dailyPerformanceVO.getCostAmount().add(insight.getCostAmount()));
                dailyPerformanceVO.setImpressions(dailyPerformanceVO.getImpressions() + insight.getImpressions());
                dailyPerformanceVO.setClicks(dailyPerformanceVO.getClicks() + insight.getClicks());
                dailyPerformanceVO.setConversions(dailyPerformanceVO.getConversions() + (insight.getConversions()));
                dailyPerformanceVO.setConversionsValue(dailyPerformanceVO.getConversionsValue().add(insight.getConversionsValue()));
            });
            if (dailyPerformanceVO.getImpressions() > 0) {
                dailyPerformanceVO.setCtr((BigDecimal.valueOf(dailyPerformanceVO.getClicks()).divide(BigDecimal.valueOf(dailyPerformanceVO.getImpressions()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (dailyPerformanceVO.getClicks() > 0 && dailyPerformanceVO.getCostAmount() != null) {
                dailyPerformanceVO.setCpc(dailyPerformanceVO.getCostAmount().divide(BigDecimal.valueOf(dailyPerformanceVO.getClicks()), 2, RoundingMode.HALF_UP));
            }
            if (dailyPerformanceVO.getClicks() > 0 && dailyPerformanceVO.getConversions() != null) {
                dailyPerformanceVO.setCvr((BigDecimal.valueOf(dailyPerformanceVO.getConversions()).divide(BigDecimal.valueOf(dailyPerformanceVO.getClicks()), 4, RoundingMode.HALF_UP).doubleValue()));
            }
            if (dailyPerformanceVO.getConversionsValue() != null && dailyPerformanceVO.getCostAmount() != null && dailyPerformanceVO.getCostAmount().compareTo(BigDecimal.ZERO) != 0) {
                dailyPerformanceVO.setRoas(dailyPerformanceVO.getConversionsValue().divide(dailyPerformanceVO.getCostAmount(), 2, RoundingMode.HALF_UP).doubleValue());
            }
            if (dailyPerformanceVO.getCostAmount() != null && dailyPerformanceVO.getConversions() != null && dailyPerformanceVO.getConversions() != 0) {
                dailyPerformanceVO.setCpa(dailyPerformanceVO.getCostAmount().divide(BigDecimal.valueOf(dailyPerformanceVO.getConversions()), 2, RoundingMode.HALF_UP));
            }
            result.add(dailyPerformanceVO);
        });

        return result;
    }

    /**
     * 分配Meta广告投手
     *
     * @param newPersonInCharge
     * @param param
     */
    @Override
    public void allocatePerson(String newPersonInCharge, MetaAdsCampaignInfoQuery param) {
        MetaAdsCampaignInfo updateInfo = new MetaAdsCampaignInfo();
        if (Constants.CANCEL_MARK.equals(newPersonInCharge)) {
            newPersonInCharge = "";
        }
        updateInfo.setPersonInCharge(newPersonInCharge);
        updateByParam(updateInfo, param);
    }
}
