package com.vmp.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.FileUploadResultDto;
import com.vmp.entity.enums.*;
import com.vmp.entity.po.AgentSkillInfo;
import com.vmp.entity.po.UserInfo;
import com.vmp.entity.query.UserInfoQuery;
import com.vmp.entity.vo.AgentSkillFileVO;
import com.vmp.entity.vo.AgentSkillInfoVO;
import com.vmp.entity.vo.UserInfoVO;
import com.vmp.exception.BusinessException;
import com.vmp.redis.RedisComponent;
import com.vmp.service.AgentSkillInfoService;
import com.vmp.service.UserInfoService;
import com.vmp.utils.CopyTools;
import com.vmp.utils.VerifyUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vmp.entity.query.AgentSkillFileQuery;
import com.vmp.entity.po.AgentSkillFile;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.AgentSkillFileMapper;
import com.vmp.service.AgentSkillFileService;
import com.vmp.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;


/**
 * Agent Skill 文件 业务接口实现
 */
@Service("agentSkillFileService")
public class AgentSkillFileServiceImpl implements AgentSkillFileService {

    private static final Logger logger = LoggerFactory.getLogger(AgentSkillFileServiceImpl.class);

    @Resource
    private AgentSkillFileMapper<AgentSkillFile, AgentSkillFileQuery> agentSkillFileMapper;

    @Resource
    private AppConfig appConfig;

    @Resource
    private RedisComponent redisComponent;

