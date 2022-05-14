//接口树相关
var sysCfgTreeObj = {
	//初始化树结构
	init:function(){
		var thisObj = this;
		var setting = {
			async: {
				enable: true,
				url:"admin/sys/cfg/json/load.htm",
				autoParam:["id=parentNodeId", "dataId=parentDataId","type=nodeType"],
				otherParam:{"condition":$("#condition").val()},
				dataFilter: ajaxDataFilter,
				headers:{
					"sysReqToken":$("#sysReqToken").val()
				}
			},
			view: {
				expandSpeed:"",
				fontCss: getFont,
				addHoverDom: thisObj.addHoverDom,
				removeHoverDom: thisObj.removeHoverDom,
				selectedMulti: false,
				showLine:false,
				showIcon:true,
				dblClickExpand: false
			},
			edit: {
				enable: false,
				drag: {
					autoExpandTrigger: true
				},
				showRemoveBtn: false,
				showRenameBtn: false
			},
			data: {
				keep:{
					parent:true
				},
				
				simpleData: {
					enable: true
				},
				key:{
					title:"title"
				}
			},
			callback: {
				onClick: thisObj.clickCallback
			}
		};
		
		//初始化树
		$.fn.zTree.init($("#sysCfgTree"), setting);
			
		//初始化查询
		$("#searchBtn").click(function(){
			thisObj.searchTree();
		});
		
		//搜索输入框回车事件
		$("#condition").bind('keypress',function(event){  
	        if(event.keyCode == "13"){  
	        	thisObj.searchTree();
	        }  
	    });  
	},

	//搜索
	searchTree:function(){
		var zTree = $.fn.zTree.getZTreeObj("sysCfgTree");
		zTree.reAsyncChildNodes(null, "refresh",true,{
			async: {
				otherParam:{"condition":$("#condition").val()},
			}
		});
	},
	
	//点击事件
	clickCallback:function (event, treeId, treeNode) {
		if(treeNode.dataId <= 0){
			return ;
		}
		
		sysCfgObj.loadInfo(treeNode.dataId,treeNode.tId);
	},

	//新增节点滑动结构
	addHoverDom:function(treeId, treeNode) {
		//操作节点
		var operId = "node_oper_" + treeNode.tId;
		if($("#" + operId).length > 0){
			return ;
		}
		
		var operJqObj = $("<span class='node_oper' id='" + operId + "'></span>");
		if(treeNode.type == 'cate'){//处理模块节点
			if(treeNode.enableDel != false){
				operJqObj.append("<span class='add' title='新增配置项' onfocus='this.blur();'><i class='fa fa-plus'></i></span>");
			}
			
			if(treeNode.enableEdit != false){
				operJqObj.append("<span class='edit' title='编辑' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
			}
			
			if(treeNode.enableDel != false){
				operJqObj.append("<span class='remove' title='删除' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
			}
			
			operJqObj.append("<span class='refresh' title='加载配置项' onfocus='this.blur();'><i class='fa fa-refresh'></i></span>");
		}
		else if(treeNode.type == 'item'){//处理配置项节点
			operJqObj.append("<span class='edit' title='编辑' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
			operJqObj.append("<span class='remove' title='删除' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
		}
		
		//名称节点
		var nodeNameJqObj = $("#" + treeNode.tId + "_span");
		nodeNameJqObj.after(operJqObj);
		
		//处理新增操作
		operJqObj.find(".add").click(function(){
			if(treeNode.type == "cate"){//新增接口
				sysCfgObj.initAddOper(treeNode.dataId,'item');
			}
			
			return false;
		});
		
		//处理刷新操作
		operJqObj.find(".refresh").click(function(e){
			var zTree = $.fn.zTree.getZTreeObj("sysCfgTree");
			zTree.reAsyncChildNodes(treeNode, "refresh",false);
			return false;
		});
		
		//处理编辑操作
		operJqObj.find(".edit").click(function(){
			//相当于点击链接
			operJqObj.parent().click();
			return false;
		});

		//处理删除操作
		operJqObj.find(".remove").click(function(){
			var msg = "确认执行删除操作？";
			if(treeNode.type == 'cate'){
				msg = "确认删除该分类？<br/>删除后该分类下的配置项移到默认分类。";
			}
			
			var zTree = $.fn.zTree.getZTreeObj("sysCfgTree");
			zTree.selectNode(treeNode);
			bootbox.confirm(msg,function(){
				var param = new Object();
				param.cfgId = treeNode.dataId;
				param.type = treeNode.type;
				doPost("admin/sys/cfg/json/del.htm",param,function(data){
					//删除节点
					zTree.removeNode(treeNode);
					//删除后展示新增页面
					$("#addSysCfgBtn").click();
					
					if(param.type == 'cate'){
						//加载分组下拉框列表
						sysCfgObj.loadCateSelect();
					}
				});
			});
			
			return false;
		});
	},

	//移除节点滑动结构
	removeHoverDom:function (treeId, treeNode) {
		var operJqObj = $("#node_oper_" + treeNode.tId);
		if(operJqObj.length > 0){
			operJqObj.remove();
		}
	},
	
	//更新节点名称
	updateNodeNameByNodeId: function(treeNodeId,nodeName){
		var thisObj = this;
		var zTree = $.fn.zTree.getZTreeObj("sysCfgTree");
		var node = zTree.getNodeByTId(treeNodeId);
		node.name = filterNodeName(nodeName);
		node.title = nodeName;
		
		zTree.updateNode(node);
	},
	
	//添加节点
	addNode:function(nodeInfo){
		var thisObj = this;
		var parentNode = thisObj.getNodeByDataId(nodeInfo.parentDataId,nodeInfo.parentType);
		var zTree = $.fn.zTree.getZTreeObj("sysCfgTree");
		//所属模块节点
		zTree.reAsyncChildNodes(parentNode, "refresh",false,null,function(childrenList){
			var treeNode = thisObj.getNodeByDataId(nodeInfo.dataId,nodeInfo.type);
			//添加后选中当前节点
			zTree.selectNode(treeNode);
		});
	},
	
	//根据dataId属性获取节点
	getNodeByDataId:function(dataId,type){
		if(type == undefined){
			return null;
		}
		
		var thisObj = this;
		var zTree = $.fn.zTree.getZTreeObj("sysCfgTree");
		return zTree.getNodesByFilter(thisObj.getNodeByDataIdFilter,true,null,{
			dataId:dataId,
			type:type
		});
	},
	
	//根据dataId属性获取节点过滤器
	getNodeByDataIdFilter:function(treeNode,filterParam){
		return (treeNode.dataId == filterParam.dataId) && (treeNode.type = filterParam.type);
	}
};

//错误码相关操作
var sysCfgObj = {
	//初始化
	init:function(){
		var thisObj = this;
		//表单验证
		$("#sysCfgForm").bootstrapValidator({
			fields:{
				name:{
	                validators: {
	                    notEmpty: {
	                        message: '名称不能为空'
	                    }
	                }
				}
			}
		});
		
		//保存
		$("#saveSysCfgBtn").click(function(){
			if(isFormValid("sysCfgForm")){
				var operType = $("#sysCfgOperTypeId").val();
				if(operType == 'add'){
					thisObj.addOper();
				}
				else if(operType == 'update'){
					thisObj.updateOper();
				}
			}
		});
		
		//加载分组下拉框列表
		thisObj.loadCateSelect();
		
		$("#typeId").change(function(){
			var type = $("#typeId").val();
			//处理自定义结构
			if(type == "cate"){
				$(".cfg-item").hide();
			}
			else{
				$(".cfg-item").show();
			}
		});
	},

	//初始化编辑操作
	loadInfo:function(cfgId,treeNodeId){
		$("#sysCfgPanel").show();
		//保存当前节点id
		$("#sysCfgForm").data("treeNodeId",treeNodeId);
		
		resetValidForm("sysCfgForm");
		$("#sysCfgOperTypeId").val("update");
		$("#sysCfgOperNameId").html("【编辑】");
		$("#cfgId").val(cfgId);
		
		var param = new Object();
		param.cfgId = cfgId;
		doGet("admin/sys/cfg/json/info.htm",param,function(cfgInfo){
			$("#sysCfgForm").find("*").setFieldsValue(cfgInfo);
			$("#typeId").trigger("change");
		});
	},

	//初始化新增
	initAddOper:function(parentId,type){
		resetValidForm("sysCfgForm");
		$("#cfgId").val("");
		
		$("#sysCfgOperTypeId").val("add");
		$("#sysCfgOperNameId").html("【新增】");
		
		//初始化选中的分组
		if(parentId != undefined){
			$("#parentSelectId").val(parentId);
		}
		
		if(type != undefined){
			$("#typeId").val(type);
		}
		
		$("#typeId").trigger("change");
		
		$("#sysCfgPanel").show();
	},
	
	//新增操作
	addOper:function(){
		var thisObj = this;
		var param = $("#sysCfgForm").find("*").getFieldsValue();
		doPost("admin/sys/cfg/json/add.htm",param,function(cfgNodeInfo){
			notice("保存成功",function(){
				//添加树节点
				sysCfgTreeObj.addNode(cfgNodeInfo);
				
				if(param.type == 'cate'){
					//加载分组下拉框列表
					thisObj.loadCateSelect();
				}
			});
		});
	},

	//编辑操作
	updateOper:function(){
		var param = $("#sysCfgForm").find("*").getFieldsValue();
		doPost("admin/sys/cfg/json/update.htm",param,function(data){
			notice("保存成功",function(){
				//更新树节点名称
				var treeNodeId = $("#sysCfgForm").data("treeNodeId");
				if(treeNodeId){
					sysCfgTreeObj.updateNodeNameByNodeId(treeNodeId,param.name);
				}
				
				if(param.type == 'cate'){
					//加载分组下拉框列表
					thisObj.loadCateSelect();
				}
			});
		});
	},
	
	//加载分组列表
	loadCateSelect:function(){
		$("#parentSelectId").empty();
		$("#parentSelectId").append("<option value='0'>无</option>");
		//初始化模块下拉框
		initSelectRemote("admin/sys/cfg/json/listCate.htm",null,"#parentSelectId");
	},
};

//初始化操作
function initBtnClick(){
	//新增错误码
	$("#addSysCfgBtn").click(function(){
		sysCfgObj.initAddOper();
	});
	
	//重新加载系统配置
	$("#reloadAppCfgBtn").click(function(){
		bootbox.confirm("是否确定使系统配置立即生效？",function(){
			doPost("admin/sys/cfg/json/reloadAppCfg.htm",null,function(data){
				notice("配置已生效!");
			});
		});
	});
}

$(function () {
	//初始化树
	sysCfgTreeObj.init();
	
	//初始化表单
	sysCfgObj.init();
	
	//初始化操作
	initBtnClick();
	
	//默认打开新增页面
	sysCfgObj.initAddOper();
});
