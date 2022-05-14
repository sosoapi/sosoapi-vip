package com.dev.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.exception.AuthException;
import com.dev.base.exception.SessionTimeoutException;
import com.dev.base.util.AuthCacheUtil;
import com.dev.base.util.WebUtil;
import com.dev.user.service.LoginService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 权限拦截器</p>
		* <p>Description: 主要处理项目相关权限，用户权限由shiro处理</p>
		* <p>CreateDate: 2015年7月11日下午2:00:35</p>
 */
public class PrivilegeInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LogManager.getLogger(PrivilegeInterceptor.class);

	@Autowired
	private LoginService loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String reqUri = request.getRequestURI();
		logger.debug(reqUri);
		
		//验证用户同时对shiro的自动登录做用户信息处理
		UserInfo userInfo = WebUtil.getUserInfo(request,loginService);
		if (userInfo == null) {
			throw new SessionTimeoutException();
		}
		
		//验证是否是httpclient请求爬数据
		validHttpClient(request);
		
		//验证请求是否有指定权限
		authReq(request, userInfo);
				
		return super.preHandle(request, response, handler);
	}
	
	//验证请求是否有指定权限
	private void authReq(HttpServletRequest request,UserInfo userInfo){
		PrivilegeInfo privInfo = (PrivilegeInfo)request.getAttribute(AppConstants.REQUEST_KEY_PRIV_INFO);
		String permission = privInfo == null ? "" : privInfo.getPermission();
		//处理项目相关权限，只要请求参数中包含projId或docId都需要验证合法性
		if (!StringUtils.isEmpty(request.getParameter("projId")) 
				|| !StringUtils.isEmpty(request.getParameter("docId"))) {
			authForProj(request,userInfo,permission);
		}
		
		//处理书籍相关权限，只要请求参数中包含bookId都需要验证合法性
		if (!StringUtils.isEmpty(request.getParameter("bookId"))) {
			authForBook(request, userInfo,permission);
		}
	}
	
	//验证是否是httpclient爬数据
	private void validHttpClient(HttpServletRequest request){
		//减少httpclient爬数据
		if (WebUtil.isAjaxReq(request)) {
			String sysReqToken = request.getHeader("sysReqToken");
			if (!CfgConstants.SYS_REQ_TOKEN.equals(sysReqToken)) {
				logger.error("bad req without sys req token:");
				throw new SessionTimeoutException();
			}
		}
	}
	
	//验证书籍权限
	private void authForBook(HttpServletRequest request,UserInfo userInfo,String permission){
		Long bookId = parseLong(request.getParameter("bookId"));
		if (bookId == null) {
			return ;
		}
		
		HttpSession session = request.getSession();
		Long projRoleId = AuthCacheUtil.getBookRoleId(session, bookId);
		authReq(session, projRoleId, permission);
	}
	
	//验证项目权限
	private void authForProj(HttpServletRequest request,UserInfo userInfo,String permission){
		HttpSession session = request.getSession();
		Long projId = parseLong(request.getParameter("projId"));
		Long docId = parseLong(request.getParameter("docId"));
		Long projRoleId = AuthCacheUtil.getProjRoleId(session, projId, docId);
		authReq(session, projRoleId, permission);
	}
	
	//验证请求
	private void authReq(HttpSession session,Long projRoleId,String permission){
		boolean isAuth = AuthCacheUtil.authPriv(session, projRoleId, permission);
		if (!isAuth) {//当前请求非合法项目
			throw new AuthException();
		}
	}
	
	//转换long
	private Long parseLong(String val){
		Long result = null;
		if (StringUtils.isEmpty(val)) {
			return result;
		}
		
		try {
			result = Long.parseLong(val);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
