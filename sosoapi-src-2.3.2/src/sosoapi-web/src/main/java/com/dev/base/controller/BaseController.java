package com.dev.base.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.dev.admin.service.RoleService;
import com.dev.base.constant.AppConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.proj.service.ProjectRoleService;
import com.dev.proj.vo.ProjectRoleInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: controller基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:02:25</p>
 */
public class BaseController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ProjectRoleService projectRoleService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor editor = new CustomDateEditor(df,true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	/**
	 * 
			*@name 获取请求参数
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:14
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String> parseReqParam(HttpServletRequest request) {
		Map<String, String> result = MapUtils.newMap();
		Enumeration<String> names = request.getParameterNames();
		String key = "";
		String value = "";
		while (names.hasMoreElements()) {
			key = names.nextElement();
			value = request.getParameter(key);
			result.put(key, value);
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 从缓存中获取用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:25
	 */
	protected UserInfo getUserInfo(HttpServletRequest request){
		UserInfo userInfo = (UserInfo)WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_USER);
		
		return userInfo;
	}
	
	/**
	 * 
			*@name 从缓存中获取登录用户的id
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:35
	 */
	protected Long getUserId(HttpServletRequest request) {
		UserInfo userInfo = getUserInfo(request);
		return userInfo == null ? null : userInfo.getUserId(); 
	}
	
	/**
	 * 
			*@name 保存用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:45
	 */
	protected void saveUserInfo(HttpServletRequest request,UserInfo userInfo) {
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_USER, userInfo);
	}
	
	/**
	 * 
			*@name 移除用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:09:27
	 */
	protected void removeUserInfo(HttpServletRequest request) {
		WebUtil.removeSessionAttr(request, AppConstants.SESSION_KEY_USER);
	}
	
	/**
	 * 
			*@name 获取用户实际ip地址
			*@Description  
			*@CreateDate 2015年9月1日下午8:55:53
	 */
	protected  String getClientIp(HttpServletRequest request){
		return WebUtil.getClientIp(request);
	}
	
	/**
	 * 
			*@name 加载系统角色下拉框列表
			*@Description  
			*@CreateDate 2017年7月4日下午6:22:51
	 */
	protected void loadRole(HttpServletRequest request,boolean refresh){
		if (WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_SYS_ROLE_LIST) == null
				|| refresh) {
			List<SelectInfo> roleList = roleService.listRole(EnableStatus.on);
			WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_SYS_ROLE_LIST, roleList);
		}
	}
	
	/**
	 * 
			*@name 更新项目权限
			*@Description  
			*@CreateDate 2017年9月6日下午2:56:07
	 */
	protected void updateUserProjRole(HttpServletRequest request,Long projId,
										Long docId,Long projRoleId) {
		UserInfo userInfo = getUserInfo(request);
		if (projId != null) {
			userInfo.getProjRoleMap().put("" + projId, projRoleId);
		}
		
		if (docId != null) {
			userInfo.getDocRoleMap().put("" + docId, projRoleId);
		}
		saveUserInfo(request, userInfo);
	}
	
	/**
	 * 
			*@name 项目角色权限变更，需要清空对应缓存，下次重新加入
			*@Description  
			*@CreateDate 2017年9月10日下午4:59:15
	 */
	protected void clearProjRolePrivMapCache(HttpServletRequest request,Long roleId){
		Map<Long, ProjectRoleInfo> projRolePrivMap = (Map<Long, ProjectRoleInfo>)WebUtil.getAppAttr(request, AppConstants.APP_KEY_PROJ_ROLE_PRIV_MAP);
		if (!CollectionUtils.isEmpty(projRolePrivMap)) {
			projRolePrivMap.remove(roleId);
		}
	}
	
	/**
	 * 
			*@name 获取项目角色信息
			*@Description  
			*@CreateDate 2017年9月10日下午4:59:15
	 */
	protected ProjectRoleInfo getProjRoleInfoCache(HttpServletRequest request,Long roleId){
		Map<Long, ProjectRoleInfo> projRolePrivMap = (Map<Long, ProjectRoleInfo>)WebUtil.getAppAttr(request, AppConstants.APP_KEY_PROJ_ROLE_PRIV_MAP);
		return CollectionUtils.isEmpty(projRolePrivMap) ? null : projRolePrivMap.get(roleId);
	}
	
	/**
	 * 
			*@name 项目角色下拉框
			*@Description  
			*@CreateDate 2019年3月5日下午4:08:45
	 */
	protected void loadProjRoleList(Model model,Long projId){
		model.addAttribute("roleList", projectRoleService.listRole(projId));
	}
}
