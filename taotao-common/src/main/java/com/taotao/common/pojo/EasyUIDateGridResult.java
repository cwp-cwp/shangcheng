package com.taotao.common.pojo;

import java.util.List;

/**
 * 保存分页参数的实体类
 * 
 * Easyui 中 datagrid 控件要求的数据格式为：
 * {total:"2",rows:[{"id":"1","name","张三"},{"id":"2","name","李四"}]}
 * @author 叔公
 *
 */
public class EasyUIDateGridResult {
	
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 每页的行数
	 */
	private List<?> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
