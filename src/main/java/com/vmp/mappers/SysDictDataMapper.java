package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 系统字典管理 数据库操作接口
 */
public interface SysDictDataMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据DictId更新
	 */
	 Integer updateByDictId(@Param("bean") T t,@Param("dictId") String dictId);


	/**
	 * 根据DictId删除
	 */
	 Integer deleteByDictId(@Param("dictId") String dictId);


	/**
	 * 根据DictId获取对象
	 */
	 T selectByDictId(@Param("dictId") String dictId);


	/**
	 * 根据DictCode更新
	 */
	 Integer updateByDictCode(@Param("bean") T t,@Param("dictCode") String dictCode);


	/**
	 * 根据DictCode删除
	 */
	 Integer deleteByDictCode(@Param("dictCode") String dictCode);


	/**
	 * 根据DictCode获取对象
	 */
	 T selectByDictCode(@Param("dictCode") String dictCode);


}
