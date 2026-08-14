package com.vmp.redis;

import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.DownloadFileDto;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.utils.StringTools;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("redisComponent")
public class RedisComponent {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 一个是由token存储对应的tokenUserInfoDto信息
     * <p>
     * 一个是由userId存储对应的latest_token值。将来可以通过userId获取最新token，再由token获取对应的tokenUserInfoDto信息
     * 因为将来有一些场景(禁止多端登录)需要latest_token
     *
     * @param tokenUserInfoDto
     */
    public void saveTokenUserInfoDto(TokenUserInfoDto tokenUserInfoDto) {
        redisUtils.setex(Constants.REDIS_KEY_ONLINE_TOKEN + tokenUserInfoDto.getToken(), tokenUserInfoDto, Constants.REDIS_KEY_EXPIRES_THREE_DAY);
        redisUtils.setex(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + tokenUserInfoDto.getUserId(), tokenUserInfoDto.getToken(), Constants.REDIS_KEY_EXPIRES_THREE_DAY);
    }

    /**
     * 根据token获取对应的tokenUserInfoDto信息
     *
     * @param token
     * @return
     */
    public TokenUserInfoDto getTokenUserInfoDto(String token) {
        TokenUserInfoDto tokenUserInfoDto = (TokenUserInfoDto) redisUtils.get(Constants.REDIS_KEY_ONLINE_TOKEN + token);
        return tokenUserInfoDto;
    }

    /**
     * 根据userId获取对应的tokenUserInfoDto信息
     *
     * @param userId
     * @return
     */
    public TokenUserInfoDto getTokenUserInfoDtoByUserId(String userId) {
        String token = (String) redisUtils.get(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + userId);
        return getTokenUserInfoDto(token);
    }

    /**
     * 根据userId获取对应的token值
     * @param userId
     * @return
     */
    public String getTokenByUserId(String userId) {
        return (String) redisUtils.get(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + userId);
    }

    public void delTokenUserInfoDto(String token) {
        redisUtils.delete(Constants.REDIS_KEY_ONLINE_TOKEN + token);
    }

    /**
     * 根据userId删除token
     * 用户下线执行
     *
     * @param userId
     */
    public void cleanLatestTokenByUserId(String userId) {
        String token = (String) redisUtils.get(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + userId);
        if (StringTools.isEmpty(token)) {
            return;
        }
        redisUtils.delete(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + userId);
    }

    /**
     * 获取在线用户userId列表
     * @return
     */
    public List<String> getOnlineUserIdList() {
        String prefix = Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN;
        Set<String> keys = redisUtils.scanKeys(prefix + "*");

        List<String> userIdList = new ArrayList<>();
        for (String key : keys) {
            userIdList.add(key.substring(prefix.length()));
        }

        return userIdList;
    }

    /**
     * Redis保存上传文件临时大小
     * @param userId
     * @param fileId
     * @param fileSize
     */
    public void saveFileTempSize(String userId, String fileId, Long fileSize) {
        Long currentSize = getFileTempSize(userId, fileId);
        redisUtils.setex(Constants.REDIS_KEY_FILE_TEMP_SIZE + userId + fileId, currentSize + fileSize, Constants.REDIS_KEY_EXPIRES_ONE_HOUR);
    }

    public Long getFileTempSize(String userId, String fileId) {
        Long currentSize = getFileSizeFromRedis(Constants.REDIS_KEY_FILE_TEMP_SIZE + userId + fileId);
        return currentSize;
    }

    private Long getFileSizeFromRedis(String key) {
        Object sizeObj = redisUtils.get(key);
        if (sizeObj == null) {
            return 0L;
        }
        if (sizeObj instanceof Integer) {
            return ((Integer) sizeObj).longValue();
        } else if (sizeObj instanceof Long) {
            return (Long) sizeObj;
        }

        return 0L;
    }

    public void saveDownloadCode(String code, DownloadFileDto downloadFileDto) {
        redisUtils.setex(Constants.REDIS_KEY_FILE_DOWNLOAD + code, downloadFileDto, Constants.REDIS_KEY_EXPIRES_FIVE_MIN);
    }

    public DownloadFileDto getDownloadCode(String code) {
        return (DownloadFileDto) redisUtils.get(Constants.REDIS_KEY_FILE_DOWNLOAD + code);
    }

}
