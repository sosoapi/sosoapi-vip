package com.dev.user.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 用户信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日下午5:05:47</p>
 */
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 用户id*/
	private Long userId;

	/** 邮箱*/
	private String email;
	
	/** 昵称*/
	private String nickName;
	
	/** 头像*/
	private String headUrl;
	
	/** 登陆token*/
	private String token;
	
	/** 是否验证 */
	private boolean valid;	
	
	/** 是否锁定 */
	private boolean locked;	
	
	/** 角色id */
	private Long roleId;
	
	/** 角色名称 */
	private String roleName;
	
	/** 注册时间*/
	private Date registDate;
	
	/** 新消息数目*/
	private int newMsgCount;
	
	/** 主页url */
	private String homeUrl;
	
	/** 消费总金额*/
	private int feeAmount;
	
	/** 已开发票金额*/
	private int invoiceAmount;

	/** 项目角色信息,{key:projId,value:projRoleId}*/
	private Map<String, Long> projRoleMap = MapUtils.newMap();
	
	/** 文档角色信息,{key:docId,value:projRoleId}*/
	private Map<String, Long> docRoleMap = MapUtils.newMap();
	
	/** 书籍角色信息,{key:bookId,value:projRoleId}*/
	private Map<String, Long> bookRoleMap = MapUtils.newMap();
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public int getNewMsgCount() {
		return newMsgCount;
	}

	public void setNewMsgCount(int newMsgCount) {
		this.newMsgCount = newMsgCount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public Map<String, Long> getProjRoleMap() {
		return projRoleMap;
	}

	public void setProjRoleMap(Map<String, Long> projRoleMap) {
		this.projRoleMap = projRoleMap;
	}

	public Map<String, Long> getDocRoleMap() {
		return docRoleMap;
	}

	public void setDocRoleMap(Map<String, Long> docRoleMap) {
		this.docRoleMap = docRoleMap;
	}

	public int getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(int feeAmount) {
		this.feeAmount = feeAmount;
	}

	public int getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(int invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Map<String, Long> getBookRoleMap() {
		return bookRoleMap;
	}

	public void setBookRoleMap(Map<String, Long> bookRoleMap) {
		this.bookRoleMap = bookRoleMap;
	}
}
