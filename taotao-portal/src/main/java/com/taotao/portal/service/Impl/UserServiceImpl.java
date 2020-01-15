package com.taotao.portal.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/**
 * 用户管理的 Service
 * 
 * @author 叔公
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * 单点登陆系统的url: http://127.0.0.1:8084
	 */
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;

	/**
	 * 根据 token 获取用户信息的 url: /user/token/
	 */
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	
	/**
	 * 单点登陆系统的 url: /page/login
	 */
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	/**
	 * 根据token换取用户信息
	 */
	@Override
	public TbUser getUserByToken(String token) {
		try {
			// 调用sso系统的服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN
					+ token);
			// 把json转换成TaotaoResult
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
