package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.dto.FileUploadResultDto;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.AgentSkillInfoStatusEnum;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.po.AgentSkillInfo;
import com.vmp.entity.query.AgentSkillFileQuery;
import com.vmp.entity.query.AgentSkillInfoQuery;
import com.vmp.entity.vo.AgentSkillFileVO;
import com.vmp.entity.vo.AgentSkillInfoVO;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.exception.BusinessException;
import com.vmp.service.AgentSkillFileService;
import com.vmp.service.AgentSkillInfoService;
import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Skill Controller
 */
@RestController("agentSkillController")
@RequestMapping("/skill")
public class AgentSkillController extends CommonFileController {

    private static final Logger logger = LoggerFactory.getLogger(AgentSkillController.class);

    @Resource
    private AgentSkillInfoService agentSkillInfoService;

    @Resource
    private AgentSkillFileService agentSkillFileService;

    /**
     * 获取Skill列表 根据条件分页查询
     *
     * @param query
     * @return
     */
    @RequestMapping("/loadSkillList")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO loadSkillList(AgentSkillInfoQuery query) {
        query.setOrderBy("create_time desc");
        PaginationResultVO resultVO = this.agentSkillInfoService.loadAgentSkillInfoVOListByPage(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, AgentSkillInfoVO.class));
    }

    /**
     * 获取Skill版本列表
     *
     * @param query
     * @param skillId
     * @return
     */
    @RequestMapping("/loadSkillVersionList/{skillId}")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO loadSkillVersionList(AgentSkillFileQuery query, @PathVariable("skillId") String skillId) {
        query.setSkillId(skillId);
        query.setOrderBy("create_time desc");
        PaginationResultVO resultVO = this.agentSkillFileService.loadAgentSkillVersionVOListByPage(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, AgentSkillFileVO.class));
    }

    /**
     * 发布Skill
     *
     * @param request
     * @param bean
     * @return
     */
    @RequestMapping("/releaseSkill")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO releaseSkill(HttpServletRequest request, AgentSkillInfo bean) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        this.agentSkillInfoService.releaseSkill(tokenUserInfoDto.getUserId(), bean);
        return getSuccessResponseVO(null);
    }

    /**
     * 发布Skill版本
     *
     * @param request
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
    @RequestMapping("/releaseSkillVersion")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO releaseSkillVersion(HttpServletRequest request, @VerifyParam(required = true) String skillId, String fileId, MultipartFile file,
                                          @VerifyParam(required = true) String versionName,
                                          @VerifyParam(required = true) String remark,
                                          @VerifyParam(required = true) String fileMd5,
                                          @VerifyParam(required = true) Integer chunkIndex,
                                          @VerifyParam(required = true) Integer chunks) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        FileUploadResultDto resultDto = this.agentSkillFileService.uploadSkillFile(tokenUserInfoDto.getUserId(), skillId, fileId, file, versionName, remark, fileMd5, chunkIndex, chunks);
        return getSuccessResponseVO(resultDto);
    }

    /**
     * 切换Skill版本
     *
     * @param skillId
     * @param versionId
     * @return
     */
    @RequestMapping("/switchSkillVersion/{skillId}/{versionId}")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO switchSkillVersion(@PathVariable("skillId") String skillId, @PathVariable("versionId") String versionId) {
        this.agentSkillFileService.switchSkillVersion(skillId, versionId);
        return getSuccessResponseVO(null);
    }

    /**
     * 修改Skill基础信息
     *
     * @param bean
     * @param skillId
     * @return
     */
    @RequestMapping("/updateSkillInfo/{skillId}")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO updateSkillInfo(AgentSkillInfo bean, @PathVariable("skillId") String skillId) {
        this.agentSkillInfoService.updateSkillInfo(bean, skillId);
        return getSuccessResponseVO(null);
    }

    /**
     * 生成下载链接-Skill File
     *
     * @param versionId
     * @return
     */
    @RequestMapping("/createDownloadUrl4Skill/{versionId}")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO createDownloadUrl4Skill(@PathVariable("versionId") String versionId) {
        return super.createDownloadUrl4Skill(versionId);
    }

    /**
     * 下载文件-Skill File
     *
     * @param request
     * @param response
     * @param code
     * @throws Exception
     */
    @RequestMapping("/downloadSkill/{code}")
    @GlobalInterceptor(checkLogin = false, checkParams = true)
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("code") String code) throws Exception {
        super.download(request, response, code);
    }

}
