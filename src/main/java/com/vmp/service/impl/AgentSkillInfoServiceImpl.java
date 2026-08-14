package com.vmp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.AgentSkillInfoStatusEnum;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.entity.po.UserInfo;
import com.vmp.entity.query.UserInfoQuery;
import com.vmp.entity.vo.AgentSkillInfoVO;
import com.vmp.entity.vo.RoleInfoVO;
import com.vmp.entity.vo.UserInfoVO;
import com.vmp.exception.BusinessException;
import com.vmp.service.UserInfoService;
import com.vmp.utils.CopyTools;
import com.vmp.utils.VerifyUtils;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.AgentSkillInfoQuery;
import com.vmp.entity.po.AgentSkillInfo;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.AgentSkillInfoMapper;
import com.vmp.service.AgentSkillInfoService;
import com.vmp.utils.StringTools;


/**
 * Agent Skill 管理表 业务接口实现
 */
@Service("agentSkillInfoService")
public class AgentSkillInfoServiceImpl implements AgentSkillInfoService {

    @Resource
    private AgentSkillInfoMapper<AgentSkillInfo, AgentSkillInfoQuery> agentSkillInfoMapper;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<AgentSkillInfo> findListByParam(AgentSkillInfoQuery param) {
        return this.agentSkillInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(AgentSkillInfoQuery param) {
        return this.agentSkillInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<AgentSkillInfo> findListByPage(AgentSkillInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<AgentSkillInfo> list = this.findListByParam(param);
        PaginationResultVO<AgentSkillInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(AgentSkillInfo bean) {
        return this.agentSkillInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<AgentSkillInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.agentSkillInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<AgentSkillInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.agentSkillInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(AgentSkillInfo bean, AgentSkillInfoQuery param) {
        StringTools.checkParam(param);
        return this.agentSkillInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(AgentSkillInfoQuery param) {
        StringTools.checkParam(param);
        return this.agentSkillInfoMapper.deleteByParam(param);
    }

    /**
     * 根据SkillId获取对象
     */
    @Override
    public AgentSkillInfo getAgentSkillInfoBySkillId(String skillId) {
        return this.agentSkillInfoMapper.selectBySkillId(skillId);
    }

    /**
     * 根据SkillId修改
     */
    @Override
    public Integer updateAgentSkillInfoBySkillId(AgentSkillInfo bean, String skillId) {
        return this.agentSkillInfoMapper.updateBySkillId(bean, skillId);
    }

    /**
     * 根据SkillId删除
     */
    @Override
    public Integer deleteAgentSkillInfoBySkillId(String skillId) {
        return this.agentSkillInfoMapper.deleteBySkillId(skillId);
    }

    /**
     * 根据NameAndPlatform获取对象
     */
    @Override
    public AgentSkillInfo getAgentSkillInfoByNameAndPlatform(String name, String platform) {
        return this.agentSkillInfoMapper.selectByNameAndPlatform(name, platform);
    }

    /**
     * 根据NameAndPlatform修改
     */
    @Override
    public Integer updateAgentSkillInfoByNameAndPlatform(AgentSkillInfo bean, String name, String platform) {
        return this.agentSkillInfoMapper.updateByNameAndPlatform(bean, name, platform);
    }

    /**
     * 根据NameAndPlatform删除
     */
    @Override
    public Integer deleteAgentSkillInfoByNameAndPlatform(String name, String platform) {
        return this.agentSkillInfoMapper.deleteByNameAndPlatform(name, platform);
    }

    /**
     * 获取Skill列表 根据条件分页查询
     *
     * @param param
     * @return
     */
    @Override
    public PaginationResultVO<AgentSkillInfoVO> loadAgentSkillInfoVOListByPage(AgentSkillInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);

        List<AgentSkillInfo> agentSkillInfoList = this.findListByParam(param);
        List<String> operatorIdList = this.findListByParam(param).stream()
                .flatMap(agentSkillInfo -> Stream.of(agentSkillInfo.getCreateBy()))
                .filter(Objects::nonNull).distinct().collect(Collectors.toList());

        UserInfoQuery userInfoQuery = new UserInfoQuery();
        userInfoQuery.setUserIdList(operatorIdList);
        List<UserInfo> operatorList = userInfoService.findListByParam(userInfoQuery);
        Map<String, UserInfo> operatorMap = operatorList.stream()
                .collect(Collectors.toMap(UserInfo::getUserId, user -> user));

        List<AgentSkillInfoVO> agentSkillInfoVOList = agentSkillInfoList.stream().map(agentSkillInfo -> {
            AgentSkillInfoVO vo = CopyTools.copy(agentSkillInfo, AgentSkillInfoVO.class);
            vo.setCreateBy(CopyTools.copy(operatorMap.get(agentSkillInfo.getCreateBy()), UserInfoVO.class));
            return vo;
        }).collect(Collectors.toList());

        PaginationResultVO<AgentSkillInfoVO> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), agentSkillInfoVOList);
        return result;
    }

    /**
     * 发布Skill
     *
     * @param userId
     * @param bean
     */
    @Override
    public void releaseSkill(String userId, AgentSkillInfo bean) {
        if (!VerifyUtils.verify(VerifyRegexEnum.SKILL_NAME, bean.getName())) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        AgentSkillInfo newSkill = new AgentSkillInfo();
        newSkill.setSkillId(StringTools.getRandomString(Constants.LENGTH_10));
        newSkill.setName(bean.getName());
        newSkill.setDescription(bean.getDescription());
        newSkill.setRemark(bean.getRemark());
        newSkill.setPlatform(bean.getPlatform());
        newSkill.setDept(bean.getDept());
        newSkill.setStatus(AgentSkillInfoStatusEnum.CREATING.getStatus());
        newSkill.setCreateBy(userId);
        Date curDate = new Date();
        newSkill.setCreateTime(curDate);
        newSkill.setUpdateTime(curDate);
        this.agentSkillInfoMapper.insert(newSkill);
    }

    /**
     * 修改Skill基础信息
     *
     * @param bean
     * @param skillId
     */
    @Override
    public void updateSkillInfo(AgentSkillInfo bean, String skillId) {
        AgentSkillInfo updateInfo = new AgentSkillInfo();
        updateInfo.setDescription(bean.getDescription());
        updateInfo.setRemark(bean.getRemark());
        updateInfo.setPlatform(bean.getPlatform());
        updateInfo.setDept(bean.getDept());
        AgentSkillInfo dbInfo = getAgentSkillInfoBySkillId(skillId);
        if (!AgentSkillInfoStatusEnum.ACTIVE.getStatus().equals(dbInfo.getStatus()) && !AgentSkillInfoStatusEnum.DISABLE.getStatus().equals(dbInfo.getStatus()) && null != bean.getStatus()) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        if (!AgentSkillInfoStatusEnum.CREATING.getStatus().equals(bean.getStatus()) && !AgentSkillInfoStatusEnum.CREATE_FAILED.getStatus().equals(bean.getStatus())) {
            updateInfo.setStatus(bean.getStatus());
        }
        Date curDate = new Date();
        updateInfo.setUpdateTime(curDate);
        updateAgentSkillInfoBySkillId(updateInfo, skillId);
    }
}