package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * 用户管理
 * 
 * 根据token换取用户信息，需要调用sso系统的服务。返回TbUser对象。如果没有就返回null。
 * @author 叔公
 *
 */
public interface UserService {
	
	/**
	 * 根据token换取用户信息
	 * @param token 登陆时存储在 Redis 中的用户信息
	 * @return 返回用户信息对象
	 */
	TbUser getUserByToken(String token);
}
