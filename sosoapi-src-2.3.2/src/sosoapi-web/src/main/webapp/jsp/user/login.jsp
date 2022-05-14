<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>登录注册  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
	
	<!-- PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="plugin/magic/magic.css" />
	<style type="text/css">
		.captcha {
			cursor:pointer;
			width:105px;
			height:33px;
		}
	</style>
	<!-- END PAGE LEVEL STYLES -->
</head>
<body class="blank">
	<div class="login-container">
		<div class="row">
	        <div class="col-md-12">
		        <div class="text-center m-b-md">
	                <a href="">
						<img src="${Cfg.IMG_DOMAIN}image/logo.png" class="logImg" alt="Logo" />
					</a>
	            </div>
		            
	            <div class="hpanel">
	                <div class="panel-body">
			            <div class="tab-content">
							<div id="login" class="tab-pane ${isLogin ? 'active' : ''}">
								<form id="loginForm" action="login.htm" class="form-signin" method="post">
									<div class="form-group">
										<div class="text-center m-b-md">
							                <h3>账号登录</h3>
							            </div>
									</div>
									
									<div class="form-group">
										<input id="loginName" type="text" name="loginName" placeholder="邮箱" class="form-control" value="${param.loginName}"/>
									</div>
									
									<div class="form-group">
										<input id="loginPasswd" type="password" name="passwd" placeholder="密码" class="form-control"/>					
									</div>
									
									<div class="form-group" style="margin-top:-10px;">
										<div class="row">
					        	        	<div class="col-lg-8">
												<input type="text" name="validCode" placeholder="验证码" class="form-control"/>
											</div>
											<div class="col-lg-4">
												<img class="captcha" id="loginValidCodeImg" src="captcha/build.htm?oper=login" title="看不清楚，换一张" align="middle"/>
											</div>
										</div>
									</div>
									
									<div class="form-group">
				                    	<label>
				                        	<input id="autoLogin" type="checkbox" name="autoLogin" checked="checked"> 下次自动登录
				                        </label>
				                    </div>
				                   	
									<div class="form-group">
										<button id="loginBtn" class="btn text-muted text-center btn-danger" type="submit">登录</button>
									</div>
								</form>
							</div>
							
							<div id="forgot" class="tab-pane">
								<form id="forgotForm" class="form-signin">
									<div class="form-group">
										<div class="text-center m-b-md">
							                <h3>发送重置密码邮件</h3>
							            </div>
									</div>
									
									<div class="form-group">
										<input id="resetCodeEmail" name="email" type="text" placeholder="E-mail" class="form-control" /> 
									</div>
									
									<div class="form-group">
										<button id="sendResetCodeBtn" class="btn text-muted text-center btn-success" type="submit">发送</button>
									</div>
								</form>
							</div>
							
							<div id="active" class="tab-pane">
								<form id="activeForm" class="form-signin">
									<div class="form-group">
										<div class="text-center m-b-md">
							                <h3>发送账号激活邮件</h3>
							            </div>
									</div>
									
									<div class="form-group">
										<input id="activeCodeEmail" name="email" type="text" placeholder="E-mail" class="form-control" /> 
									</div>
									
									<div class="form-group">
										<button id="sendActiveCodeBtn" class="btn text-muted text-center btn-success" type="submit">发送</button>
									</div>
								</form>
							</div>
							
							<div id="regist" class="tab-pane ${isRegist ? 'active' : ''}">
								<form id="registForm" action="regist/regist.htm" class="form-signin" method="post">
									<div class="form-group">
										<div class="text-center m-b-md">
							                <h3>注册用户</h3>
							            </div>
									</div>
									
									<div class="form-group">
										<input type="text" name="nickName" placeholder="昵称" class="form-control" value="${param.nickName}"/>
									</div>
									
									<div class="form-group">
										<input type="email" name="loginName" placeholder="E-mail" class="form-control" value="${param.loginName }"/>
									</div>
									
									<div class="form-group">
										<input type="password" name="passwd" placeholder="密码" class="form-control" />
									</div>
									
									<div class="form-group">
										<input type="password" name="confirmPasswd" placeholder="密码确认" class="form-control" />
									</div>
									
									<div class="form-group" style="margin-top:-10px;">
										<div class="row">
					        	        	<div class="col-lg-8">
												<input type="text" name="validCode" placeholder="验证码" class="form-control"/>
											</div>
											<div class="col-lg-4">
												<img class="captcha" id="registValidCodeImg" src="captcha/build.htm?oper=regist" title="看不清楚，换一张" align="middle"/>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<button class="btn text-muted text-center btn-success" type="submit">注册</button>
									</div>
								</form>
							</div>
						</div>
						
						<div class="text-center">
							<ul class="list-inline">
								<li>
									<a class="text-muted" href="#login" data-toggle="tab">登录</a>
								</li>
								<li>
									<a class="text-muted" href="#forgot" data-toggle="tab">忘记密码</a>
								</li>
								
								<c:if test="${Cfg.REGIST_EMAIL_VALID}">
									<li>
										<a class="text-muted" href="#active" data-toggle="tab">账号激活</a>
									</li>
								</c:if>
								
								<c:if test="${Cfg.ENABLE_REGIST}">
									<li>
										<a class="text-muted" href="#regist" data-toggle="tab">注册</a>
									</li>
								</c:if>
							</ul>
						</div>
						
						<div class="text-center">
							<c:if test="${not empty param.errorMsg}">
								<div class="alert alert-danger error-msg">
					            	<a class="close" data-dismiss="alert" href="#">×</a>
					               	<strong>${param.errorMsg}</strong>
					           	</div>
							</c:if>
						</div>
						
						<div class="text-center">
							<c:if test="${not empty errorMsg}">
								<div class="alert alert-danger error-msg">
					            	<a class="close" data-dismiss="alert" href="#">×</a>
					               	<strong>${errorMsg}</strong>
					           	</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp"/>
    <script type="text/javascript" src="plugin/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/user/login.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
