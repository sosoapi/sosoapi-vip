package com.dev.base.enums;

/**
 * 
		* <p>Title: shiro过滤链位置</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:46:15</p>
 */
public enum FilterChainPosition {
	before("前置"),
	
	after("后置");
	
	private FilterChainPosition(String displayName){
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
