<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>在线测试  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/faq/faqMenu.jsp">
		<jsp:param name="menuTest" value="true"/>
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
			                        1、为何执行测试后返回"no response from server"？
			                    </a>
			                    <div id="q1" class="panel-collapse collapse">
			                    	<hr/>
			                        <p>如果执行测试后的效果页和js调试窗口的错误信息如如下2个截图所示，则表示服务端未处理ajax跨域请求。</p>
                                	<div class="row">
										<div class="col-lg-12">
											<p>请求响应：</p>
							        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/faq/1.png">
							        		<p>&nbsp;</p>
							        		<p>js调试控制台：</p>
					        				<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/faq/2.png">
							        	</div>
							        </div>
			                    </div>
			                </div>
			                
			                <div class="panel-body">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#q2" aria-expanded="true">
			                        <i class="fa fa-chevron-down pull-right text-muted"></i>
			                        2、什么是ajax跨域请求？
			                    </a>
			                    <div id="q2" class="panel-collapse collapse">
			                    	<hr/>
			                        <p>概念：只要协议、域名、端口有任何一个不同，都被当作是不同的域。</p>
                                	<p>ajax跨域请求相关知识点可参考文章： 
                                		<a href="http://segmentfault.com/a/1190000000718840" target="_blank">详解js跨域问题</a>
                                		&nbsp;&nbsp;
                                		<a href="http://netsecurity.51cto.com/art/201311/419179.htm" target="_blank">跨域资源共享(CORS)安全性浅析</a>
                                	</p>
                                	<p>事例如下</p>
                                	<p>
                                		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/faq/cors.png">
                                	</p>
			                    </div>
			                </div>
			                
			                <div class="panel-body">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#q3" aria-expanded="true">
			                        <i class="fa fa-chevron-down pull-right text-muted"></i>
			                        3、接口测试时如何处理ajax跨域请求？
			                    </a>
			                    <div id="q3" class="panel-collapse collapse">
			                    	<hr/>
			                        <p>ajax跨域请求相关知识点可参考文章： 
	                                		<a href="http://segmentfault.com/a/1190000000718840" target="_blank">详解js跨域问题</a>
	                                		&nbsp;&nbsp;
	                                		<a href="http://netsecurity.51cto.com/art/201311/419179.htm" target="_blank">跨域资源共享(CORS)安全性浅析</a>
	                                	</p>
	                                	<p>跨域处理目前有如下几种方式：</p>
	                                	<dl>
	                                		<dt>1、服务端设置允许跨域</dt>
	                                		<dd>
	                                			<p>
	                                		以java为例，跨域请求服务端可以通过添加过滤器设置HttpServletResponse中的header来处理，部分源码如下:
