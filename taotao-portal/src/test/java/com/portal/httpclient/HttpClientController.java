package com.portal.httpclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 测试 HttpClient 的 POST 请求
 * @author 叔公
 *
 */
@Controller
public class HttpClientController {

	/**
	 * 测试  
	 * @return
	 */
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult testPost(){
		return TaotaoResult.ok();
	}
	
	/**
	 * 测试  
	 * @return
	 */
	@RequestMapping(value="/httpclient2/post", method=RequestMethod.POST)
	@ResponseBody
	public String testPost2(String username, String password){
		return "username:" + username + " password:" + password;
	}
}
