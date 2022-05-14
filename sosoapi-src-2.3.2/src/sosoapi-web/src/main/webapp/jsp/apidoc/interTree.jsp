<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>接口管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	<link href="plugin/nestable/ext/css/nestable.ext.css?v=1.0.1" rel="stylesheet">
	<link href="plugin/bootstrap-table/dist/bootstrap-table.min.css" rel="stylesheet">
	
	<style type="text/css">
		.pannel-tools-fixed{
			position: fixed;
		    z-index: 1;
		    top: 134px;
		    right: 3%;
		}
		
		.nodeInfo .panel-tools .btn {
			padding: 4px 12px;
		}
		
		#mainContent.content {
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
		
		#respSchemaFormModal .modal-dialog {
			width: 800px;
			margin: 30px auto;
		}
		
		.dd-handle-drag {
			height: 47px;
		}
		
		.dd-handle-drag:before {
			top: 14px;
		}
		
		.dd-item>button {
			margin-top: 15px;
		}
		
		.cust-ico{
			color:#a6a6a6;
		}
		
		.node_cata_code_ico_open:before,
		.node_cata_code_ico_close:before{
			content: "\f06a";
		}
		
		.node_cata_schema_ico_open:before,
		.node_cata_schema_ico_close:before{
			content: "\f247";
		}
		
		.node_cata_inter_ico_open:before,
		.node_cata_inter_ico_close:before{
			content: "\f0ce";
		}
		
		.node_module_def_ico_open:before,
		.node_module_def_ico_close:before{
			content: "\f0e8";
		}
		
		.node_module_recycle_ico_open:before,
		.node_module_recycle_ico_close:before{
			content:"\f1b8";
		}
		
		.node_module_no_group_ico_open:before,
		.node_module_no_group_ico_close:before{
			content:"\f119";
		}
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header sidebar-collapsed">
	<input type="hidden" id="enableInterList" value="${Cfg.ENABLE_INTER_LIST}"/>
	<input type="hidden" id="docId" value="${param.docId}"/>
	<input type="hidden" id="projId" value="${param.projId}"/>
	<!-- 功能权限列表 -->
	<textarea id="operPerm" class="hide"><sosoapi:printPerm projId="${param.projId}" privType="oper"></sosoapi:printPerm></textarea>
	
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
        	<!-- TOOLBAR SECTION -->
            <jsp:include page="/jsp/apidoc/toolBar.jsp">
				<jsp:param name="projId" value="${param.projId}" />
				<jsp:param name="docId" value="${param.docId}" />
			</jsp:include>
			<!-- TOOLBAR SECTION -->
				
			<div id="mainContent" class="content animate-panel">
				<div class="row">
				    <div class="col-lg-3">
				        <div class="hpanel">
				            <div class="panel-heading hbuilt">
				                <div class="panel-tools">
				                	<a href="javascript:;" id="apiSettingBtn">
					                    <span class="fa fa-cog fa-2x"></span>
					               	</a>
					               	
				                    <a href="javascript:;" data-toggle="dropdown">
					                    <span class="fa fa-plus-circle fa-2x"></span>
					               	</a>
				                	<ul class="dropdown-menu">
				                		<sosoapi:hasPermission name="docCode:add" projId="${param.projId}">
				                			<li>
					                     		<a href="javascript:void(0);" id="addErrorCodeBtn">
					                     			<i class="fa fa-exclamation-circle cust-ico"></i> 
				            						<span>新增错误码</span>
					                     		</a>
					                     	</li>
					                     	<li class="divider"></li>
				                		</sosoapi:hasPermission>
				                		
				                		<sosoapi:hasPermission name="docModule:add" projId="${param.projId}">
				                			<li>
					                     		<a href="javascript:void(0);" id="addModuleBtn">
					                     			<i class="fa fa-folder cust-ico"></i> 
				            						<span>新增模块</span>
					                     		</a>
					                     	</li>
					                     	<li class="divider"></li>
				                		</sosoapi:hasPermission>
			                          	
			                          	<sosoapi:hasPermission name="inter:addDetail" projId="${param.projId}">
			                          		<li>
					                     		<a href="javascript:void(0);" id="addInterBtn">
													<i class="fa fa-list-alt cust-ico"></i> 
													<span>新增接口</span>
					                     		</a>
					                     	</li>
			                          	</sosoapi:hasPermission>
				                 	</ul>
				                </div>
				                <%-- <span>${projTempMap[param.projId].name}</span> --%>
				                <span>接口管理</span>
				                <a href="javascript:void(0);" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="模块和接口可通过拖拽排序。" class="text-muted">
                                	<i class="fa fa-question-circle"></i>
                                </a>
				            </div>
				            <div class="panel-body">
			            		<div id="treeSearch" class="input-group">
	                            	<input id="condition" type="text" class="form-control" placeholder="接口名称  /  请求url  /  标签">
	                            	<span class="input-group-btn">
	                                	<button id="searchBtn" type="button" class="btn btn-white">
	                                		<i class="fa fa-search"></i>
	                                	</button>
	                              	</span>
	                            </div>
				            	
								<div class="row">
									<ul id="interTree" class="ztree"></ul>
								</div>
				            </div>
				        </div>
				    </div>
				    
				    <div class="col-lg-9">
				    	<jsp:include page="/jsp/apidoc/tree/apiSetting.jsp"/>
				        <jsp:include page="/jsp/apidoc/tree/moduleInfo.jsp"/>
				        <jsp:include page="/jsp/apidoc/tree/errorCodeInfo.jsp"/>
				        <jsp:include page="/jsp/apidoc/tree/interInfo.jsp"/>
				         <jsp:include page="/jsp/apidoc/tree/interList.jsp"/>
				    </div>
				</div>
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
    <script type="text/javascript" src="plugin/zTree/ext/js/jquery.ztree.all.ext.js?v=1.0.1"></script>
	<script type="text/javascript" src="plugin/nestable/ext/js/jquery.nestable.ext.js?v=1.0.0"></script>
	<c:if test="${Cfg.ENABLE_INTER_LIST}">
		<script type="text/javascript" src="plugin/bootstrap-table/dist/bootstrap-table.min.js"></script>
		<script type="text/javascript" src="plugin/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
	</c:if>
	
	<script type="text/javascript" src="plugin/extend/jquery.changed.tip.js?v=1.0.0"></script>
	<script type="text/javascript" src="js/apidoc/toolBar.js"></script>
    <script type="text/javascript" src="js/common/ztree.js?v=1.0.2"></script>
    <script type="text/javascript" src="js/apidoc/tree/apiSetting.js?v=1.0.4"></script>
    <script type="text/javascript" src="js/apidoc/tree/interInfo.js?v=1.1.0"></script>
    <script type="text/javascript" src="js/apidoc/interTree.js?v=1.0.8"></script>
    <!-- END FOOTER SECTION -->
    
</body>
</html>
