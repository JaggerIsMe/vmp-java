package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.RoleStatusEnum;
import com.vmp.entity.po.RoleInfo;
import com.vmp.entity.po.SysDictData;
import com.vmp.entity.po.SysMenuInfo;
import com.vmp.entity.query.RoleInfoQuery;
import com.vmp.entity.query.SysDictDataQuery;
import com.vmp.entity.query.SysMenuInfoQuery;
import com.vmp.entity.query.UserInfoQuery;
import com.vmp.entity.vo.*;
import com.vmp.service.RoleInfoService;
import com.vmp.service.SysDictDataService;
import com.vmp.service.SysMenuInfoService;
import com.vmp.service.UserInfoService;
import com.vmp.utils.CopyTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理员 Controller
 */
@RestController("adminController")
@RequestMapping("/admin")
public class AdminController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RoleInfoService roleInfoService;

    @Resource
    private SysMenuInfoService sysMenuInfoService;

    @Resource
    private SysDictDataService sysDictDataService;

    /**
     * 获取用户列表 根据条件分页查询
     */
    @RequestMapping("/loadUserList")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO loadUserList(UserInfoQuery query) {
        query.setUserIdList(null);
        query.setOrderBy("join_time desc");
        PaginationResultVO resultVO = userInfoService.findListByPage(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, UserInfoVO.class));
    }

    /**
     * 获取在线用户列表
     *
     * @return
     */
    @RequestMapping("/loadOnlineUserList")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO loadOnlineUserList(UserInfoQuery query) {
        query.setNickNameFuzzy(null);
        query.setAdmin(null);
        query.setStatus(null);
        query.setUserIdList(userInfoService.getOnlineUserIdList());
        query.setOrderBy("last_login_time desc");
        PaginationResultVO resultVO = userInfoService.findListByPage(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, UserInfoVO.class));
    }

    /**
     * 修改用户状态
     *
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping("/updateUserStatus")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO updateUserStatus(@VerifyParam(required = true) String userId, @VerifyParam(required = true) Integer status) {
        userInfoService.updateUserStatus(userId, status);
        return getSuccessResponseVO(null);
    }

    /**
     * 强制用户下线
     *
     * @param userId
     * @return
     */
    @RequestMapping("/forceLogout/{userId}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO forceLogout(@VerifyParam(required = true) @PathVariable("userId") String userId) {
        userInfoService.forceLogout(userId);
        return getSuccessResponseVO(null);
    }

    /**
     * 获取角色列表 根据条件分页查询
     */
    @RequestMapping("/loadRoleList")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO loadRoleList(RoleInfoQuery query) {
        query.setOrderBy("create_time desc");
        PaginationResultVO resultVO = roleInfoService.loadRoleInfoVOListByPage(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, RoleInfoVO.class));
    }

    /**
     * 获取所有可用角色列表数据
     */
    @RequestMapping("/loadAllEnableRoleList")
    @GlobalInterceptor(checkAdmin = true)
    public ResponseVO loadAllEnableRoleList() {
        RoleInfoQuery query = new RoleInfoQuery();
        query.setStatus(RoleStatusEnum.ENABLE.getStatus());
        query.setOrderBy("create_time desc");
        return getSuccessResponseVO(CopyTools.copyList(roleInfoService.findListByParam(query), RoleInfoVO.class));
    }

    /**
     * 新增角色
     */
    @RequestMapping("/newRole")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO newRole(HttpServletRequest request, RoleInfo roleInfo) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        roleInfoService.newRole(tokenUserInfoDto.getUserId(), roleInfo);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据RoleId修改角色
     */
    @RequestMapping("/updateRole/{roleId}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO updateRole(HttpServletRequest request, RoleInfo roleInfo,
                                 @VerifyParam(required = true) @PathVariable("roleId") String roleId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        roleInfo.setRoleId(roleId);
        roleInfo.setCreateBy(null);
        roleInfo.setCreateTime(null);
        roleInfoService.updateRole(tokenUserInfoDto.getUserId(), roleInfo);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据RoleId 获取角色页面权限
     *
     * @return
     */
    @RequestMapping("/loadRoleMenuPermissionsByRoleId/{roleId}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO loadRoleMenuPermissionsByRoleId(@VerifyParam(required = true) @PathVariable("roleId") String roleId) {
        List<SysMenuInfoVO> menuPermissionsList = roleInfoService.loadRoleMenuPermissionsByRoleId(roleId);
        return getSuccessResponseVO(menuPermissionsList);
    }

    /**
     * 根据RoleId 修改角色页面权限
     *
     * @return
     */
    @RequestMapping("/updateRoleMenuPermissionsByRoleId/{roleId}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO updateRoleMenuPermissionsByRoleId(HttpServletRequest request, @RequestBody List<SysMenuInfoVO> menuInfoVOList,
                                                        @VerifyParam(required = true) @PathVariable("roleId") String roleId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        roleInfoService.updateRoleMenuPermissionsByRoleId(menuInfoVOList, roleId, tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(null);
    }

    /**
     * 获取所有菜单页面数据
     *
     * @return
     */
    @RequestMapping("/loadAllMenuList")
    @GlobalInterceptor(checkAdmin = true)
    public ResponseVO loadAllMenuList() {
        SysMenuInfoQuery query = new SysMenuInfoQuery();
        query.setOrderBy("create_time asc");
        return getSuccessResponseVO(CopyTools.copyList(sysMenuInfoService.findListByParam(query), SysMenuInfoVO.class));
    }

    /**
     * 新增菜单页面
     *
     * @return
     */
    @RequestMapping("/newMenu")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO newMenu(HttpServletRequest request, SysMenuInfo menuInfo) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        sysMenuInfoService.newMenu(tokenUserInfoDto.getUserId(), menuInfo);
        return getSuccessResponseVO(null);
    }

    /**
     * 修改菜单页面
     *
     * @return
     */
    @RequestMapping("/updateMenu/{menuId}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO updateMenu(HttpServletRequest request, SysMenuInfo menuInfo,
                                 @VerifyParam(required = true) @PathVariable("menuId") String menuId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        menuInfo.setMenuId(menuId);
        menuInfo.setCreateBy(null);
        menuInfo.setCreateTime(null);
        sysMenuInfoService.updateMenu(tokenUserInfoDto.getUserId(), menuInfo);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据UserId 修改用户角色
     *
     * @param userId
     * @return
     */
    @RequestMapping("/updateUserRoleByUserId/{userId}/{roleId}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO updateUserRoleByUserId(@VerifyParam(required = true) @PathVariable("userId") String userId,
                                             @VerifyParam(required = true) @PathVariable("roleId") String roleId) {
        roleInfoService.updateUserRoleByUserId(userId, roleId);
        return getSuccessResponseVO(null);
    }

    /**
     * 获取所有一级字典数据
     *
     * @return
     */
    @RequestMapping("/loadAllFatherDictDataList")
    @GlobalInterceptor(checkAdmin = true)
    public ResponseVO loadAllFatherDictDataList() {
        SysDictDataQuery query = new SysDictDataQuery();
        query.setPid(Constants.SYS_NAME.toUpperCase());
        query.setOrderBy("create_time asc");
        return getSuccessResponseVO(CopyTools.copyList(sysDictDataService.findListByParam(query), SysDictDataVO.class));
    }

    /**
     * 获取下级字典数据列表 根据条件分页查询
     *
     * @return
     */
    @RequestMapping("/loadChildDictDataListByPid/{pid}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO loadChildDictDataListByPid(SysDictDataQuery query, @VerifyParam(required = true) @PathVariable("pid") String pid) {
        query.setPid(pid);
        query.setOrderBy("create_time asc");
        PaginationResultVO resultVO = sysDictDataService.findListByPage(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, SysDictDataVO.class));
    }

    /**
     * 新增字典数据
     *
     * @return
     */
    @RequestMapping("/newDictData/{pid}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO newDictData(SysDictData dictData, @VerifyParam(required = true) @PathVariable("pid") String pid) {
        dictData.setPid(pid);
        sysDictDataService.newDictData(dictData);
        return getSuccessResponseVO(null);
    }

    /**
     * 修改字典数据
     *
     * @return
     */
    @RequestMapping("/updateDictData/{dictId}")
    @GlobalInterceptor(checkAdmin = true, checkParams = true)
    public ResponseVO updateDictData(SysDictData dictData, @VerifyParam(required = true) @PathVariable("dictId") String dictId) {
        dictData.setDictId(dictId);
        dictData.setPid(null);
        dictData.setDictCode(null);
        dictData.setCreateTime(null);
        sysDictDataService.updateSysDictDataByDictId(dictData, dictId);
        return getSuccessResponseVO(null);
    }

}
