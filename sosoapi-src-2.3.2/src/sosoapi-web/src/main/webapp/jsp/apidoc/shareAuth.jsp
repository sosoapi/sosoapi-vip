<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>api文档分享  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>

	<!-- PAGE LEVEL STYLES -->
	<style type="text/css">
		.errorMsg {
			padding-top:20px;
			color:#a94442;
		}
    </style>
	<!-- END PAGE LEVEL STYLES -->
</head>
<body class="sticky-header sidebar-collapsed">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/common/leftEmptyMenu.jsp" />
	<!-- END LEFT SECTION -->

	<!-- body content start-->
    <div class="body-content">
    	<!-- TOP SECTION -->
	    <jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
    	
  		<!--body wrapper start-->
        <div id="wrapper">
        	<div class="lock-container">
	    		<div class="hpanel">
            		<div class="panel-body text-center">
                		<i class="fa fa-lock text-success" style="font-size: 10em;"></i>
                		<br/>
                		<h4><strong>${title}</strong></h4>
                		<form id="shareAuthForm" method="post" action="${formAction}" class="m-t">
							<input type="hidden" name="shareKey" value="${shareKey}">                    
		                    <div class="form-group">
		                        <input class="form-control" type="password" name="password" placeholder="访问密码">
		                    </div>
		                    <button class="btn btn-primary block full-width" type="submit">确定</button>
		                    <div class="form-group errorMsg">
		                        <span>${errorMsg}</span>
		                    </div>
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
        	
        	$("#shareAuthForm").bootstrapValidator({
        		fields:{
        			password:{
                        validators: {
                            notEmpty: {
                                message: '访问密码不能为空'
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
