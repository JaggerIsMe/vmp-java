package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.AdminStatusEnum;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.entity.po.UserInfo;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.entity.vo.UserInfoVO;
import com.vmp.exception.BusinessException;
import com.vmp.service.RoleInfoService;
import com.vmp.service.UserInfoService;
import com.vmp.utils.CookieUtils;
import com.vmp.utils.CopyTools;
import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户信息 Controller
 */
@RestController("accountController")
@RequestMapping("/account")
public class AccountController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

    @Resource
    private AppConfig appConfig;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RoleInfoService roleInfoService;


    /**
     * 登录
     *
     * @param response
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @GlobalInterceptor(checkLogin = false, checkParams = true)
    public ResponseVO login(HttpServletResponse response,
                            @VerifyParam(required = true) String account,
                            @VerifyParam(required = true) String password) {
        TokenUserInfoDto tokenUserInfoDto = userInfoService.login(account, password);
        CookieUtils.addHttpOnlyCookie(response, Constants.WEB_TOKEN_KEY, tokenUserInfoDto.getToken(), Constants.REDIS_KEY_EXPIRES_THREE_DAY);
        return getSuccessResponseVO(tokenUserInfoDto);
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public ResponseVO logout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtils.getCookieValue(request, Constants.WEB_TOKEN_KEY);
        CookieUtils.deleteCookie(response, Constants.WEB_TOKEN_KEY);
        if (!StringTools.isEmpty(token)) {
            userInfoService.logout(token);
        }

        return getSuccessResponseVO(null);
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getUserInfo")
    @GlobalInterceptor
    public ResponseVO getUserInfo(HttpServletRequest request) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        UserInfo userInfo = userInfoService.getUserInfoByUserId(tokenUserInfoDto.getUserId());
        UserInfoVO userInfoVO = CopyTools.copy(userInfo, UserInfoVO.class);
        userInfoVO.setAdmin(tokenUserInfoDto.getAdmin());
        userInfoVO.setNewToHere(StringTools.isEmpty(userInfo.getPassword()));
        return getSuccessResponseVO(userInfoVO);
    }

    /**
     * 重置密码
     *
     * @param request
     * @param password
     * @return
     */
    @RequestMapping("/resetPwd")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO resetPwd(HttpServletRequest request,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8, max = 18) String password) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        userInfoService.resetPwd(tokenUserInfoDto.getUserId(), password);

        return getSuccessResponseVO(null);
    }

    /**
     * 更新用户信息(账号、昵称、头像)
     *
     * @param request
     * @param userInfo
     * @param avatarFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveUserInfo")
    @GlobalInterceptor
    public ResponseVO saveUserInfo(HttpServletRequest request, UserInfo userInfo,
                                   MultipartFile avatarFile) throws IOException {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        userInfo.setUserId(tokenUserInfoDto.getUserId());
        //这些置空的操作是为了防止恶意注入导致用户某些字段值被篡改
        userInfo.setDdOpenUnionid(null);
        userInfo.setPassword(null);
        userInfo.setAdmin(null);
        userInfo.setStatus(null);
        userInfo.setJoinTime(null);
        userInfo.setLastLoginTime(null);

        this.userInfoService.updateByUserInfo(userInfo, avatarFile);
        //修改保存后，返回新的个人信息
        return getUserInfo(request);
    }

    /**
     * 获取用户头像
     *
     * @param response
     * @param userId
     */
    @RequestMapping("/getAvatar/{userId}")
    @GlobalInterceptor(checkParams = true)
    public void getAvatar(HttpServletRequest request, HttpServletResponse response, @VerifyParam(required = true) @PathVariable("userId") String userId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        // 非管理员只能获取自己的头像
        if (!tokenUserInfoDto.getUserId().equals(userId) && !AdminStatusEnum.ADMIN.getStatus().equals(tokenUserInfoDto.getAdmin())) {
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }

        String avatarFolderName = Constants.FILE_FOLDER_FILE + Constants.FILE_FOLDER_AVATAR_NAME;
        File folder = new File(appConfig.getProjectFolder() + avatarFolderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String avatarPath = appConfig.getProjectFolder() + avatarFolderName + userId + Constants.AVATAR_SUFFIX;
        File file = new File(avatarPath);
        if (!file.exists()) {
            if (!new File(appConfig.getProjectFolder() + avatarFolderName + Constants.AVATAR_DEFUALT).exists()) {
                printNoDefaultImage(response);
                return;
            }
            avatarPath = appConfig.getProjectFolder() + avatarFolderName + Constants.AVATAR_DEFUALT;
        }
        response.setContentType("image/jpg");
        readFile(response, avatarPath);
    }

    private void printNoDefaultImage(HttpServletResponse response) {
        response.setHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print("请在头像目录下放置默认头像default_avatar.jpg");
            writer.close();
        } catch (Exception e) {
            logger.error("输出无默认图失败", e);
        } finally {
            writer.close();
        }
    }

    /**
     * 初始化用户信息(账号、密码、昵称)，初次钉钉扫码登录后
     *
     * @param request
     * @param userInfo
     * @return
     * @throws IOException
     */
    @RequestMapping("/initUserInfo")
    @GlobalInterceptor
    public ResponseVO initUserInfo(HttpServletRequest request, UserInfo userInfo) throws IOException {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        //这些置空的操作是为了防止恶意注入导致用户某些字段值被篡改
        userInfo.setUserId(null);
        userInfo.setDdOpenUnionid(null);
        userInfo.setAdmin(null);
        userInfo.setStatus(null);
        userInfo.setJoinTime(null);
        userInfo.setLastLoginTime(null);

        this.userInfoService.updateUserInfoByUserId(userInfo, tokenUserInfoDto.getUserId());
        //修改保存后，返回新的个人信息
        return getUserInfo(request);
    }

    /**
     * 获取指定用户角色权限信息
     *
     * @return
     */
    @RequestMapping("/getUserRoleMenuPermissionsInfo/{userId}")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO getUserRoleMenuPermissionsInfo(HttpServletRequest request,
                                                     @VerifyParam(required = true) @PathVariable("userId") String userId){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        // 非管理员只能获取自己的角色权限信息
        if (!tokenUserInfoDto.getUserId().equals(userId) && !AdminStatusEnum.ADMIN.getStatus().equals(tokenUserInfoDto.getAdmin())) {
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }

        return getSuccessResponseVO(roleInfoService.getUserRoleMenuPermissionsInfo(userId));
    }

}