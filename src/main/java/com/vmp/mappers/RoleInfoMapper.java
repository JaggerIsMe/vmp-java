package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 系统角色信息 数据库操作接口
 */
public interface RoleInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据RoleId更新
	 */
	 Integer updateByRoleId(@Param("bean") T t,@Param("roleId") String roleId);


	/**
	 * 根据RoleId删除
	 */
	 Integer deleteByRoleId(@Param("roleId") String roleId);


	/**
	 * 根据RoleId获取对象
	 */
	 T selectByRoleId(@Param("roleId") String roleId);


	/**
	 * 根据Name更新
	 */
	 Integer updateByName(@Param("bean") T t,@Param("name") String name);


	/**
	 * 根据Name删除
	 */
	 Integer deleteByName(@Param("name") String name);


	/**
	 * 根据Name获取对象
	 */
	 T selectByName(@Param("name") String name);


}
