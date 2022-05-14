<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<c:if test="${!Cfg.REGIST_EMAIL_VALID}">
		<meta http-equiv="refresh" content="3;URL=forwardLogin.htm">
	</c:if>
	
	<title>注册成功  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="blank">
	<div class="info-container">
		<div class="row">
			<div class="col-md-12">
	            <div class="text-center m-b-md">
	                <a href="">
						<img src="${Cfg.IMG_DOMAIN}image/logo.png" class="logImg" alt="Logo" />
					</a>
	            </div>
	            
	            <c:choose>
	            	<c:when test="${Cfg.REGIST_EMAIL_VALID}">
	            		<div class="hpanel">
		                	<div class="panel-body">
		                		<div class="pull-right text-muted m-l-lg"></div>
		                       	<h3>注册成功</h3>
		                        <hr/>
		                        <div class="note-content">
									<input id="email" type="hidden" value="${param.email}">
									<h3>已发送验证邮件到<span style="color: #f76120;">${param.email}</span></h3>
									<p>请您进入邮箱中点击验证链接完成注册</p>
									<p class="buttons-group">
										<button id="forwardActiveBtn" type="button" class="btn btn-lg btn-success" style="weight:30px;">去邮箱验证</button>
									</p>
									<p><a id="resendBtn" href="javascript:void(0);">没收到？重新发送</a></p>
		                       	</div>
		                	</div>
		                </div>
	            	</c:when>
	            	
	            	<c:otherwise>
	            		<div class="hpanel">
		                	<div class="panel-body">
		                		<div class="pull-right text-muted m-l-lg"></div>
		                       	<h3>注册成功</h3>
		                        <hr/>
		                        <div class="note-content">
									<span id="countDownId" style="color: #f76120;">3</span>秒后自动为您跳转到登录页。
		                       	</div>
		                	</div>
		                </div>
	            	</c:otherwise>
	            </c:choose>
	        </div>
		</div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
