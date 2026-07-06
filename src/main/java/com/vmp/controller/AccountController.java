package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.entity.po.UserInfo;
import com.vmp.entity.query.UserInfoQuery;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.entity.vo.UserInfoVO;
import com.vmp.service.UserInfoService;
import com.vmp.utils.CopyTools;
import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    private UserInfoService userInfoService;

    @Resource
    private AppConfig appConfig;

    /**
     * 根据条件分页查询
     */
    @RequestMapping("/loadUserList")
    @GlobalInterceptor(checkParams = true, checkAdmin = true)
    public ResponseVO loadUserList(UserInfoQuery query) {
        return getSuccessResponseVO(userInfoService.findListByPage(query));
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public ResponseVO add(UserInfo bean) {
        userInfoService.add(bean);
        return getSuccessResponseVO(null);
    }

    /**
     * 批量新增
     */
    @RequestMapping("/addBatch")
    public ResponseVO addBatch(@RequestBody List<UserInfo> listBean) {
        userInfoService.addBatch(listBean);
        return getSuccessResponseVO(null);
    }

    /**
     * 批量新增/修改
     */
    @RequestMapping("/addOrUpdateBatch")
    public ResponseVO addOrUpdateBatch(@RequestBody List<UserInfo> listBean) {
        userInfoService.addBatch(listBean);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据UserId查询对象
     */
    @RequestMapping("/getUserInfoByUserId")
    public ResponseVO getUserInfoByUserId(String userId) {
        return getSuccessResponseVO(userInfoService.getUserInfoByUserId(userId));
    }

    /**
     * 根据UserId修改对象
     */
    @RequestMapping("/updateUserInfoByUserId")
    public ResponseVO updateUserInfoByUserId(UserInfo bean, String userId) {
        userInfoService.updateUserInfoByUserId(bean, userId);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据UserId删除
     */
    @RequestMapping("/deleteUserInfoByUserId")
    public ResponseVO deleteUserInfoByUserId(String userId) {
        userInfoService.deleteUserInfoByUserId(userId);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据DdOpenUnionid查询对象
     */
    @RequestMapping("/getUserInfoByDdOpenUnionid")
    public ResponseVO getUserInfoByDdOpenUnionid(String ddOpenUnionid) {
        return getSuccessResponseVO(userInfoService.getUserInfoByDdOpenUnionid(ddOpenUnionid));
    }

    /**
     * 根据DdOpenUnionid修改对象
     */
    @RequestMapping("/updateUserInfoByDdOpenUnionid")
    public ResponseVO updateUserInfoByDdOpenUnionid(UserInfo bean, String ddOpenUnionid) {
        userInfoService.updateUserInfoByDdOpenUnionid(bean, ddOpenUnionid);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据DdOpenUnionid删除
     */
    @RequestMapping("/deleteUserInfoByDdOpenUnionid")
    public ResponseVO deleteUserInfoByDdOpenUnionid(String ddOpenUnionid) {
        userInfoService.deleteUserInfoByDdOpenUnionid(ddOpenUnionid);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据Account查询对象
     */
    @RequestMapping("/getUserInfoByAccount")
    public ResponseVO getUserInfoByAccount(String account) {
        return getSuccessResponseVO(userInfoService.getUserInfoByAccount(account));
    }

    /**
     * 根据Account修改对象
     */
    @RequestMapping("/updateUserInfoByAccount")
    public ResponseVO updateUserInfoByAccount(UserInfo bean,String account) {
        userInfoService.updateUserInfoByAccount(bean,account);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据Account删除
     */
    @RequestMapping("/deleteUserInfoByAccount")
    public ResponseVO deleteUserInfoByAccount(String account) {
        userInfoService.deleteUserInfoByAccount(account);
        return getSuccessResponseVO(null);
    }

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
        Cookie cookie = new Cookie(Constants.WEB_TOKEN_KEY, tokenUserInfoDto.getToken());
        response.addCookie(cookie);
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
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (null == cookies) {
            return getSuccessResponseVO(null);
        }
        for (Cookie cookie : cookies) {
            if (Constants.WEB_TOKEN_KEY.equals(cookie.getName())) {
                token = cookie.getValue();
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
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
        return getSuccessResponseVO(userInfoVO);
    }

    /**
     * 重置密码
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
     * @param avatarCover
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveUserInfo")
    @GlobalInterceptor
    public ResponseVO saveUserInfo(HttpServletRequest request, UserInfo userInfo,
                                   MultipartFile avatarFile,
                                   MultipartFile avatarCover) throws IOException {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        userInfo.setUserId(tokenUserInfoDto.getUserId());
        //这些置空的操作是为了防止恶意注入导致用户某些字段值被篡改
        userInfo.setDdOpenUnionid(null);
        userInfo.setPassword(null);
        userInfo.setAdmin(null);
        userInfo.setStatus(null);
        userInfo.setJoinTime(null);
        userInfo.setLastLoginTime(null);

        this.userInfoService.updateByUserInfo(userInfo, avatarFile, avatarCover);
        //修改保存后，返回新的个人信息
        return getUserInfo(request);
    }

    /**
     * 获取用户头像
     * @param response
     * @param userId
     */
    @RequestMapping("/getAvatar/{userId}")
    @GlobalInterceptor(checkLogin = false, checkParams = true)
    public void getAvatar(HttpServletResponse response, @VerifyParam(required = true) @PathVariable("userId") String userId) {
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

}