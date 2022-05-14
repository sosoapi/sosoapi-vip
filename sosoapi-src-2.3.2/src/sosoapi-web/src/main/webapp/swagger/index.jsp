<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Swagger UI</title>
	<link rel="icon" type="image/png" href="${Cfg.WEB_BASE_URL}swagger/images/favicon-32x32.png" sizes="32x32" />
	<link rel="icon" type="image/png" href="${Cfg.WEB_BASE_URL}swagger/images/favicon-16x16.png" sizes="16x16" />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/typography.css' media='screen' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/reset.css' media='screen' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/screen.css' media='screen' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/reset.css' media='print' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/print.css' media='print' rel='stylesheet' type='text/css' />
	
	<style type="text/css">
		#header .swagger-ui-wrap{
			/* margin-left:20px;
			margin-right:20px; */
		}

		#api_selector{
			/* margin-right:200px; */
		}
		
		.ext-hidden {
			display: none;
		}
		
		#condition {
			width: 410px;
		}
		
		#apiName {
			
		}
		
		#apiKey {
			width:170px;
		}
		
		#apiPosition {
			font-size: 0.9em;
			height: 1.8em;
			width: 180px;
		}
		
		#btn-api-auth {
			display: block;
			text-decoration: none;
			font-weight: bold;
			padding: 6px 8px;
			font-size: 0.9em;
			color: white;
			background-color: #547f00;
			border-radius: 4px;
		}
		
		ul.api-auth{
			display: none;
			position: absolute;
			top: 40px;
			overflow: hidden;
			background-color: #fff;
			overflow-y: auto;
			border: 1px solid #999;
			border-top: 0;
			box-shadow: 0 3px 5px #999;
			z-index: 9999;
			padding:10px 0;
		}
		
		ul.api-auth li {
			height: 30px;
			line-height: 30px;
			overflow: hidden;
			padding: 0 10px;
			cursor: pointer;
			width:100%;
		}
		
		.input-select{
			width: 200px;
			height: 25px;
			background: #fff url(../../swagger/images/input-select.jpg) no-repeat right center;
		}

		.input-select input {
			background-color: #fff;
			position:absolute;
			border: 0;
			outline: 0;
			background: none;
		}
		
		.input-select ul {
			width: 200px;
			display: none;
			position: absolute;
			top: 40px;
			overflow: hidden;
			background-color: #fff;
			overflow-y: auto;
			/* border: 1px solid #999; */
			border-top: 0;
			box-shadow: 0 3px 5px #999;
			z-index: 9999;
		}
		
		.input-select ul li {
			height: 30px;
			line-height: 30px;
			overflow: hidden;
			padding: 0 10px;
			cursor: pointer;
		}
		
		.input-select ul li.on {
			background-color: #e0e0e0;
		}
		
		#apiBtn {
		    text-decoration: none;
		    font-weight: bold;
		    padding: 6px 8px;
		    font-size: 0.9em;
		    color: white;
		    background-color: #547f00;
		    -moz-border-radius: 4px;
		    -webkit-border-radius: 4px;
		    -o-border-radius: 4px;
		    -ms-border-radius: 4px;
		    -khtml-border-radius: 4px;
		    border-radius: 4px;
		}
		
		.dev-status a{
			text-transform: uppercase;
		    text-decoration: none !important;
		    color: white !important;
		    background-color: #e67e22;
		    display: inline-block;
		    width: 50px;
		    font-size: 0.7em;
		    text-align: center;
		    padding: 7px 4px 4px 4px;	
		    border-radius: 2px;		
		    text-decoration: none;
		}
	</style>
	
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery.slideto.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery.wiggle.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/handlebars-2.0.0.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/underscore-min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/backbone-min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/swagger-ui-ext.js?version=1.1.8' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/highlight.7.3.pack.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/marked.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/swagger-oauth.js' type='text/javascript'></script>

	<!-- Some basic translations -->
	<!-- <script src='${Cfg.WEB_BASE_URL}swagger/lang/translator.js' type='text/javascript'></script> -->
	<!-- <script src='${Cfg.WEB_BASE_URL}swagger/lang/ru.js' type='text/javascript'></script> -->
	<!-- <script src='${Cfg.WEB_BASE_URL}swagger/lang/en.js' type='text/javascript'></script> -->

	<script type="text/javascript">
		$(function() {
			//初始化swaggerUI
			initSwaggerUi();
			
			//初始化接口授权
			initApiAuth();
			
			//初始化文档浏览
			initExplore();
			
			//国际化
			$("#language").change(function(){
				window.swaggerUi.setLocale($("#language").val());
				window.swaggerUi.load();
			});
		});
		
		//初始化swaggerUI
		function initSwaggerUi(){
			var url = getApiUrl();
	
			// Pre load translate...
			if (window.SwaggerTranslator) {
				window.SwaggerTranslator.translate();
			}
			window.swaggerUi = new SwaggerUi(
					{
						url : url,
						validatorUrl : null,
						dom_id : "swagger-ui-container",
						supportedSubmitMethods : [ 'get', 'post', 'put', 'delete','patch' ],
						//字符集设置
						//en:英文
						//zh_CN:中文(简体)
						locale : "zh_CN",
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
							
							//初始化文档环境下拉框
							initEnv();
						},
						onFailure : function(data) {
							log("Unable to Load SwaggerUI");
						},
						docExpansion : "none",
						//模块排序
						apisSorter : "sortWeight",
						//模块内部方法排序
						operationsSorter : "sortWeight",
						//方法响应排序
						operationResponsesSorter : "sortWeight",
						showRequestHeaders : true
					});
	
			window.swaggerUi.load();
		}
		
		//初始化文档浏览
		function initExplore(){
			//浏览接口文档
			$("#explore").click(function(){
				var apiUrl = getApiUrl();
				var loadUrl = apiUrl;
				if(apiUrl.indexOf("?") != -1){
					loadUrl += "&condition=" + decodeURIComponent($("#condition").val());
				}
				else{
					loadUrl += "?condition=" + decodeURIComponent($("#condition").val());
				}
				
				$("#input_baseUrl").val(loadUrl);
				
				if (window.SwaggerTranslator) {
					window.SwaggerTranslator.translate();
				}
			});
			
			//回车查询
			$("body").keydown(function() {
			    if (event.keyCode == "13") {//keyCode=13是回车键
			        $("#explore").click();
			    }
			});    
		}
		
		//获取文档url
		function getApiUrl(){
			var url = window.location.search.match(/url=([^&]+)/);
			if (url && url.length > 1) {
				url = decodeURIComponent(url[1]);
			} 
			else {
				/* url = "http://petstore.swagger.io/v2/swagger.json"; */
				url = $("#docUrl").val();
			}
			
			return url;
		}
		
		//初始化接口授权
		function initApiAuth(){
			//接口访问令牌设置
			$(".api-auth").hide();
			
			$("#btn-api-auth").click(function(){
				$("ul.api-auth").show();
			});
			
			$("#apiBtn").click(function(){
				addApiKeyAuthorization();
				$("ul.api-auth").hide();
			});
		}
		
		function addApiKeyAuthorization() {
			var apiName = $("#apiName").val();
			if(!apiName || apiName.trim() == ''){
				apiName = "api_key";
			}
			
			var apiPosition = $("#apiPosition").val();
			if(!apiPosition || apiPosition.trim() == ''){
				apiPosition = "query";
			}
			
			var apiKey = encodeURIComponent($('#apiKey').val());
			if (apiKey && apiKey.trim() != "") {
				window.swaggerUi.api.clientAuthorizations.removeAll();
				var apiKeyAuth = new SwaggerClient.ApiKeyAuthorization(
						apiName, apiKey, apiPosition);
				window.swaggerUi.api.clientAuthorizations.add(apiName,
						apiKeyAuth);
				log("added key " + apiKey);
			}
		}
		
		function log() {
			if ('console' in window) {
				console.log.apply(console, arguments);
			}
		}
		
		//初始化环境
		function initEnv(){
			$('.input-select').click(function(e){
			    $('.input-select').find('ul').hide();
			    $(this).find('ul').show();
				e.stopPropagation();
			});

			$('.input-select li').hover(function(e){
				$(this).toggleClass('on');
				e.stopPropagation();
			});

			$('.input-select li').click(function(e){
				var apiBaseUrlJqObj = $(this).parents('.input-select').find('input');
				apiBaseUrlJqObj.val($(this).attr("data-value"));
				$('.input-select ul').hide();
				
				e.stopPropagation();
			});

			$(document).click(function(){
				$('.input-select ul').hide();
			});
		}
	</script>
