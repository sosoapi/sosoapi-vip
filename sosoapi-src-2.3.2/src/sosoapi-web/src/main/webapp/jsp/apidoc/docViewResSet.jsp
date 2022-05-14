<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>视图资源设置  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	<style type="text/css">
		.pannel-tools-fixed{
			position: fixed;
		    z-index: 1;
		    top: 92px;
		    right: 4.2%;
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
<body class="sticky-header">
	<jsp:include page="/jsp/project/projMenu.jsp" />
		
	<!-- body content start-->
    <div class="body-content" >
    	<!-- TOP SECTION -->
	    <jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
  
  		<!--body wrapper start-->
        <div id="wrapper">
			<div class="content animate-panel">
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools pannel-tools-fixed">
									<button id="saveViewResBtn" type="button" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
			                    <span>视图资源设置</span>
			                    <a href="javascript:void(0);" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-html="true" class="text-muted" 
			                    	data-content='<span><i class="fa fa-folder-open"></i>：菜单组</span></br><span><i class="fa fa-file-text"></i>：菜单</span></br><span><i class="fa fa-file-text-o"></i>：单独页面</span></br><span><i class="fa fa-star-o"></i>：页面操作</span>' >
                                	<i class="fa fa-question-circle"></i>
                                </a>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
		                			<div class="col-lg-4 col-lg-offset-4">
		                				<input id="viewId" value="${param.viewId}" type="hidden">
		                				<input id="projId" value="${param.projId}" type="hidden">
		                				<input id="docId" value="${param.docId}" type="hidden">
										<ul id="viewResTree" class="ztree"></ul>
									</div>
								</div>
		                	</div>
		                </div>
		          	</div>
		       	</div>
			</div>
		</div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/zTree/ext/js/jquery.ztree.all.ext.js"></script>
    <script type="text/javascript" src="js/common/ztree.js"></script>
    <script type="text/javascript" src="js/apidoc/docViewResSet.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
