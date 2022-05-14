package com.dev.base.enums;

import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: 启用状态</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:46:15</p>
 */
public enum EnableStatus {
	on("开启"),
	
	off("关闭"),
	
	none("未处理");
	
	private EnableStatus(String displayName){
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
	
	public static EnableStatus valueOf(String name,EnableStatus def){
		if (StringUtils.isEmpty(name)) {
			return def;
		}
		
		EnableStatus result = EnableStatus.valueOf(name);
		return result == null ? def : result;
	}
}
