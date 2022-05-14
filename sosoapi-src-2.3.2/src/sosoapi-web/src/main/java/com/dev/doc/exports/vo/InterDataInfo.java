package com.dev.doc.exports.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dev.doc.entity.Inter;
import com.dev.doc.vo.SchemaNodeInfo;

/**
 * 
		* <p>Title: 标题（要求能简洁地表达出类的功能和职责）</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月16日下午2:38:24</p>
 */
public class InterDataInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 接口信息*/
	private Inter inter;
	
	/** 请求参数列表*/
	private List<ParamDataInfo> paramList = new ArrayList<ParamDataInfo>();
	
	/** 请求响应列表*/
	private List<RespDataInfo> respList = new ArrayList<RespDataInfo>();

	/** 用于存储当前接口中请求参数的自定义类型*/
	private Map<String, String> paramExtSchemaMap = new LinkedHashMap<String,String>();

	/** 用于存储当前接口中请求响应的自定义类型*/
	private Map<String, String> respExtSchemaMap = new LinkedHashMap<String,String>();
	
	/** 用于存储关联复合对象集合，页面渲染为表格，一种响应对应一个key，value为该响应对应的属性列表，以空格组织树状层级*/
	private Map<String, List<SchemaNodeInfo>> respCustComplexSchemaMap = new LinkedHashMap<String,List<SchemaNodeInfo>>();

	public Inter getInter() {
		return inter;
	}

	public void setInter(Inter inter) {
		this.inter = inter;
	}

	public List<ParamDataInfo> getParamList() {
		return paramList;
	}

	public void setParamList(List<ParamDataInfo> paramList) {
		this.paramList = paramList;
	}

	public List<RespDataInfo> getRespList() {
		return respList;
	}

	public void setRespList(List<RespDataInfo> respList) {
		this.respList = respList;
	}

	public Map<String, String> getParamExtSchemaMap() {
		return paramExtSchemaMap;
	}

	public void setParamExtSchemaMap(Map<String, String> paramExtSchemaMap) {
		this.paramExtSchemaMap = paramExtSchemaMap;
	}

	public Map<String, String> getRespExtSchemaMap() {
		return respExtSchemaMap;
	}

	public void setRespExtSchemaMap(Map<String, String> respExtSchemaMap) {
		this.respExtSchemaMap = respExtSchemaMap;
	}

	public Map<String, List<SchemaNodeInfo>> getRespCustComplexSchemaMap() {
		return respCustComplexSchemaMap;
	}

	public void setRespCustComplexSchemaMap(Map<String, List<SchemaNodeInfo>> respCustComplexSchemaMap) {
		this.respCustComplexSchemaMap = respCustComplexSchemaMap;
	}
}
