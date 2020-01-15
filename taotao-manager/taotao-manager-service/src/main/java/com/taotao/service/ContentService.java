package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * 内容管理接口
 * @author 叔公
 *
 */
public interface ContentService {
	
	/**
	 * 向 tb_content 表中插入内容 
	 * @param content 插入的内容
	 * @return
	 */
	TaotaoResult insertContent(TbContent content);
}
