package com.dev.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;

/**
 * 
		* <p>Title: ajax跨域日志拦截器</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月24日下午4:17:24</p>
 */
public class CorsFilter implements Filter{
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse)response;
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, "
				+ "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, "
				+ "Content-Type, X-E4M-With, Accept" + getMockCorsHeaderCust());
		resp.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");  
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		
		chain.doFilter(request, response);
	}

	private String getMockCorsHeaderCust(){
		return StringUtils.isEmpty(CfgConstants.MOCK_CORS_HEADER_CUST) ? "" : "," + CfgConstants.MOCK_CORS_HEADER_CUST;
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
}
