package com.dev.proj.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.dev.admin.vo.PrivilegeInfo;

/**
 * 
		* <p>Title: 项目角色信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月6日上午10:30:42</p>
 */
public class ProjectRoleInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 角色id*/
	private Long id;
	
	/** 所属项目id*/
	private Long projId;
	
	/** 编码 */
	private String code;		
	
	/** 名称 */
	private String name;
	
	/** 主页url */
	private String homeUrl;
	
	/** 权限列表 */
	private List<PrivilegeInfo> privList = new ArrayList<PrivilegeInfo>();
	
	/** 权限授权码集合*/
	private Set<String> privPermSet = new HashSet<String>();

	
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

	public List<PrivilegeInfo> getPrivList() {
		return privList;
	}

	public void setPrivList(List<PrivilegeInfo> privList) {
		this.privList = privList;
	}
	
	public Set<String> getPrivPermSet() {
		return privPermSet;
	}

	public void setPrivPermSet(Set<String> privPermSet) {
		this.privPermSet = privPermSet;
	}

	/**
	 * 
			*@name 设置权限编码
			*@Description  
			*@CreateDate 2017年9月6日上午10:34:43
	 */
	public void setPrivPermSet(List<PrivilegeInfo> privList){
		privPermSet.clear();
		if (CollectionUtils.isEmpty(privList)) {
			return ;
		}
		
		for (PrivilegeInfo temp : privList) {
			privPermSet.add(temp.getPermission());
		}
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
}
