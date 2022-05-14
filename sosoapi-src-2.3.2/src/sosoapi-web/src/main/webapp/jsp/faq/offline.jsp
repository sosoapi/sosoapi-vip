<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>线下部署  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/faq/faqMenu.jsp">
		<jsp:param name="menuOffline" value="true"/>
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
			                        1、如何进行线下部署？
			                    </a>
			                    <div id="q1" class="panel-collapse collapse">
			                    	<hr/>
			                    	<p><a href="${Cfg.OFFLINE_HELP_URL}">线下部署说明文档</a></p>
			                    </div>
			                </div>
			                
			                <div class="panel-body">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#q2" aria-expanded="true">
			                        <i class="fa fa-chevron-down pull-right text-muted"></i>
			                        2、如何处理SwaggerUI界面国际化？
			                    </a>
			                    <div id="q2" class="panel-collapse collapse">
			                    	<hr/>
			                    	<p>
			                                		目前SwaggerUI国际化仅支持英文和中文，部署后编辑{SwaggerUI_HOME}/index.html，设置SwaggerUi的locale即可。
		<pre>
		window.swaggerUi = new SwaggerUi(
		{
			url : url,
			validatorUrl : null,
			dom_id : "swagger-ui-container",
			supportedSubmitMethods : [ 'get', 'post', 'put', 'delete','patch' ],
			//字符集设置
			//en:英文
			//zh_CN:中文(简体)
			locale : "en",
			onComplete : function(swaggerApi, swaggerUi) {
				if (typeof initOAuth == "function") {
					initOAuth({
						clientId : "your-client-id",
						clientSecret : "your-client-secret",
						realm : "your-realms",
						appName : "your-app-name",
						scopeSeparator : ","
					});
				}
		
				if (window.SwaggerTranslator) {
					window.SwaggerTranslator.translate();
				}
		
				$('pre code').each(function(i, e) {
					hljs.highlightBlock(e)
				});
		
				addApiKeyAuthorization();
			},
			onFailure : function(data) {
				log("Unable to Load SwaggerUI");
			},
			docExpansion : "none",
			apisSorter : "alpha",
			showRequestHeaders : false
		});
		</pre>
			                                	</p>
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
