package com.dev.doc.exports.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dev.base.utils.DateUtil;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.ApiEnv;
import com.dev.doc.vo.ApiErrorCodeInfo;

/**
 * 
		* <p>Title: 文档导出数据内容</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月16日下午2:28:24</p>
 */
public class DocDataInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 文档信息*/
	private ApiDoc apiDoc;
	
	/** 模块信息*/
	private List<ModuleDataInfo> moduleList = new ArrayList<ModuleDataInfo>();
	
	/** 环境基路径信息*/
	private List<ApiEnv> envList = new ArrayList<ApiEnv>();
	
	/** 错误码信息*/
	private List<ApiErrorCodeInfo> errorCodeList = new ArrayList<ApiErrorCodeInfo>();
	
	/** 公共请求参数信息*/
	private List<ParamDataInfo> commonParamList = new ArrayList<ParamDataInfo>();
	
	/** 创建时间*/
	private String buildDate = DateUtil.formatNowByLong();

	public ApiDoc getApiDoc() {
		return apiDoc;
	}

	public void setApiDoc(ApiDoc apiDoc) {
		this.apiDoc = apiDoc;
	}

	public List<ModuleDataInfo> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<ModuleDataInfo> moduleList) {
		this.moduleList = moduleList;
	}

	public List<ApiEnv> getEnvList() {
		return envList;
	}

	public void setEnvList(List<ApiEnv> envList) {
		this.envList = envList;
	}

	public List<ApiErrorCodeInfo> getErrorCodeList() {
		return errorCodeList;
	}

	public void setErrorCodeList(List<ApiErrorCodeInfo> errorCodeList) {
		this.errorCodeList = errorCodeList;
	}

	public List<ParamDataInfo> getCommonParamList() {
		return commonParamList;
	}

	public void setCommonParamList(List<ParamDataInfo> commonParamList) {
		this.commonParamList = commonParamList;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
}
