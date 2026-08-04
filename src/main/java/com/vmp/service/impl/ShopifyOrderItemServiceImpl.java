package com.vmp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.ShopifyOrderItemQuery;
import com.vmp.entity.po.ShopifyOrderItem;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.ShopifyOrderItemMapper;
import com.vmp.service.ShopifyOrderItemService;
import com.vmp.utils.StringTools;


/**
 * 官网订单-订单项 业务接口实现
 */
@Service("shopifyOrderItemService")
public class ShopifyOrderItemServiceImpl implements ShopifyOrderItemService {

	@Resource
	private ShopifyOrderItemMapper<ShopifyOrderItem, ShopifyOrderItemQuery> shopifyOrderItemMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ShopifyOrderItem> findListByParam(ShopifyOrderItemQuery param) {
		return this.shopifyOrderItemMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(ShopifyOrderItemQuery param) {
		return this.shopifyOrderItemMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<ShopifyOrderItem> findListByPage(ShopifyOrderItemQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<ShopifyOrderItem> list = this.findListByParam(param);
		PaginationResultVO<ShopifyOrderItem> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(ShopifyOrderItem bean) {
		return this.shopifyOrderItemMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ShopifyOrderItem> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.shopifyOrderItemMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ShopifyOrderItem> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.shopifyOrderItemMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(ShopifyOrderItem bean, ShopifyOrderItemQuery param) {
		StringTools.checkParam(param);
		return this.shopifyOrderItemMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(ShopifyOrderItemQuery param) {
		StringTools.checkParam(param);
		return this.shopifyOrderItemMapper.deleteByParam(param);
	}

	/**
	 * 根据OrderIdAndItemIdAndStoreId获取对象
	 */
	@Override
	public ShopifyOrderItem getShopifyOrderItemByOrderIdAndItemIdAndStoreId(String orderId, String itemId, String storeId) {
		return this.shopifyOrderItemMapper.selectByOrderIdAndItemIdAndStoreId(orderId, itemId, storeId);
	}

	/**
	 * 根据OrderIdAndItemIdAndStoreId修改
	 */
	@Override
	public Integer updateShopifyOrderItemByOrderIdAndItemIdAndStoreId(ShopifyOrderItem bean, String orderId, String itemId, String storeId) {
		return this.shopifyOrderItemMapper.updateByOrderIdAndItemIdAndStoreId(bean, orderId, itemId, storeId);
	}

	/**
	 * 根据OrderIdAndItemIdAndStoreId删除
	 */
	@Override
	public Integer deleteShopifyOrderItemByOrderIdAndItemIdAndStoreId(String orderId, String itemId, String storeId) {
		return this.shopifyOrderItemMapper.deleteByOrderIdAndItemIdAndStoreId(orderId, itemId, storeId);
	}
}