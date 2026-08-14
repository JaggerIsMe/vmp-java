package com.vmp.controller;

import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.DownloadFileDto;
import com.vmp.entity.enums.AgentSkillVersionStatusEnum;
import com.vmp.entity.enums.FileTransferStatusEnums;
import com.vmp.entity.po.AgentSkillFile;
import com.vmp.entity.po.AgentSkillInfo;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.exception.BusinessException;
import com.vmp.redis.RedisComponent;
import com.vmp.service.AgentSkillFileService;
import com.vmp.service.AgentSkillInfoService;
import com.vmp.utils.StringTools;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class CommonFileController extends ABaseController {

    @Resource
    protected AgentSkillFileService agentSkillFileService;

    @Resource
    protected AgentSkillInfoService agentSkillInfoService;

    @Resource
    protected AppConfig appConfig;

    @Resource
    protected RedisComponent redisComponent;

    /**
     * 生成下载链接-Skill File
     *
     * @param versionId
     * @return
     */
    protected ResponseVO createDownloadUrl4Skill(String versionId) {
        AgentSkillFile fileInfo = this.agentSkillFileService.getAgentSkillFileByVersionId(versionId);
        if ((!AgentSkillVersionStatusEnum.ACTIVE.getStatus().equals(fileInfo.getVersionStatus()) && !AgentSkillVersionStatusEnum.DISABLE.getStatus().equals(fileInfo.getVersionStatus())) ||
                !FileTransferStatusEnums.USING.getStatus().equals(fileInfo.getFileStatus())) {
            throw new BusinessException("当前版本不可下载");
        }
        String code = StringTools.getRandomString(Constants.LENGTH_50);
        DownloadFileDto downloadFileDto = new DownloadFileDto();
        downloadFileDto.setDownloadCode(code);
        downloadFileDto.setFilePath(appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.SKILL_FILE_FOLDER_FILE + fileInfo.getFileName());
        if ("template".equals(versionId)) {
            downloadFileDto.setFileName(fileInfo.getFileName());
        } else {
            // 重命名下载文件
            AgentSkillInfo skillInfo = this.agentSkillInfoService.getAgentSkillInfoBySkillId(fileInfo.getSkillId());
            String skillName = skillInfo.getName();
            downloadFileDto.setFileName(skillName + "@" + fileInfo.getVersionName() + Constants.SKILL_FILE_SUFFIX);
        }

        redisComponent.saveDownloadCode(code, downloadFileDto);

        return getSuccessResponseVO(code);
    }

    /**
     * 下载文件
     *
     * @param request
     * @param response
     * @param code
     * @throws Exception
     */
    protected void download(HttpServletRequest request, HttpServletResponse response, String code) throws Exception {
        DownloadFileDto downloadFileDto = redisComponent.getDownloadCode(code);
        if (null == downloadFileDto) {
            return;
        }
        String filePath = downloadFileDto.getFilePath();
        String fileName = downloadFileDto.getFileName();
        response.setContentType("application/x-msdownload; charset=UTF-8");
        if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") > 0) {//IE浏览器
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        readFile(response, filePath);
    }

}
