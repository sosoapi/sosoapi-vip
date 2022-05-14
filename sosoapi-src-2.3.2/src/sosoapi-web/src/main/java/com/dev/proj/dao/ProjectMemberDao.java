package com.dev.proj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.proj.entity.ProjectMember;

/**
 * 
		* <p>Title: 项目成员信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:53</p>
 */
public interface ProjectMemberDao extends BaseMybatisDao<ProjectMember,Long> {
	/**
	 * 
			*@name 根据用户id和项目id查询相关记录
			*@Description  
			*@CreateDate 2015年8月7日下午2:21:37
	 */
	int countRecord(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 查询项目相关成员
			*@Description  
			*@CreateDate 2015年8月7日下午6:06:44
	 */
	List<Map> listByProjId(@Param("projId")Long projId,@Param("roleId")Long roleId,
							@Param("nickName")String nickName,@Param("email")String email,
							@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询项目相关成员列表
			*@Description  
			*@CreateDate 2015年8月7日下午6:06:53
	 */
	int countByProjId(@Param("projId")Long projId,@Param("roleId")Long roleId,
						@Param("nickName")String nickName,@Param("email")String email);
	
	/**
	 * 
			*@name 删除项目成员
			*@Description  
			*@CreateDate 2015年8月22日下午2:28:11
	 */
	void delByUserIdAndProjId(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 获取用户有权限的项目列表
			*@Description  
			*@CreateDate 2015年8月29日下午3:11:35
	 */
	List<Map> listAuthProjectInfo(Long userId);
	
	/**
	 * 
			*@name 编辑用户角色
			*@Description  
			*@CreateDate 2015年9月8日下午10:59:00
	 */
	void updateRole(@Param("projId")Long projId,@Param("userId")Long userId,
			@Param("roleId")Long roleId,@Param("projNickName")String projNickName);
	
	/**
	 * 
			*@name 获取成员信息
			*@Description  
			*@CreateDate 2015年9月8日下午11:16:44
	 */
	Map getByUserIdAndProjId(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 查询具有指定角色的用户id列表
			*@Description  
			*@CreateDate 2015年9月28日下午9:55:01
	 */
	List<Long> listUserIdByRoleId(@Param("projId")Long projId,@Param("roleId")Long roleId);
	
	/**
	 * 
			*@name 获取成员邮件列表
			*@Description  
			*@CreateDate 2015年10月21日下午10:03:51
	 */
	List<String> listEmail(@Param("projId")Long projId,@Param("roleId")Long roleId);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员列表
			*@Description  被选中的用户必须与当前用户曾经在同一项目中
			*@CreateDate 2015年11月28日下午5:04:42
	 */
	List<Map> listForAdd(@Param("userId")Long userId,@Param("projId")Long projId,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员总数
			*@Description  被选中的用户必须与当前用户曾经在同一项目中
			*@CreateDate 2015年11月28日下午5:05:28
	 */
	int countForAdd(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 根据邮箱查找账号
			*@Description  
			*@CreateDate 2017年9月11日上午10:52:15
	 */
	List<Map> searchForAdd(@Param("projId")Long projId,@Param("email")String email,
							@Param("strictMatch")boolean strictMatch,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 获取邮箱查找记录数
			*@Description  
			*@CreateDate 2017年9月12日上午11:46:52
	 */
	int countForSearchForAdd(@Param("projId")Long projId,@Param("email")String email,@Param("strictMatch")boolean strictMatch);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员列表
			*@Description  被选中的用户为踢出已在当前项目中的所有已注册用户
			*@CreateDate 2017年7月25日上午9:49:21
	 */
	List<Map> listAllForAdd(@Param("projId")Long projId,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员列表
			*@Description  被选中的用户为踢出已在当前项目中的所有已注册用户
			*@CreateDate 2017年7月25日上午9:49:21
	 */
	int countAllForAdd(@Param("projId")Long projId);
	
	/**
	 * 
			*@name 查询用户和文档的关联关系
			*@Description  
			*@CreateDate 2016年12月1日下午1:35:37
	 */
	int countByUserIdAndDocId(@Param("userId")Long userId,@Param("docId")Long docId);
}
