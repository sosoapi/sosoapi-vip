package com.dev.admin.service;

import java.util.List;

import com.dev.admin.entity.Role;
import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;

/**
 * 
		* <p>Title: 角色服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:37:25</p>
 */
public interface RoleService extends BaseMybatisService<Role, Long>{
	/**
	 * 
			*@name 查询用户角色
			*@Description  
			*@CreateDate 2017年6月21日下午11:10:52
	 */
	Role getByUserId(Long userId);
	
	/**
	 * 
			*@name 查询角色列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Role> list(String name,String code,EnableStatus status,Pager pager);

	/**
	 * 
			*@name 查询角色总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(String name,String code,EnableStatus status);
	
	/**
	 * 
			*@name 查询系统角色列表
			*@Description  
			*@CreateDate 2017年7月1日下午9:07:21
	 */
	List<SelectInfo> listRole(EnableStatus status);
	
	/**
	 * 
			*@name 更新角色权限
			*@Description  
			*@CreateDate 2017年7月3日下午2:53:08
	 */
	void updatePriv(Long roleId,List<Long> privIdList);
}
