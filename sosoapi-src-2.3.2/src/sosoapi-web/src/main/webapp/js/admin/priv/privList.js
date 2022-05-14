$(function() {
	//初始化表单
	initForm();
});

//初始化表单
function initForm(){
	$("#privForm").bootstrapValidator({
		fields:{
			name:{
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
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
	$("#savePrivBtn").click(function(){
		if(isFormValid("privForm")){
			var operType = $("#operType").val();
			var param = $("#privForm").find("*").getFieldsValue();
			var reqUrl = "";
			var msg = "";
			if(operType == "add"){
				reqUrl = "admin/priv/json/add.htm";
				msg = "新增成功";
			}
			else if(operType == "update"){
				reqUrl = "admin/priv/json/update.htm";
				msg = "保存成功";
			}
			
			doPost(reqUrl,param,function(data){
				notice("保存成功",function(){
					$('#privFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}

//初始化编辑操作
function initUpdateOper(id){
	$("#privForm").resetForm();
	$("#privId").val(id);
	$("#operType").val("update");
	
	doGet("admin/priv/json/info.htm","privId=" + id,function(privInfo){
		$("#privForm").find("*").setFieldsValue(privInfo);
	});
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.privId = id;
		doPost("admin/priv/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}

//新增前初始化
function initBeforeAddOper(){
	$("#privForm").resetForm();
	$("#operType").val("add");
}

