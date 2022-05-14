<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<meta http-equiv="refresh" content="3;URL=${Cfg.WEB_BASE_URL}pass/goods/list.htm">
	<title>支付成功  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
	            
	            <div class="hpanel">
                	<div class="panel-body">
                		<div class="pull-right text-muted m-l-lg"></div>
                       	<h3>支付成功</h3>
                        <hr/>
                        <div class="note-content">
							<span id="countDownId" style="color: #f76120;">3</span>秒后自动为您跳转到<a href="pass/goods/list.htm">下载页面</a>
                       	</div>
                	</div>
                </div>
	        </div>
		</div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
   	<script type="text/javascript">
   		$(function(){
   			countDown("#countDownId",3);
   		});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
