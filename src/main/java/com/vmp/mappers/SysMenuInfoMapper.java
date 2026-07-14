package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 系统菜单目录 数据库操作接口
 */
public interface SysMenuInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据MenuId更新
	 */
	 Integer updateByMenuId(@Param("bean") T t,@Param("menuId") String menuId);


	/**
	 * 根据MenuId删除
	 */
	 Integer deleteByMenuId(@Param("menuId") String menuId);


	/**
	 * 根据MenuId获取对象
	 */
	 T selectByMenuId(@Param("menuId") String menuId);


	/**
	 * 根据Title更新
	 */
	 Integer updateByTitle(@Param("bean") T t,@Param("title") String title);


	/**
	 * 根据Title删除
	 */
	 Integer deleteByTitle(@Param("title") String title);


	/**
	 * 根据Title获取对象
	 */
	 T selectByTitle(@Param("title") String title);


}
