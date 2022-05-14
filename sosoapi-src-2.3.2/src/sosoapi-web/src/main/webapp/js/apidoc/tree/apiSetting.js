var apiSettingObj = {
	init:function(){
		var thisObj = this;
		$("#apiDocInfoTab").click(function(){
			thisObj.loadApiDoc();
		});
		
		$("#commonParamInfoTab").click(function(){
			commonParamObj.loadInfo();
		});
		
		$("#baseUrlInfoTab").click(function(){
			baseUrlObj.loadInfo();
		});
	},
	
	//加载api文档信息
	loadApiDoc:function(){
		apiDocObj.loadInfo();
	}
};

//文档相关操作
var apiDocObj = {
	//初始化
	init:function(){
		var thisObj = this;
		
		$('#docDesc').summernote({
			height: 300
		});
		
		$("#docInfoFrom").bootstrapValidator({
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
		
		$("#saveDocBtn").click(function(){
			if(isFormValid("docInfoFrom")){
				var param = $("#docInfoFrom").find("*").getFieldsValue();
				param.description = $("#docDesc").code();
				doPost("auth/doc/json/update.htm",param,function(data){
					notice("保存成功");
				});
			}
		});
		
		//默认加载
		thisObj.loadInfo();
	},
	
    //加载
	loadInfo:function(){
		$(".nodeInfo").hide();
		$("#docInfoPanel").show();
		//先重置
		resetValidForm("docInfoFrom");
		
		var param = new Object();
		param.docId = $("#docId").val();
		doGet("auth/doc/json/info.htm",param,function(docInfo){
			$("#docInfoFrom").find("*").setFieldsValue(docInfo);
			$('#docDesc').code(docInfo.description);
		});
	}
};

var commonParamObj = {
	//初始化请求公共参数表
	init:function(){
		var thisObj = this;
	    $("#commonParamTable").nestable({
	    	group: 10,
	    	enableAddChild:false,
	    	maxDepth: 1,
	    	
	        getItemContentTmpl:function(){
	        	return getTmpl("#commonParamContentTmpl");
	        },
	        
	        afterAddChild:function(li){
	        	
	        }
	    });
	    
	    $("#addCommonParamBtn").on("click", function () {
	    	$('#commonParamTable').nestable("addChild");
	    });
	    
	    //保存请求参数
	    $("#saveCommonParamBtn").on("click",function(){
	    	var rowArray = $('#commonParamTable').nestable("getData");
	    	var param = new Object();
	    	param.docId = $("#docId").val();
	    	param.commonParam = JSON.stringify(rowArray);
	    	doPost("auth/doc/common/param/json/add.htm",param,function(data){
	    		notice("保存成功");
	    		
	    		//重新加载保存后的参数
	    		thisObj.loadInfo();
	    	});
	    });
	    
	    $("#clearCommonParamBtn").on("click", function () {
	    	$('#commonParamTable').nestable("clear");
	    });
	},

	//加载请求参数
	loadInfo:function(){
		var param = new Object();
		param.docId = $("#docId").val();
		doGet("auth/doc/common/param/json/list.htm",param,function(paramList){
			$('#commonParamTable').nestable("loadData",paramList);
		});
	}
};

//基路径
var baseUrlObj = {
	//初始化请求公共参数表
	init:function(){
		var thisObj = this;
	    $("#baseUrlTable").nestable({
	    	group: 11,
	    	enableAddChild:false,
	    	maxDepth: 1,
	    	
	        getItemContentTmpl:function(){
	        	return getTmpl("#baseUrlContentTmpl");
	        },
	        
	        afterAddChild:function(li){
	        	
	        }
	    });
	    
	    $("#addBaseUrlBtn").on("click", function () {
	    	$('#baseUrlTable').nestable("addChild");
	    });
	    
	    
	    //保存请求参数
	    $("#saveBaseUrlBtn").on("click",function(){
	    	var rowArray = $('#baseUrlTable').nestable("getData");
	    	var param = new Object();
	    	param.docId = $("#docId").val();
	    	param.envParam = JSON.stringify(rowArray);
	    	doPost("auth/doc/env/json/add.htm",param,function(data){
	    		notice("保存成功");
	    		
	    		//重新加载保存后的环境列表
	    		thisObj.loadInfo();
	    	});
	    });
	    
	    $("#clearBaseUrlBtn").on("click", function () {
	    	$('#baseUrlTable').nestable("clear");
	    });
	},

	//加载请求参数
	loadInfo:function(){
		var param = new Object();
		param.docId = $("#docId").val();
		doGet("auth/doc/env/json/list.htm",param,function(paramList){
			$('#baseUrlTable').nestable("loadData",paramList);
		});
	}
};

$(function(){
	apiSettingObj.init();
	
	commonParamObj.init();
	
	baseUrlObj.init();
});