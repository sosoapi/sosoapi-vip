<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
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
        <ul id="userMenuTree" class="nav nav-pills nav-stacked side-navigation">
        	<c:if test="${not empty curPrivList}">
        		<c:forEach items="${curPrivList}" var="privInfo">
        			<c:if test="${(privInfo.type eq 'cate') or (privInfo.type eq 'menu')}">
        				<c:choose>
	        				<c:when test="${privInfo.childCount > 0}">
	        					<!-- 二级菜单 -->
	        					<li class="menu-list ${reqPrivInfo.menuParentCode == privInfo.code ? 'nav-active' : ''}">
	        					<!-- <li class="menu-list"> -->
					            	<a href="${privInfo.url}">
					                	<i class="${privInfo.iconClass}"></i>  
					                	<span>${privInfo.name}</span>
										<c:if test="${not empty privInfo.imgUrl}">
											<img alt="" src="${privInfo.imgUrl}">
										</c:if>
					                </a>
					                <ul class="child-list">
					                	<c:forEach items="${privInfo.childList}" var="childInfo">
					                		<li class="${reqPrivInfo.menuCode == childInfo.code ? 'active' : ''}">
									            <a href="${childInfo.url}">
									            	<i class="${childInfo.iconClass}"></i> 
									            	<span>${childInfo.name}</span>
									            	<c:if test="${not empty childInfo.imgUrl}">
														<img alt="" src="${childInfo.imgUrl}">
													</c:if>
									            </a>
									        </li>
					                	</c:forEach>
					               	</ul>
					           	</li>
	        				</c:when>
	        				
	        				<c:otherwise>
	        					<!-- 一级菜单 -->
	        					<li class="${reqPrivInfo.menuCode == privInfo.code ? 'active' : ''}">
						            <a href="${privInfo.url}">
						            	<i class="${privInfo.iconClass}"></i> 
						            	<span>${privInfo.name}</span>
						            	<c:if test="${not empty privInfo.imgUrl}">
											<img alt="" src="${privInfo.imgUrl}">
										</c:if>
						            </a>
						        </li>
	        				</c:otherwise>
	        			</c:choose>
        			</c:if>
        		</c:forEach>
        	</c:if>
        </ul>
        <!--sidebar nav end-->
    </div>
</div>
<!-- sidebar left end-->