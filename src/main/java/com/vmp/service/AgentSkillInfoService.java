package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.AgentSkillInfoQuery;
import com.vmp.entity.po.AgentSkillInfo;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * Agent Skill 管理表 业务接口
 */
public interface AgentSkillInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<AgentSkillInfo> findListByParam(AgentSkillInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(AgentSkillInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<AgentSkillInfo> findListByPage(AgentSkillInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(AgentSkillInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<AgentSkillInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<AgentSkillInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(AgentSkillInfo bean,AgentSkillInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(AgentSkillInfoQuery param);

	/**
	 * 根据SkillId查询对象
	 */
	AgentSkillInfo getAgentSkillInfoBySkillId(String skillId);


	/**
	 * 根据SkillId修改
	 */
	Integer updateAgentSkillInfoBySkillId(AgentSkillInfo bean,String skillId);


	/**
	 * 根据SkillId删除
	 */
	Integer deleteAgentSkillInfoBySkillId(String skillId);


	/**
	 * 根据NameAndPlatform查询对象
	 */
	AgentSkillInfo getAgentSkillInfoByNameAndPlatform(String name,String platform);


	/**
	 * 根据NameAndPlatform修改
	 */
	Integer updateAgentSkillInfoByNameAndPlatform(AgentSkillInfo bean,String name,String platform);


	/**
	 * 根据NameAndPlatform删除
	 */
	Integer deleteAgentSkillInfoByNameAndPlatform(String name,String platform);

	/**
	 * 获取Skill列表 根据条件分页查询
	 * @param param
	 * @return
	 */
	PaginationResultVO loadAgentSkillInfoVOListByPage(AgentSkillInfoQuery param);

	/**
	 * 发布Skill
	 * @param userId
	 * @param bean
	 */
    void releaseSkill(String userId, AgentSkillInfo bean);

	/**
	 * 修改Skill基础信息
	 * @param bean
	 * @param skillId
	 */
	void updateSkillInfo(AgentSkillInfo bean, String skillId);
}