<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>角色权限设置  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	<style type="text/css">
		.pannel-tools-fixed{
			position: fixed;
		    z-index: 1;
		    top: 92px;
		    right: 4.2%;
		}
		
		.ztree li span.button::before {
		    color: #32323a;
		}
		
		.ztree li span.button.chk.checkbox_true_part::before {
		    color: #32323a;
		}
		
		/* 菜单组样式 */
		.node_cate_ico_open:before{
			content: "\f07c";
		}
		.node_cate_ico_docu:before,
		.node_cate_ico_close:before{
			content: "\f07b";
		}
		
		/* 菜单样式 */
		.node_menu_ico_open:before,
		.node_menu_ico_close:before,
		.node_menu_ico_docu:before{
			content: "\f15c";
		}
		
		/* 页面样式 */
		.node_page_ico_open:before,
		.node_page_ico_close:before,
		.node_page_ico_docu:before{
			content: "\f0f6";
		}
		
		/* 操作样式 */
		.node_oper_ico_open:before,
		.node_oper_ico_close:before,
		.node_oper_ico_docu:before{
			content: "\f006";
		}
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/user/controlMenu.jsp" />
	<!-- END LEFT SECTION -->
		
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
									<button id="saveRolePrivBtn" type="button" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
			                    <span>角色权限设置</span>
			                    <a href="javascript:void(0);" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-html="true" class="text-muted" 
			                    	data-content='<span><i class="fa fa-folder-open"></i>：菜单组</span></br><span><i class="fa fa-file-text"></i>：菜单</span></br><span><i class="fa fa-file-text-o"></i>：单独页面</span></br><span><i class="fa fa-star-o"></i>：页面操作</span>' >
                                	<i class="fa fa-question-circle"></i>
                                </a>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
		                			<div class="col-lg-4 col-lg-offset-4">
		                				<input id="roleId" value="${param.roleId}" type="hidden">
										<ul id="privTree" class="ztree"></ul>
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
    <script type="text/javascript" src="js/admin/priv/rolePrivSet.js?v=1.0.0"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
