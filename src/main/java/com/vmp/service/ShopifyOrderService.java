package com.vmp.service;

import java.util.Date;
import java.util.List;

import com.vmp.entity.query.ShopifyOrderQuery;
import com.vmp.entity.po.ShopifyOrder;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.dashboardVo.ShopifySalesDailyPerformanceVO;


/**
 * 官网订单表 业务接口
 */
public interface ShopifyOrderService {

	/**
	 * 根据条件查询列表
	 */
	List<ShopifyOrder> findListByParam(ShopifyOrderQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(ShopifyOrderQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ShopifyOrder> findListByPage(ShopifyOrderQuery param);

	/**
	 * 新增
	 */
	Integer add(ShopifyOrder bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ShopifyOrder> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ShopifyOrder> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(ShopifyOrder bean,ShopifyOrderQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(ShopifyOrderQuery param);

	/**
	 * 根据StoreIdAndOrderId查询对象
	 */
	ShopifyOrder getShopifyOrderByStoreIdAndOrderId(String storeId,String orderId);


	/**
	 * 根据StoreIdAndOrderId修改
	 */
	Integer updateShopifyOrderByStoreIdAndOrderId(ShopifyOrder bean,String storeId,String orderId);


	/**
	 * 根据StoreIdAndOrderId删除
	 */
	Integer deleteShopifyOrderByStoreIdAndOrderId(String storeId,String orderId);

	/**
	 * 同步官网订单数据
	 * @param host
	 * @param first
	 * @param after
	 * @param startDate
	 * @param endDate
	 */
	void syncShopifyOrders(String host, Integer first, String after, Date startDate, Date endDate);

	/**
	 * 多条件查询获取订单列表
	 * @param query
	 * @return
	 */
	PaginationResultVO<ShopifyOrder> loadShopifyOrderListByMultiQuery(ShopifyOrderQuery query);

	/**
	 * 获取日销售表现
	 * @param query
	 * @return
	 */
	List<ShopifySalesDailyPerformanceVO> getShopifySalesDailyPerformance(ShopifyOrderQuery query);
}