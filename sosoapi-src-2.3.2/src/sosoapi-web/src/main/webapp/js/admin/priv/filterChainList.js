$(function() {
	//初始化表单
	initForm();
});

//初始化表单
function initForm(){
	$("#chainForm").bootstrapValidator({
		fields:{
			name:{
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
			},
			url:{
                validators: {
                    notEmpty: {
                        message: 'url规则不能为空'
                    }
                }
			},
			filter:{
                validators: {
                    notEmpty: {
                        message: '过滤链不能为空'
                    }
                }
			},
			sortWeight:{
                validators: {
                	integer: {
                        message: '权重值只能为整数'
                    },
                    notEmpty: {
                        message: '权重值不能为空'
                    }
                }
			}
		}
	});
	
	//初始化保存操作
	$("#saveChainBtn").click(function(){
		if(isFormValid("chainForm")){
			var operType = $("#operType").val();
			var param = $("#chainForm").find("*").getFieldsValue();
			var reqUrl = "";
			var msg = "";
			if(operType == "add"){
				reqUrl = "admin/filter/chain/json/add.htm";
				msg = "新增成功";
			}
			else if(operType == "update"){
				reqUrl = "admin/filter/chain/json/update.htm";
				msg = "保存成功";
			}
			
			doPost(reqUrl,param,function(data){
				notice("保存成功",function(){
					$('#chainFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}

//初始化编辑操作
function initUpdateOper(id){
	$("#chainForm").resetForm();
	$("#chainId").val(id);
	$("#operType").val("update");
	
	doGet("admin/filter/chain/json/info.htm","chainId=" + id,function(chainInfo){
		$("#chainForm").find("*").setFieldsValue(chainInfo);
	});
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.chainId = id;
		doPost("admin/filter/chain/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}

//新增前初始化
function initBeforeAddOper(){
	$("#chainForm").resetForm();
	$("#operType").val("add");
}

