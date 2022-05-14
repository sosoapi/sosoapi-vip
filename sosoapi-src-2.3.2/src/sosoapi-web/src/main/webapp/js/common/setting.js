$(function(){
	//初始化左侧菜单选中状态
//	initMenuTreeActive();
	
	//点击控件后隐藏错误提示信息
	clearErrorMsg();
	
	//初始化bootbox
	initBootbox();
	
	//初始化提示信息
	/*initPopover();*/
	
	//初始化下拉框的值
	initSelectValue();
	
	//初始化modal，每次关闭之前清空当前验证信息
	initModal();
	
	//扩展treeGrid
	initTreeGrid();
	
	//初始化富文本
	initSummernote();
});

//初始化左侧菜单选中状态
function initMenuTreeActive(){
	var activeMenu = $("#userMenuTree").find("ul.child-list li.active");
	if(activeMenu != undefined && activeMenu.length > 0){
		$(activeMenu[0]).parent().parent().addClass("nav-active");
	}
}

//点击控件后隐藏错误提示信息
function clearErrorMsg(){
	//进入框架后，清空与后台交互产生的错误提示信息
	$("body").focusin(function(){
		$(".error-msg").hide();
	});
}


//初始化modal
//需要添加data-form参数，用于重置表单
//每次关闭之前清空当前验证信息
function initModal(){
	$("[data-toggle='modal']").each(function(){
		var $this = $(this);

		//默认配置
		var option = {
			backdrop : 'static', // 默认配置,要背景div(单击空白区域不触发hide方法)
			keyboard : false // 要desc键按了就消失的功能
		};
		
		$.extend($this.data(),option);
		
		var modalTarget = $this.attr("data-target") || $this.attr("href");
		var modalForm = $this.attr("data-form");
		if(modalTarget && modalForm){
			$(modalTarget).on('hide.bs.modal', function () {
				//清空数据
				resetValidForm(modalForm);
			});
		}
	});
}

//初始化提示信息
function initPopover(){
	$("[data-toggle='popover']").each(function(){
		var $this = $(this);
		var placement = $this.attr("data-placement");
		if(isNull(placement)){
			placement = "right";
		}
		
		$this.popover({
			placement:placement,
			trigger:"hover",
			container:"body",
			html:true
		});
	});
}

//初始化bootbox
function initBootbox(){
	//必须放在默认值设置之前，否则无效
	bootbox.addLocale("zh_CN",{
	    OK : '确定',
	    CANCEL : '取消',
	    CONFIRM : '确定'
	});
	
	//初始化bootbox相关默认值
	bootbox.setDefaults({
		"locale":"zh_CN",
		"backdrop":false,
		defBtnClassName:"btn-success",
		alertOpt:{
			/*title:'<i class="icon-info-sign"></i> 操作提示',*/
			size:'small'
		},
		confirmOpt:{
			/*title:'<i class="icon-remove-sign"></i> 操作提示',*/
			size:'small',
			//回调函数默认为执行确认操作
			callbackJustForConfirm:true
		}
	});
}

//扩展treeGrid
function initTreeGrid(){
	if($.fn.treegrid){
		$.extend($.fn.treegrid.defaults, {
		    expanderExpandedClass: 'fa fa-caret-down',
		    expanderCollapsedClass: 'fa fa-caret-right',
		    nodeRemoveTemplate: '<span class="treegrid-node-remove fa fa-times" style="cursor:pointer;padding-right:5px;"></span>',
	        nodeAddChildTemplate: '<span class="treegrid-node-add-child fa fa-plus" style="cursor:pointer;padding-right:5px;"></span>',
	        //nodeDragTemplate: '<span class="treegrid-node-drag fa fa-bars" style="cursor:pointer;padding-right:5px;"></span>',
	        
	        //缩进所在的列
	        indentColumn: 1,
	        //操作所在的列
	        operColumn: 0,
		});
	}
}

//初始化富文本
function initSummernote(){
	$.extend(true,$.summernote.options, {
		minHeight: null,
		maxHeight: null,
		focus: false,
		lang: 'zh-CN' // default: 'en-US'
	});
	
	//在$.extend无法覆盖，只会合并
	$.summernote.options.toolbar = [
		                	          ['font', ['fontsize','bold', 'italic', 'underline']],
		                	          ['color', ['color']],
		                	          ['para', ['ul', 'ol', 'paragraph']],
		                	          ['insert', ['table','link','hr']],
		                	          ['view', ['clear','fullscreen','codeview','preview']]
		                	  	];
}