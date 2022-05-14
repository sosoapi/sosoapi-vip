package com.dev.doc.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: api接口环境</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月13日下午5:44:56</p>
 */
public class ApiEnv extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 文档id */
	private Long docId;		
	
	/** 名称 */
	private String name;		
	
	/** 基路径 */
	private String baseUrl;		
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 是否默认 */
	private boolean def;		
	
	/** 描述 */
	private String description;		
	
	/** 变量列表 */
	private String variable;

	/** 排序权重*/
	private int sortWeight;
	
	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

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

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public boolean isDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
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
}
