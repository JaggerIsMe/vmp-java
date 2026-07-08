package com.vmp.service;

import java.io.IOException;
import java.util.List;

import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.po.UserInfo;
import com.vmp.entity.query.UserInfoQuery;
import com.vmp.entity.vo.PaginationResultVO;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用户信息 业务接口
 */
public interface UserInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<UserInfo> findListByParam(UserInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UserInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserInfo> findListByPage(UserInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(UserInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<UserInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(UserInfo bean,UserInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(UserInfoQuery param);

	/**
	 * 根据UserId查询对象
	 */
	UserInfo getUserInfoByUserId(String userId);


	/**
	 * 根据UserId修改
	 */
	Integer updateUserInfoByUserId(UserInfo bean,String userId);


	/**
	 * 根据UserId删除
	 */
	Integer deleteUserInfoByUserId(String userId);


	/**
	 * 根据DdOpenUnionid查询对象
	 */
	UserInfo getUserInfoByDdOpenUnionid(String ddOpenUnionid);


	/**
	 * 根据DdOpenUnionid修改
	 */
	Integer updateUserInfoByDdOpenUnionid(UserInfo bean,String ddOpenUnionid);


	/**
	 * 根据DdOpenUnionid删除
	 */
	Integer deleteUserInfoByDdOpenUnionid(String ddOpenUnionid);


	/**
	 * 根据Account查询对象
	 */
	UserInfo getUserInfoByAccount(String account);


	/**
	 * 根据Account修改
	 */
	Integer updateUserInfoByAccount(UserInfo bean,String account);


	/**
	 * 根据Account删除
	 */
	Integer deleteUserInfoByAccount(String account);

	/**
	 * 由UserInfo获取TokenUserInfoDto对象
	 *
	 * @param userInfo
	 * @return
	 */
	TokenUserInfoDto getTokenUserInfoDto(UserInfo userInfo);

	/**
	 * 登录
	 * @param account
	 * @param password
	 * @return
	 */
	TokenUserInfoDto login(String account, String password);

	/**
	 * 退出登录
	 * @param token
	 */
	void logout(String token);

	/**
	 * 重置密码
	 * @param userId
	 * @param password
	 */
	void resetPwd(String userId, String password);

	/**
	 * 更新用户信息
	 * @param userInfo
	 * @param avatarFile
	 */
	void updateByUserInfo(UserInfo userInfo, MultipartFile avatarFile) throws IOException;

}