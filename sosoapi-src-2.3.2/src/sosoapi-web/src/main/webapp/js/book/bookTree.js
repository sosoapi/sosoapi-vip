//文档设置相关
var bookSettingObj = {
	//初始化
	init:function(){
		var thisObj = this;
		
		$('#bookBrief').summernote({
			height: 200
		});
		
		$("#bookSettingForm").bootstrapValidator({
			fields:{
				title:{
	                validators: {
	                    notEmpty: {
	                        message: '标题不能为空'
	                    }
	                }
				}
			}
		});
		
		$("#saveBookSettingBtn").click(function(){
			if(isFormValid("bookSettingForm")){
				var param = $("#bookSettingForm").find("*").getFieldsValue();
				param.bookId = $("#bookId").val();
				param.brief = $("#bookBrief").code();
				doPost("auth/book/setting/json/update.htm",param,function(data){
					notice("保存成功");
				});
			}
		});
	},
	
	//初始化tab
	initTab:function(){
		var thisObj = this;
		$("#bookSettingTab").click(function(){
			thisObj.loadInfo();
		});
	},
	
    //加载
	loadInfo:function(){
		$(".nodeInfo").hide();
		$("#bookSettingPanel").show();
		resetValidForm("bookSettingForm");
		
		var param = new Object();
		param.bookId = $("#bookId").val();
		doGet("auth/book/setting/json/getInfo.htm",param,function(settingInfo){
			$("#bookSettingForm").find("*").setFieldsValue(settingInfo);
			$('#bookBrief').code(settingInfo.brief);
		});
	}
};

//文档内容相关
var bookContentObj = {
	markdownEditor: null,
	
	//初始化
	init:function(){
		var thisObj = this;

		$('#richTextEditor').summernote({
			height: 600
		});
		
		thisObj.markdownEditor = editormd("markdownEditor", {
            width   : "100%",
            height  : 640,
            watch : false,
            placeholder : "Enjoy Markdown! coding now...",
            syncScrolling : "single",
            path    : "plugin/editormd/lib/",
            saveHTMLToTextarea : true,
            toolbarIcons : [
                "undo", "redo", "|", 
                "bold", "del", "italic", "|", 
                "h1", "h2", "h3", "h4", "h5", "h6", "|", 
                "list-ul", "list-ol", "hr", "|",
                "link", "image", "code","code-block", "table", "|",
                "watch", "preview"
            ],
            
            /**上传图片相关配置如下*/
            /*imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "",//注意你后端的上传图片服务地址,*/            
            onload : function(){
            	//为解决markdown插件先隐藏后通过编辑文件打开样式错乱问题，默认展示，在初始化完毕后需要隐藏
            	$("#bookContentPanel").hide();
            }
        });
		
		//快捷键设置
		$("#bookContentPanel").keypress(function(e){
			if(e.ctrlKey && (e.which == 13 || e.which == 10)) {
				$("#saveBookContentBtn").trigger("click");
			} 
		});
		
		//保存
		$("#saveBookContentBtn").click(function(){
			if(isFormValid("bookContentForm")){
				var operType = $("#contentOperTypeId").val();
				if(operType == 'add' || operType == 'copy'){
					thisObj.addOper();
				}
				else if(operType == 'update'){
					thisObj.updateOper();
				}
			}
		});
		
		//初始化未保存离开提示
		$('.changed-tip').changedTip();
		
		$("#contentTypeId").change(function(){
			var val = $(this).val();
			if(val == "html"){
				$("#richTextWrapId").show();
				$("#markdownWrapId").hide();
			}
			else {
				$("#richTextWrapId").hide();
				$("#markdownWrapId").show();
			}
		});
		//默认富文本隐藏
		$("#richTextWrapId").hide();
	},
	
	//获取markdown编辑器
	getMarkdownEditor:function(){
		return this.markdownEditor;
	},
	
	isChanged:function(callback){
		var flag = $("#bookContentPanel").is(":visible") && $('.changed-tip').changedTip("isChanged");
		if(flag){
			bootbox.confirm("文档信息未保存,您确定离开吗?",function() {
				$('.changed-tip').changedTip("skip");
				callback();
	        });
		}
		else{
			callback();
		}
	},
	
	updateOper:function(){
		var thisObj = this;
		var param = $("#bookContentForm").find("*").getFieldsValue();
		param.bookId = $("#bookId").val();
		param.richTextContent = $("#richTextEditor").code();
		param.markdownContent = thisObj.getMarkdownEditor().getMarkdown();
		//markdown直接由editormd直接渲染不转换html，否则容易样式出错
		/*if($("#contentTypeId").val() == "markdown"){
			param.htmlContent = thisObj.getMarkdownEditor().getHTML();
		}
		else{
			param.htmlContent = $("#richTextEditor").code();
		}*/
		
		doPost("auth/book/content/json/update.htm",param,function(){
			$('.changed-tip').changedTip("init");
			//更新title
			var chapterNode = bookTreeObj.getNodeByDataId($("#chapterId").val(),"bookFile");
			bookTreeObj.rename(chapterNode,param.title);
			
			notice("修改成功",function(){
				
			});
		});
	},
	
	//加载信息
	loadInfo:function(treeNode){
		var thisObj = this;
		$(".nodeInfo").hide();
		$("#bookContentPanel").show();
		
		var contentId = treeNode.otherData;
		var param = new Object();
		param.bookId = $("#bookId").val();
		param.contentId = contentId;
		doGet("auth/book/content/json/getInfo.htm",param,function(contentInfo){
			contentInfo.contentId = contentId;
			contentInfo.chapterId = treeNode.dataId;
			
			$("#contentOperTypeId").val("update");
			$("#contentOperNameId").html("【编辑】");
			
			$("#bookContentForm").find("*").setFieldsValue(contentInfo);
			$("#richTextEditor").code(contentInfo.richTextContent);
			thisObj.getMarkdownEditor().setMarkdown(contentInfo.markdownContent);
			
			$("#contentTitle").val(treeNode.title);
			$("#contentTypeId").trigger("change");
			
			$('.changed-tip').changedTip("init");
		});
	}
};

