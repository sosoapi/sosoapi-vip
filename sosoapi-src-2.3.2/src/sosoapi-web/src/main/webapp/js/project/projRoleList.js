$(function() {
	//初始化表单
	initForm();
});

//初始化表单
function initForm(){
	$("#roleForm").bootstrapValidator({
		fields:{
			name:{
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
			},
			code:{
                validators: {
                    notEmpty: {
                        message: '编码不能为空'
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
	$("#saveRoleBtn").click(function(){
		if(isFormValid("roleForm")){
			var operType = $("#operType").val();
			var param = $("#roleForm").find("*").getFieldsValue();
			var reqUrl = "";
			var msg = "";
			if(operType == "add"){
				reqUrl = "auth/proj/role/json/add.htm";
				msg = "新增成功";
			}
			else if(operType == "update"){
				reqUrl = "auth/proj/role/json/update.htm";
				msg = "保存成功";
			}
			
			doPost(reqUrl,param,function(data){
				notice("保存成功",function(){
					$('#roleFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}

//初始化编辑操作
function initUpdateOper(projId,roleId){
	$("#roleForm").resetForm();
	$("#roleId").val(roleId);
	$("#operType").val("update");
	
	var param = new Object();
	param.projId = projId;
	param.roleId = roleId;
	doGet("auth/proj/role/json/info.htm",param,function(roleInfo){
		$("#roleForm").find("*").setFieldsValue(roleInfo);
	});
}

//初始化删除操作
function initDelOper(projId,roleId){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.projId = projId;
		param.roleId = roleId;
		doPost("auth/proj/role/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}

//新增前初始化
function initBeforeAddOper(){
	$("#roleForm").resetForm();
	$("#operType").val("add");
}
