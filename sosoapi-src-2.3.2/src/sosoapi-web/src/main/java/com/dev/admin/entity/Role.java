package com.dev.admin.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 角色 </p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:35:40</p>
 */
public class Role extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 名称 */
	private String name;		
	
	/** 编码 */
	private String code;		
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 排序权重 */
	private int sortWeight;		
	
	/** 主页url */
	private String homeUrl;
	
	/** 禁止删除*/
	private boolean delDisabled;
	
	/** 描述*/
	private String description;

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public void setSortWeight(int sortWeight){
		this.sortWeight = sortWeight;
	}
	
	public int getSortWeight(){
		return sortWeight;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
