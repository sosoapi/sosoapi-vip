package com.dev.admin.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.MsgType;

/**
 * 
		* <p>Title: 系统消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:25:03</p>
 */
public class SysMsgInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long id;
	
	/** 发布时间 */
	private Date pubDate;		
	
	/** 标题 */
	private String title;		
	
	/** 内容 */
	private String content;		
	
	/** 消息类型 */
	private MsgType msgType;		
	
	/** 接收消息角色id */
	private Long roleId;		
	
	/** 接收消息角色名称 */
	private String roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
