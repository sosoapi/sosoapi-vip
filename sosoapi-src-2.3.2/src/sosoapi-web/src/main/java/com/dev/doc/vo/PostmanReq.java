package com.dev.doc.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.dev.base.enums.ReqMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
		* <p>Title: Postman请求接口</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年12月1日上午10:42:41</p>
 */
public class PostmanReq implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 参数类型*/
	public static String DATA_MODE_PARAMS = "params";

	/** 接口id*/
	@JsonProperty("id")
	private String id;
	
	/** 集合id*/
	@JsonProperty("collectionId")
	private String collectionId;
	
	/** 接口名称*/
	@JsonProperty("name")
	private String name;
	
	/** 接口描述*/
	@JsonProperty("description")
	private String description;
	
	/** 接口url*/
	@JsonProperty("url")
	private String url;
	
	/** 接口请求方式*/
	@JsonProperty("method")
	private ReqMethod method;
	
	/** 请求头部信息，\n分隔*/
	@JsonProperty("headers")
	private String headers;
	
	/** 参数列表*/
	@JsonProperty("data")
	private List<PostmanReqParam> paramList;
	
	/** 参数模式*/
	@JsonProperty("dataMode")
	private String dataMode = DATA_MODE_PARAMS;
	
	/** 时间戳*/
	@JsonProperty("timestamp")
	private Date timestamp;
	
	/** 时间戳*/
	@JsonProperty("time")
	private Date time;
	
	/** 版本号*/
	@JsonProperty("version")
	private int version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ReqMethod getMethod() {
		return method;
	}

	public void setMethod(ReqMethod method) {
		this.method = method;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public List<PostmanReqParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<PostmanReqParam> paramList) {
		this.paramList = paramList;
	}

	public String getDataMode() {
		return dataMode;
	}

	public void setDataMode(String dataMode) {
		this.dataMode = dataMode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
