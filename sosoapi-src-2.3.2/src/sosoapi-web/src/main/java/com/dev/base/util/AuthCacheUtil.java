package com.dev.base.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.SpringContextUtils;
import com.dev.book.service.BookService;
import com.dev.book.vo.BookInfo;
import com.dev.proj.entity.ProjectRole;
import com.dev.proj.service.ProjectRoleService;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;
import com.dev.proj.vo.ProjectRoleInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目相关授权信息缓存工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年12月6日下午2:38:41</p>
 */
public class AuthCacheUtil {
	private static ProjectService projectService;
	
	private static ProjectRoleService projectRoleService;
	
	private static BookService bookService;
	
	/**
	 * 
			*@name 验证权限
			*@Description  
			*@CreateDate 2017年12月6日下午3:26:25
	 */
	public static boolean authPriv(HttpSession session,Long projRoleId,String reqPermission){
		if (projRoleId == null) {//无角色，非法访问
			return false;
		}
		
		if (StringUtils.isEmpty(reqPermission)) {//无需权限验证
			return true;
		}
		
		ProjectRoleInfo projRoleInfo = getProjectRoleInfo(session, projRoleId);
		return projRoleInfo == null ? false : projRoleInfo.getPrivPermSet().contains(reqPermission);
	}
	
	/**
	 * 
			*@name 获取角色信息
			*@Description  
			*@CreateDate 2017年12月6日下午4:19:34
	 */
	public static ProjectRoleInfo getProjectRoleInfo(HttpSession session,Long projRoleId){
		if (projRoleId == null) {
			return null;
		}
		
		Map<Long, ProjectRoleInfo> projRolePrivMap = (Map<Long, ProjectRoleInfo>)getAppAttr(session, AppConstants.APP_KEY_PROJ_ROLE_PRIV_MAP);
		if (projRolePrivMap == null) {
			projRolePrivMap = MapUtils.newMap();
		}
		
		//每次有未缓存的项目角色权限则缓存
		ProjectRoleInfo projRoleInfo = projRolePrivMap.get(projRoleId);
		if (projRoleInfo == null) {
			ProjectRole role = getProjectRoleService().getById(projRoleId);
			List<PrivilegeInfo> privList = getProjectRoleService().listPriv(projRoleId, EnableStatus.on, null);
			projRoleInfo = new ProjectRoleInfo();
			projRoleInfo.setId(projRoleId);
			projRoleInfo.setProjId(role.getProjId());
			projRoleInfo.setCode(role.getCode());
			projRoleInfo.setName(role.getName());
			projRoleInfo.setHomeUrl(role.getHomeUrl());
			projRoleInfo.setPrivList(privList);
			projRoleInfo.setPrivPermSet(privList);
			projRolePrivMap.put(projRoleId, projRoleInfo);
			
			setAppAttr(session, AppConstants.APP_KEY_PROJ_ROLE_PRIV_MAP, projRolePrivMap);
		}
		
		return projRoleInfo;
	}
	
	/**
	 * 
			*@name 获取项目角色id
			*@Description  
			*@CreateDate 2017年12月6日下午3:16:51
	 */
	public static Long getBookRoleId(HttpSession session,Long bookId){
		if (bookId == null) {
			return null;
		}
		
		UserInfo userInfo = getUserInfo(session);
		if (FormatUtils.isEqual(userInfo.getRoleId(), CfgConstants.ROLE_ID_ADMIN)){//处理超管
			return CfgConstants.PROJ_ROLE_ID_ADMIN;
		}
		else {
			return getBookRoleIdForUser(session, userInfo, bookId);
		}
	}
	
	//普通用户获取项目权限信息
	private static Long getBookRoleIdForUser(HttpSession session,UserInfo userInfo,Long bookId){
		Long projRoleId = userInfo.getBookRoleMap().get("" + bookId);
		if (projRoleId == null) {//获取不到角色信息，尝试数据库重新加载
			userInfo = reloadBookRoleInfo(session);
			projRoleId = userInfo.getBookRoleMap().get("" + bookId);
		}
		
		return projRoleId;
	}
	
	/**
	 * 
			*@name 获取项目角色id
			*@Description  
			*@CreateDate 2017年12月6日下午3:16:51
	 */
	public static Long getProjRoleId(HttpSession session,Long projId,Long docId){
		if (projId == null && docId == null) {
			return null;
		}
		
		UserInfo userInfo = getUserInfo(session);
		if (FormatUtils.isEqual(userInfo.getRoleId(), CfgConstants.ROLE_ID_ADMIN)){//处理超管
			return CfgConstants.PROJ_ROLE_ID_ADMIN;
		}
		else {
			return getProjRoleIdForUser(session, userInfo, projId, docId);
		}
	}
		
