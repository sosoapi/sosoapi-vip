package com.dev.doc.vo;

import java.io.Serializable;
import java.util.List;

import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;

/**
 * 
		* <p>Title: 接口详情</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月28日下午2:32:28</p>
 */
public class InterDetailInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 基本信息*/
	private InterInfo basicInfo;
	
	/** 参数列表*/
	private List<InterParam> paramList;
	
	/** 响应列表*/
	private List<InterResp> respList;

	public InterInfo getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(InterInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	public List<InterParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<InterParam> paramList) {
		this.paramList = paramList;
	}

	public List<InterResp> getRespList() {
		return respList;
	}

	public void setRespList(List<InterResp> respList) {
		this.respList = respList;
	}
}
