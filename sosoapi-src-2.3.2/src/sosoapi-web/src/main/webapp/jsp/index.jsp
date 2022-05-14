<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="${Cfg.WEB_PAGE_META_DESCRIPTION}">
	<meta name="Keywords" content="${Cfg.WEB_PAGE_META_KEYWORDS}">
	<c:if test="${Cfg.PROD_FLAG}">
	<meta name="robots" content="none,noarchive">
	</c:if>
	<title>${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<link rel="shortcut icon" href="favicon.ico">
	
	<c:choose>
		<c:when test="${Cfg.WEB_CDN_ENABLE}">
			<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
			<link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
			<link href="css/theme/component.css" rel="stylesheet">
			
			<link href="plugin/bootsnav/css/bootsnav.css" rel="stylesheet">
			<link href="plugin/bootsnav/css/style.css" rel="stylesheet">
		</c:when>
		
		<c:otherwise>
			<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
			<link href="plugin/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
			<link href="css/theme/component.css" rel="stylesheet">
			
			<link href="plugin/bootsnav/css/bootsnav.css" rel="stylesheet">
			<link href="plugin/bootsnav/css/style.css" rel="stylesheet">
		</c:otherwise>
	</c:choose>
    <style type="text/css">
    	html {
			overflow:visible;
		}
		
		.wrapper {
		    padding: 0 0;
		}
		
		.landing-page header {
		    height: 480px;
		    padding-top: 0;
		    margin-bottom: 30px;
		}
		
		.landing-page .navbar-nav > li > a {
		    font-size: 16px;
		    font-weight: 400;
		}

		.landing-page .navbar-default .navbar-toggle:hover, 
		.landing-page .navbar-default .navbar-toggle:focus {
		    background: #fff;
		}

		nav.navbar.bootsnav .navbar-nav {
		    padding-left: 15px;
		    padding-right: 30px;
		}

		nav.navbar.bootsnav .navbar-toggle {
		    margin-top: 25px;
		}

		nav.navbar.bootsnav ul.nav > li > a {
		    padding: 30px 15px 0 15px;
		    font-weight: 600;
		}
		
		nav.navbar ul.nav > li > a {
		    padding: 30px 15px 0 15px;
		    font-weight: 600;
		}

		table#version-contrast thead tr{
		    background: #f9f9f9;
		}
		
		table#version-contrast tbody th {
		    font-weight: 600;
		    vertical-align: middle;
		    text-align: center;
		    background: #fff;
		}
		
		td .fa-check{
			color: #62cb31;
		}
		
		td .fa-close{
			color: #e74c3c;
		}
		
		.qrcode-container{
			width: 172px;
			height: 200px;
			font-size:12px; 
			background:#fff;
			text-align:center;
			position: absolute;
			border:1px solid #e4e5e7;
			top: 60px;
			left: -50px;
			display:none;
		}
		
		.qrcode-container .item{
			width:170px; 
			height:200px; 
			padding:10px; 
			background:#fff; 
			text-align:center;
			position:absolute; 
		}
		
		.qrcode-container .item img{ 
			/* margin-bottom:5px; */
		}
		
		.qrcode-container .arrow{ 
			width:0; 
			height:0; 
			border-bottom:10px solid #e4e5e7;
			border-left:10px solid transparent;
			border-right:10px solid transparent; 
			position:absolute; 
			left:75px; 
			top:-10px;
		}
		
		.qrcode-container.dubble{
			width: 342px;
			left:-130px;
		}
		
		.qrcode-container.dubble .arrow{
			left:160px; 
		}
		
		.qrcode-container.dubble .item:last-child{
			right:0;
		}
		
		a.qrcode-linker:hover ~ div.qrcode-container{
			display:block;
		}
    </style>
