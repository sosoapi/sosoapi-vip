<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="plugin/zTree/css/awesomeStyle/awesome.css" rel="stylesheet"> -->
	<!-- <link href="plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"> -->
	<link href="plugin/zTree/css/metroStyle/metroStyle.css" rel="stylesheet">
	
	<style type="text/css">
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="blank">
	<div class="content animate-panel">
		<div class="row">
		    <div class="col-lg-6">
		        <div id="nestable-menu">
		        </div>
		    </div>
		</div>

		<div class="row">
		    <div class="col-lg-6">
		        <div class="hpanel">
		            <div class="panel-heading">
		                <div class="panel-tools">
		                    <a class="showhide"><i class="fa fa-chevron-up"></i></a>
		                    <a class="closebox"><i class="fa fa-times"></i></a>
		                </div>
		                <span>简单数据</span>
		            </div>
		            <div class="panel-body">
						<div>
							<ul id="treeDemo" class="ztree"></ul>
						</div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/zTree/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript">
    var setting = {
			check: {
				enable: true,
				chkDisabledInherit: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"disabled 1-1-1", chkDisabled:true},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"disabled 1-2", chkDisabled:true, checked:true, open:true},
			{ id:121, pId:12, name:"disabled 1-2-1", checked:true},
			{ id:122, pId:12, name:"disabled 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"}
		];

		function disabledNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			disabled = e.data.disabled,
			nodes = zTree.getSelectedNodes(),
			inheritParent = false, inheritChildren = false;
			if (nodes.length == 0) {
				alert("请先选择一个节点");
			}
			if (disabled) {
				inheritParent = $("#py").attr("checked");
				inheritChildren = $("#sy").attr("checked");
			} else {
				inheritParent = $("#pn").attr("checked");
				inheritChildren = $("#sn").attr("checked");
			}

			for (var i=0, l=nodes.length; i<l; i++) {
				zTree.setChkDisabled(nodes[i], disabled, inheritParent, inheritChildren);
			}
		}
	
		$(function () {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	    });
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
