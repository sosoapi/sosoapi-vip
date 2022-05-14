//静态数据
var staticInfo = {
	"cataNodeIdCode":-10,
	"cataNodeIdSchema":-20,
	"cataNodeIdInter":-30,
	"moduleNodeIdDef":-1,
	"moduleNodeIdRecycle":-2,
	"moduleNodeIdNoGroup":-3
};

//权限相关
var privObj = {
	//权限编码
	privPerm:$("#operPerm").val(),
	
	//判断是否包含指定权限
	hasPerm:function(perm){
		return this.privPerm.indexOf(perm) != -1;
	},

	enableModuleAdd:function(){
		return this.hasPerm("docModule:add");
	},
	
	enableModuleUpdate:function(){
		return this.hasPerm("docModule:update");
	},
	
	enableCodeAdd:function(){
		return this.hasPerm("docCode:add");
	},
	
	enableCodeUpdate:function(){
		return this.hasPerm("docCode:update");
	},
	
	enableInterTreeLoad:function(){
		return this.hasPerm("interTree:load");
	},
	
	enableInterTreeSort:function(){
		return this.hasPerm("interTree:sort");
	},
	
	enableInterTreeDel:function(){
		return this.hasPerm("interTree:del");
	},
	
	enableInterDetailInfo:function(){
		return this.hasPerm("inter:detailInfo");
	},
	
	enableInterDetailAdd:function(){
		return this.hasPerm("inter:addDetail");
	},
	
	enableInterDetailUpdate:function(){
		return this.hasPerm("inter:updateDetail");
	}
}

