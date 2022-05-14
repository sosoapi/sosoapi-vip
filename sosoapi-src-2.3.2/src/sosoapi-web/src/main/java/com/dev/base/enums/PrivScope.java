package com.dev.base.enums;

/**
 * 
		* <p>Title: 权限适用范围</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月3日上午10:30:07</p>
 */
public enum PrivScope {
	user("用户"),
	
	proj("项目"),
	
	common("公共");
	
	private PrivScope(String displayName){
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
