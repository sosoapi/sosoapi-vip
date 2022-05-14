<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>常见问题 ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	<style type="text/css">
		.content {
			padding: 20px;
		}
		
		#treeSearch .input-group-btn button {
			margin-top: -15px;
			padding: 2px 5px;
		}
		
		#treeSearch .form-control {
			margin-top: -10px;
			height: 25px;
			font-size:12px;
		}
	</style>
	<!-- END PAGE LEVEL  STYLES -->
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
        	<div class="content animate-panel">
        		<div class="row">
                	<div class="col-lg-3">
				        <div class="hpanel">
				            <div class="panel-heading hbuilt">
				                <span>常见问题</span>
				            </div>
				            <div class="panel-body">
			            		<div id="treeSearch" class="input-group">
	                            	<input id="condition" type="text" class="form-control" placeholder="">
	                            	<span class="input-group-btn">
	                                	<button id="searchBtn" type="button" class="btn btn-white">
	                                		<i class="fa fa-search"></i>
	                                	</button>
	                              	</span>
	                            </div>
				            	
								<div class="row">
									<ul id="faqTree" class="ztree"></ul>
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
    <script type="text/javascript" src="plugin/zTree/ext/js/jquery.ztree.all.ext.js?v=1.0.1"></script>
    <script type="text/javascript" src="js/help/faq.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
