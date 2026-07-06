package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 用户信息 数据库操作接口
 */
public interface UserInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据UserId更新
	 */
	 Integer updateByUserId(@Param("bean") T t,@Param("userId") String userId);


	/**
	 * 根据UserId删除
	 */
	 Integer deleteByUserId(@Param("userId") String userId);


	/**
	 * 根据UserId获取对象
	 */
	 T selectByUserId(@Param("userId") String userId);


	/**
	 * 根据DdOpenUnionid更新
	 */
	 Integer updateByDdOpenUnionid(@Param("bean") T t,@Param("ddOpenUnionid") String ddOpenUnionid);


	/**
	 * 根据DdOpenUnionid删除
	 */
	 Integer deleteByDdOpenUnionid(@Param("ddOpenUnionid") String ddOpenUnionid);


	/**
	 * 根据DdOpenUnionid获取对象
	 */
	 T selectByDdOpenUnionid(@Param("ddOpenUnionid") String ddOpenUnionid);


	/**
	 * 根据Account更新
	 */
	 Integer updateByAccount(@Param("bean") T t,@Param("account") String account);


	/**
	 * 根据Account删除
	 */
	 Integer deleteByAccount(@Param("account") String account);


	/**
	 * 根据Account获取对象
	 */
	 T selectByAccount(@Param("account") String account);


}
