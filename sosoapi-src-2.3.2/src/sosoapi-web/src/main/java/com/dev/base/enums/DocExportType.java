package com.dev.base.enums;

/**
 * 
		* <p>Title: 文档导出类型</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:44:28</p>
 */
public enum DocExportType {
	html("html","html文档"),
	
	doc("doc","word文档"),
	
	swagger("json","swagger文档"),
	
	postman("json","postman文档"),
	
	pdf("pdf","pdf文档");
	
	private DocExportType(String format,String displayName){
		this.format = format;
		this.displayName = displayName;
	}
	
	/** 文档格式*/
	private String format;
	
	/** 展示名称*/
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
