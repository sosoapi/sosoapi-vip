package com.dev.monitor.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 警报接收者</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:56:37</p>
 */
public class AlarmReceiver extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 创建者id */
	private Long userId;		
	
	/** 是否系统用户 */
	private boolean sysFlag;		
	
	/** 系统用户id */
	private Long sysUserId;		
	
	/** 姓名 */
	private String name;		
	
	/** 手机号 */
	private String phone;		
	
	/** 邮箱 */
	private String email;		
	
	/** 手机号是否验证 */
	private boolean phoneValid;		
	
	/** 邮箱是否验证 */
	private boolean emailValid;		
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(boolean sysFlag) {
		this.sysFlag = sysFlag;
	}

	public Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPhoneValid() {
		return phoneValid;
	}

	public void setPhoneValid(boolean phoneValid) {
		this.phoneValid = phoneValid;
	}

	public boolean isEmailValid() {
		return emailValid;
	}

	public void setEmailValid(boolean emailValid) {
		this.emailValid = emailValid;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}		
}
