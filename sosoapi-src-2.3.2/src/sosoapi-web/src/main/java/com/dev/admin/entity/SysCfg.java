package com.dev.admin.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.SysCfgType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 系统参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月17日上午10:30:27</p>
 */
public class SysCfg extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 名称*/
	private String name;
	
	/** 编码 */
	private String code;		
	
	/** 值 */
	private String value;	
	
	/** 属性默认值*/
	private String defVal;
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 父节点 */
	private Long parentId;		
	
	/** 排序权重 */
	private int sortWeight;		
	
	/** 类型 */
	private SysCfgType type;	
	
	/** 描述信息*/
	private String description;
	

	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return parentId;
	}
	
	public void setSortWeight(int sortWeight){
		this.sortWeight = sortWeight;
	}
	
	public int getSortWeight(){
		return sortWeight;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public SysCfgType getType() {
		return type;
	}

	public void setType(SysCfgType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefVal() {
		return defVal;
	}

	public void setDefVal(String defVal) {
		this.defVal = defVal;
	}
}
