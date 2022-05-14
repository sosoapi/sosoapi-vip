package com.dev.monitor.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 警报接收组</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:48:46</p>
 */
public class AlarmGroup extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;

	/** 创建者id */
	private Long userId;		
	
	/** 名称 */
	private String name;		
	
	/** 邮件报警 */
	private boolean emailAlarm;		
	
	/** 短信报警 */
	private boolean phoneAlarm;		
	
	/** 接收报警开始时间 */
	private String startTime;		
	
	/** 接收报警结束时间 */
	private String endTime;		
	
	/** 备注 */
	private String description;		
	
	/** 状态 */
	private EnableStatus status;		
	

	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isEmailAlarm() {
		return emailAlarm;
	}

	public void setEmailAlarm(boolean emailAlarm) {
		this.emailAlarm = emailAlarm;
	}

	public boolean isPhoneAlarm() {
		return phoneAlarm;
	}

	public void setPhoneAlarm(boolean phoneAlarm) {
		this.phoneAlarm = phoneAlarm;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	
	public String getStartTime(){
		return startTime;
	}
	
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}

	public EnableStatus getStatus() {
		return status;
	}
}
