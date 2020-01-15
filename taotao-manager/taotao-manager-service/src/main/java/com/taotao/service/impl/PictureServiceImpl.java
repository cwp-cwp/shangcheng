package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传服务处理业务类
 * 
 * @author 叔公
 * 
 */
@Service
// 表明 service 层
public class PictureServiceImpl implements PictureService {

	/**
	 * 读取 taotao-manage-web 的 resource 中的 resource.properties 配置文件中的值
	 */
	@Value("${FTP_ADDRESS}")// ftp 服务器ip地址
	private String FTP_ADDRESS;
	
	@Value("${FTP_PORT}")// ftp 服务器端口号
	private Integer FTP_PORT;
	
	@Value("${FTP_USERNAME}")// ftp 服务器用户名
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")// ftp 服务器密码
	private String FTP_PASSWORD;
	
	@Value("${FTP_BASE_PATH}")// ftp 服务器根路径
	private String FTP_BASE_PATH;
	
	@Value("${IMAGE_BASE_URL}")// 图片服务器的 url
	private String IMAGE_BASE_URL;

	/**
	 * 图片上传
	 */
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			// 生成一个新的文件名
			// 取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			// 生成新的文件名
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			// 图片上传
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT,
					FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newName,
					uploadFile.getInputStream());
			//返回结果
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
			return resultMap;
			
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
	}

}
