package com.dev.monitor.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.MonitorType;

/**
 * 
		* <p>Title: 监控运行日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月15日上午10:55:19</p>
 */
public class MonitorLogInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 日志id*/
	private Long id;

	/** 用户id */
	private Long userId;	
	
	/** 用户邮箱*/
	private String userEmail;
	
	/** 监控id */
	private Long monitorId;		
	
	/** 监控类型 */
	private MonitorType monitorType;		
	
	/** 监控对象 */
	private String sub;		
	
	/** 对象状态 */
	private MonitorStatus subStatus;		
	
	/** 异常概要 */
	private String errorBrief;		
	
	/** 异常详情 */
	private String errorDetail;		
	
	/** 响应内容 */
	private String respContent;		
	
	/** 响应时间，毫秒 */
	private long spendTime;
	
	/** 监控名称*/
	private String monitorName;
	
	/** 创建时间*/
	private Date createDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	public MonitorType getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(MonitorType monitorType) {
		this.monitorType = monitorType;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public MonitorStatus getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(MonitorStatus subStatus) {
		this.subStatus = subStatus;
	}

	public String getErrorBrief() {
		return errorBrief;
	}

	public void setErrorBrief(String errorBrief) {
		this.errorBrief = errorBrief;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public String getRespContent() {
		return respContent;
	}

	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}

	public long getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(long spendTime) {
		this.spendTime = spendTime;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
