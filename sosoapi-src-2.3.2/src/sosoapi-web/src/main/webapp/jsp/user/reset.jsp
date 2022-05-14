<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>密码重置  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
	
	<!-- PAGE LEVEL STYLES -->
	<!-- END PAGE LEVEL STYLES -->
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
	            
	            <div class="hpanel">
	                <div class="panel-body">
	                    <div class="text-center m-b-md">
			                <h3>密码重置</h3>
			            </div>
	                    <form id="resetForm" method="post" action="resetPasswd.htm">
							<input type="hidden" name="code" value="${param.code}">                    
		                    <div class="form-group">
		                    	<label class="control-label" for="passwd">新密码</label>
		                        <input class="form-control" type="password" name="passwd" placeholder="">
		                    </div>
		                    <div class="form-group">
		                    	<label class="control-label" for="confirmPasswd">确认密码</label>
		                        <input class="form-control" type="password" name="confirmPasswd" placeholder="">
		                    </div>
		
		                    <button class="btn btn-success btn-block" type="submit">重置</button>
		                </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    <script type="text/javascript">
        $(function() {
        	//设置提示标题宽度
        	setAlertWidth();
        	
        	$("#resetForm").bootstrapValidator({
        		fields:{
        			passwd:{
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            }
                        }
        			},
                    confirmPasswd: {
                    	trigger:"blur",
                        validators: {
                        	notEmpty: {
                                message: '密码确认不能为空'
                            },
                            identical: {
                                field: 'passwd',
                                message: '输入的密码不一致'
                            }
                        }
                    }
        		}
        	});
        });
        
        function setAlertWidth(){
        	// Setting width of the alert box
            var alert = $('.alert');
            var formWidth = $('.bootstrap-admin-login-form').innerWidth();
            var alertPadding = parseInt($('.alert').css('padding'));
            if (isNaN(alertPadding)) {
                alertPadding = parseInt($(alert).css('padding-left'));
            }
            $('.alert').width(formWidth - 2 * alertPadding);
        }
    </script>
</body>
</html>
