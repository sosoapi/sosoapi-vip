package com.dev.doc.exports.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dev.base.enums.SchemaType;
import com.dev.doc.vo.SchemaNodeInfo;

/**
 * 
		* <p>Title: 响应信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月17日下午3:37:33</p>
 */
public class RespDataInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long id;		
	
	/** 编码 */
	private String code;		
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 结构 */
	private String custSchema;		
	
	/** 类型 */
	private SchemaType type;		
	
	/** 响应数据结构id */
	private Long refSchemaId;	
	
	/** 响应数据结构code*/
	private String refSchemaCode;

	/** 扩展自定义结构体,仅当type为cust_开头*/
	private String extSchema;
	
	/** 是否是默认 */
	private boolean def;
	
	/** 自定义结构节点树*/
	private SchemaNodeInfo custSchemaTree;
	
	/** 当前结构子节点列表*/
	private List<SchemaNodeInfo> childList = new ArrayList<SchemaNodeInfo>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getCustSchema() {
		return custSchema;
	}

	public void setCustSchema(String custSchema) {
		this.custSchema = custSchema;
	}

	public SchemaType getType() {
		return type;
	}

	public void setType(SchemaType type) {
		this.type = type;
	}

	public Long getRefSchemaId() {
		return refSchemaId;
	}

	public void setRefSchemaId(Long refSchemaId) {
		this.refSchemaId = refSchemaId;
	}

	public String getRefSchemaCode() {
		return refSchemaCode;
	}

	public void setRefSchemaCode(String refSchemaCode) {
		this.refSchemaCode = refSchemaCode;
	}

	public String getExtSchema() {
		return extSchema;
	}

	public void setExtSchema(String extSchema) {
		this.extSchema = extSchema;
	}

	public boolean isDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
	}

	public SchemaNodeInfo getCustSchemaTree() {
		return custSchemaTree;
	}

	public void setCustSchemaTree(SchemaNodeInfo custSchemaTree) {
		this.custSchemaTree = custSchemaTree;
	}

	public List<SchemaNodeInfo> getChildList() {
		return childList;
	}

	public void setChildList(List<SchemaNodeInfo> childList) {
		this.childList = childList;
	}
}
