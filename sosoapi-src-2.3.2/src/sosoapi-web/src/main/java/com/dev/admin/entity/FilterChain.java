package com.dev.admin.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.FilterChainPosition;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: shiro过滤链</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:30:26</p>
 */
public class FilterChain extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 名称 */
	private String name;		
	
	/** url */
	private String url;		
	
	/** 过滤链 */
	private String filter;		
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 排序权重 */
	private int sortWeight;	
	
	/** 描述*/
	private String description;
	
	/** 加入位置*/
	private FilterChainPosition position;
	

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setFilter(String filter){
		this.filter = filter;
	}
	
	public String getFilter(){
		return filter;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FilterChainPosition getPosition() {
		return position;
	}

	public void setPosition(FilterChainPosition position) {
		this.position = position;
	}
}
