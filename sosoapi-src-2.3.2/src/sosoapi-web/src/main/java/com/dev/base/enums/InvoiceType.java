package com.dev.base.enums;

/**
 * 
		* <p>Title: 发票类型</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:46:15</p>
 */
public enum InvoiceType {
	common("增值税普通发票"),
	
	eleCommon("增值税普通电子发票"),
	
	special ("增值税专用电子发票");
	
	private InvoiceType(String displayName){
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
