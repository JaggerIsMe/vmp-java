package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * Agent Skill 管理表 数据库操作接口
 */
public interface AgentSkillInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据SkillId更新
	 */
	 Integer updateBySkillId(@Param("bean") T t,@Param("skillId") String skillId);


	/**
	 * 根据SkillId删除
	 */
	 Integer deleteBySkillId(@Param("skillId") String skillId);


	/**
	 * 根据SkillId获取对象
	 */
	 T selectBySkillId(@Param("skillId") String skillId);


	/**
	 * 根据NameAndPlatform更新
	 */
	 Integer updateByNameAndPlatform(@Param("bean") T t,@Param("name") String name,@Param("platform") String platform);


	/**
	 * 根据NameAndPlatform删除
	 */
	 Integer deleteByNameAndPlatform(@Param("name") String name,@Param("platform") String platform);


	/**
	 * 根据NameAndPlatform获取对象
	 */
	 T selectByNameAndPlatform(@Param("name") String name,@Param("platform") String platform);


}
