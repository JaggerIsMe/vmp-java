package com.vmp.redis;

import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.utils.StringTools;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
        redisUtils.setex(Constants.REDIS_KEY_ONLINE_TOKEN + tokenUserInfoDto.getToken(), tokenUserInfoDto, Constants.REDIS_KEY_EXPIRES_ONE_HOUR);
        redisUtils.setex(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + tokenUserInfoDto.getUserId(), tokenUserInfoDto.getToken(), Constants.REDIS_KEY_EXPIRES_ONE_HOUR);
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

}
