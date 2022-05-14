package com.dev.monitor.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorStatus;

/**
 * 
		* <p>Title: http监控</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:57:30</p>
 */
public class HttpMonitorInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 监控id*/
	private Long id;
	
	/** 用户id */
	private Long userId;
	
	/** 用户邮箱*/
	private String userEmail;
	
	/** 状态 */
	private EnableStatus status;		
	
	/** 名称 */
	private String name;		
	
	/** 监控url */
	private String sub;		
	
	/** 监控对象状态 */
	private MonitorStatus subStatus;	
	
	/** 监控频率，单位分 */
	private int rate;
	
	/** 创建时间*/
	private Date createDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}		
}
