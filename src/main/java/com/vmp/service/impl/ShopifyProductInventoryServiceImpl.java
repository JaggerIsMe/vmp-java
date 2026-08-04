package com.vmp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.ShopifyProductInventoryQuery;
import com.vmp.entity.po.ShopifyProductInventory;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.ShopifyProductInventoryMapper;
import com.vmp.service.ShopifyProductInventoryService;
import com.vmp.utils.StringTools;


/**
 * 官网产品库存 业务接口实现
 */
@Service("shopifyProductInventoryService")
public class ShopifyProductInventoryServiceImpl implements ShopifyProductInventoryService {

	@Resource
	private ShopifyProductInventoryMapper<ShopifyProductInventory, ShopifyProductInventoryQuery> shopifyProductInventoryMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ShopifyProductInventory> findListByParam(ShopifyProductInventoryQuery param) {
		return this.shopifyProductInventoryMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(ShopifyProductInventoryQuery param) {
		return this.shopifyProductInventoryMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<ShopifyProductInventory> findListByPage(ShopifyProductInventoryQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<ShopifyProductInventory> list = this.findListByParam(param);
		PaginationResultVO<ShopifyProductInventory> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(ShopifyProductInventory bean) {
		return this.shopifyProductInventoryMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ShopifyProductInventory> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.shopifyProductInventoryMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ShopifyProductInventory> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.shopifyProductInventoryMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(ShopifyProductInventory bean, ShopifyProductInventoryQuery param) {
		StringTools.checkParam(param);
		return this.shopifyProductInventoryMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(ShopifyProductInventoryQuery param) {
		StringTools.checkParam(param);
		return this.shopifyProductInventoryMapper.deleteByParam(param);
	}

	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId获取对象
	 */
	@Override
	public ShopifyProductInventory getShopifyProductInventoryByVariantIdAndProductIdAndWarehouseIdAndStoreId(String variantId, String productId, String warehouseId, String storeId) {
		return this.shopifyProductInventoryMapper.selectByVariantIdAndProductIdAndWarehouseIdAndStoreId(variantId, productId, warehouseId, storeId);
	}

	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId修改
	 */
	@Override
	public Integer updateShopifyProductInventoryByVariantIdAndProductIdAndWarehouseIdAndStoreId(ShopifyProductInventory bean, String variantId, String productId, String warehouseId, String storeId) {
		return this.shopifyProductInventoryMapper.updateByVariantIdAndProductIdAndWarehouseIdAndStoreId(bean, variantId, productId, warehouseId, storeId);
	}

	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId删除
	 */
	@Override
	public Integer deleteShopifyProductInventoryByVariantIdAndProductIdAndWarehouseIdAndStoreId(String variantId, String productId, String warehouseId, String storeId) {
		return this.shopifyProductInventoryMapper.deleteByVariantIdAndProductIdAndWarehouseIdAndStoreId(variantId, productId, warehouseId, storeId);
	}
}