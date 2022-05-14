package com.dev.base.vo;

import java.io.Serializable;
import java.util.List;

import com.dev.base.enums.EnableStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
		* <p>Title: 树节点</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月9日上午10:08:55</p>
 */
public class TreeNodeInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 默认根节点父id*/
	public static final Long ROOT_DATA_ID = -1L; 
	
	/**
	 * 弃用节点样式
	 */
	public final static String FONT_CLASS_DEPRECATED = "{\"text-decoration\":\"line-through\"}";
	
	/** 节点id*/
	private String id;
	
	/** 节点父id*/
	@JsonProperty("pId")
	private String parentId;

	/** 节点业务数据id*/
	private Long dataId;
	
	/** 父节点业务数据id*/
	private Long parentDataId;
	
	/** 节点类型*/
	private String type;
	
	/** 父节点类型*/
	private String parentType;
	
	/** 名称*/
	private String name;
	
	/** 展示提示信息*/
	private String title;
	
	/** 是否展开*/
	private Boolean open;
	
	/** 是否是父节点*/
	private Boolean isParent;
	
	/** 节点url*/
	private String url;
	
	/** 同超链接 target 属性: "_blank", "_self" 或 其他指定窗口名称*/
	private String target;
	
	/** 排序权重*/
	private Integer sortWeight;
	
	/** 子节点*/
	private List<TreeNodeInfo> children;
	
	/** 子节点数目*/
	private Integer childrenCount;
	
	/** 是否允许拖拽*/
	private Boolean drag;
	
	/** 是否允许子节点移走*/
	private Boolean childOuter;
	
	/** 是否允许子节点内部排序*/
	private Boolean childOrder;
	
	/** 是否允许外部拖拽成为子节点*/
	private Boolean dropInner;
	
	/** 当前节点最小层级*/
	private Integer minLevel;
	
	/** 当前节点最大层级*/
	private Integer maxLevel;
	
	/** 是否允许新增子节点*/
	private Boolean enableAdd;
	
	/** 是否允许编辑*/
	private Boolean enableEdit;
	
	/** 是否允许删除*/
	private Boolean enableDel;
	
	/** 图标样式*/
	@JsonProperty("iconSkin")
	private String iconClass;
	
	/** 字体样式*/
	@JsonProperty("font")
	private String fontClass;

	/** 节点状态*/
	private EnableStatus status;
	
	/** 是否选中 */
	private Boolean checked;
	
	/** 是否禁用check */
	private Boolean chkDisabled;
	
	/** 其他信息*/
	private Object otherData;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<TreeNodeInfo> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeInfo> children) {
		this.children = children;
	}

	public Boolean getDrag() {
		return drag;
	}

	public void setDrag(Boolean drag) {
		this.drag = drag;
	}

	public Boolean getChildOuter() {
		return childOuter;
	}

	public void setChildOuter(Boolean childOuter) {
		this.childOuter = childOuter;
	}

	public Boolean getChildOrder() {
		return childOrder;
	}

	public void setChildOrder(Boolean childOrder) {
		this.childOrder = childOrder;
	}

	public Boolean getDropInner() {
		return dropInner;
	}

	public void setDropInner(Boolean dropInner) {
		this.dropInner = dropInner;
	}

	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public Boolean getEnableAdd() {
		return enableAdd;
	}

	public void setEnableAdd(Boolean enableAdd) {
		this.enableAdd = enableAdd;
	}

	public Boolean getEnableEdit() {
		return enableEdit;
	}

	public void setEnableEdit(Boolean enableEdit) {
		this.enableEdit = enableEdit;
	}

	public Boolean getEnableDel() {
		return enableDel;
	}

	public void setEnableDel(Boolean enableDel) {
		this.enableDel = enableDel;
	}

	public Integer getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Integer getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(Integer sortWeight) {
		this.sortWeight = sortWeight;
	}

	public Long getParentDataId() {
		return parentDataId;
	}

	public void setParentDataId(Long parentDataId) {
		this.parentDataId = parentDataId;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getFontClass() {
		return fontClass;
	}

	public void setFontClass(String fontClass) {
		this.fontClass = fontClass;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public Object getOtherData() {
		return otherData;
	}

	public void setOtherData(Object otherData) {
		this.otherData = otherData;
	}
}
