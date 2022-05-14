package com.dev.base.vo;

import java.io.Serializable;

/**
 * 
		* <p>Title: 前台,下拉框信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
public class SelectInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 下拉框展示名称*/
	private String name;
	
	/** 下拉框值*/
	private String code;
	
	/** 是否选中 */
	private boolean selected;
	
	public SelectInfo(){

	}
	
	public SelectInfo(String name,String code){
		this.name = name;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
