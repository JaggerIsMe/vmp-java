package com.vmp.service;

import java.util.List;

import com.vmp.entity.dto.FileUploadResultDto;
import com.vmp.entity.query.AgentSkillFileQuery;
import com.vmp.entity.po.AgentSkillFile;
import com.vmp.entity.vo.PaginationResultVO;
import org.springframework.web.multipart.MultipartFile;


/**
 * Agent Skill 文件 业务接口
 */
public interface AgentSkillFileService {

	/**
	 * 根据条件查询列表
	 */
	List<AgentSkillFile> findListByParam(AgentSkillFileQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(AgentSkillFileQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<AgentSkillFile> findListByPage(AgentSkillFileQuery param);

	/**
	 * 新增
	 */
	Integer add(AgentSkillFile bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<AgentSkillFile> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<AgentSkillFile> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(AgentSkillFile bean,AgentSkillFileQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(AgentSkillFileQuery param);

	/**
	 * 根据VersionId查询对象
	 */
	AgentSkillFile getAgentSkillFileByVersionId(String versionId);


	/**
	 * 根据VersionId修改
	 */
	Integer updateAgentSkillFileByVersionId(AgentSkillFile bean,String versionId);


	/**
	 * 根据VersionId删除
	 */
	Integer deleteAgentSkillFileByVersionId(String versionId);


	/**
	 * 根据FileName查询对象
	 */
	AgentSkillFile getAgentSkillFileByFileName(String fileName);


	/**
	 * 根据FileName修改
	 */
	Integer updateAgentSkillFileByFileName(AgentSkillFile bean,String fileName);


	/**
	 * 根据FileName删除
	 */
	Integer deleteAgentSkillFileByFileName(String fileName);


	/**
	 * 根据SkillIdAndVersionName查询对象
	 */
	AgentSkillFile getAgentSkillFileBySkillIdAndVersionName(String skillId,String versionName);


	/**
	 * 根据SkillIdAndVersionName修改
	 */
	Integer updateAgentSkillFileBySkillIdAndVersionName(AgentSkillFile bean,String skillId,String versionName);


	/**
	 * 根据SkillIdAndVersionName删除
	 */
	Integer deleteAgentSkillFileBySkillIdAndVersionName(String skillId,String versionName);


	/**
	 * 根据SkillIdAndFileMd5查询对象
	 */
	AgentSkillFile getAgentSkillFileBySkillIdAndFileMd5(String skillId,String fileMd5);


	/**
	 * 根据SkillIdAndFileMd5修改
	 */
	Integer updateAgentSkillFileBySkillIdAndFileMd5(AgentSkillFile bean,String skillId,String fileMd5);


	/**
	 * 根据SkillIdAndFileMd5删除
	 */
	Integer deleteAgentSkillFileBySkillIdAndFileMd5(String skillId,String fileMd5);

	/**
	 * 上传Skill文件包-分片上传-zip格式
	 * @param userId
	 * @param skillId
	 * @param fileId(versionId)
	 * @param file
	 * @param versionName
	 * @param remark
	 * @param fileMd5
	 * @param chunkIndex
	 * @param chunks
	 * @return
	 */
	FileUploadResultDto uploadSkillFile(String userId, String skillId, String fileId, MultipartFile file, String versionName, String remark, String fileMd5, Integer chunkIndex, Integer chunks);

	/**
	 * 切换Skill版本
	 * @param skillId
	 * @param versionId
	 */
	void switchSkillVersion(String skillId, String versionId);

	/**
	 * 获取Skill版本列表
	 * @param param
	 * @return
	 */
	PaginationResultVO loadAgentSkillVersionVOListByPage(AgentSkillFileQuery param);
}