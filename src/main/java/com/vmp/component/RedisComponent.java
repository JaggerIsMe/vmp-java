package com.vmp.component;

import com.vmp.entity.po.UserInfo;
import com.vmp.entity.query.UserInfoQuery;
import com.vmp.mappers.UserInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("redisComponent")
public class RedisComponent {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

}
