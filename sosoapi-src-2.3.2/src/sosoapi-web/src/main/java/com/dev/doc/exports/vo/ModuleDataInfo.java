package com.dev.doc.exports.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dev.doc.entity.Module;

/**
 * 
		* <p>Title: 模块数据信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月16日下午2:29:50</p>
 */
public class ModuleDataInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 模块信息*/
	private Module module;

	/** 接口列表*/
	private List<InterDataInfo> interList = new ArrayList<InterDataInfo>();
	
	/** 公共数据结构信息*/
	private List<RespDataInfo> respSchemaList = new ArrayList<RespDataInfo>();

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<InterDataInfo> getInterList() {
		return interList;
	}

	public void setInterList(List<InterDataInfo> interList) {
		this.interList = interList;
	}

	public List<RespDataInfo> getRespSchemaList() {
		return respSchemaList;
	}

	public void setRespSchemaList(List<RespDataInfo> respSchemaList) {
		this.respSchemaList = respSchemaList;
	}
}
