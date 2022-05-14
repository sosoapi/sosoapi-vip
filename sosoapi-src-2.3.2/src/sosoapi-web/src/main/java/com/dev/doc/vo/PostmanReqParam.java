package com.dev.doc.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
		* <p>Title: Postman请求参数</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年12月1日上午10:42:41</p>
 */
public class PostmanReqParam implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 文本类型*/
	public static String TYPE_TEXT = "text";
	
	/** 文件*/
	public static String TYPE_FILE = "file";
	
	/** 参数名字*/
	@JsonProperty("key")
	private String key;
	
	/** 参数值*/
	@JsonProperty("value")
	private String value;
	
	/** 参数类型*/
	@JsonProperty("type")
	private String type;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
