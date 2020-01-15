package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户注册，登陆页面跳转 Controller
 * 
 * @author 叔公
 * 
 */
@Controller
@RequestMapping("/page")
public class PageController {

	/**
	 * 页面跳转至注册界面 register.jsp
	 * 
	 * 访问URL：http://127.0.0.1:8084/page/register
	 * 
	 * @return
	 */
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}

	/**
	 * 页面跳转至登陆界面 login.jsp
	 * 
	 * @param redirect
	 *            登陆成功后回调的参数，把回调的url传递给jsp页面，当登录成功后，js的逻辑中判断是否有回调的url，
	 *            如果有就跳转到此url，如果没有就跳转到商城首页
	 * 
	 *            例如URL:http://127.0.0.1:8084/page/login?redirect=http://www.
	 *            baidu.com
	 * @param model
	 *            传递给数据给页面的model
	 * @return
	 */
	@RequestMapping("/login")
	private String showLogin(
			@RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
			Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}

}
