package com.dev.proj.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.proj.entity.ProjectMember;
import com.dev.proj.vo.ProjectInfo;
import com.dev.proj.vo.ProjectMemberInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目成员相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface ProjectMemberService extends BaseMybatisService<ProjectMember, Long>{
	/**
	 * 
			*@name 查询项目成员列表
			*@Description  
			*@CreateDate 2015年8月7日下午6:18:16
	 */
	List<ProjectMemberInfo> listByProjId(Long projId,Long roleId,
							String nickName,String email,Pager pager);
	
	/**
	 * 
			*@name 查询项目成员总数
			*@Description  
			*@CreateDate 2015年8月7日下午6:19:18
	 */
	int countByProjId(Long projId,Long roleId,String nickName,String email);
	
	/**
	 * 
			*@name 邀请加入项目
			*@Description  
			*@CreateDate 2015年8月22日下午1:38:31
	 */
	void invite(Long userId,Long projId,String invitedEmail);
	
	/**
	 * 
			*@name 加入项目
			*@Description  
			*@CreateDate 2015年8月22日下午2:08:51
	 */
	Long accept(Long userId,String code,String projNickName);
	
	/**
	 * 
			*@name 加入指定项目
			*@Description  
			*@CreateDate 2015年10月8日上午2:25:22
	 */
	void accept(Long userId,Long projId,Long roleId,String projNickName);
	
	/**
	 * 
			*@name 移除项目成员
			*@Description  
			*@CreateDate 2015年8月22日下午2:30:18
	 */
	void remove(Long currentId,Long userId,Long projId);
	
	/**
	 * 
			*@name 退出项目
			*@Description  
			*@CreateDate 2015年12月20日下午8:41:24
	 */
	void quit(Long userId,Long projId);
	
	/**
	 * 
			*@name 获取用户有权限的项目列表
			*@Description  
			*@CreateDate 2015年8月29日下午3:11:35
	 */
	List<ProjectInfo> listAuthProjectInfo(Long userId);
	
	/**
	 * 
			*@name 编辑用户角色
			*@Description  
			*@CreateDate 2015年9月8日下午10:59:00
	 */
	void updateRole(Long currentId,Long projId,Long userId,Long roleId,String projNickName);
	
	/**
	 * 
			*@name 获取成员信息
			*@Description  
			*@CreateDate 2015年9月8日下午11:16:44
	 */
	ProjectMemberInfo getByUserIdAndProjId(Long userId,Long projId);
	
	/**
	 * 
			*@name 获取成员邮件列表
			*@Description  
			*@CreateDate 2015年10月21日下午10:03:51
	 */
	List<String> listEmail(Long projId,Long roleId);
	
	/**
	 * 
			*@name 向团队成员发送变更通知
			*@Description  
			*@CreateDate 2015年10月21日下午10:12:34
	 */
	void sendNotice(UserInfo userInfo,Long projId,
					String receiverRole,String otherReceivers,
					String title,String content);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员列表
			*@Description  被选中的用户必须与当前用户曾经在同一项目中
			*@CreateDate 2015年11月28日下午5:04:42
	 */
	List<ProjectMemberInfo> listForAdd(Long userId,Long projId,Pager pager);
	
	/**
	 * 
			*@name 根据邮箱查找账号
			*@Description  
			*@CreateDate 2017年9月11日上午10:52:15
	 */
	List<ProjectMemberInfo> searchForAdd(Long projId,String email,boolean strictMatch,Pager pager);
	
	/**
	 * 
			*@name 获取邮箱查找记录数
			*@Description  
			*@CreateDate 2017年9月12日上午11:46:52
	 */
	int countForSearchForAdd(Long projId,String email,boolean strictMatch);
	
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员总数
			*@Description  被选中的用户必须与当前用户曾经在同一项目中
			*@CreateDate 2015年11月28日下午5:05:28
	 */
	int countForAdd(Long userId,Long projId);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员列表
			*@Description  被选中的用户为踢出已在当前项目中的所有已注册用户
			*@CreateDate 2017年7月25日上午9:49:21
	 */
	List<ProjectMemberInfo> listAllForAdd(Long projId,Pager pager);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的成员列表
			*@Description  被选中的用户为踢出已在当前项目中的所有已注册用户
			*@CreateDate 2017年7月25日上午9:49:21
	 */
	int countAllForAdd(Long projId);
	
	/**
	 * 
			*@name 管理员直接添加项目成员
			*@Description  
			*@CreateDate 2015年11月28日下午5:35:19
	 */
	void add(Long inviterId,Long userId,Long projId,Long roleId,String projNickName);
	
	/**
	 * 
			*@name 查询用户和文档的关联关系
			*@Description  
			*@CreateDate 2016年12月1日下午1:35:37
	 */
	int countByUserIdAndDocId(Long userId,Long docId);
}
