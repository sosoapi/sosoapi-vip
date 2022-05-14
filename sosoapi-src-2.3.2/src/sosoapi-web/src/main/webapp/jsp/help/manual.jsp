<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>快速上手 ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	<style type="text/css">
		html {
			overflow:visible;
		}
		
		.sidebar-collapsed .sticky-sidebar{
			position: fixed;
		}
		
		.manual-tree{
			position: fixed;
		    z-index: 1;
		    top: 80px;
		    left: 57px;
		}
		
		.ztree{
		    height: 420px;
		    overflow-y: auto;
		    overflow-x: auto;
		}
		
		.content {
			padding: 20px;
		}
		
		.ztree li span.button.ico_open::before {
		    content: "";
		}
		
		.ztree li span.button.ico_close::before {
		    content: "";
		}
		
		.ztree li span.button.ico_docu::before {
		    content: "";
		}

		.ztree .node_name{
			font-size:14px;
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
		
		.blog-article-box .panel-heading {
		  	padding: 20px 10px;
		  	position: relative;
		  	font-weight: 500;
		  	text-align: center;
		}
		
		.sub-title{
			font-size: 20px;
			font-weight: bold;
			margin:10px 0;
		}
		
		[id ^= 'sub-title-']{
			margin: 20px 0;
		}
		
		.sub-content{
			font-size: 18px;
			line-height: 28px;
		}

		.sub-content li > p{
			text-indent:0; 
		}
				
		.sub-content p{
			text-indent:2em; 
		}
		
		.sub-content ol{
			list-style-type:upper-alpha;
		}
		
		.sub-content img{
			margin-top:20px;
		}
		
		.lazy{
			/* margin: auto; */
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
                	<div class="col-lg-3 manual-tree">
				        <div class="hpanel">
				            <div class="panel-heading hbuilt">
				                <span>目录</span>
				            </div>
				            <div class="panel-body">
			            		<!-- <div id="treeSearch" class="input-group">
	                            	<input id="condition" type="text" class="form-control" placeholder="">
	                            	<span class="input-group-btn">
	                                	<button id="searchBtn" type="button" class="btn btn-white">
	                                		<i class="fa fa-search"></i>
	                                	</button>
	                              	</span>
	                            </div> -->
				            	
								<div class="row">
									<ul id="manualTree" class="ztree"></ul>
								</div>
				            </div>
				        </div>
				    </div>
				    
				    <div class="col-lg-offset-3 col-lg-9">
				    	<div class="hpanel blog-article-box">
				            <div class="panel-heading">
				                <h4>SosoApi用户手册</h4>
				            </div>
				            <div class="panel-body">
				            	<div id="sub-title-1">
				            		<span class="sub-title">1 关于SosoApi</span>
				            		<div class="sub-content">
				            			<p>
				            				SosoApi接口管理系统是基于swagger开发的一套接口管理系统，主要包含2大模块，线上接口编辑和项目管理。其中，各个模块的具体功能点如下：
				            			</p>
				            			<ol>
				            				<li>
				            					<span>线上编辑</span>
				            					<ul>
				            						<li>接口文档基本信息(请求信息，响应，公共参数，错误码等等)</li>
													<li>模块管理</li>
													<li>接口管理</li>
													<li>公共数据结构管理</li>
													<li>在线预览</li>
													<li>在线测试</li>
													<li>Mock服务</li>
													<li>导入导出(swagger,postman,html,word)</li>
				            					</ul>
				            				</li>
				            				<li>
				            					<span>项目管理</span>
				            					<ul>
				            						<li>协助权限控制</li>
													<li>团队成员管理</li>
													<li>接口变更通知</li>
													<li>文档分享</li>
				            					</ul>
				            				</li>
				            			</ol>
				            		</div>
				            	</div>
			            		
			            		<div id="sub-title-2">
			            			<span class="sub-title">2 登录注册</span>
			            			<div class="sub-content">
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-2.1">
			            			<span class="sub-title">2.1 注册</span>
			            			<div class="sub-content">
			            				<p>新用户注册并登陆后才能使用该系统。目前仅支持通过邮箱进行注册。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/login_01.png" class="img-responsive lazy">
			            				<p>填写完注册信息并点击后就会给注册邮箱发送验证链接。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/login_02.png" class="img-responsive lazy">
			            				<p>点击验证链接后即完成注册操作。</p>
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-2.2">
			            			<span class="sub-title">2.2 登录</span>
			            			<div class="sub-content">
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-2.2.1">
			            			<span class="sub-title">2.2.1 直接登录</span>
			            			<div class="sub-content">
			            				<p>如果账号已激活即可通过用户名和密码直接登陆。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/login_03.png" class="img-responsive lazy">
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-2.2.2">
			            			<span class="sub-title">2.2.2 未激活</span>
			            			<div class="sub-content">
			            				<p>如果登陆时提示账号未激活，则需要先激活账号才可以使用。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/login_04.png" class="img-responsive lazy">
			            				<p>点击发送按钮后会给未激活邮箱发送验证码, 用户只需要登陆邮箱点击激活链接即可完成激活账号操作。</p>
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-2.2.3">
			            			<span class="sub-title">2.2.3 忘记密码</span>
			            			<div class="sub-content">
			            				<p>如果忘记登陆密码，则可通过忘记密码功能重置登陆密码进行登陆。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/login_05.png" class="img-responsive lazy">
			            				<p>填写要重置密码的账号后点击发送即可向该邮箱发送重置链接。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/login_06.png" class="img-responsive lazy">
			            				<p>点击该链接后跳到重置密码页面。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/login_07.png" class="img-responsive lazy">
			            				<p>按要求填写新密码并提交就可以完成密码重置。</p>
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-3">
			            			<span class="sub-title">3 用户后台</span>
			            			<div class="sub-content">
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-3.1">
			            			<span class="sub-title">3.1 首页</span>
			            			<div class="sub-content">
			            				<p>首页主要用于提供帮助的快捷入口，包括demo，编辑说明，部署说明和swagger扩展版下载。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_index_01.png" class="img-responsive lazy">
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-3.2">
			            			<span class="sub-title">3.2 项目管理</span>
			            			<div class="sub-content">
			            				<p>创建接口文档前需要先创建一个项目。创建的项目只有在同一个项目中的成员才能看到。其他用户无法看到创建的项目任何信息。</p>
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-3.2.1">
			            			<span class="sub-title">3.2.1 项目列表</span>
			            			<div class="sub-content">
			            				<p>查询当前已创建的项目，并提供创建入口和导入。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_01.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_02.png" class="img-responsive lazy">
			            				<p>点击列表右上角的新增按钮即为创建项目入口，填写项目基本信息并保存后就完成了项目的创建。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_03.png" class="img-responsive lazy">
			            				<p>如果当前已有swagger json文件，也可通过右上角的导入完成项目创建。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_04.png" class="img-responsive lazy">
			            				<p>项目列表同时提供了详情，退出，复制和删除相关入口和操作。</p>
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-3.2.2">
			            			<span class="sub-title">3.2.2 项目详情</span>
			            			<div class="sub-content">
			            				<p>点击项目列表的“详情”即可进入项目详情，主要包括项目基本信息，项目成员，变更通知，变更历史，接口管理和数据结构6大模块。</p>
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-3.2.2.1">
			            			<span class="sub-title">3.2.2.1 项目基本信息</span>
			            			<div class="sub-content">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_05.png" class="img-responsive lazy">
			            				<p>展示项目相关名称，描述，状态等。</p>
			            			</div>
			            		</div>
			            		
			            		<div id="sub-title-3.2.2.2">
			            			<span class="sub-title">3.2.2.2 项目成员</span>
			            			<div class="sub-content">
			            				<p>目前只有在项目中的成员才能看到创建的项目。成员有2种角色，管理员角色和访客角色，管理员角色(创建者或由创建者设置)可以对该项目下的所有功能进行增删改查，访客角色只有查看功能。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_06.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_07.png" class="img-responsive lazy">
			            				<p>点击列表中的编辑可以对成员设置角色和项目昵称，类似qq群昵称。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_08.png" class="img-responsive lazy">
			            				<p>点击成员列表右上角”邀请成员”即可进入邀请成员页面。邀请成员目前有2种方式，直接邀请和邮件邀请。
			            				对于第一次在同一个项目中的成员只能通过邮件邀请，
			            				如果之前有在同一个项目中合作过，那么在“直接加入”列表就会显示该用户信息，按需要调整角色或昵称即可直接加入。
			            				<span class="text-danger">对于邮件邀请的成员，该成员必须用邀请时填入的邮箱进行登陆后再点击邀请连接才可以加入该项目中，
			            				如果未注册需要用被邀请的相同邮箱进行注册，否则会有403错误。</span></p>
			            			</div>
			            		</div>
								
								<div id="sub-title-3.2.2.3">
									<span class="sub-title">3.2.2.3 变更通知</span>
									<div class="sub-content">
										<p>在api接口管理中，如果有接口变更之类的需要通知所有相关人员，可以使用变更通知功能。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_09.png" class="img-responsive lazy">
			            				<p>变更通知对象可以是所有成员或指定某些角色的成员，如果不在项目中也可以在“其他通知邮箱”中填入需要告知的用户。</p>
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.4">
									<span class="sub-title">3.2.2.4 变更日志</span>
									<div class="sub-content">
										<p>如果某次接口改动比较大，为了减少沟通成本和日后查看，可以创建变更日志，用于详细记录此次接口的变更部分。
										与变更通知的区别是，变更日志会随项目一直保留，
										而变更通知只是通知相关人员，发送变更通知时如果选择添加到变更历史中，那么就会在发出邮件通知的同时将该变更保存到日志中。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_10.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5">
									<span class="sub-title">3.2.2.5 接口管理</span>
									<div class="sub-content">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.1">
									<span class="sub-title">3.2.2.5.1 Api文档信息</span>
									<div class="sub-content">
										<p>Api文档信息主要用于配置渲染swagger相关的文档标题，描述，版本等信息。<span class="text-danger">其中，只有发布后才能在“Api文档管理”列表中展示。</span></p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_01.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.2">
									<span class="sub-title">3.2.2.5.2 公共请求参数</span>
									<div class="sub-content">
										<p>如果api接口中有很多请求参数是一样的，则为了编辑方便可以创建公共请求参数，
										这样在渲染成swagger时会自动把公共请求参数加到相关的接口请求参数列表中。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_02.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.3">
									<span class="sub-title">3.2.2.5.3  返回码</span>
									<div class="sub-content">
										<p>Api接口开发过程中对于有不同返回码的情况，可以创建全局返回码，方便开发调试和排错。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_03.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.4">
									<span class="sub-title">3.2.2.5.4  创建模块</span>
									<div class="sub-content">
										<p>接口模块主要用于对接口进行分组，方便管理。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_04.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.5">
									<span class="sub-title">3.2.2.5.5  接口列表</span>
									<div class="sub-content">
										<p>接口列表用于展示当前项目的所有接口，按自定义模块和内置模块（所有接口，回收站，未分组）分类。可根据接口名称和url进行查询。同时每个节点都有相关的功能操作，具体如下：</p>
										<ol>
											<li>
												<span>模块节点</span>
												<p>该节点右侧菜单提供了编辑，删除，新增接口和刷新等功能。</p>	
											</li>
											<li>
												<span>接口节点</span>
												<p>该节点右侧菜单提供编辑，删除和复制等功能菜单。</p>	
											</li>
										</ol>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_05.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.6">
									<span class="sub-title">3.2.2.5.6  创建接口</span>
									<div class="sub-content">
										<p>接口主要由接口基本信息，请求参数和请求响应3部分组成。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_06.png" class="img-responsive lazy">
			            				<ol>
			            					<li>
			            						<span>接口基本信息</span>
			            						<p>主要用于设置接口的基本信息，如名称，url等。其中，“请求url”和“请求方式”唯一确认一个接口，不能重复。</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_01.png" class="img-responsive lazy">
			            					</li>
			            					
			            					<li>
			            						<span>接口请求参数</span>
			            						<p>主要用于设置接口参数列表。</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_02.png" class="img-responsive lazy">
			            						<p>目前支持的“参数位置”如下：</p>
			            						<ul>
			            							<li>body：http请求body，只能有1个body的参数，不能与formData混用，file除外</li>
			            							<li>cookie：本地cookie</li>
			            							<li>formData：表单参数，不能与body混用，file除外</li>
			            							<li>header：http请求header</li>
			            							<li>path：http请求url,如getInfo/{userId}</li>
			            							<li>query：http请求拼接，如getInfo?userId={userId}</li>
			            						</ul>
			            						<br/>
			            						<p>目前支持的“数据类型”如下：</p>
			            						<ul>
			            							<li>string:字符串类型</li>
			            							<li>array:数组类型，支持多层嵌套</li>
			            							<li>object:对象类型，支持多层嵌套</li>
			            							<li>int:短整型</li>
			            							<li>long:长整型</li>
			            							<li>float:浮点型</li>
			            							<li>double:浮点型</li>
			            							<li>decimal:精确到比较高的浮点型</li>
			            							<li>ref:引用类型，即引用定义好的数据结构，共用数据结构</li>
			            							<li>file:文件类型</li>
			            							<li>自定义:自定义的json格式，仅适用于“参数位置”为body的自定义json参数</li>
			            						</ul>
			            					</li>
			            					
			            					<li>
			            						<span>接口响应参数</span>
			            						<p>
			            							<span class="text-danger">支持多种不同情况的返回值，比如同一个用户名和密码，根据角色的不同登陆时可能返回不同角色用户相关信息。一条记录代表一种返回值。</span>
			            						</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_03.png" class="img-responsive lazy">
			            						<p>该图表示返回结果可能有4种。</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_04.png" class="img-responsive lazy">
			            						<p>新增响应时可以根据需要选择不同的数据类型。其中，array，object，ref和自定义用于创建复杂的数据结构。</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_05.png" class="img-responsive lazy">
			            						<p>简单数据类型。</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_06.png" class="img-responsive lazy">
			            						<p>引用数据类型，其中“已有结构”参见“数据结构”章节。</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_07.png" class="img-responsive lazy">
			            						<p>Object或array等复合数据结构，支持水平和垂直拖动创建需要的json数据结构。</p>
			            						<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_08.png" class="img-responsive lazy">
			            						<p>自定义，用于直接录入返回值。</p>
			            					</li>
			            				</ol>
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.7">
									<span class="sub-title">3.2.2.5.7  快捷工具栏</span>
									<div class="sub-content">
										<p>提供常见功能的快捷入口。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_09.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_10.png" class="img-responsive lazy">
			            				<p>预览接口文档。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_11.png" class="img-responsive lazy">
			            				<p>分享设置</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_12.png" class="img-responsive lazy">
			            				<p>Mock设置</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_13.png" class="img-responsive lazy">
			            				<p>导出</p>
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.5.8">
									<span class="sub-title">3.2.2.5.8 Mock服务</span>
									<div class="sub-content">
										<ol>
											<li>
												<span>什么是mock服务？</span>
												<p class="font-bold">背景</p>
												<p>在实际项目开发协同过程中，特别是前后端由不同开发人员开发时，
												前端接口联调必须要等后端接口开发完成后才可以。这种方式使得前端极大的依赖 于后端，
												使得原本可以并行的工作被阻塞了，特别是流程性质的功能。
												mock服务就是一种可以提供给前端联调测试并返回约定结构而不依赖于服务端的服务。
												在服务端未开发完，可以先 使用mock功能，等后端开发完后只需切换请求基地址即可调用到真实的后端服务。</p>
												<p class="font-bold">示例说明：</p>
												<p>真实的后端接口：http://www.sosoapi.com/user/add.htm</p>
												<p>mock接口：http://www.sosoapi.com/mock/user/add.htm</p>
												<p>
													那么在实际调用过程中就可以将接口设置为由基路径+请求路径组成，如"{baseUrl}/user/add.htm"， 
													调用真实接口时，设置baseUrl为"http://www.sosoapi.com",调用mock服务时，
													设置baseUrl为"http://www.sosoapi.com/mock"。在后端接口 未开发完成时使用mock服务，开发好后再切换回去即可。
												</p>
											</li>
											<li>
												<span>如何使用mock服务？</span>
												<p>接口响应配置相关的mock信息：</p>
												<ul>
													<li>静态数据:目前支持json格式数据，每次mock返回填写的固定数据</li>
													<li>mock规则:调用返回的mock数据为根据规则动态生成，规则同mockjs规则。</li>
												</ul>
												<br/>
												<p>配置mock基地址，进入项目详情点击"mock设置"即可看到该项目的mock地址和启用开关。其中</p>
												<ul>
													<li>mock基地址：该地址会优先使用动态数据，如果动态数据没有使用静态数据。可通过参数mockType指定，"rule":动态数据，"data":静态数据</li>
													<li>mock静态基地址：该地址只会使用静态数据，需要配置静态数据。</li>
													<li>mock动态基地址：该地址只会使用动态数据，需要配置mockjs规则。</li>
												</ul>
												<br/>
												<p class="font-bold">sosoapi-demo mock示例:</p>
												<p>假设mock基地址为：http://www.sosoapi.com/pass/mock/1</p>
												<p>新增用户接口:/user/simple/add.htm，则可按真实接口调用方式来调用，如</p>
												<p>http://www.sosoapi.com/pass/mock/1/user/simple/add.htm</p>
												<p>http://www.sosoapi.com/pass/mock/1/user/simple/add.htm?name=123</p>
												<br/>
												<p>查询用户接口:/user/simple/{userId}/info.htm，则可按真实接口调用方式来调用，如</p>
												<p>http://www.sosoapi.com/pass/mock/1/user/simple/123/info.htm</p>
												<p>http://www.sosoapi.com/pass/mock/1/user/simple/123/info.htm?name=123</p>
											</li>
										</ol>
										<br/>
										<p><span class="text-danger">注意事项:</span></p>
										<ul>
											<li>默认mock返回的数据为接口响应中名称为"200"或"default"或设置为默认的响应所对应的mock数据，如果有多个，则按取最近的一个</li>
											<li>如果需要返回指定的响应mock数据可通过添加参数"mockRespCode"为指定的响应名称即可</li>
											<li>默认mock是根据请求url和请求方式去匹配对应的接口，如果出现无法匹配情况，可通过添加参数"mockInterId"指定具体接口</li>
											<li>mock基地址,该地址会优先使用动态数据，如果动态数据没有使用静态数据。可通过参数mockType指定，"rule":动态数据，"data":静态数据</li>
											<li>mock请求url可按实际需要的参数进行传递，建议将请求基路径设置为变量，方便后续环境切换</li>
											<li>版本的mock已添加跨域处理，无论web前端或app前端可直接调用。</li>
										</ul>
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.6">
									<span class="sub-title">3.2.2.6  数据结构</span>
									<div class="sub-content">
										<p>数据结构主要用于多个接口中比较常用的部分，方便引用，类似面向对象编程中的对象，
										创建好之后可在多个接口的请求参数或响应参数为“ref”中引用使用。数据结构的优势是抽取公共部分，方便后续更新维护。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_inter_14.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.7">
									<span class="sub-title">3.2.2.7  文档视图</span>
									<div class="sub-content">
										<p>该功能类似数据库视图功能，用于将特定的接口和错误码组合成不同的视图提供给不同的用户查看。</p>
										<p>将不同的接口和错误码组合成视图后依然具有单文档的预览，分享和下载功能。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_08.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_09.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_10.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.8">
									<span class="sub-title">3.2.2.8  项目角色</span>
									<div class="sub-content">
										<p>该功能用于实现需要对项目权限做细分化控制的用户需求。启用自定义角色后在成员管理页面的角色列表将由"系统项目角色"+"项目角色"2部分组成。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_role_01.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_role_02.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_role_03.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_proj_role_04.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.2.2.9">
									<span class="sub-title">3.2.2.9  接口归档</span>
									<div class="sub-content">
										<p>该功能主要用于将某个时期已经定稿的接口做个归档备份，类似svn里面的tag功能，归档后项目中后续的接口变更不会同步到归档中但依然可以预览和分享给客户查看。
										</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_archive_01.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_archive_02.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.3">
									<span class="sub-title">3.3 Api文档管理</span>
									<div class="sub-content">
										<p>只有在“api文档信息”中设置为发布后才会在该列表中展示。主要用于给访客角色的用户查看已定稿的接口文档。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_doc_07.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.4">
									<span class="sub-title">3.4 个人中心</span>
									<div class="sub-content">
			            			</div>
								</div>
								
								<div id="sub-title-3.4.1">
									<span class="sub-title">3.4.1 账号管理</span>
									<div class="sub-content">
										<p>用于管理用户的账号相关信息，包括个人信息，更改密码，换绑邮箱。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_user_01.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_user_02.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_user_03.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.4.2">
									<span class="sub-title">3.4.2 消息管理</span>
									<div class="sub-content">
										<p>查看用户收到的网站管理员发出的通知或更新等系统信息。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_user_04.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.5">
									<span class="sub-title">3.5 意见反馈</span>
									<div class="sub-content">
										<p>用于反馈在使用过程中发现的问题或需求等信息。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_suggest_01.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.6">
									<span class="sub-title">3.6 网站监控</span>
									<div class="sub-content">
										<p>用于监控线上或测试环境相关的http服务是否可用，并在异常时发出警报通知。</p>
			            			</div>
								</div>
								
								<div id="sub-title-3.6.1">
									<span class="sub-title">3.6.1 http监控管理</span>
									<div class="sub-content">
										<p>用于创建需要监控的http相关服务，保存后将定时调用，并且根据相关预警条件给指定人员发送预警警报，帮助运维人员快速排查和恢复服务</p>
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_01.png" class="img-responsive lazy">
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_02.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.6.2">
									<span class="sub-title">3.6.2 监控日志</span>
									<div class="sub-content">
										<p>定时任务执行日志，点击详情可查看具体执行信息</p>
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_03.png" class="img-responsive lazy">
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_04.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.6.3">
									<span class="sub-title">3.6.3 警报日志</span>
									<div class="sub-content">
										<p>当监控执行返回信息条件满足预警条件时将发出警报，给相关人员发送邮件提醒，目前短信提醒暂不开通。</p>
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_05.png" class="img-responsive lazy">
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_06.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.6.4">
									<span class="sub-title">3.6.4 警报接收者管理</span>
									<div class="sub-content">
										<p>配置警报接收人员，便于出现预警时可第一时间获悉异常信息。</p>
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_07.png" class="img-responsive lazy">
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_08.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-3.6.5">
									<span class="sub-title">3.6.5 警报接收组管理</span>
									<div class="sub-content">
										<p>对预警人员进行分组和预警时间段免打扰设置。</p>
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_09.png" class="img-responsive lazy">
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/user_monitor_10.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4">
									<span class="sub-title">4 管理员后台</span>
									<div class="sub-content">
									</div>
								</div>
								
								<div id="sub-title-4.1">
									<span class="sub-title">4.1 系统管理</span>
									<div class="sub-content">
									</div>
								</div>
								
								<div id="sub-title-4.1.1">
									<span class="sub-title">4.1.1 用户管理</span>
									<div class="sub-content">
										<p>服务于运维过程中对用户的管理，包括锁定，重置密码，激活等。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_user_mgr_01.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_user_mgr_02.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_user_mgr_03.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.1.2">
									<span class="sub-title">4.1.2 角色管理</span>
									<div class="sub-content">
										<p>服务于运维过程中对角色的管理，包括增删改查和权限配置等。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_role_mgr_01.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_role_mgr_02.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_role_mgr_03.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.1.3">
									<span class="sub-title">4.1.3 消息管理</span>
									<div class="sub-content">
										<p>用于运维过程中的系统消息，分为版本升级，公告和其他等类型。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_msg_mgr_01.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_msg_mgr_02.png" class="img-responsive lazy">
			            				<p>可面向特定用户群发送消息。</p>
			            			</div>
								</div>
								
								<div id="sub-title-4.1.4">
									<span class="sub-title">4.1.4 系统参数</span>
									<div class="sub-content">
										<p>用于动态管理网站相关功能开关和配置项设置，包括注册入口，分页每页显示记录数等。</p>
										<p>更改保存后不会立即生效，如需生效可点击图中红色标注按钮。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_sys_cfg_mgr_01.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.2">
									<span class="sub-title">4.2 运营管理</span>
									<div class="sub-content">
			            			</div>
								</div>
								
								<div id="sub-title-4.2.1">
									<span class="sub-title">4.2.1 用户统计</span>
									<div class="sub-content">
										<p>用于统计当天和历史的一个用户数据。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_cube_01.png" class="img-responsive lazy">
									</div>
								</div>
								
								<div id="sub-title-4.2.2">
									<span class="sub-title">4.2.2 项目管理</span>
									<div class="sub-content">
										<p>用于统计所有的项目情况。仅用于统计，因隐私无法查看项目的具体信息。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_proj_mgr_01.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.2.3">
									<span class="sub-title">4.2.3 用户反馈</span>
									<div class="sub-content">
										<p>处理用户的反馈信息，可对反馈信息做回复，处理和删除操作。可根据用户的反馈信息调整运营策略和开发计划。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_suggest_mgr_01.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.2.4">
									<span class="sub-title">4.2.4 系统项目角色</span>
									<div class="sub-content">
										<p>处理系统内置的项目角色，并分配角色相关的权限，便于项目管理员对项目的管理。</p>
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_proj_role_01.png" class="img-responsive lazy">
										<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_proj_role_02.png" class="img-responsive lazy">
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_proj_role_03.png" class="img-responsive lazy">
			            				<p>启用后即可在项目成员页面显示。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_proj_role_04.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.3">
									<span class="sub-title">4.3 网站监控</span>
									<div class="sub-content">
									</div>
								</div>
								
								<div id="sub-title-4.3.1">
									<span class="sub-title">4.3.1 http监控管理</span>
									<div class="sub-content">
										<p>查看系统所有的http监控信息，只能查看标题，无法查看具体配置。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_monitor_mgr_01.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.3.2">
									<span class="sub-title">4.3.2 监控日志</span>
									<div class="sub-content">
										<p>查看系统所有的http监控运行日志信息，只能查看标题。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_monitor_mgr_02.png" class="img-responsive lazy">
			            			</div>
								</div>
								
								<div id="sub-title-4.3.3">
									<span class="sub-title">4.3.3 警报日志</span>
									<div class="sub-content">
										<p>查看系统所有的http监控运行警报日志信息，只能查看标题。</p>
			            				<img data-original="${Cfg.IMG_DOMAIN}image/help/manual/admin_monitor_mgr_03.png" class="img-responsive lazy">
			            			</div>
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
    <script type="text/javascript" src="js/help/manual.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