//目录树相关
var bookTreeObj = {
	//初始化树结构
	init:function(){
		var thisObj = this;
		var setting = {
			async: {
				enable: true,
				url:"auth/book/tree/json/load.htm",
				autoParam:["dataId=parentId"],
				otherParam:{"bookId":$("#bookId").val(),"condition":$("#condition").val()},
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
				onClick: thisObj.clickCallback,
				onRename: function(event, treeId, treeNode, isCancel){
					var chapterTitle = treeNode.name;
					//显示右侧菜单
					bookTreeObj.showNodeOper(treeNode,true);
					
					//将最终的值同步到title
					bookTreeObj.rename(treeNode,chapterTitle);
					
					if(isCancel){
						return ;
					}
					
					var param = new Object();
					param.bookId = $("#bookId").val();
					param.chapterId = treeNode.dataId;
					param.title = chapterTitle;
					doPost("auth/book/tree/json/rename.htm",param,function(){
						
					});
				}
			}
		};
		
		//初始化树
		$.fn.zTree.init($("#bookTree"), setting);
			
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

	//重命名
	rename:function(treeNode,newName){
		var zTree = $.fn.zTree.getZTreeObj("bookTree");
		treeNode.title = newName;
		treeNode.name = filterNodeName(newName);
		zTree.updateNode(treeNode);
	},
	
	//显示或隐藏节点操作
	showNodeOper:function(treeNode,isShow){
		var thisObj = this;
		var operId = thisObj.getOperId(treeNode.tId);
		if(isShow){
			$("#" + operId).show();
		}
		else{
			$("#" + operId).hide();
		}
	},
	
	//搜索
	searchTree:function(){
		var zTree = $.fn.zTree.getZTreeObj("bookTree");
		zTree.reAsyncChildNodes(null, "refresh",true,{
			async: {
				otherParam:{"bookId":$("#bookId").val(),"condition":$("#condition").val()},
			}
		});
	},
	
	//拖拽前置处理
	beforeDropCallbackCust:function(treeId, treeNodes, targetNode, moveType, isCopy){
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
		param.bookId = $("#bookId").val();
		param.moveType = moveType;
		doPost("auth/book/tree/json/sort.htm",param,function(){
			var zTree = $.fn.zTree.getZTreeObj("bookTree");
			var targetParentNode = getParentNode(moveType,targetNode);
			if(targetParentNode != undefined && targetParentNode != null){
				zTree.reAsyncChildNodes(targetParentNode, "refresh",false);
			}
		});
	},

	//点击事件
	clickCallback:function (event, treeId, treeNode) {
		if(treeNode.dataId <= 0 || treeNode.type != 'bookFile'){
			return ;
		}
		
		bookContentObj.isChanged(function(){
			bookContentObj.loadInfo(treeNode);
		});
	},

	getOperId:function(nodeId){
		return "node_oper_" + nodeId;
	},
	
	//新建文件夹节点
	addFolderNode:function(treeNode){
		var param = new Object();
		param.bookId = $("#bookId").val();
		param.parentId = treeNode == null ? -1 : treeNode.dataId;
		param.type = "bookFolder";
		param.title = "新建文件夹";
		doPost("auth/book/tree/json/addChapter.htm",param,function(nodeInfo){
			var zTree = $.fn.zTree.getZTreeObj("bookTree");
			if(treeNode != null && treeNode.open){
				var newNode = zTree.addNodes(treeNode, nodeInfo);
				zTree.editName(newNode[0]);
				bookTreeObj.showNodeOper(newNode[0],false);
			}
			else{
				zTree.reAsyncChildNodes(treeNode, "refresh",false);
			}
		});
	},
	
	//新建文件节点
	addFileNode:function(treeNode){
		var param = new Object();
		param.bookId = $("#bookId").val();
		param.parentId = treeNode == null ? -1 : treeNode.dataId;
		param.type = "bookFile";
		param.title = "新建文件";
		doPost("auth/book/tree/json/addChapter.htm",param,function(nodeInfo){
			var zTree = $.fn.zTree.getZTreeObj("bookTree");
			if(treeNode != null && treeNode.open){
				var newNode = zTree.addNodes(treeNode, nodeInfo);
				zTree.editName(newNode[0]);
				bookTreeObj.showNodeOper(newNode[0],false);
			}
			else{
				zTree.reAsyncChildNodes(treeNode, "refresh",false);
			}
		});
		
		//bookContentObj.initAddOper();
	},
	
	//新增节点滑动结构
	addHoverDom:function(treeId, treeNode) {
		//操作节点
		var operId = bookTreeObj.getOperId(treeNode.tId);
		if($("#" + operId).length > 0){
			return ;
		}
		
		var operJqObj = $("<span class='node_oper' id='" + operId + "'></span>");
		if(treeNode.type == 'bookFolder'){
			operJqObj.append("<span class='addFolder' title='新增子文件夹' onfocus='this.blur();'><i class='fa fa-plus-square'></i></span>");
			operJqObj.append("<span class='addFile' title='新增文件' onfocus='this.blur();'><i class='fa fa-plus'></i></span>");
			operJqObj.append("<span class='refresh' title='重新加载' onfocus='this.blur();'><i class='fa fa-refresh'></i></span>");
			operJqObj.append("<span class='rename' title='重命名' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
			operJqObj.append("<span class='remove' title='删除' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
		}
		else if(treeNode.type == 'bookFile'){
			operJqObj.append("<span class='edit' title='编辑' onfocus='this.blur();'><i class='fa fa-edit'></i></span>");
			operJqObj.append("<span class='remove' title='删除' onfocus='this.blur();'><i class='fa fa-trash-o'></i></span>");
		}
		
		//名称节点
		var nodeNameJqObj = $("#" + treeNode.tId + "_span");
		nodeNameJqObj.after(operJqObj);
		
		//处理新增文件夹操作
		operJqObj.find(".addFolder").click(function(){
			bookTreeObj.addFolderNode(treeNode);
			return false;
		});
		
		//处理新增文件操作
		operJqObj.find(".addFile").click(function(){
			var zTree = $.fn.zTree.getZTreeObj("bookTree");
			zTree.selectNode(treeNode,false,false);
			
			bookContentObj.isChanged(function(){
				bookTreeObj.addFileNode(treeNode);
			});
			
			return false;
		});
		
		//处理刷新操作
		operJqObj.find(".refresh").click(function(e){
			var zTree = $.fn.zTree.getZTreeObj("bookTree");
			zTree.reAsyncChildNodes(treeNode, "refresh",false,null);
			
			return false;
		});
		
		//处理重命名操作
		operJqObj.find(".rename").click(function(e){
			var zTree = $.fn.zTree.getZTreeObj("bookTree");
			//更新时因为展示名称过长会被截取，故取自title的值来进行更新
			//更新后再把name的最终值设置回title
			treeNode.name = treeNode.title;
			zTree.updateNode(treeNode);
			
			zTree.editName(treeNode);
			
			bookTreeObj.showNodeOper(treeNode,false);
			return false;
		});
		
		//处理编辑操作
		operJqObj.find(".edit").click(function(){
			bookContentObj.isChanged(function(){
				//相当于点击链接
				operJqObj.parent().click();
			});
			
			return false;
		});

		//处理删除操作
		operJqObj.find(".remove").click(function(){
			bookContentObj.isChanged(function(){
				var msg = "确认执行删除操作？";
				if(treeNode.type == 'bookFolder'){
					msg = "确认删除该文件夹？<br/>该操作将一并删除所有子文件夹和文件。";
				}
				
				var zTree = $.fn.zTree.getZTreeObj("bookTree");
				zTree.selectNode(treeNode);
				bootbox.confirm(msg,function(){
					var param = new Object();
					param.chapterId = treeNode.dataId;
					param.bookId = $("#bookId").val();
					doPost("auth/book/tree/json/del.htm",param,function(data){
						//删除节点
						zTree.removeNode(treeNode);
						//删除后展示api文档配置信息
						$("#bookSettingBtn").click();
					});
				});
			});
			
			return false;
		});
	},

	//移除节点滑动结构
	removeHoverDom:function (treeId, treeNode) {
		var operJqObj = $("#" + bookTreeObj.getOperId(treeNode.tId));
		if(operJqObj.length > 0){
			operJqObj.remove();
		}
	},
	
	//根据dataId属性获取节点
	getNodeByDataId:function(dataId,type){
		if(type == undefined){
			return null;
		}
		
		var thisObj = this;
		var zTree = $.fn.zTree.getZTreeObj("bookTree");
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

$(function () {
	//初始化树
	bookTreeObj.init();
	
	//初始化文档设置
	bookSettingObj.init();
	
	//初始化文档内容
	bookContentObj.init();
	
	//初始化操作
	initBtnClick();
	
	//默认加载
	bookSettingObj.loadInfo();
	
	//解决markdown插件先隐藏后通过编辑文件打开样式错乱问题
	$("#bookContentPanel").show();
});

//初始化操作
function initBtnClick(){
	//修改api设置
	$("#bookSettingBtn").click(function(){
		bookContentObj.isChanged(function(){
			bookSettingObj.loadInfo();
		});
	});
	
	//新增文件夹
	$("#addFolderBtn").click(function(){
		bookTreeObj.addFolderNode(null);
	});
	
	//新增模块
	$("#addFileBtn").click(function(){
		bookContentObj.isChanged(function(){
			bookTreeObj.addFileNode(null);
		});
	});
}
