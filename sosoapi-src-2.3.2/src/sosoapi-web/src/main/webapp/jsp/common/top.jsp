<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!-- header section start-->
<div class="header-section">
	<!--logo and logo icon start-->
    <div class="logo dark-logo-bg hidden-xs hidden-sm hidden-md">
		<a href="">
			<img src="${Cfg.IMG_DOMAIN}image/logo-icon.png" alt="">
            <span class="brand-name">SosoApi</span>
        </a>
    </div>

    <div class="icon-logo dark-logo-bg hidden-xs hidden-sm hidden-md">
        <a href="">
			<img src="${Cfg.IMG_DOMAIN}image/logo-icon.png" alt="">
        </a>
    </div>
    <!--logo and logo icon end-->

    <!--toggle button start-->
    <a class="toggle-btn">
    	<i class="fa fa-outdent"></i>
    </a>
    <!--toggle button end-->

    <div class="notification-wrap">
    	<!--left notification start-->
    	<!-- <div class="left-notification nav nav-pills nav-stacked side-navigation"> -->
    	<div class="left-notification nav nav-pills nav-stacked">
    		<ul class="notification-menu">
    			<li>
    				<!-- <a href="javascript:;" data-hover="dropdown" class="btn btn-default dropdown-toggle info-number"> -->
    				<a href="javascript:;" data-toggle="dropdown" class="btn btn-default dropdown-toggle info-number">   
    					<span>控制台</span>
    					<i class="fa fa-angle-down"></i>
    				</a>
	               	<ul class="dropdown-menu green">
	               		<c:if test="${not empty curPrivList}">
			        		<c:forEach items="${curPrivList}" var="privInfo">
			        			<c:choose>
			        				<c:when test="${privInfo.childCount > 0}">
			        					<!-- 二级菜单，默认以第一个子菜单为主菜单 -->
			        					<li>
								            <a href="${privInfo.childList[0].url}">
								            	<i class="${privInfo.iconClass}"></i> 
								            	<span>${privInfo.name}</span>
								            </a>
								        </li>
			        				</c:when>
			        				
			        				<c:otherwise>
			        					<!-- 一级菜单 -->
			        					<li>
								            <a href="${privInfo.url}">
								            	<i class="${privInfo.iconClass}"></i> 
								            	<span>${privInfo.name}</span>
								            </a>
								        </li>
			        				</c:otherwise>
			        			</c:choose>
			        		</c:forEach>
			        	</c:if>
	               	</ul>
	         	</li>
			    
			    <li class="media-small-hide">
			    	<a href="javascript:;" data-toggle="dropdown" class="btn btn-default dropdown-toggle info-number">   
    					<span>帮助</span>
    					<i class="fa fa-angle-down"></i>
    				</a>
	               	<ul class="dropdown-menu green">
       					<li>
       						<a href="pass/help/manual.htm">
       							<i class="fa fa-book"></i>
       							<span>快速上手</span>
       						</a>
				        </li>
				        <li>
       						<a href="pass/faq/home.htm">
       							<i class="fa fa-question-circle"></i>
       							<span>常见问题</span>
       						</a>
				        </li>
				        <li>
       						<a href="javascript:void(0);">
       							<i class="fa fa-info-circle"></i>
       							<span>版本${Cfg.GOODS_LATEST_VERSION}</span>
       						</a>
				        </li>
	               	</ul>
			    </li>
			    
			    <li class="media-small-hide">
           			<a href="https://promotion.aliyun.com/ntms/act/ambassador/sharetouser.html?userCode=770zadgk&utm_source=770zadgk" target="_blank" class="btn btn-default dropdown-toggle info-number">
                   		<span>阿里云券</span>
                 	</a>
                   	<img alt="" src="image/hot.gif" style="margin-left:-65px;">
              	</li>
                
                <c:if test="${!Cfg.PROD_FLAG}">
                	<li class="media-small-hide">
                   		<a href="pass/goods/list.htm" class="btn btn-default dropdown-toggle info-number">
                   			<span>专业版下载</span>
                   		</a>
                  	</li>
                </c:if>
	    	</ul>
		</div>
    	<!--left notification end-->

    	<!--right notification start-->
   	 	<div class="right-notification">
        	<ul class="notification-menu">
        		<li class="chat-group media-small-hide">
        			<span>技术交流群:</span>
        			<a href="https://jq.qq.com/?_wv=1027&k=5s42mTF" target="_blank" class="text-muted text-center">317297749</a>
         		</li>
         		<shiro:user>
         			<li class="media-small-hide">
           				<a href="auth/msg/list.htm" class="btn btn-default dropdown-toggle info-number">
			            	<i class="fa fa-bell-o"></i>
			               	<span class="badge bg-warning">${userInfo.newMsgCount}</span>
			           	</a>
			       	</li>
                 
                 	<li>
	                 	<!-- <a href="javascript:;" class="btn btn-default dropdown-toggle" data-hover="dropdown"> -->
	                 	<a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
		     				<img src="${Cfg.IMG_DOMAIN}image/def/head.png" alt="">
		                    <span>${userInfo.nickName}</span>
		                    <span class="fa fa-angle-down"></span>
		               	</a>
	                	<ul class="dropdown-menu dropdown-usermenu green pull-right">
                          	<li>
	                     		<a href="auth/user/setting.htm">
	                     			<i class="fa fa-user"></i> 
            						<span>个人中心</span>
	                     		</a>
	                     	</li>
	                     	<li class="divider"></li>
	                     	<li>
	                     		<a href="logout.htm">
									<i class="fa fa-sign-out"></i> 
									<span>退出</span>
	                     		</a>
	                     	</li>
	                 	</ul>
	             	</li>
         		</shiro:user>
         		
         		<shiro:guest>
         			<li>
           				<a href="forwardLogin.htm"> 登录</a>
           			</li>
           			
           			<c:if test="${Cfg.ENABLE_REGIST}">
           				<li>
            				<a href="regist/forwardRegist.htm"> 注册</a>
            			</li>
           			</c:if>
         		</shiro:guest>
         	</ul>
    	</div>
    	<!--right notification end-->
    </div>
</div>
<!-- header section end-->
