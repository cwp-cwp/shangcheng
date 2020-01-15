package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

/**
 * 内容管理接口
 * @author 叔公
 *
 */
public interface ContentService {

	/**
	 * 接收内容分类id，根据分类id查询分类列表
	 * @param contentCid 分类id
	 * @return pojo列表
	 */
	 List<TbContent> getContentList(long contentCid);
}
