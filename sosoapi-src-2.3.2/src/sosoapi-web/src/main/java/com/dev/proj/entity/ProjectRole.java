package com.dev.proj.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 项目角色信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:34:32</p>
 */
public class ProjectRole extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 项目id,若为空则适用于所有项目 */
	private Long projId;
	
	/** 编码 */
	private String code;		
	
	/** 名称 */
	private String name;		
	
	/** 角色描述 */
	private String description;		
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 排序权重 */
	private int sortWeight;	
	
	/** 主页url */
	private String homeUrl;
	
	/** 禁止删除*/
	private boolean delDisabled;
	

	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public boolean isDelDisabled() {
		return delDisabled;
	}

	public void setDelDisabled(boolean delDisabled) {
		this.delDisabled = delDisabled;
	}
}
