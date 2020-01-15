package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片处理接口
 * @author 叔公
 *
 */
public interface PictureService {
	
	/**
	 * 上传图片
	 * @param uploadFile 图片
	 * @return 返回一个 map
	 *  //成功时
	 *	{
  	 *       "error" : 0,
  	 *       "url" : "http://www.example.com/path/to/file.ext"
	 *	}
	 *	//失败时
	 *	{
  	 *       "error" : 1,
  	 *       "message" : "错误信息"
	 *	} 
	 *    Key         vaule
	 *   ----------------------
	 *    error        1/0
	 *    url          图片的url (成功时)
	 *    message      错误信息(失败时)
	 */
	Map uploadPicture(MultipartFile uploadFile);
}
