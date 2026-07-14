package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 系统用户-角色分配信息 数据库操作接口
 */
public interface SysUsersRolesMapper<T,P> extends BaseMapper<T,P> {

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


}
