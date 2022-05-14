<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>首页 ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<style type="text/css">
		.quick-btn {
		  	position: relative;
		  	display: inline-block;
		  	width: 90px;
		  	height: 80px;
		  	padding-top: 16px;
		  	margin: 10px;
		  	color: #444444;
		  	text-align: center;
		  	text-decoration: none;
		  	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.6);
		 	border:none;
		}
		
		.quick-btn span {
			display: block;
		}
		
		.quick-btn .label {
		  	position: absolute;
		  	top: -5px;
		  	right: -5px;
		}
		
		.quick-btn:hover {
		  	color: #0053ff;
		  	text-decoration: none;
		}
		
		.quick-btn.small {
		  	width: 40px;
		  	height: 30px;
		  	padding-top: 6px;
		}
	</style>
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
        		<div class="row m-t-md">
		            <div class="col-lg-2 col-lg-offset-2">
		                <div class="hpanel">
		                    <div class="panel-body text-center h-100">
		                        <a class="quick-btn" href="auth/apidoc/preview.htm?docId=1" target="_blank"> 
									<i class="fa fa-bolt fa-2x"></i> 
									<span>Demo</span> 
									<!-- <span class="label label-success">456</span> -->
								</a>
		                    </div>
		                </div>
		            </div>
		            
		            <div class="col-lg-2">
		                <div class="hpanel">
		                    <div class="panel-body text-center h-100">
		                        <a class="quick-btn" href="${Cfg.ONLINE_HELP_URL }"> 
									<i class="fa fa-edit fa-2x"></i>
									<span> 编辑说明</span> 
									<!-- <span class="label label-danger">2</span> -->
								</a> 
		                    </div>
		                </div>
		            </div>
		            
		            <div class="col-lg-2">
		                <div class="hpanel">
		                    <div class="panel-body text-center h-100">
		                        <a class="quick-btn" href="${Cfg.OFFLINE_HELP_URL }"> 
									<i class="fa fa-cloud fa-2x"></i>
									<span> 部署说明</span> 
									<!-- <span class="label label-danger">2</span> -->
								</a> 
		                    </div>
		                </div>
		            </div>
		            
		            <div class="col-lg-2">
		                <div class="hpanel">
		                    <div class="panel-body text-center h-100">
		                        <a class="quick-btn" href="${Cfg.SWAGGER_UI_DOWNLOAD_URL}"> 
									<i class="fa fa-cloud-download fa-2x"></i> 
									<span>SwaggerUI扩展版下载</span> 
								</a>
		                    </div>
		                </div>
		            </div>
		    	</div>
        	</div>
        </div>
        
        <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
   	</div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
</body>
</html>
