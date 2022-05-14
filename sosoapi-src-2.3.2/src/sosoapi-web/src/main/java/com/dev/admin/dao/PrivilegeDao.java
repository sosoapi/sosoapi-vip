package com.dev.admin.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.dev.admin.entity.Privilege;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.PrivilegeType;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 权限服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:36:59</p>
 */
public interface PrivilegeDao extends BaseMybatisDao<Privilege,Long> {
	/**
	 * 
			*@name 查询权限列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> list(@Param("name")String name,@Param("url")String url,
							@Param("type")PrivilegeType type,@Param("status")EnableStatus status,
							@Param("accessVerify")Boolean accessVerify,@Param("parentId")Long parentId,
							@Param("scope")String scope,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询权限总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(@Param("name")String name,@Param("url")String url,
						@Param("type")PrivilegeType type,@Param("status")EnableStatus status,
						@Param("accessVerify")Boolean accessVerify,@Param("parentId")Long parentId,@Param("scope")String scope);
	
	/**
	 * 
			*@name 查询权限列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Privilege> listAll(@Param("type")PrivilegeType type,@Param("status")EnableStatus status,
								@Param("accessVerify")Boolean accessVerify,@Param("parentId")Long parentId,@Param("scope")String scope);
	
	/**
	 * 
			*@name 查询角色关联权限列表
			*@Description  用于权限验证
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listByRoleId(@Param("roleId")Long roleId,@Param("status")EnableStatus status,@Param("accessVerify")Boolean accessVerify);
	
	/**
	 * 
			*@name 查询角色对应的权限编码
			*@Description  
			*@CreateDate 2017年7月4日下午3:19:36
	 */
	Set<String> listPermByRoleId(@Param("roleId")Long roleId);
	
	/**
	 * 
			*@name 查询父级菜单包括菜单组和菜单
			*@Description  
			*@CreateDate 2017年6月30日下午5:46:46
	 */
	List<Privilege> listParent(@Param("status")EnableStatus status,@Param("scope")String scope);
	
	/**
	 * 
			*@name 查询角色权限树
			*@Description  用于角色权限设置
			*@CreateDate 2017年7月2日下午5:12:42
	 */
	List<Map> listTreeByRoleId(@Param("roleId")Long roleId);
}
