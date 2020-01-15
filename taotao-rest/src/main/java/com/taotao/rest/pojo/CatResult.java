package com.taotao.rest.pojo;

import java.util.List;

/**
 * 返回的分类，返回的属性是一个 list
 * @author 叔公
 *
 */
public class CatResult {
	
	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
}
