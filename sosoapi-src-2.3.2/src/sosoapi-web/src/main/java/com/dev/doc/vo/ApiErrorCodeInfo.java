package com.dev.doc.vo;

import java.io.Serializable;

/**
 * 
		* <p>Title: api错误码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月15日下午11:34:35</p>
 */
public class ApiErrorCodeInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键*/
	private Long id;

	/** 错误码*/
	private String code;
	
	/** 错误提示信息*/
	private String msg;
	
	/** 备注*/
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