    @Resource
    private AgentSkillInfoService agentSkillInfoService;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<AgentSkillFile> findListByParam(AgentSkillFileQuery param) {
        return this.agentSkillFileMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(AgentSkillFileQuery param) {
        return this.agentSkillFileMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<AgentSkillFile> findListByPage(AgentSkillFileQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<AgentSkillFile> list = this.findListByParam(param);
        PaginationResultVO<AgentSkillFile> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(AgentSkillFile bean) {
        return this.agentSkillFileMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<AgentSkillFile> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.agentSkillFileMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<AgentSkillFile> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.agentSkillFileMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(AgentSkillFile bean, AgentSkillFileQuery param) {
        StringTools.checkParam(param);
        return this.agentSkillFileMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(AgentSkillFileQuery param) {
        StringTools.checkParam(param);
        return this.agentSkillFileMapper.deleteByParam(param);
    }

    /**
     * 根据VersionId获取对象
     */
    @Override
    public AgentSkillFile getAgentSkillFileByVersionId(String versionId) {
        return this.agentSkillFileMapper.selectByVersionId(versionId);
    }

    /**
     * 根据VersionId修改
     */
    @Override
    public Integer updateAgentSkillFileByVersionId(AgentSkillFile bean, String versionId) {
        return this.agentSkillFileMapper.updateByVersionId(bean, versionId);
    }

    /**
     * 根据VersionId删除
     */
    @Override
    public Integer deleteAgentSkillFileByVersionId(String versionId) {
        return this.agentSkillFileMapper.deleteByVersionId(versionId);
    }

    /**
     * 根据FileName获取对象
     */
    @Override
    public AgentSkillFile getAgentSkillFileByFileName(String fileName) {
        return this.agentSkillFileMapper.selectByFileName(fileName);
    }

    /**
     * 根据FileName修改
     */
    @Override
    public Integer updateAgentSkillFileByFileName(AgentSkillFile bean, String fileName) {
        return this.agentSkillFileMapper.updateByFileName(bean, fileName);
    }

    /**
     * 根据FileName删除
     */
    @Override
    public Integer deleteAgentSkillFileByFileName(String fileName) {
        return this.agentSkillFileMapper.deleteByFileName(fileName);
    }

    /**
     * 根据SkillIdAndVersionName获取对象
     */
    @Override
    public AgentSkillFile getAgentSkillFileBySkillIdAndVersionName(String skillId, String versionName) {
        return this.agentSkillFileMapper.selectBySkillIdAndVersionName(skillId, versionName);
    }

    /**
     * 根据SkillIdAndVersionName修改
     */
    @Override
    public Integer updateAgentSkillFileBySkillIdAndVersionName(AgentSkillFile bean, String skillId, String versionName) {
        return this.agentSkillFileMapper.updateBySkillIdAndVersionName(bean, skillId, versionName);
    }

    /**
     * 根据SkillIdAndVersionName删除
     */
    @Override
    public Integer deleteAgentSkillFileBySkillIdAndVersionName(String skillId, String versionName) {
        return this.agentSkillFileMapper.deleteBySkillIdAndVersionName(skillId, versionName);
    }

    /**
     * 根据SkillIdAndFileMd5获取对象
     */
    @Override
    public AgentSkillFile getAgentSkillFileBySkillIdAndFileMd5(String skillId, String fileMd5) {
        return this.agentSkillFileMapper.selectBySkillIdAndFileMd5(skillId, fileMd5);
    }

    /**
     * 根据SkillIdAndFileMd5修改
     */
    @Override
    public Integer updateAgentSkillFileBySkillIdAndFileMd5(AgentSkillFile bean, String skillId, String fileMd5) {
        return this.agentSkillFileMapper.updateBySkillIdAndFileMd5(bean, skillId, fileMd5);
    }

    /**
     * 根据SkillIdAndFileMd5删除
     */
    @Override
    public Integer deleteAgentSkillFileBySkillIdAndFileMd5(String skillId, String fileMd5) {
        return this.agentSkillFileMapper.deleteBySkillIdAndFileMd5(skillId, fileMd5);
    }

    /**
     * 上传Skill文件包-分片上传-zip格式
     *
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileUploadResultDto uploadSkillFile(String userId, String skillId, String fileId, MultipartFile file, String versionName, String remark, String fileMd5, Integer chunkIndex, Integer chunks) {

        File tempSkillFileFolder = null;
        Boolean uploadSuccess = true;
        try {
            FileUploadResultDto fileResultDto = new FileUploadResultDto();
            if (StringTools.isEmpty(fileId)) {
                fileId = StringTools.getRandomString(Constants.LENGTH_10);
            }
            fileResultDto.setFileId(fileId);

            // 版本号校验
            if (chunkIndex == 0) {
                AgentSkillFileQuery versionNameQuery = new AgentSkillFileQuery();
                versionNameQuery.setSkillId(skillId);
                versionNameQuery.setOrderBy("create_time desc");
                List<AgentSkillFile> versionList = findListByParam(versionNameQuery);
                String latestVersionName = null;
                if (null != versionList && !versionList.isEmpty()) {
                    latestVersionName = versionList.get(0).getVersionName();
                }
                if (!StringTools.isValidVersionName(versionName, latestVersionName)) {
                    throw new BusinessException("版本号不合法");
                }
            }
            // 在第一个分片检查Skill版本文件内容冲突
            if (chunkIndex == 0) {
                AgentSkillFile conflictSkillFile = this.agentSkillFileMapper.selectBySkillIdAndFileMd5(skillId, fileMd5);
                if (null != conflictSkillFile) {
                    throw new BusinessException("当前上传版本文件内容与版本号" + conflictSkillFile.getVersionName() + "重复");
                }
            }
            // 在第一个分片校验Skill文件格式
            if (chunkIndex == 0) {
                validateZipFileType(file);
            }

            //暂存在skills临时目录
            String tempSkillFolderName = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.SKILL_FILE_FOLDER_FILE + Constants.FILE_FOLDER_TEMP;
            String currentSkillFolderName = skillId + fileId;
            //创建skills临时目录
            tempSkillFileFolder = new File(tempSkillFolderName + currentSkillFolderName);
            if (!tempSkillFileFolder.exists()) {
                tempSkillFileFolder.mkdirs();
            }

            File newFile = new File(tempSkillFileFolder.getPath() + "/" + chunkIndex);
            file.transferTo(newFile);

            //Redis保存临时大小
            redisComponent.saveFileTempSize(skillId, fileId, file.getSize());
            //不是最后一个分片，继续上传，直接返回 文件上传中
            if (chunkIndex < chunks - 1) {
                fileResultDto.setStatus(FileUploadStatusEnums.UPLOADING.getCode());
                return fileResultDto;
            }
            //最后一个分片上传完成，记录数据库，异步合并分片
            Date curDate = new Date();
            AgentSkillFile skillFile = new AgentSkillFile();
            skillFile.setVersionId(fileId);
            skillFile.setSkillId(skillId);
            skillFile.setVersionName(versionName);
            skillFile.setRemark(remark);
            skillFile.setFileMd5(fileMd5);
            skillFile.setFileName(skillId + "_" + fileId + Constants.SKILL_FILE_SUFFIX);
            skillFile.setVersionStatus(AgentSkillVersionStatusEnum.CREATING.getStatus());
            skillFile.setFileStatus(FileTransferStatusEnums.TRANSFER.getStatus());
            skillFile.setFileSize(redisComponent.getFileTempSize(skillId, fileId));
            skillFile.setCreateBy(userId);
            skillFile.setCreateTime(curDate);
            skillFile.setUpdateTime(curDate);
            this.agentSkillFileMapper.insert(skillFile);

            fileResultDto.setStatus(FileUploadStatusEnums.UPLOAD_FINISH.getCode());
            // 事务提交后调用异步方法 转码文件
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    transferSkillFile(skillFile.getSkillId(), skillFile.getVersionId());
                }
            });

            return fileResultDto;
        } catch (BusinessException e) {
            uploadSuccess = false;
            logger.error("文件上传失败", e);
            throw e;
        } catch (Exception e) {
            uploadSuccess = false;
            logger.error("文件上传失败", e);
            throw new BusinessException("文件上传失败");
        } finally {
            //如果上传失败，清除临时目录
            if (tempSkillFileFolder != null && !uploadSuccess) {
                try {
                    FileUtils.deleteDirectory(tempSkillFileFolder);
                } catch (IOException e) {
                    logger.error("删除临时目录失败");
                }
            }
        }
    }

    /**
     * 校验是否为ZIP文件（读取文件头）
     */
    private void validateZipFileType(MultipartFile file) {
        // 1. 检查文件扩展名（快速过滤）
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && !originalFilename.toLowerCase().endsWith(".zip")) {
            throw new BusinessException("请上传有效的ZIP格式文件");
        }
        // 2. 检查文件头魔数（核心校验）
        try (InputStream is = file.getInputStream()) {
            byte[] header = new byte[4];
            int read = is.read(header);
            if (read < 4) {
                throw new BusinessException("请上传有效的ZIP格式文件");
            }
            // 标准ZIP文件头
            if (!(header[0] == 0x50 && header[1] == 0x4B && header[2] == 0x03 && header[3] == 0x04)) {
                throw new BusinessException("请上传有效的ZIP格式文件");
            }
        } catch (IOException e) {
            logger.error("读取文件头失败", e);
            throw new BusinessException("文件校验失败");
        }
    }

    @Async
    public void transferSkillFile(String skillId, String versionId) {
        Boolean transferSuccess = true;
        String targetSkillFilePath = null;

        AgentSkillFile agentSkillFile = this.agentSkillFileMapper.selectByVersionId(versionId);
        try {
            if (null == agentSkillFile || !FileTransferStatusEnums.TRANSFER.getStatus().equals(agentSkillFile.getFileStatus())) {
                return;
            }
            // 临时目录
            String tempSkillFolderName = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.SKILL_FILE_FOLDER_FILE + Constants.FILE_FOLDER_TEMP;
            String currentSkillFolderName = skillId + versionId;
            File tempSkillFileFolder = new File(tempSkillFolderName + currentSkillFolderName);
            if (!tempSkillFileFolder.exists()) {
                tempSkillFileFolder.mkdirs();
            }
            // 目标目录
            String targetSkillFolderName = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.SKILL_FILE_FOLDER_FILE;
            File targetSkillFolder = new File(targetSkillFolderName);
            if (!targetSkillFolder.exists()) {
                targetSkillFolder.mkdirs();
            }
            // 真实文件路径
            targetSkillFilePath = targetSkillFolder.getPath() + "/" + agentSkillFile.getFileName();
            unionSkillFile(tempSkillFileFolder.getPath(), targetSkillFilePath, true);
        } catch (Exception e) {
            logger.error("Skill文件转码失败，Skill文件skillId:{},versionId:{}", skillId, versionId, e);
            transferSuccess = false;
        } finally {
            AgentSkillFile updateInfo = new AgentSkillFile();
            updateInfo.setFileStatus(transferSuccess ? FileTransferStatusEnums.USING.getStatus() : FileTransferStatusEnums.TRANSFER_FAIL.getStatus());
            updateInfo.setVersionStatus(transferSuccess ? AgentSkillVersionStatusEnum.DISABLE.getStatus() : AgentSkillVersionStatusEnum.CREATE_FAILED.getStatus());
            this.agentSkillFileMapper.updateFileStatusWithOldTransferStatus(versionId, FileTransferStatusEnums.TRANSFER.getStatus(), updateInfo);

            AgentSkillInfo skillUpdateInfo = new AgentSkillInfo();
            if (transferSuccess) {
                switchSkillVersion(skillId, versionId);
                skillUpdateInfo.setStatus(AgentSkillInfoStatusEnum.ACTIVE.getStatus());
                this.agentSkillInfoService.updateAgentSkillInfoBySkillId(skillUpdateInfo, skillId);
            } else {
                skillUpdateInfo.setStatus(AgentSkillInfoStatusEnum.CREATE_FAILED.getStatus());
                skillUpdateInfo.setActiveVersionId(agentSkillFile.getVersionId());
                skillUpdateInfo.setActiveVersionName(agentSkillFile.getVersionName());
                skillUpdateInfo.setActiveFileName(agentSkillFile.getFileName());
                Date curDate = new Date();
                skillUpdateInfo.setUpdateTime(curDate);
                this.agentSkillInfoService.updateAgentSkillInfoBySkillId(skillUpdateInfo, skillId);
            }
        }
    }

    /**
     * 合并Skill分片文件
     *
     * @param dirPath
     * @param toFilePath
     * @param delSource
     * @throws BusinessException
     */
    public void unionSkillFile(String dirPath, String toFilePath, boolean delSource) throws BusinessException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            throw new BusinessException("目录不存在");
        }
        File fileList[] = dir.listFiles();
        File targetFile = new File(toFilePath);
        RandomAccessFile writeFile = null;
        try {
            writeFile = new RandomAccessFile(targetFile, "rw");
            byte[] b = new byte[1024 * 10];
            for (int i = 0; i < fileList.length; i++) {
                int len = -1;
                //创建读块文件的对象
                File chunkFile = new File(dirPath + File.separator + i);
                RandomAccessFile readFile = null;
                try {
                    readFile = new RandomAccessFile(chunkFile, "r");
                    while ((len = readFile.read(b)) != -1) {
                        writeFile.write(b, 0, len);
                    }
                } catch (Exception e) {
                    logger.error("合并Skill分片文件失败", e);
                    throw new BusinessException("合并Skill分片文件失败");
                } finally {
                    readFile.close();
                }
            }
        } catch (Exception e) {
            logger.error("合并Skill分片文件失败", e);
            throw new BusinessException("合并Skill分片文件失败");
        } finally {
            try {
                if (null != writeFile) {
                    writeFile.close();
                }
            } catch (IOException e) {
                logger.error("关闭流失败", e);
            }
            if (delSource) {
                if (dir.exists()) {
                    try {
                        FileUtils.deleteDirectory(dir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 切换Skill版本
     *
     * @param skillId
     * @param versionId
     */
    @Override
    public void switchSkillVersion(String skillId, String versionId) {
        AgentSkillFile newVersion = this.agentSkillFileMapper.selectByVersionId(versionId);
        if (!AgentSkillVersionStatusEnum.DISABLE.getStatus().equals(newVersion.getVersionStatus())) {
            throw new BusinessException("无法切换至当前所选Skill版本");
        }
        AgentSkillFileQuery currentVersionQuery = new AgentSkillFileQuery();
        currentVersionQuery.setSkillId(skillId);
        currentVersionQuery.setVersionStatus(AgentSkillVersionStatusEnum.ACTIVE.getStatus());
        AgentSkillFile updateInfo = new AgentSkillFile();
        // 禁用当前版本
        updateInfo.setVersionStatus(AgentSkillVersionStatusEnum.DISABLE.getStatus());
        updateByParam(updateInfo, currentVersionQuery);
        // 激活新版本
        updateInfo.setVersionStatus(AgentSkillVersionStatusEnum.ACTIVE.getStatus());
        this.agentSkillFileMapper.updateByVersionId(updateInfo, versionId);

        // 更新主Skill信息
        AgentSkillInfo skillUpdateInfo = new AgentSkillInfo();
        skillUpdateInfo.setActiveVersionId(newVersion.getVersionId());
        skillUpdateInfo.setActiveVersionName(newVersion.getVersionName());
        skillUpdateInfo.setActiveFileName(newVersion.getFileName());
        Date curDate = new Date();
        skillUpdateInfo.setUpdateTime(curDate);
        this.agentSkillInfoService.updateAgentSkillInfoBySkillId(skillUpdateInfo, skillId);
    }

    /**
     * 获取Skill版本列表
     *
     * @param param
     * @return
     */
    @Override
    public PaginationResultVO loadAgentSkillVersionVOListByPage(AgentSkillFileQuery param) {
        int count = findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);

        List<AgentSkillFile> agentSkillFileList = findListByParam(param);
        List<String> operatorIdList = findListByParam(param).stream()
                .flatMap(agentSkillFile -> Stream.of(agentSkillFile.getCreateBy()))
                .filter(Objects::nonNull).distinct().collect(Collectors.toList());

        UserInfoQuery userInfoQuery = new UserInfoQuery();
        userInfoQuery.setUserIdList(operatorIdList);
        List<UserInfo> operatorList = this.userInfoService.findListByParam(userInfoQuery);
        Map<String, UserInfo> operatorMap = operatorList.stream()
                .collect(Collectors.toMap(UserInfo::getUserId, user -> user));

        List<AgentSkillFileVO> agentSkillFileVOList = agentSkillFileList.stream().map(agentSkillFile -> {
            AgentSkillFileVO vo = CopyTools.copy(agentSkillFile, AgentSkillFileVO.class);
            vo.setCreateBy(CopyTools.copy(operatorMap.get(agentSkillFile.getCreateBy()), UserInfoVO.class));
            return vo;
        }).collect(Collectors.toList());

        PaginationResultVO<AgentSkillFileVO> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), agentSkillFileVOList);
        return result;
    }
}