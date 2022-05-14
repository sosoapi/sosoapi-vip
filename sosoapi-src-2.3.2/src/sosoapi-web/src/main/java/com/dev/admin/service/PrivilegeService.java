package com.dev.admin.service;

import java.util.List;
import java.util.Set;

import com.dev.admin.entity.Privilege;
import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.PrivScope;
import com.dev.base.enums.PrivilegeType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: 权限服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:36:59</p>
 */
public interface PrivilegeService extends BaseMybatisService<Privilege, Long>{
	/**
	 * 
			*@name 查询权限列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<PrivilegeInfo> list(String name,String url,PrivilegeType type,
							EnableStatus status,Boolean accessVerify,Long parentId,PrivScope scope,Pager pager);

	/**
	 * 
			*@name 查询权限列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Privilege> listAll(PrivilegeType type,EnableStatus status,Boolean accessVerify,Long parentId,PrivScope scope);
	
	/**
	 * 
			*@name 查询权限总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(String name,String url,PrivilegeType type,
						EnableStatus status,Boolean accessVerify,Long parentId,PrivScope scope);
	
	/**
	 * 
			*@name 查询角色关联权限列表
			*@Description  用于权限验证
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<PrivilegeInfo> listByRoleId(Long roleId,EnableStatus status,Boolean accessVerify);
	
	/**
	 * 
			*@name 查询角色对应的权限编码
			*@Description  
			*@CreateDate 2017年7月4日下午3:19:36
	 */
	Set<String> listPermByRoleId(Long roleId);
	
	/**
	 * 
			*@name 查询父级菜单包括菜单组和菜单
			*@Description  
			*@CreateDate 2017年6月30日下午5:46:46
	 */
	List<Privilege> listParent(EnableStatus status,PrivScope scope);
	
	/**
	 * 
			*@name 查询父级菜单包括菜单组
			*@Description  
			*@CreateDate 2017年7月1日下午9:03:52
	 */
	List<SelectInfo> listParentPriv(PrivScope scope);
	
	/**
	 * 
			*@name 查询角色权限树
			*@Description  用于角色权限设置
			*@CreateDate 2017年7月2日下午5:12:42
	 */
	List<TreeNodeInfo> listTreeByRoleId(Long roleId);
	
	/**
	 * 
			*@name 根据角色id创建菜单树
			*@Description  
			*@CreateDate 2017年7月4日下午3:46:09
	 */
	List<PrivilegeInfo> buildMenuTree(Long roleId);
}
