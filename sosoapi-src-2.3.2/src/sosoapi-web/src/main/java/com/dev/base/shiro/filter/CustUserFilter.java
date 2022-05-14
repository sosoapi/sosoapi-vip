package com.dev.base.shiro.filter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import com.dev.base.exception.code.ErrorCode;
import com.dev.base.exception.errorcode.ErrorCodeTool;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 扩展shiro UserFilter</p>
		* <p>Description: 处理ajax session过期</p>
		* <p>CreateDate: 2017年9月29日上午11:10:25</p>
 */
public class CustUserFilter extends UserFilter{
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//继承至UserFilter
		saveRequest(request);

		Map<String,Object> errorInfo = MapUtils.newMap();
		errorInfo.put("errorCode", ErrorCode.SYS_011);
		errorInfo.put("errorMsg", ErrorCodeTool.get(ErrorCode.SYS_011));
		
		if (!WebUtil.isAjaxReq((HttpServletRequest)request)) {//处理非ajax请求
			WebUtils.issueRedirect(request, response, getLoginUrl(), errorInfo);
		}
		else {//处理ajax请求
			WebUtil.printJson((HttpServletResponse)response, errorInfo);
		}
		
		return false;
	}
}
