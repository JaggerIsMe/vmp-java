package com.vmp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.MetaCampaignInsightQuery;
import com.vmp.entity.po.MetaCampaignInsight;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.MetaCampaignInsightMapper;
import com.vmp.service.MetaCampaignInsightService;
import com.vmp.utils.StringTools;


/**
 * meta广告系列表现指标 业务接口实现
 */
@Service("metaCampaignInsightService")
public class MetaCampaignInsightServiceImpl implements MetaCampaignInsightService {

	@Resource
	private MetaCampaignInsightMapper<MetaCampaignInsight, MetaCampaignInsightQuery> metaCampaignInsightMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<MetaCampaignInsight> findListByParam(MetaCampaignInsightQuery param) {
		return this.metaCampaignInsightMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(MetaCampaignInsightQuery param) {
		return this.metaCampaignInsightMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<MetaCampaignInsight> findListByPage(MetaCampaignInsightQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<MetaCampaignInsight> list = this.findListByParam(param);
		PaginationResultVO<MetaCampaignInsight> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(MetaCampaignInsight bean) {
		return this.metaCampaignInsightMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<MetaCampaignInsight> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.metaCampaignInsightMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<MetaCampaignInsight> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.metaCampaignInsightMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(MetaCampaignInsight bean, MetaCampaignInsightQuery param) {
		StringTools.checkParam(param);
		return this.metaCampaignInsightMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(MetaCampaignInsightQuery param) {
		StringTools.checkParam(param);
		return this.metaCampaignInsightMapper.deleteByParam(param);
	}

	/**
	 * 根据SegmentDateAndCampaignUid获取对象
	 */
	@Override
	public MetaCampaignInsight getMetaCampaignInsightBySegmentDateAndCampaignUid(Date segmentDate, String campaignUid) {
		return this.metaCampaignInsightMapper.selectBySegmentDateAndCampaignUid(segmentDate, campaignUid);
	}

	/**
	 * 根据SegmentDateAndCampaignUid修改
	 */
	@Override
	public Integer updateMetaCampaignInsightBySegmentDateAndCampaignUid(MetaCampaignInsight bean, Date segmentDate, String campaignUid) {
		return this.metaCampaignInsightMapper.updateBySegmentDateAndCampaignUid(bean, segmentDate, campaignUid);
	}

	/**
	 * 根据SegmentDateAndCampaignUid删除
	 */
	@Override
	public Integer deleteMetaCampaignInsightBySegmentDateAndCampaignUid(Date segmentDate, String campaignUid) {
		return this.metaCampaignInsightMapper.deleteBySegmentDateAndCampaignUid(segmentDate, campaignUid);
	}
}