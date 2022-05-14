<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"> -->
	<!-- <link href="plugin/zTree/css/awesomeStyle/awesome.css" rel="stylesheet"> -->
	<!-- <link href="plugin/zTree/css/metroStyle/metroStyle.css" rel="stylesheet"> -->
	
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	
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
		    <div class="col-lg-3">
		        <div class="hpanel">
		            <div class="panel-heading hbuilt">
		                <div class="panel-tools">
		                    <a class="showhide"><i class="fa fa-chevron-up"></i></a>
		                    <a class="closebox"><i class="fa fa-times"></i></a>
		                </div>
		                <span>复合结构</span>
		            </div>
		            <div class="panel-body">
		            	<div class="row">
		            		<div class="input-group">
                            	<span class="input-group-btn">
                                	<button type="button" class="btn btn-white"><i class="fa fa-search"></i></button>
                              	</span>
                            	<input type="text" class="form-control">
                            </div>
		            	</div>
		            	
						<div class="row">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
		            </div>
		        </div>
		    </div>
		    
		    <div class="col-lg-9">
		        <div class="hpanel">
		            <div class="panel-heading">
		                <div class="panel-tools">
		                    <!-- <a class="showhide"><i class="fa fa-chevron-up"></i></a>
		                    <a class="closebox"><i class="fa fa-times"></i></a> -->
		                </div>
		                <span>数据展示</span>
		            </div>
		            <div class="panel-body">
						<div class="row">
							<textarea id="nodeInfo" class="form-control" rows="10"></textarea>
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
	
    <script type="text/javascript">
    $(function () {
    	var treeObj = $("#treeDemo");
    	$.fn.zTree.init(treeObj, setting,zNodes);
    	/* $.fn.zTree.init(treeObj, setting); */
    });
    
    var zNodes =[
     			{ id:1, pId:0, name:"随意拖拽 1", open:true},
     			{ id:11, pId:1, name:"随意拖拽 1-1"},
     			{ id:12, pId:1, name:"随意拖拽 1-2,chkDisabled",chkDisabled:true},
     			{ id:111, pId:11, name:"随意拖拽 1-1"},
     			{ id:112, pId:11, name:"随意拖拽 1-2"},
     			{ id:121, pId:12, name:"随意拖拽 1-2-1,chkDisabled",chkDisabled:true,checked:true},
     			{ id:122, pId:12, name:"随意拖拽 1-2-2"},
     			{ id:123, pId:12, name:"随意拖拽 1-2-3"},
     			{ id:13, pId:1, name:"禁止拖拽 1-3", open:true, drag:false,checked:true},
     			{ id:131, pId:13, name:"禁止拖拽 1-3-1", drag:false},
     			{ id:132, pId:13, name:"禁止拖拽 1-3-2", drag:false},
     			{ id:132, pId:13, name:"禁止拖拽 1-3-3", drag:false},
     			{ id:2, pId:0, name:"禁止子节点移走 2", open:true, childOuter:false},
     			{ id:21, pId:2, name:"我不想成为父节点 2-1", dropInner:false},
     			{ id:22, pId:2, name:"我不要成为根节点 2-2", minLevel:1},
     			{ id:23, pId:2, name:"拖拽试试看 2-3"},
     			{ id:3, pId:0, name:"禁止子节点排序/增加 3", open:true, childOrder:false, dropInner:false},
     			{ id:31, pId:3, name:"随意拖拽 3-1"},
     			{ id:32, pId:3, name:"随意拖拽 3-2"},
     			{ id:33, pId:3, name:"随意拖拽 3-3"}
     		];
    
    var setting = {
		async: {
			enable: true,
			url:"demo/tree//loadData.htm",
			autoParam:["id", "name=n", "level=lv"],
			otherParam:{"otherParam":"zTreeAsyncTest"},
			dataFilter: ajaxDataFilter
		},
		view: {
			expandSpeed:"",
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false,
			showLine:false,
			showIcon:true,
			dblClickExpand: false
		},
		edit: {
			enable: true,
			drag: {
				autoExpandTrigger: true
			},
			removeTitle:'删除',
			renameTitle:'编辑',
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		check: {
			enable: true,
			chkDisabledInherit: true,
			chkboxType:{ "Y": "ps", "N": "ps" }
		},
		data: {
			simpleData: {
				enable: true
			},
			key:{
				title:"title"
			}
		},
		callback: {
			beforeRemove: beforeRemoveCallback,
			beforeRename: beforeRenameCallback,
			beforeDrop: beforeDropCallback,
			onDrop: dropCallback,
			onClick: clickCallback
		}
	};

    //拖拽结束事件
    function dropCallback(event, treeId, treeNodes, targetNode, moveType){
    	var info = " moveType:" + moveType + ",\r\n target name:" + targetNode.name + ",\r\n target level:" + targetNode.level + ",\r\n target tId:" + targetNode.tId;
    	$("#nodeInfo").val(info);
    }
    
    //点击事件
    function clickCallback(event, treeId, treeNode) {
    	var info = " name:" + treeNode.name + ",\r\n level:" + treeNode.level + ",\r\n tId:" + treeNode.tId;
    	$("#nodeInfo").val(info);
    }
    
	function beforeRemoveCallback(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}		
	
	function beforeRenameCallback(treeId, treeNode, newName) {
		if (newName.length == 0) {
			setTimeout(function() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.cancelEditName();
				alert("节点名称不能为空.");
			}, 0);
			return false;
		}
		return true;
	}

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		if(treeNode.enableAdd == false){
			return ;
		}
		
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='新增接口' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(event){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.addNodes(treeNode, {pId:treeNode.id,enableAdd:false, name:"new node" + (newCount++)});
			return false;
		});
	};
	
	function removeHoverDom(treeId, treeNode) {
		if($("#addBtn_"+treeNode.tId).length > 0){
			$("#addBtn_"+treeNode.tId).unbind().remove();
		}
	};
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
