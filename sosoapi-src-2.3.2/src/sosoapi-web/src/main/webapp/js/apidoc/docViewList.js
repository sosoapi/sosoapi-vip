$(function() {
	//初始化表单
	initForm();
});

//初始化表单
function initForm(){
	$("#docViewForm").bootstrapValidator({
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
	
	//初始化保存操作
	$("#saveViewBtn").click(function(){
		if(isFormValid("docViewForm")){
			var operType = $("#operType").val();
			var param = $("#docViewForm").find("*").getFieldsValue();
			var reqUrl = "";
			var msg = "";
			if(operType == "add"){
				reqUrl = "auth/doc/view/json/add.htm";
				msg = "新增成功";
			}
			else if(operType == "update"){
				reqUrl = "auth/doc/view/json/update.htm";
				msg = "保存成功";
			}
			
			param.docId = $("#docId").val();
			param.projId = $("#projId").val();
			doPost(reqUrl,param,function(data){
				notice("保存成功",function(){
					$('#docViewFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}

//初始化编辑操作
function initUpdateOper(viewId,docId){
	$("#docViewForm").resetForm();
	$("#viewId").val(viewId);
	$("#operType").val("update");
	
	var param = new Object();
	param.viewId = viewId;
	param.docId = docId;
	doGet("auth/doc/view/json/info.htm",param,function(viewInfo){
		$("#docViewForm").find("*").setFieldsValue(viewInfo);
	});
}

//初始化删除操作
function initDelOper(viewId,docId){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.viewId = viewId;
		param.docId = docId;
		doPost("auth/doc/view/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}

//新增前初始化
function initBeforeAddOper(){
	$("#docViewForm").resetForm();
	$("#operType").val("add");
}
