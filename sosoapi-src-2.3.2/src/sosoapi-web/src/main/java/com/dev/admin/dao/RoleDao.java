package com.dev.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.admin.entity.Role;
import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 角色服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:37:25</p>
 */
public interface RoleDao extends BaseMybatisDao<Role,Long> {
	/**
	 * 
			*@name 查询用户角色
			*@Description  
			*@CreateDate 2017年6月21日下午11:10:52
	 */
	Role getByUserId(@Param("userId")Long userId);
	
	/**
	 * 
			*@name 查询角色列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Role> list(@Param("name")String name,@Param("code")String code,@Param("status")EnableStatus status,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询角色总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(@Param("name")String name,@Param("code")String code,@Param("status")EnableStatus status);
	
	/**
	 * 
			*@name 删除全部权限
			*@Description  
			*@CreateDate 2017年7月3日下午2:42:07
	 */
	void batchDelPriv(@Param("roleId")Long roleId);
	
	/**
	 * 
			*@name 批量新增权限
			*@Description  
			*@CreateDate 2017年7月3日下午2:49:54
	 */
	void batchAddPriv(@Param("roleId")Long roleId,@Param("idList")List<Long> idList);
}