	//普通用户获取项目权限信息
	private static Long getProjRoleIdForUser(HttpSession session,UserInfo userInfo,
												Long projId,Long docId){
		Long projRoleId = getProjRoleIdForUser(userInfo, projId, docId);
		if (projRoleId == null) {//获取不到角色信息，尝试数据库重新加载
			userInfo = reloadProjRoleInfo(session);
			projRoleId = getProjRoleIdForUser(userInfo, projId, docId);
		}
		
		return projRoleId;
	}
		
	//获取项目角色信息
	private static Long getProjRoleIdForUser(UserInfo userInfo,Long projId,Long docId){
		Long projRoleId = null;
		if (projId != null && docId == null) {//处理projId不为空情况
			projRoleId = userInfo.getProjRoleMap().get("" + projId);
		}
		else if (projId == null && docId != null) {//处理docId不为空情况
			projRoleId = userInfo.getDocRoleMap().get("" + docId);
		}
		else if(projId != null && docId != null){//处理projId和docId都不为空情况，确保对应的角色id是一致的
			Long tempForProjId = userInfo.getProjRoleMap().get("" + projId);
			Long tempForDocId = userInfo.getDocRoleMap().get("" + docId);
			if (tempForProjId != null && tempForDocId != null
					&& FormatUtils.isEqual(tempForProjId, tempForDocId)) {
				projRoleId = tempForProjId;
			}
		}
		
		return projRoleId;
	}
	
	/**
	 * 
			*@name 重加载项目角色信息
			*@Description  
			*@CreateDate 2017年12月6日下午2:59:06
	 */
	public static UserInfo reloadProjRoleInfo(HttpSession session) {
		UserInfo userInfo = getUserInfo(session);
		List<ProjectInfo> projList = getProjectService().listByUserId(userInfo.getUserId(),null,null, null, new Pager(1, Integer.MAX_VALUE));
		for (ProjectInfo projectInfo : projList) {
			userInfo.getProjRoleMap().put("" + projectInfo.getProjId(), projectInfo.getProjRoleId());
			userInfo.getDocRoleMap().put("" + projectInfo.getDocId(), projectInfo.getProjRoleId());
		}
		
		saveUserInfo(session, userInfo);
		return userInfo;
	}
	
	/**
	 * 
			*@name 重新加载书籍角色信息
			*@Description  
			*@CreateDate 2017年12月6日下午3:51:43
	 */
	public static UserInfo reloadBookRoleInfo(HttpSession session) {
		UserInfo userInfo = getUserInfo(session);
		List<BookInfo> bookInfoList = getBookService().listAuth(userInfo.getUserId());
		for (BookInfo bookInfo : bookInfoList) {
			userInfo.getBookRoleMap().put("" + bookInfo.getId(), bookInfo.getRoleId());
		}
		
		saveUserInfo(session, userInfo);
		return userInfo;
	}
	
	/**
	 * 获取session属性
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getSessionAttr(HttpSession session,String key){
		return session.getAttribute(key);
	}
	
	/**
	 * 添加session属性
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setSessionAttr(HttpSession session,String key,Object value){
		session.setAttribute(key, value);
	}
	
	/**
	 * 添加应用级属性
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAppAttr(HttpSession session,String key,Object value){
		session.getServletContext().setAttribute(key, value);
	}
	
	/**
	 * 获取应用级属性
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAppAttr(HttpSession session,String key){
		return session.getServletContext().getAttribute(key);
	}

	/**
	 * 
			*@name 获取用户信息
			*@Description  
			*@CreateDate 2017年12月6日下午4:14:49
	 */
	public static UserInfo getUserInfo(HttpSession session){
		return (UserInfo)session.getAttribute(AppConstants.SESSION_KEY_USER);
	}
	
	/**
	 * 
			*@name 保存用户信息
			*@Description  
			*@CreateDate 2017年12月6日下午4:15:17
	 */
	public static void saveUserInfo(HttpSession session,UserInfo userInfo){
		session.setAttribute(AppConstants.SESSION_KEY_USER, userInfo);
	}
	
	//获取项目服务类
	private static ProjectService getProjectService(){
		if (projectService == null) {
			projectService = SpringContextUtils.getBean(ProjectService.class);
		}
		
		return projectService;
	}
	
	//获取项目角色类
	private static ProjectRoleService getProjectRoleService(){
		if (projectRoleService == null) {
			projectRoleService = SpringContextUtils.getBean(ProjectRoleService.class);
		}
		
		return projectRoleService;
	}
	
	//获取书籍服务类
	private static BookService getBookService(){
		if (bookService == null) {
			bookService = SpringContextUtils.getBean(BookService.class);
		}
		
		return bookService;
	}
}
