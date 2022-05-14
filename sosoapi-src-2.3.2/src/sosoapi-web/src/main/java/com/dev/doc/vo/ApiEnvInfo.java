package com.dev.doc.vo;

import java.io.Serializable;

import com.dev.base.enums.EnableStatus;

/**
 * 
		* <p>Title: 文档环境</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月26日下午4:18:08</p>
 */
public class ApiEnvInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 名称*/
	private String name;
	
	/** 基路径*/
	private String baseUrl;
	
	/** 描述 */
	private String description;		
	
	/** 变量列表 */
	private String variable;

	/** 排序权重*/
	private int sortWeight;
	
	/** 是否是mock环境*/
	private boolean mock;
	
	/** 启用状态 */
	private EnableStatus status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public boolean isMock() {
		return mock;
	}

	public void setMock(boolean mock) {
		this.mock = mock;
	}
}
