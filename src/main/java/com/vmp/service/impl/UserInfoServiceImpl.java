package com.vmp.service.impl;

import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.AdminStatusEnum;
import com.vmp.entity.enums.PageSize;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.enums.UserStatusEnum;
import com.vmp.entity.po.SysRolesMenus;
import com.vmp.entity.po.SysUsersRoles;
import com.vmp.entity.po.UserInfo;
import com.vmp.entity.query.*;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.vo.RoleInfoVO;
import com.vmp.entity.vo.SysMenuInfoVO;
import com.vmp.exception.BusinessException;
import com.vmp.mappers.UserInfoMapper;
import com.vmp.redis.RedisComponent;
import com.vmp.service.*;
import com.vmp.utils.CopyTools;
import com.vmp.utils.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 用户信息 业务接口实现
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

    @Resource
    private RedisComponent redisComponent;

    @Resource
    private AppConfig appConfig;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<UserInfo> findListByParam(UserInfoQuery param) {
        return this.userInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(UserInfoQuery param) {
        return this.userInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<UserInfo> list = this.findListByParam(param);
        PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(UserInfo bean) {
        return this.userInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<UserInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<UserInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(UserInfo bean, UserInfoQuery param) {
        StringTools.checkParam(param);
        return this.userInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(UserInfoQuery param) {
        StringTools.checkParam(param);
        return this.userInfoMapper.deleteByParam(param);
    }

    /**
     * 根据UserId获取对象
     */
    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        return this.userInfoMapper.selectByUserId(userId);
    }

    /**
     * 根据UserId修改
     */
    @Override
    public Integer updateUserInfoByUserId(UserInfo bean, String userId) {
        return this.userInfoMapper.updateByUserId(bean, userId);
    }

    /**
     * 根据UserId删除
     */
    @Override
    public Integer deleteUserInfoByUserId(String userId) {
        return this.userInfoMapper.deleteByUserId(userId);
    }

    /**
     * 根据DdOpenUnionid获取对象
     */
    @Override
    public UserInfo getUserInfoByDdOpenUnionid(String ddOpenUnionid) {
        return this.userInfoMapper.selectByDdOpenUnionid(ddOpenUnionid);
    }

    /**
     * 根据DdOpenUnionid修改
     */
    @Override
    public Integer updateUserInfoByDdOpenUnionid(UserInfo bean, String ddOpenUnionid) {
        return this.userInfoMapper.updateByDdOpenUnionid(bean, ddOpenUnionid);
    }

    /**
     * 根据DdOpenUnionid删除
     */
    @Override
    public Integer deleteUserInfoByDdOpenUnionid(String ddOpenUnionid) {
        return this.userInfoMapper.deleteByDdOpenUnionid(ddOpenUnionid);
    }

    /**
     * 根据Account获取对象
     */
    @Override
    public UserInfo getUserInfoByAccount(String account) {
        return this.userInfoMapper.selectByAccount(account);
    }

    /**
     * 根据Account修改
     */
    @Override
    public Integer updateUserInfoByAccount(UserInfo bean, String account) {
        return this.userInfoMapper.updateByAccount(bean, account);
    }

    /**
     * 根据Account删除
     */
    @Override
    public Integer deleteUserInfoByAccount(String account) {
        return this.userInfoMapper.deleteByAccount(account);
    }

    /**
     * 由UserInfo获取TokenUserInfoDto对象
     *
     * @param userInfo
     * @return
     */
    @Override
    public TokenUserInfoDto getTokenUserInfoDto(UserInfo userInfo) {
        TokenUserInfoDto tokenUserInfoDto = new TokenUserInfoDto();
        tokenUserInfoDto.setUserId(userInfo.getUserId());
        tokenUserInfoDto.setAccount(userInfo.getAccount());
        tokenUserInfoDto.setNickName(userInfo.getNickName());
        tokenUserInfoDto.setDdOpenUnionid(userInfo.getDdOpenUnionid());
        tokenUserInfoDto.setAvatar(userInfo.getAvatar());
        tokenUserInfoDto.setAdmin(userInfo.getAdmin());

        return tokenUserInfoDto;
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public TokenUserInfoDto login(String account, String password) {
        UserInfo userInfo = this.userInfoMapper.selectByAccount(account);
        if (null == userInfo || !userInfo.getPassword().equals(password)) {
            throw new BusinessException("账号或密码错误");
        }
        if (UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus())) {
            throw new BusinessException("账号已被禁用无法登录");
        }
        UserInfo updateInfo = new UserInfo();
        updateInfo.setLastLoginTime(new Date());
        this.userInfoMapper.updateByUserId(updateInfo, userInfo.getUserId());

        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto(userInfo);

        tokenUserInfoDto.setToken(StringTools.createToken(userInfo.getUserId()));
        redisComponent.saveTokenUserInfoDto(tokenUserInfoDto);

        return tokenUserInfoDto;
    }

    /**
     * 退出登录
     *
     * @param token
     */
    @Override
    public void logout(String token) {
        TokenUserInfoDto tokenUserInfoDto = redisComponent.getTokenUserInfoDto(token);
        if (null != tokenUserInfoDto) {
            redisComponent.cleanLatestTokenByUserId(tokenUserInfoDto.getUserId());
        }
        redisComponent.delTokenUserInfoDto(token);
    }

    /**
     * 强制用户下线
     *
     * @param userId
     */
    @Override
    public void forceLogout(String userId) {
        redisComponent.cleanLatestTokenByUserId(userId);
    }

    /**
     * 重置密码
     *
     * @param userId
     * @param password
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(String userId, String password) {
        UserInfo userInfo = this.userInfoMapper.selectByUserId(userId);
        if (null == userInfo) {
            throw new BusinessException("账号不存在");
        }
        UserInfo updateInfo = new UserInfo();
        updateInfo.setPassword(StringTools.encodeByMD5(password));
        this.userInfoMapper.updateByUserId(updateInfo, userInfo.getUserId());
    }

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @param avatarFile
     * @throws IOException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByUserInfo(UserInfo userInfo, MultipartFile avatarFile) throws IOException {
        if (null != avatarFile) {
            String baseFolder = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE;
            File targetFileFolder = new File(baseFolder + Constants.FILE_FOLDER_AVATAR_NAME);
            if (!targetFileFolder.exists()) {
                targetFileFolder.mkdirs();
            }
            String filePath = targetFileFolder.getPath() + "/" + userInfo.getUserId() + Constants.AVATAR_SUFFIX;
            avatarFile.transferTo(new File(filePath));
            userInfo.setAvatar(userInfo.getUserId() + Constants.AVATAR_SUFFIX);
        }
        //查询获取更新前的用户信息，保存下来
        UserInfo dbInfo = this.userInfoMapper.selectByUserId(userInfo.getUserId());
        //更新用户信息
        this.userInfoMapper.updateByUserId(userInfo, userInfo.getUserId());
        /**
         * 上面的小细节
         * 先查询后更新的小细节：
         * 先查询后更新，事务开启的时间就比较短
         * 查询时不会开启事务，更新时才会开启事务
         * 如果先更新，开启事务，然后再查询，如果查询耗时较长，可能导致事务超时
         */
        String accountUpdate = null;
        String nickNameUpdate = null;
        //如果更新前的账号(或昵称)和更新后的账号(或昵称)不相等
        if (!dbInfo.getAccount().equals(userInfo.getAccount()) || !dbInfo.getNickName().equals(userInfo.getNickName())) {
            accountUpdate = userInfo.getAccount();
            nickNameUpdate = userInfo.getNickName();
        }
        if (null == accountUpdate && null == nickNameUpdate) {
            return;
        }

        //更新缓存里的tokenUserInfo
        TokenUserInfoDto tokenUserInfoDto = redisComponent.getTokenUserInfoDtoByUserId(userInfo.getUserId());
        if (null != accountUpdate) {
            tokenUserInfoDto.setAccount(accountUpdate);
        }
        if (null != nickNameUpdate) {
            tokenUserInfoDto.setNickName(nickNameUpdate);
        }

        redisComponent.saveTokenUserInfoDto(tokenUserInfoDto);
    }

    /**
     * 修改用户状态
     *
     * @param userId
     * @param status
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(String userId, Integer status) {
        if (!UserStatusEnum.ENABLE.getStatus().equals(status) && !UserStatusEnum.DISABLE.getStatus().equals(status)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setStatus(status);
        // 强制用户下线
        if (UserStatusEnum.DISABLE.getStatus().equals(status)) {
            forceLogout(userId);
        }
        userInfoMapper.updateByUserId(userInfo, userId);
    }

    /**
     * 获取在线用户userId列表
     *
     * @return
     */
    @Override
    public List<String> getOnlineUserIdList() {
        return redisComponent.getOnlineUserIdList();
    }
}
