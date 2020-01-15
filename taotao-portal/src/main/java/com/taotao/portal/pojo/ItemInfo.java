package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * 展示商品基本信息的实体类
 * @author 叔公
 *
 */
public class ItemInfo extends TbItem{
	
	public String[] getImages(){
		if(this.getImage() != null){
			String[] images = this.getImage().split(",");
			return images;
		}
		return null;
	}
}
