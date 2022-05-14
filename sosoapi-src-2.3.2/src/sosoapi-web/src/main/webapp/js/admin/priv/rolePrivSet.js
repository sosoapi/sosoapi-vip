$(function () {
	//初始化权限树
	initPrivTree();
	
	//初始化提交
	initSubmit();
});

//初始化权限树
function initPrivTree(){
	var setting = {
		async: {
			enable: true,
			url:"admin/priv/json/listTree.htm",
			otherParam:{"roleId":parseInt($("#roleId").val())},
			dataFilter: privDataFilter,
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
			chkboxType:{ "Y": "", "N": "" }
		},
		data: {
			simpleData: {
				enable: true
			},
			key:{
				title:"title"
			}
		}
	};
	
	$.fn.zTree.init($("#privTree"), setting);
}

//数据过滤
function privDataFilter(treeId, parentNode, responseData) {
	if (responseData) {
		var data = null;
		var type = null;
    	for(var i =0; i < responseData.length; i++) {
    		data = responseData[i];
    		data.iconSkin = "node_" + data.type;
      	}
  	}
	
	return responseData;
};

//初始化提交
function initSubmit(){
	$("#saveRolePrivBtn").click(function(){
		var nodes = $.fn.zTree.getZTreeObj("privTree").getCheckedNodes(true);
		var privIdList = "";
		for(var i = 0; i < nodes.length; i ++){
			if(nodes[i].dataId == undefined){
				continue;
			}
			
			privIdList += nodes[i].dataId + ",";
		}
		
		var param = new Object();
		param.roleId = $("#roleId").val();
		param.privIdList = privIdList;
		doPost("admin/role/json/updatePriv.htm",param,function(interInfo){
			notice("更新成功",function(){
				redirectUrl("admin/role/list.htm");
			});
		});
	});
}
