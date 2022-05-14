<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>账号管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/user/controlMenu.jsp" />
	<!-- END LEFT SECTION -->
	
	<!-- body content start-->
    <div class="body-content">
    	<!-- TOP SECTION -->
	    <jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
    	
  		<!--body wrapper start-->
        <div id="wrapper">
        	<div class="content animate-panel">
        		<div class="row">
                	<div class="col-lg-12">
                		<div class="hpanel">
	                		<ul class="nav nav-tabs">
	                    		<li class="active">
	                    			<a href="#basicInfo" data-toggle="tab">基本信息</a>
	                           	</li>
	                            <li class="">
	                            	<a href="#updatePasswd" data-toggle="tab">修改密码</a>
	                            </li>
	                            <li class="">
	                            	<a href="#updateEmail" data-toggle="tab">换绑邮箱</a>
	                            </li>
	                     	</ul>
	                            
	                      	<div class="tab-content">
	                        	<div class="tab-pane active" id="basicInfo">	
	                        		<div class="panel-body">
	                        			<form id="userInfoFrom" class="form-horizontal margin-top">
							                <div class="form-group">
							                    <label class="control-label col-lg-4">邮箱</label>
							
							                    <div class="col-lg-4">
							                        <input type="text" name="email" value="${userInfo.email}" class="form-control" readonly>
							                    </div>
							                </div>
						                    
						                    <div class="form-group">
							                    <label class="control-label col-lg-4">角色</label>
							
							                    <div class="col-lg-4">
							                        <input type="text" name="roleName" value="${userInfo.roleName}" class="form-control" readonly>
							                    </div>
							                </div>
							                
						                    <div class="form-group">
							                    <label class="control-label col-lg-4">昵称</label>
							
							                    <div class="col-lg-4">
							                        <input type="text" name="nickName" value="${userInfo.nickName}" class="form-control">
							                    </div>
							                </div>
							                
						                    <div class="form-group">
						                        <div class="col-lg-1 col-lg-offset-4">
						                            <button id="updateInfoBtn" class="btn btn-success btn-lg" type="button">保存</button>
						                        </div>
						                    </div>
									    </form>
	                        		</div>
	                           	</div>
	                            
	                           	<div class="tab-pane" id="updatePasswd">
	                           		<div class="panel-body">
		                           		<form id="updatePasswdFrom" class="form-horizontal margin-top">
							                <div class="form-group">
							                    <label class="control-label col-lg-4">旧密码</label>
							
							                    <div class="col-lg-4">
							                        <input type="password" name="oldPasswd" class="form-control">
							                    </div>
							                </div>
						                    
						                    <div class="form-group">
							                    <label class="control-label col-lg-4">新密码</label>
							
							                    <div class="col-lg-4">
							                        <input type="password" name="newPasswd" class="form-control">
							                    </div>
							                </div>
							                
							                <div class="form-group">
							                    <label class="control-label col-lg-4">确认密码</label>
							
							                    <div class="col-lg-4">
							                        <input type="password" name="confirmPasswd" class="form-control">
							                    </div>
							                </div>
							                
						                    <div class="form-group">
						                        <div class="col-lg-1 col-lg-offset-4">
						                            <button id="updatePasswdBtn" class="btn btn-success btn-lg" type="button">保存</button>
						                        </div>
						                    </div>
									    </form>
								   	</div>
	                           	</div>
	                           	
	                           	<div class="tab-pane" id="updateEmail">
	                           		<div class="panel-body">
		                           		<form id="updateEmailFrom" class="form-horizontal margin-top">
							                <div class="form-group">
							                    <label class="control-label col-lg-4">当前密码</label>
							
							                    <div class="col-lg-4">
							                        <input type="password" name="passwd" class="form-control">
							                    </div>
							                </div>
						                    
						                    <div class="form-group">
							                    <label class="control-label col-lg-4">新邮箱</label>
							
							                    <div class="col-lg-4">
							                        <input type="text" name="email" class="form-control">
							                    </div>
							                </div>
							                
						                    <div class="form-group">
						                        <div class="col-lg-1 col-lg-offset-4">
						                            <button id="sendEmailBtn" class="btn btn-success btn-lg" type="button">发送验证</button>
						                        </div>
						                    </div>
									    </form>
								   	</div>
	                           	</div>
	                    	</div>
                		</div>
                	</div>
                </div>
            </div>
            <!-- END MAIN SECTION -->
        </div>
        
        <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
    <script type="text/javascript" src="js/user/userInfo.js"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
