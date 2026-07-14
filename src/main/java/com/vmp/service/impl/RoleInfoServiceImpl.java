package com.vmp.service.impl;

import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.PageSize;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.enums.RoleLevelEnum;
import com.vmp.entity.enums.RoleStatusEnum;
import com.vmp.entity.po.*;
import com.vmp.entity.query.*;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.vo.RoleInfoVO;
import com.vmp.entity.vo.SysMenuInfoVO;
import com.vmp.entity.vo.UserInfoVO;
import com.vmp.exception.BusinessException;
import com.vmp.mappers.RoleInfoMapper;
import com.vmp.service.*;
import com.vmp.utils.CopyTools;
import com.vmp.utils.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 系统角色信息 业务接口实现
 */
@Service("roleInfoService")
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    private RoleInfoMapper<RoleInfo, RoleInfoQuery> roleInfoMapper;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private SysMenuInfoService sysMenuInfoService;

    @Resource
    private SysRolesMenusService sysRolesMenusService;

    @Resource
    private SysUsersRolesService sysUsersRolesService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<RoleInfo> findListByParam(RoleInfoQuery param) {
        return this.roleInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(RoleInfoQuery param) {
        return this.roleInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<RoleInfo> findListByPage(RoleInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<RoleInfo> list = this.findListByParam(param);
        PaginationResultVO<RoleInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(RoleInfo bean) {
        return this.roleInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<RoleInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.roleInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<RoleInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.roleInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(RoleInfo bean, RoleInfoQuery param) {
        StringTools.checkParam(param);
        return this.roleInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(RoleInfoQuery param) {
        StringTools.checkParam(param);
        return this.roleInfoMapper.deleteByParam(param);
    }

    /**
     * 根据RoleId获取对象
     */
    @Override
    public RoleInfo getRoleInfoByRoleId(String roleId) {
        return this.roleInfoMapper.selectByRoleId(roleId);
    }

    /**
     * 根据RoleId修改
     */
    @Override
    public Integer updateRoleInfoByRoleId(RoleInfo bean, String roleId) {
        return this.roleInfoMapper.updateByRoleId(bean, roleId);
    }

    /**
     * 根据RoleId删除
     */
    @Override
    public Integer deleteRoleInfoByRoleId(String roleId) {
        return this.roleInfoMapper.deleteByRoleId(roleId);
    }

    /**
     * 根据Name获取对象
     */
    @Override
    public RoleInfo getRoleInfoByName(String name) {
        return this.roleInfoMapper.selectByName(name);
    }

    /**
     * 根据Name修改
     */
    @Override
    public Integer updateRoleInfoByName(RoleInfo bean, String name) {
        return this.roleInfoMapper.updateByName(bean, name);
    }

    /**
     * 根据Name删除
     */
    @Override
    public Integer deleteRoleInfoByName(String name) {
        return this.roleInfoMapper.deleteByName(name);
    }

    /**
     * 分页查询 RoleInfoVO
     *
     * @param param
     * @return
     */
    @Override
    public PaginationResultVO<RoleInfoVO> loadRoleInfoVOListByPage(RoleInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);

        List<RoleInfo> roleInfoList = this.findListByParam(param);
        List<String> operatorIdList = this.findListByParam(param).stream()
                .flatMap(roleInfo -> Stream.of(roleInfo.getCreateBy(), roleInfo.getUpdateBy()))
                .filter(Objects::nonNull).distinct().collect(Collectors.toList());

        UserInfoQuery userInfoQuery = new UserInfoQuery();
        userInfoQuery.setUserIdList(operatorIdList);
        List<UserInfo> operatorList = userInfoService.findListByParam(userInfoQuery);
        Map<String, UserInfo> operatorMap = operatorList.stream()
                .collect(Collectors.toMap(UserInfo::getUserId, user -> user));

        List<RoleInfoVO> roleInfoVOList = roleInfoList.stream().map(roleInfo -> {
            RoleInfoVO vo = CopyTools.copy(roleInfo, RoleInfoVO.class);
            vo.setCreateBy(CopyTools.copy(operatorMap.get(roleInfo.getCreateBy()), UserInfoVO.class));
            vo.setUpdateBy(CopyTools.copy(operatorMap.get(roleInfo.getUpdateBy()), UserInfoVO.class));
            return vo;
        }).collect(Collectors.toList());

        PaginationResultVO<RoleInfoVO> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), roleInfoVOList);
        return result;
    }

    /**
     * 新增角色
     *
     * @param operatorId
     * @param roleInfo
     */
    @Override
    public void newRole(String operatorId, RoleInfo roleInfo) {
        Date curDate = new Date();
        roleInfo.setRoleId(StringTools.getRandomString(Constants.LENGTH_20));
        roleInfo.setStatus(RoleStatusEnum.ENABLE.getStatus());
        roleInfo.setCreateBy(operatorId);
        roleInfo.setUpdateBy(operatorId);
        roleInfo.setCreateTime(curDate);
        roleInfo.setUpdateTime(curDate);
        add(roleInfo);
    }

    /**
     * 修改角色信息
     *
     * @param operatorId
     * @param roleInfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(String operatorId, RoleInfo roleInfo) {
        if (!RoleStatusEnum.ENABLE.getStatus().equals(roleInfo.getStatus()) && !RoleStatusEnum.DISABLE.getStatus().equals(roleInfo.getStatus())
                && !RoleLevelEnum.DEVELOPER.getLevel().equals(roleInfo.getLevel()) && !RoleLevelEnum.BOSS.getLevel().equals(roleInfo.getLevel())
                && !RoleLevelEnum.MANAGER.getLevel().equals(roleInfo.getLevel()) && !RoleLevelEnum.LEADER.getLevel().equals(roleInfo.getLevel())
                && !RoleLevelEnum.MEMBER.getLevel().equals(roleInfo.getLevel())) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        Date curDate = new Date();
        roleInfo.setUpdateBy(operatorId);
        roleInfo.setUpdateTime(curDate);
        updateRoleInfoByRoleId(roleInfo, roleInfo.getRoleId());
    }

    /**
     * 根据RoleId 获取角色页面权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenuInfoVO> loadRoleMenuPermissionsByRoleId(String roleId) {
        SysRolesMenusQuery rolesMenusQuery = new SysRolesMenusQuery();
        rolesMenusQuery.setRoleId(roleId);
        List<String> menuIds = sysRolesMenusService.findListByParam(rolesMenusQuery)
                .stream()
                .map(SysRolesMenus::getMenuId)
                .collect(Collectors.toList());

        SysMenuInfoQuery menuInfoQuery = new SysMenuInfoQuery();
        menuInfoQuery.setMenuIdList(menuIds);
        List<SysMenuInfo> menuInfoList = sysMenuInfoService.findListByParam(menuInfoQuery);

        return CopyTools.copyList(menuInfoList, SysMenuInfoVO.class);
    }

    /**
     * 根据RoleId 修改角色页面权限
     *
     * @param menuInfoVOList
     * @param roleId
     * @param userId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenuPermissionsByRoleId(List<SysMenuInfoVO> menuInfoVOList, String roleId, String userId) {
        // 1. 删除角色原有所有菜单权限
        SysRolesMenusQuery deleteQuery = new SysRolesMenusQuery();
        deleteQuery.setRoleId(roleId);
        sysRolesMenusService.deleteByParam(deleteQuery);

        // 2. 新增新的菜单权限
        if (CollectionUtils.isEmpty(menuInfoVOList)) {
            return;
        }

        List<SysRolesMenus> rolesMenusList = menuInfoVOList.stream()
                .map(menu -> {
                    SysRolesMenus rolesMenus = new SysRolesMenus();
                    rolesMenus.setRoleId(roleId);
                    rolesMenus.setMenuId(menu.getMenuId());
                    return rolesMenus;
                })
                .collect(Collectors.toList());
        sysRolesMenusService.addBatch(rolesMenusList);

        RoleInfo updateInfo = new RoleInfo();
        Date curDate = new Date();
        updateInfo.setUpdateBy(userId);
        updateInfo.setUpdateTime(curDate);
        updateRoleInfoByRoleId(updateInfo, userId);
    }

    /**
     * 获取指定用户角色权限信息
     *
     * @param userId
     * @return
     */
    @Override
    public RoleInfoVO getUserRoleMenuPermissionsInfo(String userId) {
        SysUsersRoles userRole = sysUsersRolesService.getSysUsersRolesByUserId(userId);
        if (null == userRole) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        SysRolesMenusQuery roleMenusQuery = new SysRolesMenusQuery();
        roleMenusQuery.setRoleId(userRole.getRoleId());
        List<String> menuIds = sysRolesMenusService.findListByParam(roleMenusQuery)
                .stream()
                .map(SysRolesMenus::getMenuId)
                .collect(Collectors.toList());
        SysMenuInfoQuery menuInfoQuery = new SysMenuInfoQuery();
        menuInfoQuery.setMenuIdList(menuIds);
        List<SysMenuInfoVO> roleMenuPermissionsList = CopyTools.copyList(sysMenuInfoService.findListByParam(menuInfoQuery), SysMenuInfoVO.class);

        RoleInfoVO roleInfoVO = CopyTools.copy(getRoleInfoByRoleId(userRole.getRoleId()), RoleInfoVO.class);
        roleInfoVO.setRoleMenuPermissionsList(roleMenuPermissionsList);

        return roleInfoVO;
    }

    /**
     * 根据UserId 修改用户角色
     *
     * @param roleId
     * @param userId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRoleByUserId(String userId, String roleId) {
        SysUsersRoles updateUserRoleInfo = new SysUsersRoles();
        updateUserRoleInfo.setRoleId(roleId);
        sysUsersRolesService.updateSysUsersRolesByUserId(updateUserRoleInfo, userId);
    }
}
