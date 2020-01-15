package com.taotao.portal.service;

/**
 * 根据内容分类id查询分类的内容列表，需要使用httpclient调用taotao-rest的服务。得到一个json字符串。
 * 需要把字符串转换成java对象taotaoResult对象。
 * 从taotaoResult对象中取data属性，得到内容列表。把内容列表转换成jsp页面要求的json格式。返回一个json字符串
 * 
 * @author 叔公
 * 
 */
public interface ContentService {

	/**
	 * 获取内容列表
	 * @return
	 */
	String getContentList();
}
