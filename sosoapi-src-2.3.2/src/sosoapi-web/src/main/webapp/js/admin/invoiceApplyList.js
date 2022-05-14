$(function() {
	//初始化表单
	initApplyForm();
});

//初始化表单
function initApplyForm(){
	$.datetimepicker.setLocale('zh');
	$('#mailingDate').datetimepicker({
		format:'Y-m-d H:i:s',
		timepicker:false
	});
	
	//初始化金额控件
	$("#totalAmount").TouchSpin({
        min: 0,
        max: 999999,
        step: 1,
        decimals: 0,
        boostat: 5,
        maxboostedstep: 10
    });
	
	$("#applyForm").bootstrapValidator({
		group:".valid-group",
		fields:{
			email:{
                validators: {
                    notEmpty: {
                        message: '用户邮箱不能为空'
                    }
                }
			},
			productName:{
                validators: {
                    notEmpty: {
                        message: '产品名称不能为空'
                    }
                }
			},
			totalAmount:{
                validators: {
                    notEmpty: {
                        message: '发票金额不能为空'
                    }
                }
			},
			companyName:{
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    }
                }
			},
			companyTaxNo:{
                validators: {
                    notEmpty: {
                        message: '公司税号不能为空'
                    }
                }
			}
		}
	});
	
	//初始化保存操作
	$("#saveBtn").click(function(){
		if(isFormValid("applyForm")){
			var operType = $("#operType").val();
			var param = $("#applyForm").find("*").getFieldsValue();
			var reqUrl = "";
			var msg = "";
			if(operType == "add"){
				reqUrl = "admin/invoice/apply/json/add.htm";
				msg = "新增成功";
			}
			else if(operType == "update"){
				reqUrl = "admin/invoice/apply/json/update.htm";
				msg = "保存成功";
			}
			
			param.totalAmount = Math.round(Number(param.totalAmount) * 100);
			doPost(reqUrl,param,function(data){
				notice("保存成功",function(){
					$('#applyFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
	
	$("#saveAndSubmitBtn").click(function(){
		$("#submitFlag").val("true");
		$("#saveBtn").click();
	});
	
	//初始化审批操作
	$("#approveBtn").click(function(){
		if(isFormValid("applyForm")){
			var param = $("#applyForm").find(".approve-info").getFieldsValue();
			param.applyId = $("#applyId").val();
			var reqUrl = "admin/invoice/apply/json/approve.htm";
			doPost(reqUrl,param,function(data){
				notice("提交成功",function(){
					$('#applyFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}

//初始化详情操作
function initInfoOper(id,userEmail){
	$("#applyForm").resetForm();
	$("#applyId").val(id);
	
	showForm(true,true,false,true,true,false,false);
	
	//填充表单
	fillForm(id,userEmail);
}

//初始化编辑操作
function initUpdateOper(id,userEmail){
	$("#applyForm").resetForm();
	$("#applyId").val(id);
	$("#operType").val("update");
	
	showForm(true,false,true,false,false,false,true);
	
	//填充表单
	fillForm(id,userEmail);
}

//初始化提交审批操作
function initSubmitForApproveOper(id){
	bootbox.confirm("确定提交当前申请？",function(){
		var param = new Object();
		param.applyId = id;
		doPost("admin/invoice/apply/json/submit.htm",param,function(data){
			notice("提交成功",function(){
				location.reload();
			});
		});
	});
}

//初始化审批操作
function initApproveOper(id,userEmail){
	$("#applyForm").resetForm();
	$("#applyId").val(id);
	
	showForm(true,true,false,true,false,true,true);
			
	//填充表单
	fillForm(id,userEmail);
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.applyId = id;
		doPost("admin/invoice/apply/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}

//新增前初始化
function initBeforeAddOper(){
	$("#applyForm").resetForm();
	$("#operType").val("add");
	
	showForm(true,false,true,false,false,false,true);
}

//填充表单
function fillForm(applyId,userEmail){
	doGet("admin/invoice/apply/json/info.htm","applyId=" + applyId,function(applyInfo){
		applyInfo.totalAmount = Math.round(applyInfo.totalAmount / 100);
		applyInfo.createDate = formatTimestamp(applyInfo.createDate);
		applyInfo.dealDate = formatTimestamp(applyInfo.dealDate);
		applyInfo.mailingDate = formatTimestamp(applyInfo.mailingDate);
		$("#applyForm").find("*").setFieldsValue(applyInfo);
		$("#userEmail").val(userEmail);
	});
}

//设置表单展示信息
function showForm(showApplyInfo,applyInfoRead,showSaveBtn,
					showApproveInfo,approveInfoRead,showApproveBtn,
					showFooter){
	//设置申请信息展示
	if(showApplyInfo){
		$("#applyFormModal").find(".apply-info-group").show();
	}
	else{
		$("#applyFormModal").find(".apply-info-group").hide();
	}
	
	//设置申请信息只读
	$("#applyFormModal").find(".apply-info").attr("readonly",applyInfoRead);
	
	//设置保存按钮
	if(showSaveBtn){
		$("#saveBtn").show();
		$("#saveAndSubmitBtn").show();
	}
	else{
		$("#saveBtn").hide();
		$("#saveAndSubmitBtn").hide();
	}
	
	//设置审批信息展示
	if(showApproveInfo){
		$("#applyFormModal").find(".approve-info-group").show();
	}
	else{
		$("#applyFormModal").find(".approve-info-group").hide();
	}
	
	//设置审批信息只读
	$("#applyFormModal").find(".approve-info").attr("readonly",approveInfoRead);
	
	//设置审批按钮
	if(showApproveBtn){
		$("#approveBtn").show();
	}
	else{
		$("#approveBtn").hide();
	}
	
	//设置操作按钮
	if(showFooter){
		$("#applyFormModal").find(".modal-footer").show();
	}
	else{
		$("#applyFormModal").find(".modal-footer").hide();
	}
	
	//处理发票类型
	$("#typeId").change(function(){
		var val = $(this).val();
		if(val == 'special'){
			$("#applyFormModal").find(".apply-special-info").show();
		}
		else {
			$("#applyFormModal").find(".apply-special-info").hide();
		}
	});
	
	$("#typeId").trigger("change");
}