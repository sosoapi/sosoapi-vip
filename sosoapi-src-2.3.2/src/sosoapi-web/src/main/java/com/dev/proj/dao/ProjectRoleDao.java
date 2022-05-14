package com.dev.proj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.proj.entity.ProjectRole;

/**
 * 
		* <p>Title: 项目角色信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:29:34</p>
 */
public interface ProjectRoleDao extends BaseMybatisDao<ProjectRole,Long> {
	/**
	 * 
			*@name 查询角色列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<ProjectRole> list(@Param("projId")Long projId,@Param("name")String name,
							@Param("code")String code,@Param("status")EnableStatus status,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询角色总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(@Param("projId")Long projId,@Param("name")String name,
				@Param("code")String code,@Param("status")EnableStatus status);
	
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
	
	/**
	 * 
			*@name 查询指定项目相关角色列表
			*@Description  
			*@CreateDate 2017年9月3日下午10:14:38
	 */
	List<ProjectRole> listByProjId(@Param("projId")Long projId);
	
	/**
	 * 
			*@name 查询角色权限树
			*@Description  用于角色权限设置
			*@CreateDate 2017年7月2日下午5:12:42
	 */
	List<Map> listTreeByRoleId(@Param("roleId")Long roleId);
	
	/**
	 * 
			*@name 查询角色
			*@Description  
			*@CreateDate 2017年9月4日下午3:43:38
	 */
	int countByProjId(@Param("projId")Long projId,@Param("roleId")Long roleId);
	
	/**
	 * 
			*@name 查询用户在指定项目中的角色信息
			*@Description  
			*@CreateDate 2017年9月4日下午5:18:55
	 */
	ProjectRole getByProjIdAndUserId(@Param("projId")Long projId,@Param("userId")Long userId);
	
	/**
	 * 
			*@name 查询角色关联权限列表
			*@Description  用于权限验证
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listPriv(@Param("roleId")Long roleId,@Param("status")EnableStatus status,@Param("accessVerify")Boolean accessVerify);
	
}
