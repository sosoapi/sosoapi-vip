<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>关于我们  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/faq/faqMenu.jsp">
		<jsp:param name="menuAbout" value="true"/>
	</jsp:include>
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
                		<div class="hpanel panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			                <div class="panel-body">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#q1" aria-expanded="true">
			                        <i class="fa fa-chevron-down pull-right text-muted"></i>
			                        1、qq群
			                    </a>
			                    <div id="q1" class="panel-collapse collapse">
			                    	<hr/>
			                        <p>&nbsp;&nbsp;SosoApi开发者交流群①：305629848</p>
			                        <p>&nbsp;&nbsp;SosoApi开发者交流群②：317297749</p>
		                    		<p>SwagggerUI技术交流群：482162806</p>
			                    </div>
			                </div>
			                
			                <div class="panel-body">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#q2" aria-expanded="true">
			                        <i class="fa fa-chevron-down pull-right text-muted"></i>
			                        2、联系我们
			                    </a>
			                    <div id="q2" class="panel-collapse collapse">
			                    	<hr/>
			                        <p>您可以通过如下方式与我们联系。</p>
				                    <ul>
				                    	<li>邮箱：3259073638@qq.com</li>
				                    	<li>&nbsp;&nbsp;&nbsp;qq：3259073638</li>
				                    </ul>
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
</body>
</html>
