package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * Agent Skill 文件 数据库操作接口
 */
public interface AgentSkillFileMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据VersionId更新
	 */
	 Integer updateByVersionId(@Param("bean") T t,@Param("versionId") String versionId);


	/**
	 * 根据VersionId删除
	 */
	 Integer deleteByVersionId(@Param("versionId") String versionId);


	/**
	 * 根据VersionId获取对象
	 */
	 T selectByVersionId(@Param("versionId") String versionId);


	/**
	 * 根据FileName更新
	 */
	 Integer updateByFileName(@Param("bean") T t,@Param("fileName") String fileName);


	/**
	 * 根据FileName删除
	 */
	 Integer deleteByFileName(@Param("fileName") String fileName);


	/**
	 * 根据FileName获取对象
	 */
	 T selectByFileName(@Param("fileName") String fileName);


	/**
	 * 根据SkillIdAndVersionName更新
	 */
	 Integer updateBySkillIdAndVersionName(@Param("bean") T t,@Param("skillId") String skillId,@Param("versionName") String versionName);


	/**
	 * 根据SkillIdAndVersionName删除
	 */
	 Integer deleteBySkillIdAndVersionName(@Param("skillId") String skillId,@Param("versionName") String versionName);


	/**
	 * 根据SkillIdAndVersionName获取对象
	 */
	 T selectBySkillIdAndVersionName(@Param("skillId") String skillId,@Param("versionName") String versionName);


	/**
	 * 根据SkillIdAndFileMd5更新
	 */
	 Integer updateBySkillIdAndFileMd5(@Param("bean") T t,@Param("skillId") String skillId,@Param("fileMd5") String fileMd5);


	/**
	 * 根据SkillIdAndFileMd5删除
	 */
	 Integer deleteBySkillIdAndFileMd5(@Param("skillId") String skillId,@Param("fileMd5") String fileMd5);


	/**
	 * 根据SkillIdAndFileMd5获取对象
	 */
	 T selectBySkillIdAndFileMd5(@Param("skillId") String skillId,@Param("fileMd5") String fileMd5);


    void updateFileStatusWithOldTransferStatus(@Param("versionId") String versionId, @Param("oldStatus") Integer oldStatus, @Param("bean") T updateInfo);
}
