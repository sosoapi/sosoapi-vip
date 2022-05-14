<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- sidebar left start-->
<div class="sidebar-left sticky-sidebar">
    <!--responsive view logo start-->
    <div class="logo dark-logo-bg visible-xs-* visible-sm-*">
        <a href="">
            <img src="${Cfg.IMG_DOMAIN}image/logo-icon.png" alt="">
            <span class="brand-name">SosoApi</span>
        </a>
    </div>
    <!--responsive view logo end-->

    <div class="sidebar-left-info">
        <!-- visible small devices start-->
        <div class="search-field"></div>
        <!-- visible small devices end-->

        <!--sidebar nav start-->
        <ul class="nav nav-pills nav-stacked side-navigation">
            <li class="${param.menuSwagger ? 'active' : ''}">
	            <a href="pass/faq/swagger.htm">
	            	<i class="fa fa-code"></i> 
	            	<span>SwaggerUI</span>
	            </a>
	        </li>
	        
	        <li class="${param.menuOnline ? 'active' : ''}">
	            <a href="pass/faq/online.htm">
	     			<i class="fa fa-edit"></i> 
	     			<span>线上编辑</span>       
	            </a>
	        </li>
	        
	        <li class="${param.menuOffline ? 'active' : ''}">
	            <a href="pass/faq/offline.htm">
	            	<i class="fa fa-cloud-download"></i> 
	            	<span>线下部署</span>
	            </a>
	        </li>
	        
	        <li class="${param.menuTest ? 'active' : ''}">
	            <a href="pass/faq/test.htm">
	            	<i class="fa fa-bug"></i> 
	            	<span>在线测试</span>
	            </a>
	        </li>
	        
	        <li class="${param.menuAbout ? 'active' : ''}">
	            <a href="pass/faq/about.htm">
	            	<i class="fa fa-address-card-o"></i> 
	            	<span>关于我们</span>
	            </a>
	        </li>
        </ul>
        <!--sidebar nav end-->
    </div>
</div>
<!-- sidebar left end-->