$(function(){
	//文档分享
	initDocShare();
});

//文档分享
function initDocShare(){
	$("#docShareSaveBtn").click(function(){
		var param = $("#docShareForm").find("*").getFieldsValue();
		doPost("auth/doc/json/updateShareInfo.htm",param,function(data){
			notice("保存成功",function(){
				$('#docShareFormModal').modal('hide');
			});
		});
	});
	
	$("#docShareBtn").click(function(){
		doGet("auth/doc/json/getShareInfo.htm","docId=" + $("#toolBarDocId").val(),function(docInfo){
			$("#docShareForm").find("*").setFieldsValue(docInfo);
		});
	});
}

//初始化编辑操作
function initUpdateShareInfo(){
	doGet("auth/doc/json/getShareInfo.htm","docId=" + $("#toolBarDocId").val(),function(docInfo){
		$("#docShareForm").find("*").setFieldsValue(docInfo);
	});
}

//编辑操作
function updateShareInfo(){
	var param = $("#docShareForm").find("*").getFieldsValue();
	doPost("auth/doc/json/updateShareInfo.htm",param,function(data){
		notice("保存成功",function(){
			$('#docShareFormModal').modal('hide');
		});
	});
}
