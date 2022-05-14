package com.dev.doc.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
		* <p>Title: Postman集合</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年12月1日上午10:42:22</p>
 */
public class PostmanCollection implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 集合id*/
	@JsonProperty("id")
	private String id;
	
	/** 集合名称*/
	@JsonProperty("name")
	private String name;

	/** 修改时间*/
	@JsonProperty("timestamp")
	private Date timestamp;
	
	/** 接口顺序*/
	@JsonProperty("order")
	private List<String> reqOrder;
	
	/** 接口列表*/
	@JsonProperty("requests")
	private List<PostmanReq> reqList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getReqOrder() {
		return reqOrder;
	}

	public void setReqOrder(List<String> reqOrder) {
		this.reqOrder = reqOrder;
	}

	public List<PostmanReq> getReqList() {
		return reqList;
	}

	public void setReqList(List<PostmanReq> reqList) {
		this.reqList = reqList;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
