package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传控制器
 * @author 叔公
 *
 */
@Controller //前端控制器
public class PictureController {
	
	@Autowired //自动注入 PictureService 接口的动态代理实现类对象
	private PictureService pictureService;
	
	/**
	 * 图片上传
	 * @param uploadFile 上传的图片
	 * @return 返回 json 格式的字符串
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody //返回json 数据
	private String pictureUpload(MultipartFile uploadFile){
		Map result = pictureService.uploadPicture(uploadFile);
		//为了保证浏览器的兼容性，需要把结果转换成 json 格式的字符串
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
