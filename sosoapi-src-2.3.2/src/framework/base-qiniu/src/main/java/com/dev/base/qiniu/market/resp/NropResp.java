package com.dev.base.qiniu.market.resp;

import java.util.List;

/**
 * 
		* <p>Title: 鉴黄服务结果</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午5:02:46</p>
 */
public class NropResp {
	/** 处理状态*/
	/* 0：调用成功; 
	 * 1：授权失败； 
	 * 2：模型ID错误； 
	 * 3：没有上传文件； 
	 * 4：API版本号错误； 
	 * 5：API版本已弃用； 
	 * 6：secretId 错误； 
	 * 7：任务Id错误，您的secretId不能调用该任务； 
	 * 8：secretId状态异常； 
	 * 9：尚未上传证书； 
	 * 100：服务器错误； 
	 * 101：未知错误*/
	private String code;
	
	/** 与code对应的状态描述信息*/
	private String message;
	
	/** 鉴别的图片文件列表*/
	private List<NropFileInfo> fileList;
	
	/** 图像机器判定结果统计数组，分别对应判定为0-3的数据总量*/
	private List<Integer> statistic;
	
	/** 调用的唯一标识符，用于后续的人工复审的结果反馈*/
	private String callRecordId;
	
	/** API的版本*/
	private String APIVersion;
	
	/** 需要人工复审的图片数量*/
	private int reviewCount;
	
	/** 随机数*/
	private String nonce;
	
	/** 当前的服务器的Unix时间，UTC格式时间字符串*/
	private String timestamp;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<NropFileInfo> getFileList() {
		return fileList;
	}

	public void setFileList(List<NropFileInfo> fileList) {
		this.fileList = fileList;
	}

	public List<Integer> getStatistic() {
		return statistic;
	}

	public void setStatistic(List<Integer> statistic) {
		this.statistic = statistic;
	}

	public String getCallRecordId() {
		return callRecordId;
	}

	public void setCallRecordId(String callRecordId) {
		this.callRecordId = callRecordId;
	}

	public String getAPIVersion() {
		return APIVersion;
	}

	public void setAPIVersion(String aPIVersion) {
		APIVersion = aPIVersion;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
