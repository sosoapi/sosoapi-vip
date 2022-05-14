package com.dev.proj.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 项目成员信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:34:02</p>
 */
public class ProjectMember extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 项目id */
	private Long projId;		
	
	/** 用户id */
	private Long userId;		
	
	/** 项目角色id */
	private Long projRoleId;		
	
	/** 项目昵称*/
	private String projNickName;

	public void setProjId(Long projId){
		this.projId = projId;
	}
	
	public Long getProjId(){
		return projId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setProjRoleId(Long projRoleId){
		this.projRoleId = projRoleId;
	}
	
	public Long getProjRoleId(){
		return projRoleId;
	}

	public String getProjNickName() {
		return projNickName;
	}

	public void setProjNickName(String projNickName) {
		this.projNickName = projNickName;
	}
}
