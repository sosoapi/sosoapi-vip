package com.dev.monitor.entity;

import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.MonitorType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 警报日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:52:52</p>
 */
public class AlarmLog extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
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
	
	/** 警报组 */
	private Long alarmGroupId;		
	
	/** 运行日志id*/
	private Long monitorLogId;

	/** 预警人员信息 */
	private String receiverInfo;
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setMonitorId(Long monitorId){
		this.monitorId = monitorId;
	}
	
	public Long getMonitorId(){
		return monitorId;
	}
	
	public void setSub(String sub){
		this.sub = sub;
	}
	
	public String getSub(){
		return sub;
	}
	
	public void setErrorBrief(String errorBrief){
		this.errorBrief = errorBrief;
	}
	
	public String getErrorBrief(){
		return errorBrief;
	}
	
	public void setErrorDetail(String errorDetail){
		this.errorDetail = errorDetail;
	}
	
	public String getErrorDetail(){
		return errorDetail;
	}
	
	public void setRespContent(String respContent){
		this.respContent = respContent;
	}
	
	public String getRespContent(){
		return respContent;
	}
	
	public long getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(long spendTime) {
		this.spendTime = spendTime;
	}

	public void setAlarmGroupId(Long alarmGroupId){
		this.alarmGroupId = alarmGroupId;
	}
	
	public Long getAlarmGroupId(){
		return alarmGroupId;
	}

	public MonitorType getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(MonitorType monitorType) {
		this.monitorType = monitorType;
	}

	public MonitorStatus getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(MonitorStatus subStatus) {
		this.subStatus = subStatus;
	}

	public Long getMonitorLogId() {
		return monitorLogId;
	}

	public void setMonitorLogId(Long monitorLogId) {
		this.monitorLogId = monitorLogId;
	}

	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}
}
