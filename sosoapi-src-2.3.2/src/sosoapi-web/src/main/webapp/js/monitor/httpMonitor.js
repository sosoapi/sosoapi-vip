$(function(){
	//初始化表单
	initMonitorForm();
		
	//初始化参数
	initParamTable();
});

//初始化表单
function initMonitorForm(){
	$("#monitorInfoForm").bootstrapValidator({
		group:".valid-group",
		
		fields:{
			name:{
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
			},
			sub:{
                validators: {
                    notEmpty: {
                        message: '监控url不能为空'
                    },
                    url: {
                        message: '监控url格式不正确'
                    }
                }
			},
			maxSpendTime:{
                validators: {
                    notEmpty: {
                        message: '最大响应时间不能为空'
                    },
                    greaterThan: {
                    	inclusive:true,
                        value: 0,
                        message: '最大响应时间大于或等于0'
                    }
                }
			},
			maxErrorCount:{
                validators: {
                    notEmpty: {
                        message: '最大连续错误次数不能为空'
                    },
                    greaterThan: {
                    	inclusive:true,
                        value: 0,
                        message: '最大连续错误次数大于或等于0'
                    }
                }
			},
			maxAlarmCount:{
                validators: {
                    notEmpty: {
                        message: '最大预警次数不能为空'
                    },
                    greaterThan: {
                    	inclusive:true,
                        value: 0,
                        message: '最大预警次数大于或等于0'
                    }
                }
			},
			respContent:{
                validators: {
                	notEmptyCascade: {
                		field:"validResp",
                		value:"true",
                        message: '验证内容不能为空'
                    }
                }
			}
		}
	});
	
	$("#saveMonitorBtn").click(function(){
		if(isFormValid("monitorInfoForm")){
			var msg = "";
			if($("#operType").val() == "add"){
				msg = "确认新增该监控？";
			}
			else{
				msg = "确认修改该监控？";
			}
			
			bootbox.confirm(msg,function(){
				var rowArray = $('#paramTable').nestable("getData");
				$("#reqParam").val(JSON.stringify(rowArray));
		    	
				//表单提交
				$("#monitorInfoForm").bootstrapValidator('defaultSubmit');
		});
		}
	});
	
	$('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green'
    });
	
	$("#validResp").change(function(){
		var val = $(this).val();
		if(val == "true"){
			$("#respContentDiv").show();
		}
		else{
			$("#respContentDiv").hide();
		}
	});
}

//初始化参数
function initParamTable(){
	$("#paramTable").nestable({
    	group: 1,
    	enableAddChild:false,
    	maxDepth: 1,
    	
        getItemContentTmpl:function(){
        	return getTmpl("#paramContentTmpl");
        },
        
        afterAddChild:function(li){
        	
        }
    });
    
    $("#addParamBtn").on("click", function () {
    	$('#paramTable').nestable("addChild");
    });
    
    $("#clearParamBtn").on("click", function () {
    	$('#paramTable').nestable("clear");
    });
    
    var reqParamContent = $("#reqParam").val();
    if($("#operType").val() == "update" && reqParamContent.length > 0){
    	$('#paramTable').nestable("loadData",JSON.parse(reqParamContent));
    }
}
