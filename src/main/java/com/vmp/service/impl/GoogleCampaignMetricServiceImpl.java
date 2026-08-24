package com.vmp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.GoogleCampaignMetricQuery;
import com.vmp.entity.po.GoogleCampaignMetric;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.GoogleCampaignMetricMapper;
import com.vmp.service.GoogleCampaignMetricService;
import com.vmp.utils.StringTools;


/**
 * 谷歌广告系列表现指标 业务接口实现
 */
@Service("googleCampaignMetricService")
public class GoogleCampaignMetricServiceImpl implements GoogleCampaignMetricService {

	@Resource
	private GoogleCampaignMetricMapper<GoogleCampaignMetric, GoogleCampaignMetricQuery> googleCampaignMetricMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<GoogleCampaignMetric> findListByParam(GoogleCampaignMetricQuery param) {
		return this.googleCampaignMetricMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(GoogleCampaignMetricQuery param) {
		return this.googleCampaignMetricMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<GoogleCampaignMetric> findListByPage(GoogleCampaignMetricQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<GoogleCampaignMetric> list = this.findListByParam(param);
		PaginationResultVO<GoogleCampaignMetric> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(GoogleCampaignMetric bean) {
		return this.googleCampaignMetricMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<GoogleCampaignMetric> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.googleCampaignMetricMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<GoogleCampaignMetric> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.googleCampaignMetricMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(GoogleCampaignMetric bean, GoogleCampaignMetricQuery param) {
		StringTools.checkParam(param);
		return this.googleCampaignMetricMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(GoogleCampaignMetricQuery param) {
		StringTools.checkParam(param);
		return this.googleCampaignMetricMapper.deleteByParam(param);
	}

	/**
	 * 根据SegmentDateAndCampaignId获取对象
	 */
	@Override
	public GoogleCampaignMetric getGoogleCampaignMetricBySegmentDateAndCampaignId(Date segmentDate, String campaignId) {
		return this.googleCampaignMetricMapper.selectBySegmentDateAndCampaignId(segmentDate, campaignId);
	}

	/**
	 * 根据SegmentDateAndCampaignId修改
	 */
	@Override
	public Integer updateGoogleCampaignMetricBySegmentDateAndCampaignId(GoogleCampaignMetric bean, Date segmentDate, String campaignId) {
		return this.googleCampaignMetricMapper.updateBySegmentDateAndCampaignId(bean, segmentDate, campaignId);
	}

	/**
	 * 根据SegmentDateAndCampaignId删除
	 */
	@Override
	public Integer deleteGoogleCampaignMetricBySegmentDateAndCampaignId(Date segmentDate, String campaignId) {
		return this.googleCampaignMetricMapper.deleteBySegmentDateAndCampaignId(segmentDate, campaignId);
	}
}