$(function () {
	//初始化视图树
	initViewTree();
	
	//初始化提交
	initSubmit();
});

//初始化视图树
function initViewTree(){
	var setting = {
		async: {
			enable: true,
			url:"auth/doc/view/json/load.htm",
			otherParam:{"docId":$("#docId").val(),"viewId":$("#viewId").val()},
			dataFilter: ajaxDataFilter,
			headers:{
				"sysReqToken":$("#sysReqToken").val()
			}
		},
		view: {
			expandSpeed:"",
			selectedMulti: false,
			showLine:false,
			showIcon:true,
			dblClickExpand: false
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
		callback:{
			beforeAsync:function(treeId,treeNode){
				return treeNode == null;
			}
		}
	};
	
	$.fn.zTree.init($("#viewResTree"), setting);
}

//初始化提交
function initSubmit(){
	$("#saveViewResBtn").click(function(){
		var nodes = $.fn.zTree.getZTreeObj("viewResTree").getCheckedNodes(true);
		
		var resArray = new Array();
		var resObj = null;
		var node = null;
		for(var i = 0; i < nodes.length; i ++){
			node = nodes[i];
			if(node.dataId == undefined){
				continue;
			}
			
			resObj = new Object();
			resObj.dataId = node.dataId;
			resObj.type = node.type;
			resArray[i] = resObj;
		}
		
		var param = new Object();
		param.viewId = $("#viewId").val();
		param.docId = $("#docId").val();
		param.resInfo = JSON.stringify(resArray);
		doPost("auth/doc/view/json/updateRes.htm",param,function(result){
			notice("更新成功",function(){
				redirectUrl("auth/doc/view/list.htm?projId=" + $("#projId").val() + "&docId=" + $("#docId").val());
			});
		});
	});
}
