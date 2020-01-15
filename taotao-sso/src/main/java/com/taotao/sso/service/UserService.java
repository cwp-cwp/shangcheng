package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * 用户管理接口
 * 
 * @author 叔公
 * 
 */
public interface UserService {

	/**
	 * 用户校验
	 * 
	 * @param content
	 *            校验内容
	 * @param type
	 *            校验类型，1、2、3分别代表username、phone、email
	 * @return
	 */
	TaotaoResult checkData(String content, Integer type);

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户对象
	 * @return
	 */
	TaotaoResult createUser(TbUser user);

	/**
	 * 用户登陆
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	TaotaoResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response);

	/**
	 * 到redis中查询token对应的用户信息。返回用户信息并更新过期时间
	 * 
	 * @param token
	 *            用户信息
	 * @return
	 */
	TaotaoResult getUserByToken(String token);
}
