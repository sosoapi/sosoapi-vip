package com.dev.base.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.constant.AppConstants;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.SpringContextUtils;
import com.dev.sys.service.StaticDataService;

/**
 * 
		* <p>Title: 菜单过滤器</p>
		* <p>Description: 根据请求url，匹配权限菜单用于前端界面左侧菜单树选中设置</p>
		* <p>CreateDate: 2017年7月4日上午11:19:18</p>
 */
public class MenuFilter implements Filter{
	private static Logger logger = LogManager.getLogger(MenuFilter.class);
	
	private StaticDataService staticDataService;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String uri = httpServletRequest.getRequestURI();
		PrivilegeInfo privilegeInfo = matchMenuPriv(uri);
		if (privilegeInfo != null) {
			logger.debug("menu filter:\r\n" 
					+ "uri:" + uri 
					+ "\r\n privInfo:" + JsonUtils.toFormatJson(privilegeInfo));
			httpServletRequest.setAttribute(AppConstants.REQUEST_KEY_PRIV_INFO, privilegeInfo);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	//匹配对应的菜单权限
	private PrivilegeInfo matchMenuPriv(String uri){
		List<PrivilegeInfo> privList = getStaticDataService().loadPrivList(false);
		PrivilegeInfo result = null;
		if (StringUtils.isEmpty(uri)) {
			return result;
		}
		
		for (PrivilegeInfo info : privList) {
			if (uri.contains(info.getUrl())) {
				result = info;
				break;
			}
		}
		
		return result;
	}
	
	//获取service
	private StaticDataService getStaticDataService(){
		if (staticDataService == null) {
			staticDataService = SpringContextUtils.getBean(StaticDataService.class);
		}
		
		return staticDataService;
	}
}
