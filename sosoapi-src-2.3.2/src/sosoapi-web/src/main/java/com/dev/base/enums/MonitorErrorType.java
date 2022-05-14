package com.dev.base.enums;

/**
 * 
		* <p>Title: 监控失败类型</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:44:28</p>
 */
public enum MonitorErrorType {
	execFail("执行请求失败"),
	
	timeout("响应时间超过设置值"),
	
	respValidFail("响应与设置不匹配");
	
	
	
	private MonitorErrorType(String displayName){
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