//接口树相关
var interTreeObj = {
	//初始化树结构
	init:function(){
		//无查看接口权限
		if(!privObj.enableInterTreeLoad()){
			return ;
		}
		
		var thisObj = this;
		var setting = {
			async: {
				enable: true,
				url:"auth/doc/inter/tree/json/load.htm",
				autoParam:["id=parentNodeId", "dataId=parentDataId","type=nodeType"],
				otherParam:{"docId":$("#docId").val(),"condition":$("#condition").val()},
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
				enable: true,
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
				beforeDrop: thisObj.beforeDropCallbackCust,
				onDrop: thisObj.dropCallback,
				onClick: thisObj.clickCallback
			}
		};
		
		//初始化树
		$.fn.zTree.init($("#interTree"), setting);
			
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
		var zTree = $.fn.zTree.getZTreeObj("interTree");
		zTree.reAsyncChildNodes(null, "refresh",true,{
			async: {
				otherParam:{"docId":$("#docId").val(),"condition":$("#condition").val()},
			}
		});
	},
	
	//拖拽前置处理
	beforeDropCallbackCust:function(treeId, treeNodes, targetNode, moveType, isCopy){
		if(!privObj.enableInterTreeSort()){
			return false;
		}
		
		return beforeDropCallback(treeId, treeNodes, targetNode, moveType, isCopy);
	},
	
	//拖拽
	dropCallback:function (event, treeId, treeNodes, targetNode, moveType){
		if(targetNode == null){
			return ;
		}
		
		var treeNode = treeNodes[0];
		
		var src = new Object();
		src.dataId = treeNode.dataId;
		src.parentDataId = treeNode.parentDataId;
		src.type = treeNode.type;
		src.parentType = treeNode.parentType;
		src.isParent = treeNode.isParent;
		
		var target = new Object();
		target.dataId = targetNode.dataId;
		target.parentDataId = targetNode.parentDataId;
		target.type = targetNode.type;
		target.parentType = targetNode.parentType;
		target.isParent = targetNode.isParent;
		
		var param = new Object();
		param.srcNodeInfo = JSON.stringify(src);
		param.targetNodeInfo = JSON.stringify(target);
		param.docId = $("#docId").val();
		param.moveType = moveType;
		doPost("auth/doc/inter/tree/json/sort.htm",param,function(){
			//处理不同模块下的接口拖动，更新对应接口模块下的接口数目
			if(src.type != 'inter'){//源对象不是接口拖动，直接返回
				return ;
			}
			
			var srcParentDataId = null;
			var targetParentDataId = null;
			if(target.type != 'inter'){//目标对象不是接口，则为模块
				if(src.parentDataId != target.dataId){
					srcParentDataId = src.parentDataId;
					targetParentDataId = target.dataId;
				}
			}
			else {//目标对象也是接口
				if(src.parentDataId != target.parentDataId){
					srcParentDataId = src.parentDataId;
					targetParentDataId = target.parentDataId;
				}
			}
			
			//源对象和目标对象父节点都不为空
			if(srcParentDataId != null && targetParentDataId != null){
				var srcParentNode = interTreeObj.getNodeByDataId(srcParentDataId,'module');
				var targetParentNode = interTreeObj.getNodeByDataId(targetParentDataId,'module');
				//parentId未更新，导出错误
//				interTreeObj.updateNodeName(srcParentNode,srcParentNode.title,srcParentNode.childrenCount - 1);
//				interTreeObj.updateNodeName(targetParentNode,targetParentNode.title,targetParentNode.childrenCount + 1);
				
				var zTree = $.fn.zTree.getZTreeObj("interTree");
				zTree.reAsyncChildNodes(srcParentNode, "refresh",true,null,function(childrenList){
					//更新接口总数
					interTreeObj.updateNodeName(srcParentNode,srcParentNode.title,childrenList.length);
				});
				
				zTree.reAsyncChildNodes(targetParentNode, "refresh",false,null,function(childrenList){
					//更新接口总数
					interTreeObj.updateNodeName(targetParentNode,targetParentNode.title,childrenList.length);
					
					var currNode = interTreeObj.getNodeByDataId(src.dataId,'inter');
					//添加后选中当前节点
					zTree.selectNode(currNode);
				});
			}
		});
	},

	//点击事件
	clickCallback:function (event, treeId, treeNode) {
		if(treeNode.dataId <= 0){
			return ;
		}
		
		interObj.isChanged(function(){
			if(treeNode.type == 'module'){//加载模块信息
				moduleObj.loadInfo(treeNode.dataId,treeNode.tId);
			}
			else if(treeNode.type == 'inter'){//加载接口信息
				interObj.loadInfo(treeNode.dataId,false,treeNode.tId);
			}
			else if(treeNode.type == 'code'){//加载错误码信息
				errorCodeObj.loadInfo($("#docId").val(),treeNode.dataId,treeNode.tId);
			}
		});
	},

	//新增节点滑动结构
	addHoverDom:function(treeId, treeNode) {
		//操作节点
		var operId = "node_oper_" + treeNode.tId;
		if($("#" + operId).length > 0){
			return ;
		}
		
		var operJqObj = $("<span class='node_oper' id='" + operId + "'></span>");
		if(treeNode.type == 'cata'){//处理分类节点
			if(treeNode.dataId == staticInfo.cataNodeIdCode && privObj.enableCodeAdd()){
				operJqObj.append("<span class='add' title='新增返回码' onfocus='this.blur();'><i class='fa fa-plus'></i></span>");
			}
			else if(treeNode.dataId == staticInfo.cataNodeIdInter && privObj.enableModuleAdd()){
				operJqObj.append("<span class='add' title='新增模块' onfocus='this.blur();'><i class='fa fa-plus'></i></span>");
			}
			operJqObj.append("<span class='refresh' title='重新加载' onfocus='this.blur();'><i class='fa fa-refresh'></i></span>");
		}
		else if(treeNode.type == 'module'){//处理模块节点
			var cataId = interTreeObj.getModuleCateId(treeNode.id);
			//处理接口模块
			if(cataId == staticInfo.cataNodeIdInter){
				if(privObj.enableInterDetailInfo()){
					operJqObj.append("<span class='add' title='新增接口' onfocus='this.blur();'><i class='fa fa-plus'></i></span>");
				}
				
				if(treeNode.enableEdit != false && privObj.enableModuleUpdate()){
					operJqObj.append("<span class='edit' title='编辑模块' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
				}
				
				if(treeNode.enableDel != false && privObj.enableInterTreeDel()){
					operJqObj.append("<span class='remove' title='删除模块' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
				}
				
				operJqObj.append("<span class='refresh' title='加载接口' onfocus='this.blur();'><i class='fa fa-refresh'></i></span>");
				
				//后台控制，默认隐藏
				if($("#enableInterList").val() == 'true'){
					operJqObj.append("<span class='list' title='列表展示' onfocus='this.blur();'><i class='fa fa-list'></i></span>");
				}
			}
			//处理返回码模块
			else if(cataId == staticInfo.cataNodeIdCode){
				if(privObj.enableCodeAdd()){
					operJqObj.append("<span class='add' title='新增返回码' onfocus='this.blur();'><i class='fa fa-plus'></i></span>");
				}
				
				if(treeNode.enableEdit != false && privObj.enableModuleUpdate()){
					operJqObj.append("<span class='edit' title='编辑模块' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
				}
				
				if(treeNode.enableDel != false && privObj.enableInterTreeDel()){
					operJqObj.append("<span class='remove' title='删除模块' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
				}
				
				operJqObj.append("<span class='refresh' title='加载返回码' onfocus='this.blur();'><i class='fa fa-refresh'></i></span>");
			}
		}
		else if(treeNode.type == 'inter'){//处理接口节点
			if(privObj.enableInterDetailAdd()){
				operJqObj.append("<span class='copy' title='复制接口' onfocus='this.blur();'><i class='fa fa-copy'></i></span>");
			}
			
			if(privObj.enableInterDetailUpdate()){
				operJqObj.append("<span class='edit' title='编辑接口' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
			}
			
			if(privObj.enableInterTreeDel()){
				operJqObj.append("<span class='remove' title='删除接口' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
			}
		}
		else if(treeNode.type == 'code'){//处理错误码节点
			if(privObj.enableCodeUpdate()){
				operJqObj.append("<span class='edit' title='编辑错误码' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
			}
			
			if(privObj.enableInterTreeDel()){
				operJqObj.append("<span class='remove' title='删除错误码' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
			}
		}
		
		//名称节点
		var nodeNameJqObj = $("#" + treeNode.tId + "_span");
		nodeNameJqObj.after(operJqObj);
		
		//处理新增操作
		operJqObj.find(".add").click(function(){
			interObj.isChanged(function(){
				if(treeNode.type == "module"){
					var cataId = interTreeObj.getModuleCateId(treeNode.id);
					if(cataId == staticInfo.cataNodeIdInter){//处理接口模块
						interObj.initAddOper(treeNode.dataId);
					}
					else if (cataId == staticInfo.cataNodeIdCode){//处理返回码模块
						errorCodeObj.initAddOper(treeNode.dataId);
					}
				}
				else if(treeNode.type == "cata"){
					if(treeNode.dataId == staticInfo.cataNodeIdCode){
						errorCodeObj.initAddOper();
					}
					else if(treeNode.dataId == staticInfo.cataNodeIdInter){
						moduleObj.initAddOper();
					}
				}
			});
			
			return false;
		});
		
		//处理复制操作
		operJqObj.find(".copy").click(function(e){
			interObj.isChanged(function(){
				if(treeNode.type == "inter"){//复制接口
					interObj.loadInfo(treeNode.dataId,true);
				}
			});
			
			return false;
		});
		
		//处理刷新操作
		operJqObj.find(".refresh").click(function(e){
			interObj.isChanged(function(){
				var zTree = $.fn.zTree.getZTreeObj("interTree");
				zTree.reAsyncChildNodes(treeNode, "refresh",false,null,function(childrenList){
					if(treeNode.type == 'module'){
						//更新接口总数
						interTreeObj.updateNodeName(treeNode,treeNode.title,childrenList.length);
					}
				});
			})
			
			return false;
		});
		
		//处理展示接口列表
		operJqObj.find(".list").click(function(e){
			interObj.isChanged(function(){
				if(treeNode.type == "module"){
					interListObj.loadInfo(treeNode.dataId);
				}
			});
			
			return false;
		});
		
		//处理编辑操作
		operJqObj.find(".edit").click(function(){
			interObj.isChanged(function(){
				//相当于点击链接
				operJqObj.parent().click();
			});
			
			return false;
		});

		//处理删除操作
		operJqObj.find(".remove").click(function(){
			interObj.isChanged(function(){
				var msg = "确认执行删除操作？";
				if(treeNode.type == 'module'){
					msg = "确认删除该模块？<br/>删除后该模块下接口移到默认分组。";
				}
				
				var zTree = $.fn.zTree.getZTreeObj("interTree");
				zTree.selectNode(treeNode);
				bootbox.confirm(msg,function(){
					var param = new Object();
					param.dataId = treeNode.dataId;
					param.type = treeNode.type;
					param.docId = $("#docId").val();
					doPost("auth/doc/inter/tree/json/del.htm",param,function(data){
						//删除节点
						zTree.removeNode(treeNode);
						//删除后展示api文档配置信息
						$("#apiSettingBtn").click();
						
						//删除模块
						if(treeNode.type == 'module'){
							//更新接口编辑模块选择下拉框
							interBasicObj.loadModuleSelect();
						}
						else if(treeNode.type == 'inter'){
							//更新父节点
							var parentNode = treeNode.getParentNode();
							interTreeObj.updateNodeName(parentNode,parentNode.title,parentNode.childrenCount - 1);
						}
					});
				});
			});
			
			return false;
		});
	},

	//获取模块对应的分类id
	getModuleCateId:function(treeId){
		return parseInt(treeId.split("_")[1]);
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
		var zTree = $.fn.zTree.getZTreeObj("interTree");
		var node = zTree.getNodeByTId(treeNodeId);
		thisObj.updateNodeName(node, nodeName, node.childrenCount);
	},
	
	//更新节点名称
	updateNodeName: function(treeNode,nodeName,childrenCount){
		var zTree = $.fn.zTree.getZTreeObj("interTree");
		treeNode.name = filterNodeName(nodeName);
		treeNode.title = nodeName;
		if(treeNode.type == 'module' && childrenCount != undefined){
			treeNode.name = treeNode.name + " [" + childrenCount + "]";
			treeNode.childrenCount = childrenCount;
    	}
		zTree.updateNode(treeNode);
	},
	
	//添加节点
	addNode:function(nodeInfo){
		var thisObj = this;
		var parentNode = thisObj.getNodeByDataId(nodeInfo.parentDataId,nodeInfo.parentType);
		var zTree = $.fn.zTree.getZTreeObj("interTree");
		if(nodeInfo.type == 'module'){//模块节点，添加后选中
			var treeNode = zTree.addNodes(parentNode,-1,nodeInfo,true)[0];
			thisObj.updateNodeName(treeNode,treeNode.name,0);
			//添加后选中当前节点
			zTree.selectNode(treeNode);
		}
		else if(nodeInfo.type == 'inter'){//接口节点，添加后刷新所在模块节点并选中添加的接口节点
			//所属模块节点
			zTree.reAsyncChildNodes(parentNode, "refresh",false,null,function(childrenList){
				//更新父节点
				thisObj.updateNodeName(parentNode,parentNode.title,childrenList.length);
				
				var treeNode = thisObj.getNodeByDataId(nodeInfo.dataId,nodeInfo.type);
				//添加后选中当前节点
				zTree.selectNode(treeNode);
			});
		}
		if(nodeInfo.type == 'code'){//错误码节点，添加后选中
			//所属模块节点
			zTree.reAsyncChildNodes(parentNode, "refresh",false,null,function(childrenList){
				var treeNode = thisObj.getNodeByDataId(nodeInfo.dataId,nodeInfo.type);
				//添加后选中当前节点
				zTree.selectNode(treeNode);
			});
		}
	},
	
	//根据dataId属性获取节点
	getNodeByDataId:function(dataId,type){
		if(type == undefined){
			return null;
		}
		
		var thisObj = this;
		var zTree = $.fn.zTree.getZTreeObj("interTree");
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

//模块相关操作
var moduleObj = {
	//初始化
	init:function(){
		var thisObj = this;
		$("#moduleForm").bootstrapValidator({
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
		
		$("#saveModuleBtn").click(function(){
			if(isFormValid("moduleForm")){
				var operType = $("#moduleOperTypeId").val();
				if(operType == 'add'){
					thisObj.addOper();
				}
				else if(operType == 'update'){
					thisObj.updateOper();
				}
			}
		});
	},

	//初始化编辑操作
	loadInfo:function(moduleId,treeNodeId){
		$(".nodeInfo").hide();
		$("#modulePanel").show();
		//保存当前节点id
		$("#moduleForm").data("treeNodeId",treeNodeId);
		
		resetValidForm("moduleForm");
		$("#moduleOperTypeId").val("update");
		$("#moduleId").val(moduleId);
		doGet("auth/doc/module/json/info.htm","moduleId=" + moduleId,function(moduleInfo){
			$("#moduleForm").find("*").setFieldsValue(moduleInfo);
		});
	},

	//初始化新增
	initAddOper:function(){
		resetValidForm("moduleForm");
		$("#moduleId").val("");
		
		$("#moduleOperTypeId").val("add");
		$(".nodeInfo").hide();
		$("#modulePanel").show();
	},
	
	//新增操作
	addOper:function(){
		var param = $("#moduleForm").find("*").getFieldsValue();
		doPost("auth/doc/module/json/add.htm",param,function(moduleNodeInfo){
			notice("保存成功",function(){
				//添加树节点
				interTreeObj.addNode(moduleNodeInfo);
				
				//更新接口编辑模块选择下拉框
				interBasicObj.loadModuleSelect();
			});
		});
	},

	//编辑操作
	updateOper:function(){
		var param = $("#moduleForm").find("*").getFieldsValue();
		doPost("auth/doc/module/json/update.htm",param,function(data){
			notice("保存成功",function(){
				//更新树节点名称
				var treeNodeId = $("#moduleForm").data("treeNodeId");
				if(treeNodeId){
					interTreeObj.updateNodeNameByNodeId(treeNodeId,param.name);
				}
				
				//更新接口编辑模块选择下拉框
				interBasicObj.loadModuleSelect();
			});
		});
	}
};

//错误码相关操作
var errorCodeObj = {
	//初始化
	init:function(){
		var thisObj = this;
		$("#errorCodeForm").bootstrapValidator({
			fields:{
				code:{
	                validators: {
	                    notEmpty: {
	                        message: '错误码不能为空'
	                    }
	                }
				}
			}
		});
		
		$("#saveErrorCodeBtn").click(function(){
			if(isFormValid("errorCodeForm")){
				var operType = $("#errorCodeOperTypeId").val();
				if(operType == 'add'){
					thisObj.addOper();
				}
				else if(operType == 'update'){
					thisObj.updateOper();
				}
			}
		});
		
		//初始化模块选择下拉框
//		thisObj.loadModuleSelect();
	},

	//初始化编辑操作
	loadInfo:function(docId,codeId,treeNodeId){
		$(".nodeInfo").hide();
		$("#errorCodePanel").show();
		//保存当前节点id
		$("#errorCodeForm").data("treeNodeId",treeNodeId);
		
		resetValidForm("errorCodeForm");
		$("#errorCodeOperTypeId").val("update");
		$("#codeId").val(codeId);
		
		var param = new Object();
		param.docId = docId;
		param.codeId = codeId;
		doGet("auth/doc/code/json/info.htm",param,function(errorCodeInfo){
			$("#errorCodeForm").find("*").setFieldsValue(errorCodeInfo);
		});
	},

	//加载模块列表
	loadModuleSelect:function(){
		$("#errorCodeModuleId").empty();
		//初始化模块下拉框
		initSelectRemote("auth/doc/module/json/list.htm","docId=" + $("#docId").val(),"#errorCodeModuleId");
	},
	
	//初始化新增
	initAddOper:function(moduleId){
		resetValidForm("errorCodeForm");
		$("#codeId").val("");
		if(moduleId != undefined){
			$("#errorCodeModuleId").val(moduleId);
		}
		
		$("#errorCodeOperTypeId").val("add");
		$(".nodeInfo").hide();
		$("#errorCodePanel").show();
	},
	
	//新增操作
	addOper:function(){
		var param = $("#errorCodeForm").find("*").getFieldsValue();
		doPost("auth/doc/code/json/add.htm",param,function(codeNodeInfo){
			notice("保存成功",function(){
				//添加树节点
				interTreeObj.addNode(codeNodeInfo);
			});
		});
	},

	//编辑操作
	updateOper:function(){
		var param = $("#errorCodeForm").find("*").getFieldsValue();
		doPost("auth/doc/code/json/update.htm",param,function(data){
			notice("保存成功",function(){
				//更新树节点名称
				var treeNodeId = $("#errorCodeForm").data("treeNodeId");
				if(treeNodeId){
					interTreeObj.updateNodeNameByNodeId(treeNodeId,param.code);
				}
			});
		});
	}
};

//接口列表
var interListObj = {
	init:function(){
		var thisObj = this;
		//初始化模块下拉框
		thisObj.loadModuleSelect();
		
		//初始化表格控件
		$("#interListTable").bootstrapTable({
			classes: 'table table-hover table-fixed',
			cache:false,
			pagination:true,
			queryParamsType:'',
			sidePagination: 'server',
		    pageNumber: 1,
		    pageSize: 10,
		    pageList:[10, 25, 50, 100],
		    dataField:"list",
		    totalField:"totalCount",
		    url: 'auth/doc/inter/json/load.htm',
		    ajaxOptions:{
		    	headers:{
					"sysReqToken":$("#sysReqToken").val()
				}
		    },
		    queryParams: function (params) {
		    	var result = $("#interListForm").find("*").getFieldsValue();
		    	result.pageSize = params.pageSize;
		    	result.pageNumber = params.pageNumber;
		    	return result;
		    },
		    responseHandler:function(response){
		    	var list = response[this.dataField];
		    	if(list.length){
		    		for(var i = 0; i < list.length; i ++){
			    		list[i].index = i + 1;
			    	}
		    	}
		    	
		    	return response;
		    },
			columns: [
			    {
			        field: 'index',
			        title: '#',
			        "class":"table-index"
				},
				{
			        field: 'name',
			        title: '名称',
			        "class":"col-lg-2"
				},
				{
			        field: 'path',
			        title: '请求url',
			        "class":"col-lg-3"
				},
				{
			        field: 'moduleName',
			        title: '所属模块',
			        "class":"col-lg-2"
				},
				{
			        field: 'modifyDate',
			        title: '更新时间',
			        "class":"col-lg-2"
				},
				{
					field: 'operation',
			        title: '操作',
			        "class":"col-lg-3",
			        formatter:function(value,row,index){
			        	var result = '<span class="inter-list-oper">'
			        				+ '<a class="btn btn-sm btn-success copy" href="javascript:void(0);"><i class="fa fa-plus"></i> 复制</button>'
			        				+ '<a class="btn btn-sm btn-primary edit" href="javascript:void(0);"><i class="fa fa-pencil"></i> 编辑</a>'
			        				+ '<a class="btn btn-sm btn-danger delete" href="javascript:void(0);"><i class="fa fa-trash"></i> 删除</a>';
			        				+ '</span>';
			        	return result;
			        },
			        events: thisObj.operateEvents
				},
			]
		});
		
		//初始化搜索按钮
		$("#interListSearchBtn").click(function(){
			$('#interListTable').bootstrapTable('refresh');
		});
	},
	
	operateEvents:{
		'click .inter-list-oper .copy': function (e, value, row, index) {
			interObj.loadInfo(row.interId,true);
	    },
	    'click .inter-list-oper .edit': function (e, value, row, index) {
	    	interObj.loadInfo(row.interId,false,null);
	    },
	    'click .inter-list-oper .delete': function (e, value, row, index) {
	    	bootbox.confirm("确认执行删除操作",function(){
				var param = new Object();
				param.dataId = row.interId;
				param.type = "inter";
				param.docId = $("#docId").val();
				doPost("auth/doc/inter/tree/json/del.htm",param,function(data){
					$('#interListTable').bootstrapTable('refresh');
				});
			});
	    }
	},
	
	//加载模块列表
	loadModuleSelect:function(){
		$("#interListModuleId").empty();
		//初始化模块下拉框
		initSelectRemote("auth/doc/module/json/list.htm","docId=" + $("#docId").val(),"#interListModuleId");
	},
	
	loadInfo:function(moduleId){
		$(".nodeInfo").hide();
		$("#interListPanel").show();
		$("#interListModuleId").val(moduleId);
		
		$('#interListTable').bootstrapTable('refresh');
	}
};

$(function () {
	//初始化树
	interTreeObj.init();

	//初始化文档信息
	apiDocObj.init();
	
	//初始化模块信息
	moduleObj.init();
	
	//初始化错误码信息
	errorCodeObj.init();
	
	if($("#enableInterList").val() == 'true'){
		//接口列表初始化
		interListObj.init();
	}
	
	//初始化操作
	initBtnClick();
});

//初始化操作
function initBtnClick(){
	//修改api设置
	$("#apiSettingBtn").click(function(){
		interObj.isChanged(function(){
			$("#apiDocInfoTab").click();
		});
	});
	
	//新增错误码
	$("#addErrorCodeBtn").click(function(){
		interObj.isChanged(function(){
			errorCodeObj.initAddOper();
		});
	});
	
	//新增模块
	$("#addModuleBtn").click(function(){
		interObj.isChanged(function(){
			moduleObj.initAddOper();
		});
	});
	
	//新增接口
	$("#addInterBtn").click(function(){
		interObj.isChanged(function(){
			interObj.initAddOper();
		});
	});
}
