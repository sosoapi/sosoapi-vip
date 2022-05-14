package com.dev.base.shiro.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.dev.admin.entity.FilterChain;
import com.dev.admin.entity.Privilege;
import com.dev.admin.service.FilterChainService;
import com.dev.admin.service.PrivilegeService;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.FilterChainPosition;
import com.dev.base.enums.PrivScope;
import com.dev.base.json.JsonUtils;
import com.dev.base.shiro.service.AbstractFilterChainDefinitionsService;

/**
 * 
		* <p>Title: 数据库加载权限过滤链</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午5:44:52</p>
 */
public class JdbcFilterChainDefinitionsServiceImpl extends AbstractFilterChainDefinitionsService{
	private static Logger logger = LogManager.getLogger(JdbcFilterChainDefinitionsServiceImpl.class);
	
	@Autowired
	private FilterChainService filterChainService;
	
	@Autowired
	private PrivilegeService privilegeService;

	@Override
	public LinkedHashMap<String, String> loadOtherFilterChain() {
		LinkedHashMap<String, String> otherFilterChain = new LinkedHashMap<String,String>();
		
		//加载前置过滤链
		loadChain(otherFilterChain, FilterChainPosition.before);
		//加载权限过滤
		loadPriv(otherFilterChain);
		//加载后置过滤链
		loadChain(otherFilterChain, FilterChainPosition.after);
		
		logger.debug("load filter chain:\r\n" + JsonUtils.toFormatJson(otherFilterChain));
		return otherFilterChain;
	}
	
	//加载过滤链
	private void loadChain(LinkedHashMap<String, String> otherFilterChain,FilterChainPosition position){
		List<FilterChain> chainList = filterChainService.listByPostion(EnableStatus.on, position);
		String filterUrl = "";
		String filter = "";
		for (FilterChain filterChain : chainList) {
			filterUrl = buildUrl(filterChain.getUrl());
			filter = filterChain.getFilter();
			if (StringUtils.isEmpty(filterUrl) || StringUtils.isEmpty(filter)) {
				continue ;
			}
			
			otherFilterChain.put(filterUrl, filter.trim());
		}
	}
	
	//加载权限
	private void loadPriv(LinkedHashMap<String, String> otherFilterChain){
		String privUrl = "";
		String perm = "";
		List<Privilege> privList = privilegeService.listAll(null, EnableStatus.on, true, null,PrivScope.user);
		for (Privilege privilege : privList) {
			privUrl = buildUrl(privilege.getUrl());
			perm = privilege.getPermission();
			if (StringUtils.isEmpty(privUrl) || StringUtils.isEmpty(perm)) {
				continue ;
			}
			otherFilterChain.put(privUrl, "perms[" + perm.trim() + "]");
		}
	}
	
	//url必须全部以"/"开头
	private String buildUrl(String url){
		if (url == null) {
			return "";
		}
		
		url = url.trim();
		if (StringUtils.isEmpty(url)) {
			return url;
		}
		
		return url.startsWith("/") ? url : "/" + url;
	}
}