</head>

<body class="swagger-section">
	<input type="hidden" id="docUrl" value="${docUrl}">
	<div id='header'>
		<div class="swagger-ui-wrap" style="max-width:1000px;">
			<a id="logo" href="http://swagger.io">swagger</a>
			<form id='api_selector'>
				<div class='input'>
					<input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="hidden"/>
				</div>
				<div class='input'>
					<input placeholder="接口名称  /  请求url  /  标签" id="condition" name="condition" type="text" />
				</div>
				<div class='input'>
					<a id="explore" href="javascript:void(0);">Explore</a>
				</div>
				<div class='input'>
					<!-- <a id="explore" href="#" data-sw-translate>Explore</a> -->
					<a id="btn-api-auth" href="javascript:void(0);">接口令牌设置</a>
					<ul class="input api-auth ext-hidden">
						<li>
							<input placeholder="接口令牌名称" id="apiName" name="apiName" type="text" />
						</li>
						<li>
							<input placeholder="接口令牌值" id="apiKey" name="apiKey" type="text" />
						</li>
						<li>
							<select id="apiPosition">
								<option value="header">令牌位置-header</option>
								<option value="query">令牌位置-query</option>
							</select>
						</li>
						<li>
							<a id="apiBtn" href="javascript:void(0);">确定</a>
						</li>
					</ul>
				</div>
				<div class='input'>
					<select id="language" class="ext-hidden">
						<option value="en">English</option>
						<option value="zh_CN">中文(简体)</option>
					</select>
				</div>
				
				<div class='input input-select'>
					<input id="apiBaseUrl" type="text" placeholder="设置接口基路径">
					<ul id="apiEnvSelect"></ul>
				</div>
			</form>
		</div>
	</div>

	<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
	<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
	
	<script id="errorCodeTmpl" type="text/html">
		<li id="resource_cust_ext_error_code" class="resource">
			<div class="heading">
				<h2>
					<a href="#!/cust_ext_error_code" class="toggleEndpointList" data-id="cust_ext_error_code">全局返回码</a>
				</h2>
				<ul class="options">
					<li>
						<a href="#!/cust_ext_error_code" id="endpointListTogger_cust_ext_error_code" class="toggleEndpointList" data-id="cust_ext_error_code" data-sw-translate="">显示/隐藏</a>
					</li>
					<li>
						<a href="#" class="collapseResource" data-id="cust_ext_error_code" data-sw-translate=""> 收缩 </a></li>
					<li>
						<a href="#" class="expandResource" data-id="cust_ext_error_code" data-sw-translate=""> 展开 </a>
					</li>
				</ul>
			</div>
			<ul class="endpoints" id="cust_ext_error_code_endpoint_list" style="display: none;">
				<li class="endpoint">
					<ul class="operations">
						<li class="post operation" id="cust_ext_error_code_get_1">
							<div class="content" id="cust_ext_error_code_get_1_content" style="display: block;border-top:1px solid #c3d9ec;">
								<table class="fullwidth">
									<thead>
										<tr>
											<th data-sw-translate="">返回码</th>
											<th data-sw-translate="">返回信息</th>
											<th data-sw-translate="">说明</th>
										</tr>
									</thead>
									<tbody class="operation-status">
										<!-- <tr>
											<td width="15%" class="code">200</td>
											<td class="markdown"></td>
											<td width="70%"></td>
										</tr> -->
									</tbody>
								</table>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</script>
</body>
</html>


