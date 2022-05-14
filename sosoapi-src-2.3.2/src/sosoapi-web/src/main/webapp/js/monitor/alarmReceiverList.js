$(function() {
	//初始化表单
	initReceiverForm();
	
	$('#receiverFormModal').on("hide.bs.modal",function(){
		resetValidForm("receiverForm");
	});
});

//新增前初始化
function initBeforeAddOper(){
	$("#operType").val("add");
}

//初始化表单
function initReceiverForm(){
	$("#receiverForm").bootstrapValidator({
		fields:{
			name:{
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
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
	                }
	            }
			}
		}
	});
	
	$("#saveReceiverBtn").click(function(){
		if(isFormValid("receiverForm")){
			var param = $("#receiverForm").find("*").getFieldsValue();
			var url = "";
			var msg = "";
			if(param.operType == 'add'){
				url = "auth/monitor/alarm/receiver/json/add.htm";
				msg = "新增成功";
			}
			else if(param.operType == 'update'){
				url = "auth/monitor/alarm/receiver/json/update.htm";
				msg = "保存成功";
			}
			
			doPost(url,param,function(data){
				notice(msg,function(){
					$('#receiverFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}

//初始化编辑
function initUpdateOper(receiverId){
	$("#operType").val("update");
	$("#receiverId").val(receiverId);
	
	doGet("auth/monitor/alarm/receiver/json/info.htm","receiverId=" + receiverId,function(receiverInfo){
		$("#receiverForm").find("*").setFieldsValue(receiverInfo);
	});
}

//初始化删除操作
function delReceiver(receiverId){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.receiverId = receiverId;
		doPost("auth/monitor/alarm/receiver/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}