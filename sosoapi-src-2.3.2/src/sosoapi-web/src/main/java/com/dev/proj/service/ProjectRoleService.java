package com.dev.proj.service;

import java.util.List;

import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.proj.entity.ProjectRole;

/**
 * 
		* <p>Title: 项目角色相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface ProjectRoleService extends BaseMybatisService<ProjectRole, Long>{
	/**
	 * 
			*@name 查询角色列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<ProjectRole> list(Long projId,String name,String code,EnableStatus status,Pager pager);

	/**
	 * 
			*@name 查询角色总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(Long projId,String name,String code,EnableStatus status);
	
	/**
	 * 
			*@name 查询系统角色列表
			*@Description  
			*@CreateDate 2017年7月1日下午9:07:21
	 */
	List<SelectInfo> listRole(Long projId);
	
	/**
	 * 
			*@name 更新角色权限
			*@Description  
			*@CreateDate 2017年7月3日下午2:53:08
	 */
	void updatePriv(Long roleId,List<Long> privIdList);
	
	/**
	 * 
			*@name 查询角色权限树
			*@Description  用于角色权限设置
			*@CreateDate 2017年7月2日下午5:12:42
	 */
	List<TreeNodeInfo> listTreeByRoleId(Long roleId);
	
	/**
	 * 
			*@name 判断是否存在
			*@Description  
			*@CreateDate 2017年9月4日下午3:45:23
	 */
	boolean isExist(Long projId,Long roleId);
	
	/**
	 * 
			*@name 查询用户在指定项目中的角色信息
			*@Description  
			*@CreateDate 2017年9月4日下午5:18:55
	 */
	ProjectRole getByProjIdAndUserId(Long projId,Long userId);
	
	/**
	 * 
			*@name 处理用户在指定项目中的权限树
			*@Description  用于权限验证
			*@CreateDate 2017年9月5日上午11:48:55
	 */
	List<PrivilegeInfo> listPriv(Long roleId,EnableStatus status,Boolean accessVerify);
}