</head>
<body class="landing-page">
	<!-- Start Navigation -->
    <nav class="navbar navbar-default bootsnav navbar-fixed-top">
        <div class="container">      
            <!-- Start Header Navigation -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
                    <i class="fa fa-bars"></i>
                </button>
                <a href="javascript:void(0);">
					<img src="${Cfg.IMG_DOMAIN}image/logo.png" style="height:80px;margin-right:10px;">
		        </a>
            </div>
            <!-- End Header Navigation -->

            <div class="collapse navbar-collapse" id="navbar-menu">
                <ul class="nav navbar-nav navbar-left" data-in="fadeInDown" data-out="fadeInUp">
                    <li><a href="#page-top">首页</a></li>                    
                    <li><a href="#page-features">功能简介</a></li>
                    <li><a href="#page-download">源码下载</a></li>
                    <li><a href="pass/faq/home.htm">常见问题</a></li>
                    <li><a href="pass/help/manual.htm">快速上手</a></li>
                    <!-- <li><a href="pass/apidoc/demo.htm" target="_blank">Demo</a></li> -->
                    <li>
                    	<a href="https://promotion.aliyun.com/ntms/act/ambassador/sharetouser.html?userCode=770zadgk&utm_source=770zadgk" target="_blank">
                        	<span>阿里云券</span>
                      	</a>
                        <img alt="" src="image/hot.gif" style="margin-top:-90px;margin-left:30px;">
                   	</li>
                    
                    <%-- 
                    <li>
                    	<a class="qrcode-linker" href="javascript:void(0);">公众号</a>
                    	<img alt="" src="image/hot.gif" style="margin-top:-90px;margin-left:25px;">
                    	<!-- <div class="qrcode-container dubble"> -->
                    	<div class="qrcode-container">
                    		<div class="arrow"></div>
                    		<div class="item">
                    			<img src="image/qrcode_weixin.jpg" width="150" height="150" />
						       	<span>关注官方微信</span>
                    		</div>
                    		
                    		<!-- <div class="item">
						       	<img src="image/qrcode_act_juli.png" width="135" height="135" 
						       		style="margin-top:10px;margin-bottom:5px;" />
						       	<span>领取红包</span>
                    		</div> -->
						</div>
                    </li>
                    --%>
                    
                    <!-- <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" >Dropdown</a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Custom Menu</a></li>
                            <li><a href="#">Custom Menu</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" >Sub Menu</a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Custom Menu</a></li>
                                    <li><a href="#">Custom Menu</a></li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" >Sub Menu</a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Custom Menu</a></li>
                                            <li><a href="#">Custom Menu</a></li>
                                            <li><a href="#">Custom Menu</a></li>
                                            <li><a href="#">Custom Menu</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Custom Menu</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Custom Menu</a></li>
                        </ul>
                    </li> -->
                </ul>
                
                <ul class="nav navbar-nav navbar-right">
	            	<shiro:user>
	            		<li>
							<a href="${Cfg.WEB_BASE_URL}${userInfo.homeUrl}">${userInfo.nickName}</a>
						</li>
	            	</shiro:user>
	            	
	            	<shiro:guest>
	            		<li>
		                	<a href="forwardLogin.htm">登录</a>
		                </li>
		                
		                <c:if test="${Cfg.ENABLE_REGIST}">
			                <li>
			                	<a href="regist/forwardRegist.htm">注册</a>
			                </li>
		                </c:if>
	            	</shiro:guest>
	            </ul>
            </div>
        </div>   
    </nav>
    <!-- End Navigation -->
    <!-- <div class="clearfix"></div> -->
    
	<header id="page-top" class="bg-light">
	    <div class="banner text-center">
			<div class="container">
		    	<div class="banner-info">
		    		<c:choose>
		    			<c:when test="${Cfg.PROD_FLAG}">
		    				<h1>&nbsp;</h1>
		    			</c:when>
		    			<c:otherwise>
		    				<h1>SosoApi</h1>
		    			</c:otherwise>
		    		</c:choose>
		      		
		      		<p>
		      			Simple online,Simple offline
		      			<br>
		        		专注于API接口文档管理及线上线下测试的API服务平台
		        	</p>
		      		<a class="btn btn-success btn-lg" href="pass/apidoc/demo.htm" target="_blank">在线demo</a> 
		      	</div>
		  	</div>
		</div>
	</header>
	
	<section id="page-banner" style="padding-top:0px;padding-bottom: 0px;margin-top:120px;">
		<div class="container text-center">
			<ul class="list-inline m-t-sm">
				<li>
					<a></a>
				</li>
				
				<li>
					<a></a>
				</li>
				
				<li>
					<a></a>
				</li>
			</ul>
	    </div>
	</section>
	
	<section id="page-features" class="bg-light">
	    <div class="container">
	    	<div class="row">
                <div class="col-sm-3">
                    <div class="hpanel plan-box hyellow">
                        <div class="panel-heading hbuilt text-center">
                            <h4 class="font-bold">文档齐全</h4>
                        </div>
                        <div class="panel-body">
                            <p class="text-muted">
                               	<span>提供丰富的在线文档，包括线上编辑和线下部署及常见问题，减少上手学习成本。</span>                       
                            </p>
                            <table class="table">
                                <thead>
	                                <tr>
	                                    <td>
	                                        <span>包含但不限于</span>
	                                    </td>
	                                </tr>
                                </thead>
                                <tbody>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 在线Demo事例
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 线上编辑文档
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 线下部署文档
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 常见问题集
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 技术交流群
	                                    </td>
	                                </tr>
                                </tbody>
                            </table>
                            
                            <p class="text-muted">
                                <span></span>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="hpanel plan-box hgreen active">
                        <div class="panel-heading hbuilt text-center">
                            <h4 class="font-bold">线上编辑</h4>
                        </div>
                        <div class="panel-body">
                            <p class="text-muted">
                                <span>通过编辑表单的方式创建基于swagger ui的数据文档，从而可在线预览和测试。</span>
                                <span>在线编辑解决了swagger editor学习成本高及代码集成不好维护的问题。</span>
                            </p>
                            <table class="table">
                                <thead>
                                <tr>
                                    <td>
                                        <span>包含但不限于</span>
                                    </td>
                                </tr>
                                </thead>
                                <tbody>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 接口文档基本信息
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 模块管理
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 接口管理
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 公共数据结构管理
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 在线预览
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 在线测试
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> mock服务
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 导入导出(swagger,postman,html,word)
	                                    </td>
	                                </tr>
                                </tbody>
                            </table>
                            
                            <p class="text-muted">
                                <span></span>
                            </p>
                        </div>
                    </div>
                </div>
                
                <div class="col-sm-3">
                    <div class="hpanel plan-box hblue">
                        <div class="panel-heading hbuilt text-center">
                            <h4 class="font-bold">项目管理</h4>
                        </div>
                        <div class="panel-body">
                            <p class="text-muted">
                                <span>团队协作，内置2种角色，方便接口文档的协作管理。</span>
                            </p>
                            <table class="table">
                                <thead>
	                                <tr>
	                                    <td>
	                                        <span>包含但不限于</span>
	                                    </td>
	                                </tr>
                                </thead>
                                <tbody>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 协作权限控制
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 团队成员管理
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 接口变更通知
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 文档分享
	                                    </td>
	                                </tr>
                                </tbody>
                            </table>
                            
                            <p class="text-muted">
                                <span></span>
                            </p>
                        </div>
                    </div>
                </div>
                
                <div class="col-sm-3">
                    <div class="hpanel plan-box hred">
                        <div class="panel-heading hbuilt text-center">
                            <h4 class="font-bold">线下部署</h4>
                        </div>
                        <div class="panel-body">
                            <p class="text-muted">
                                <span>
                                	为方便小团队及公司内部使用，可导出swagger数据文档线下部署或
                                	<a href="https://github.com/sosoapi" target="_blank">GitHub源码(v1.0.0)</a>
                                	下载部署。
                                </span>
                            </p>
                            <table class="table">
                                <thead>
	                                <tr>
	                                    <td>
	                                        <span>包含但不限于</span>
	                                    </td>
	                                </tr>
                                </thead>
                                <tbody>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> swagger数据文档部署
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 开源版源码(v1.0.0)部署
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <i class="fa fa-check-square-o"></i> 专业版源码部署
	                                    </td>
	                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
	        <div class="row m-t-xl">
	            <div class="col-md-2">
	                <h4 class="m-t-xxl">项目管理</h4>
	            </div>
	            <div class="col-md-10">
	                <img src="${Cfg.IMG_DOMAIN}image/index/proj.png" class="img-responsive lazy">
	            </div>
	        </div>
	     
	        <div class="row m-t-xl">
	            <div class="col-md-10">
	            	<img src="${Cfg.IMG_DOMAIN}image/index/doc.png" class="img-responsive lazy">
	            </div>
	            <div class="col-md-2">
	                <h4 class="m-t-xxl">Api文档管理</h4>
	            </div>
	        </div>
	        
	        <div class="row m-t-xl">
	            <div class="col-md-2">
	                <h4 class="m-t-xxl">接口管理</h4>
	            </div>
	            <div class="col-md-10">
	                <img src="${Cfg.IMG_DOMAIN}image/index/inter-list.png" class="img-responsive lazy">
	            </div>
	        </div>
	        
	        <div class="row m-t-xl">
	            <div class="col-md-10">
	            	<img src="${Cfg.IMG_DOMAIN}image/index/inter-detail.png" class="img-responsive lazy">
	            </div>
	            <div class="col-md-2">
	                <h4 class="m-t-xxl">接口详情</h4>
	            </div>
	        </div>
	        
	        <div class="row m-t-xl">
	            <div class="col-md-2">
	                <h4 class="m-t-xxl">Swagger预览</h4>
	            </div>
	            <div class="col-md-10">
	                <img src="${Cfg.IMG_DOMAIN}image/index/swagger.png" class="img-responsive lazy">
	            </div>
	        </div>
	        
	        <div class="row m-t-xl">
	            <div class="col-md-10">
	            	<img src="${Cfg.IMG_DOMAIN}image/index/swagger-inter.png" class="img-responsive lazy">
	            </div>
	            <div class="col-md-2">
	                <h4 class="m-t-xxl">Swagger测试</h4>
	            </div>
	        </div>
		</div>
    </section>
    
    <section id="page-download">
	    <div class="container">
	    	<div class="row">
				<p class="text-muted">
                    <h4 class="font-bold">源码下载</h4>
                </p>
                
                <div class="col-md-12">
		            <div class="col-md-offset-2 col-md-4">
		                <div class="hpanel">
		                    <div class="panel-body">
		                        <div class="text-center">
		                            <h2 class="m-b-xs">开源版</h2>
		                            <p class="font-bold text-success">v1.0.0</p>
		                            <div class="m">
		                                <i class="fa fa-cloud-download fa-5x"></i>
		                            </div>
		                            <p class="small">开源的版本为v1.0.0，因运营需要后续版本不再开源。开源版和专业版仅能用于团队或公司内部。</p>
		                            <a href="https://github.com/sosoapi" target="_blank" class="btn btn-success btn-lg">下载</a>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <div class="col-md-4">
		                <div class="hpanel">
		                    <div class="panel-body">
		                        <div class="text-center">
		                            <h2 class="m-b-xs">专业版</h2>
		                            <p class="font-bold text-danger">${Cfg.GOODS_LATEST_VERSION}</p>
		                            <div class="m">
		                                <i class="fa fa-credit-card fa-5x"></i>
		                            </div>
		                            <p class="small">
		                               	 购买后后续小版本升级全部免费，大版本收取小额费用。开源版和专业版仅能用于团队或公司内部。
		                            </p>
		                            <c:choose>
		                            	<c:when test="${Cfg.PROD_FLAG}">
		                            		<a href="http://www.sosoapi.com/pass/goods/list.htm" target="blank_" class="btn btn-danger btn-lg">购买</a>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<a href="pass/goods/list.htm" class="btn btn-danger btn-lg">购买</a>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                </div>
		            </div>
		    	</div>
		    	
                <p class="text-muted p-xs">
                    <span>开源版与专业版对比</span>
                </p>
                <div class="table-responsive">
	                <table id="version-contrast" cellpadding="1" cellspacing="1" class="table table-bordered table-striped">
	                    <thead>
		                    <tr>
		                    	<th colspan="2" class="col-sm-6"></th>
		                        <th class="col-sm-3">开源版</th>
		                        <th class="col-sm-3">专业版</th>
		                    </tr>
	                    </thead>
	                    <tbody>
	                    	<tr>
	                    		<th rowspan="13">线上编辑</th>
	                    	</tr>
		                    <tr>
		                        <td>接口文档基本信息</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>模块管理</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>接口管理</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>公共数据结构管理</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>在线预览</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>在线测试</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>导出</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>导入</td>
		                        <td><i class="fa fa-close"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>mock服务</td>
		                        <td><i class="fa fa-close"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>demo事例</td>
		                        <td><i class="fa fa-close"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>新版UI</td>
		                        <td><i class="fa fa-close"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>v1.0.0之后功能</td>
		                        <td><i class="fa fa-close"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
	                    		<th rowspan="6">项目管理</th>
	                    	</tr>
	                    	<tr>
		                        <td>协作权限控制</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>团队成员管理</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>接口变更通知</td>
		                        <td><i class="fa fa-check"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>文档分享</td>
		                        <td><i class="fa fa-close"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
		                    <tr>
		                        <td>v1.0.0之后功能</td>
		                        <td><i class="fa fa-close"></i></td>
		                        <td><i class="fa fa-check"></i></td>
		                    </tr>
	                    </tbody>
	                </table>
                </div>
            </div>
    	</div>
    </section>
    
	<section style="padding-top:10px;padding-bottom: 0px;border-bottom:0;">
		<div class="container text-center">
			<ul class="list-inline m-t-sm">
				<li>
					<span>${Cfg.WEB_COPYRIGHT}</span>
				</li>
			</ul>
	    </div>
	</section>
	
	<!-- FOOTER SECTION -->
	<c:choose>
		<c:when test="${Cfg.WEB_CDN_ENABLE}">
			<script src="//cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>
			<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
			<script src="//cdn.bootcss.com/jquery_lazyload/1.9.7/jquery.lazyload.min.js"></script>
			<script src="js/common/theme.js"></script>
			<script type="text/javascript" src="plugin/bootsnav/js/bootsnav.js"></script>
			
			<!-- cnzz统计 -->
			<script type="text/javascript">
			var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1263206566'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1263206566%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
			$("#cnzz_stat_icon_1263206566").hide();
			</script>
		
			<!-- baidu统计 -->
			<script>
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "https://hm.baidu.com/hm.js?f2d73569c8bccbec048875092fe0591f";
			  var s = document.getElementsByTagName("script")[0]; 
			  s.parentNode.insertBefore(hm, s);
			})();
			</script>
		</c:when>
		
		<c:otherwise>
			<script type="text/javascript" src="plugin/jquery-2.0.3.min.js"></script>
			<script type="text/javascript" src="plugin/bootstrap/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="js/common/theme.js"></script>
			<script type="text/javascript" src="plugin/bootsnav/js/bootsnav.js"></script>
		</c:otherwise>
	</c:choose>
    <!-- END FOOTER SECTION -->
</body>
</html>
