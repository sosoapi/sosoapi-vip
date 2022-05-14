package com.dev.base.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dev.admin.service.SysCfgService;
import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.vo.SelectInfo;
import com.dev.proj.service.ProjectRoleService;

/**
 * 
		* <p>Title: 系统初始化</p>
		* <p>Description: 用于系统启动时相关信息的预处理</p>
		* <p>CreateDate: 2015年7月11日下午1:39:37</p>
 */
public class AppInitListener implements ServletContextListener{
	private ApplicationContext appContext;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		//保存cfg.properties中设置的公共属性
		initCfg(servletContext);
		
		//设置系统级属性
		initAppAttr(servletContext);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	//保存cfg.properties中设置的公共属性
	private void initCfg(ServletContext application){
		//加载数据库配置信息
		SysCfgService sysCfgService = appContext.getBean(SysCfgService.class);
		CfgConstants.loadDbCfg(sysCfgService, true);
		CfgConstants.updateApplication(application);
	}
	
	//设置系统级属性
	private void initAppAttr(ServletContext application){
		ProjectRoleService projectRoleService = appContext.getBean(ProjectRoleService.class);
		List<SelectInfo> roleList = projectRoleService.listRole(null);
		application.setAttribute(AppConstants.APP_KEY_PROJ_ROLE_LIST, roleList);
	}
}
