package com.dev.base.enums;

/**
 * 
		* <p>Title: 登陆方式</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:44:28</p>
 */
public enum LoginType {
	email("邮箱"),
	
	phone("手机"),
	
	token("token"),
	
	remember("自动登录");
	
	private LoginType(String displayName){
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
