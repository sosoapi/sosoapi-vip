<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>表格  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/project/projMenu.jsp" />
	<!-- END LEFT SECTION -->
		
	<!-- body content start-->
    <div class="body-content" >
    	<!-- TOP SECTION -->
	    <jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
  
  		<!--body wrapper start-->
        <div id="wrapper">
        	<div class="small-header transition animated fadeIn">
		        <div class="hpanel">
		            <div class="panel-body">
		                <div id="hbreadcrumb" class="pull-right">
		                    <ol class="hbreadcrumb breadcrumb">
		                        <li><a href="index.html">Dashboard</a></li>
		                        <li>
		                            <span>Forms</span>
		                        </li>
		                        <li class="active">
		                            <span>Text editor </span>
		                        </li>
		                    </ol>
		                </div>
		                <h2 class="font-light m-b-xs">
		                    Text editor
		                </h2>
		                <small>Lorem Ipsum has been the dustrys</small>
		            </div>
		        </div>
		    </div>
		    
			<div class="content animate-panel">
				<!-- TOOLBAR SECTION -->
             	<jsp:include page="/jsp/apidoc/toolBar.jsp">
					<jsp:param name="projId" value="${param.projId}" />
					<jsp:param name="docId" value="${param.docId}" />
				</jsp:include>
				<!-- TOOLBAR SECTION -->
				
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>查询</span>
			                </div>
		                	<div class="panel-body">
		                		
		                	</div>
		                </div>
				   	</div>
				</div>
				
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                        
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                   	<span>接口列表</span>
			                </div>
		                	<div class="panel-body">
		                		<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
			                            <jsp:include page="/jsp/common/paginate.jsp"/>
	                            	</div>
	                            </div>
	                            
		                	</div>
		                </div>
				   	</div>
				</div>
				
				<!-- MODAL SECTION -->
				
				<!-- END MODAL SECTION -->
		   	</div>
        </div>
        <!--body wrapper end-->
        
        <!--footer section start-->
	    <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
	    <!--footer section end-->
	</div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/apidoc/interList.js?version=1.0.3"></script>
    <script type="text/javascript" src="js/apidoc/toolBar.js"></script>
    <!-- END FOOTER SECTION -->
    
</body>
</html>