<pre>
@Override
public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
	HttpServletResponse resp = (HttpServletResponse)response;
	//"*"存在风险，建议指定可信任的域名来接收响应信息，如"http://www.sosoapi.com"
	resp.addHeader("Access-Control-Allow-Origin", "*");
	//如果存在自定义的header参数，需要在此处添加，逗号分隔
	resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, "
			+ "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, "
			+ "Content-Type, X-E4M-With");
	resp.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");  
	resp.addHeader("Access-Control-Allow-Credentials", "true");
	
	chain.doFilter(request, response);
}
</pre>
	                                			</p>
	                                		</dd>
	                                		
	                                		<dt>2、线下部署</dt>
	                                		<dd>
	                                			<p>参考线下部署章节。</p>
	                                		</dd>
	                                		
	                                		<dt>3、部署代理服务器</dt>
	                                		<dd>
	                                			<p>
	                                				由交流群群友提供，在ajax请求和后台服务器直接添加代理服务器，在代理服务器设置跨域需要的头信息，流程大概为:ajax->proxy->服务器。
	                                			</p>
	                                		</dd>
	                                		
	                                		<dt>4、浏览器屏蔽跨域安全检查</dt>
	                                		<dd>
	                                			<p>
	                                				以chrome为例，可参考<a href="http://www.tuicool.com/articles/AjuqA3" target="_blank">http://www.tuicool.com/articles/AjuqA3</a>。
	                                				如果添加"--disable-web-security --user-data-dir"无效的话，重启下浏览器并确保所有chrome后台进程已被关闭，重启后再次访问就可以了
	                                			</p>
	                                		</dd>
	                                		
	                                		<dt>5、浏览器添加伪造Access-Control-Allow-Origin的插件(建议)</dt>
	                                		<dd>
	                                			<p>
	                                				由交流群群友提供，以chrome为例，下载<a href="http://7xndff.com1.z0.glb.clouddn.com/attach%2FAllow-Control-Allow-Origin.zip">Access-Control-Allow-Origin插件</a>。
	                                			</p>
	                                		</dd>
	                                	</dl>
			                    </div>
			                </div>
			                
			                <div class="panel-body">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#q4" aria-expanded="true">
			                        <i class="fa fa-chevron-down pull-right text-muted"></i>
			                        4、什么是mock服务？
			                    </a>
			                    <div id="q4" class="panel-collapse collapse">
			                    	<hr/>
			                        <p>背景：</p>
			                        <p>在实际项目开发协同过程中，特别是前后端由不同开发人员开发时，前端接口联调必须要等后端接口开发完成后才可以。这种方式使得前端极大的依赖
			                        于后端，使得原本可以并行的工作被阻塞了，特别是流程性质的功能。mock服务就是一种可以提供给前端联调测试并返回约定结构而不依赖于服务端的服务。在服务端未开发完，可以先
			                        使用mock功能，等后端开发完后只需切换请求基地址即可调用到真实的后端服务。</p>
                                	<p>示例说明： </p>
                                	<p>真实的后端接口：http://www.sosoapi.com/user/add.htm</br>
                                	   mock接口：http://www.sosoapi.com/mock/user/add.htm
                                	   </br>
									       那么在实际调用过程中就可以将接口设置为由基路径+请求路径组成，如"{baseUrl}/user/add.htm"，
									       调用真实接口时，设置baseUrl为"http://www.sosoapi.com",调用mock服务时，设置baseUrl为"http://www.sosoapi.com/mock"。在后端接口
									       未开发完成时使用mock服务，开发好后再切换回去即可。
                                	</p>
			                    </div>
			                </div>
			                
			                <div class="panel-body">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#q5" aria-expanded="true">
			                        <i class="fa fa-chevron-down pull-right text-muted"></i>
			                        5、如何使用mock服务？
			                    </a>
			                    <div id="q5" class="panel-collapse collapse">
			                    	<div class="row">
										<div class="col-lg-12">
			                    			<dl>
												<dd>
													<ol type="a">
														<li>
															<p>接口响应配置相关的mock信息：</p>
															<ul>
																<li>静态数据:目前支持json格式数据，每次mock返回填写的固定数据</li>
																<li>mock规则:调用返回的mock数据为根据规则动态生成，规则同<a href="http://mockjs.com/examples.html" target="_blank">mockjs规则</a>。</li>
															</ul>
														</li>
														
														<li>
															<p>配置mock基地址，进入项目详情点击"mock设置"即可看到该项目的mock地址和启用开关。其中</p>
															<ul>
																<li>mock基地址：该地址会优先使用动态数据，如果动态数据没有使用静态数据。可通过参数mockType指定，"rule":动态数据，"data":静态数据</li>
																<li>mock静态基地址：该地址只会使用静态数据，需要配置静态数据。</li>
																<li>mock动态基地址：该地址只会使用动态数据，需要配置mockjs规则。</li>
															</ul>
														</li>
														
														<li>
															<p>sosoapi-demo mock示例，假设mock基地址为：http://www.sosoapi.com/pass/mock/1</p>
															<p>新增用户接口:/user/simple/add.htm，则可按真实接口调用方式来调用，如</p>
															<ul>
																<li>http://www.sosoapi.com/pass/mock/1/user/simple/add.htm</li>
																<li>http://www.sosoapi.com/pass/mock/1/user/simple/add.htm?name=123</li>
															</ul>
															</br>
															<p>查询用户接口:/user/simple/{userId}/info.htm，则可按真实接口调用方式来调用，如</p>
															<ul>
																<li>http://www.sosoapi.com/pass/mock/1/user/simple/123/info.htm</li>
																<li>http://www.sosoapi.com/pass/mock/1/user/simple/123/info.htm?name=123</li>
															</ul>
														</li>
													</ol>
												</dd>
												
												<dt>注意事项</dt>
												<dd>
													<ol>
														<li>默认mock返回的数据为接口响应中名称为"200"或"default"或设置为默认的响应所对应的mock数据，如果有多个，则按取最近的一个</li>
														<li>如果需要返回指定的响应mock数据可通过添加参数"mockRespCode"为指定的响应名称即可</li>
														<li>默认mock是根据请求url和请求方式去匹配对应的接口，如果出现无法匹配情况，可通过添加参数"mockInterId"指定具体接口</li>
														<li>mock基地址,该地址会优先使用动态数据，如果动态数据没有使用静态数据。可通过参数mockType指定，"rule":动态数据，"data":静态数据</li>
														<li>mock请求url可按实际需要的参数进行传递，建议将请求基路径设置为变量，方便后续环境切换</li>
														<li>在线版本的mock已添加跨域处理，无论web前端或app前端可直接调用。</li>
													</ol>
												</dd>
											</dl>
										</div>
									</div>
			                    </div>
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
