package com.dev.base.enums;

/**
 * 
		* <p>Title: 系统配置类型</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月17日上午10:32:41</p>
 */
public enum SysCfgType {
	cate("分组"),
	
	item("配置项");
	
	private SysCfgType(String displayName){
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
