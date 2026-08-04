package com.vmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.vmp.entity.apidto.ShopifyShopInfo;
import com.vmp.entity.apidto.ShopifyqlQueryResponse;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.service.ShopifyApiService;
import com.vmp.utils.CopyTools;
import com.vmp.utils.DateUtil;
import com.vmp.utils.JsonUtils;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.ShopifySessionReportQuery;
import com.vmp.entity.po.ShopifySessionReport;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.ShopifySessionReportMapper;
import com.vmp.service.ShopifySessionReportService;
import com.vmp.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 官网流量报告 业务接口实现
 */
@Service("shopifySessionReportService")
public class ShopifySessionReportServiceImpl implements ShopifySessionReportService {

    @Resource
    private ShopifySessionReportMapper<ShopifySessionReport, ShopifySessionReportQuery> shopifySessionReportMapper;

    @Resource
    private ShopifyApiService shopifyApiService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<ShopifySessionReport> findListByParam(ShopifySessionReportQuery param) {
        return this.shopifySessionReportMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(ShopifySessionReportQuery param) {
        return this.shopifySessionReportMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<ShopifySessionReport> findListByPage(ShopifySessionReportQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<ShopifySessionReport> list = this.findListByParam(param);
        PaginationResultVO<ShopifySessionReport> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(ShopifySessionReport bean) {
        return this.shopifySessionReportMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<ShopifySessionReport> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.shopifySessionReportMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<ShopifySessionReport> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.shopifySessionReportMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(ShopifySessionReport bean, ShopifySessionReportQuery param) {
        StringTools.checkParam(param);
        return this.shopifySessionReportMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(ShopifySessionReportQuery param) {
        StringTools.checkParam(param);
        return this.shopifySessionReportMapper.deleteByParam(param);
    }

    /**
     * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath获取对象
     */
    @Override
    public ShopifySessionReport getShopifySessionReportByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(Date reportDay, String storeId, String landingPageType, String landingPagePath) {
        return this.shopifySessionReportMapper.selectByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(reportDay, storeId, landingPageType, landingPagePath);
    }

    /**
     * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath修改
     */
    @Override
    public Integer updateShopifySessionReportByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(ShopifySessionReport bean, Date reportDay, String storeId, String landingPageType, String landingPagePath) {
        return this.shopifySessionReportMapper.updateByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(bean, reportDay, storeId, landingPageType, landingPagePath);
    }

    /**
     * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath删除
     */
    @Override
    public Integer deleteShopifySessionReportByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(Date reportDay, String storeId, String landingPageType, String landingPagePath) {
        return this.shopifySessionReportMapper.deleteByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(reportDay, storeId, landingPageType, landingPagePath);
    }

    /**
     * 分析官网流量Sessions
     *
     * @param host
     * @param type
     * @param day
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void getShopifySessionsReport(String host, String type, Date day) {
        ShopifyShopInfo shopInfo = shopifyApiService.getShopifyShopInfo(host);

        ShopifyqlQueryResponse reportResponse = shopifyApiService.getShopifySessionsReport(host, type, day);

        List<JSONObject> jsonRows = reportResponse.getTableData().getRows();
        List<ShopifySessionReport> rows = new ArrayList<>();
        jsonRows.forEach(jsonRow -> {
            ShopifySessionReport row = new ShopifySessionReport();
            row.setReportDay(day);
            row.setStoreId(shopInfo.getId());
            row.setLandingPageType(type);
            row.setLandingPagePath(jsonRow.getString("landing_page_path"));
            row.setOnlineStoreVisitors(jsonRow.getLong("online_store_visitors"));
            row.setSessions(jsonRow.getLong("sessions"));
            row.setSessionsWithCartAdditions(jsonRow.getLong("sessions_with_cart_additions"));
            row.setSessionsThatReachedCheckout(jsonRow.getLong("sessions_that_reached_checkout"));
            rows.add(row);
        });

        addOrUpdateBatch(rows);
    }
}