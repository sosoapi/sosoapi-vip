$(function() {
	//初始化更新保存操作
	$("#saveUserBtn").click(function(){
		var param = $("#userForm").find("*").getFieldsValue();
		doPost("admin/user/json/update.htm",param,function(data){
			notice("保存成功",function(){
				$('#userFormModal').modal('hide');
				location.reload();
			});
		});
	});
	
	//初始化重置密码操作
	$("#resetBtn").click(function(){
		var param = $("#resetForm").find("*").getFieldsValue();
		doPost("admin/user/json/resetPasswd.htm",param,function(data){
			notice("密码重置成功",function(){
				$('#resetFormModal').modal('hide');
			});
		});
	});
	
	//初始化新增
	initAddOper();
});

//初始化编辑操作
function initUpdateOper(id){
	$("#userForm").resetForm();
	$("#userId").val(id);
	
	doGet("admin/user/json/info.htm","userId=" + id,function(userInfo){
		$("#userForm").find("*").setFieldsValue(userInfo);
	});
}

//初始化重置密码操作
function initResetOper(id){
	$("#resetForm").resetForm();
	$("#resetUserId").val(id);
}

//新增前初始化
function initBeforeAddOper(){
	$("#addForm").resetForm();
}

//初始化新增
function initAddOper(){
	$("#addForm").bootstrapValidator({
		fields:{
			nickName:{
                validators: {
                    notEmpty: {
                        message: '昵称不能为空'
                    }
                }
			},
			email:{
				trigger:"blur",
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress:{
                    	message: '邮箱格式错误'
                    },
                    remote:{
                    	message: '该邮箱已注册',
                    	type:'GET',
                    	url:'regist/validEmail.htm',
                    	name:'email'
                    }
                }
			},
			passwd:{
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
			}
		}
	});
	
	//初始化新增操作
	$("#addUserBtn").click(function(){
		if(isFormValid("addForm")){
			var param = $("#addForm").find("*").getFieldsValue();
			doPost("admin/user/json/add.htm",param,function(data){
				notice("新增成功",function(){
					$('#addFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}