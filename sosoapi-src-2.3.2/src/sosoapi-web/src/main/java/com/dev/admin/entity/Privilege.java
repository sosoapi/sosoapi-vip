package com.dev.admin.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MenuDisPosition;
import com.dev.base.enums.PrivScope;
import com.dev.base.enums.PrivilegeType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 权限</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:35:08</p>
 */
public class Privilege extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 名称 */
	private String name;		
	
	/** 编码 */
	private String code;	
	
	/** 权限适用范围 */
	private PrivScope scope;
	
	/** url */
	private String url;		
	
	/** 类型 */
	private PrivilegeType type;		
	
	/** 上级id */
	private Long parentId;		
	
	/** shiro权限 */
	private String permission;		
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 访问授权 */
	private boolean accessVerify;		
	
	/** 排序权重 */
	private int sortWeight;		
	
	/** 显示位置 */
	private MenuDisPosition disPosition;
	
	/** 图标样式 */
	private String iconClass;
	
	/** 描述*/
	private String description;
	
	/** 扩展图片url*/
	private String imgUrl;

	
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

	public MenuDisPosition getDisPosition() {
		return disPosition;
	}

	public void setDisPosition(MenuDisPosition disPosition) {
		this.disPosition = disPosition;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
