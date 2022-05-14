package com.dev.base.enums;

/**
 * 
		* <p>Title: 树节点类型</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:46:15</p>
 */
public enum InterNodeType {
	cata("类别"),
	
	module("接口模块"),
	
	inter("接口"),
	
	code("错误码");
	
	private InterNodeType(String displayName){
		this.displayName = displayName;
	}
	
	/** 展示名称*/
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
