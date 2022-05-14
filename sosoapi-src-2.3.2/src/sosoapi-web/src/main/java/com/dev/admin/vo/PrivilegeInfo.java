package com.dev.admin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.PrivScope;
import com.dev.base.enums.PrivilegeType;

/**
 * 
		* <p>Title: 权限</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:35:08</p>
 */
public class PrivilegeInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键*/
	private Long id;
	
	/** 名称 */
	private String name;		
	
	/** 编码 */
	private String code;		
	
	/** url */
	private String url;		
	
	/** 类型 */
	private PrivilegeType type;		
	
	/** 上级id */
	private Long parentId;	
	
	/** 父菜单名称*/
	private String parentName;
	
	/** 父菜单编码*/
	private String parentCode;
	
	/** shiro权限 */
	private String permission;		
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 访问授权 */
	private boolean accessVerify;		
	
	/** 排序权重 */
	private int sortWeight;		
	
	/** 创建时间*/
	private Date createDate;
	
	/** 图标样式*/
	private String iconClass;
	
	/** 适用范围*/
	private PrivScope scope;
	
	/** 扩展图片url*/
	private String imgUrl;
	
	/** 子权限列表*/
	private List<PrivilegeInfo> childList = new ArrayList<PrivilegeInfo>();;

	/** 用于展示左侧菜单编码*/
	private String menuCode;
	
	/** 用于展示左侧菜单父编码*/
	private String menuParentCode;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PrivilegeType getType() {
		return type;
	}

	public void setType(PrivilegeType type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public boolean isAccessVerify() {
		return accessVerify;
	}

	public void setAccessVerify(boolean accessVerify) {
		this.accessVerify = accessVerify;
	}

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public List<PrivilegeInfo> getChildList() {
		return childList;
	}

	public void setChildList(List<PrivilegeInfo> childList) {
		this.childList = childList;
	}

	public int getChildCount() {
		return CollectionUtils.isEmpty(childList) ? 0 : childList.size();
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuParentCode() {
		return menuParentCode;
	}

	public void setMenuParentCode(String menuParentCode) {
		this.menuParentCode = menuParentCode;
	}

	public PrivScope getScope() {
		return scope;
	}

	public void setScope(PrivScope scope) {
		this.scope = scope;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}	
}
