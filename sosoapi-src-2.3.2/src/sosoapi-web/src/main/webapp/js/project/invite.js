$(function(){
	//初始化邀请信息
	initInvite();
});

//初始化邀请信息
function initInvite(){
	$("#inviteForm").bootstrapValidator({
		fields:{
			invitedEmail:{
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
	
	$("#inviteBtn").click(function(){
		if(isFormValid("inviteForm")){
			var param = new Object();
			param.invitedEmail = $("#invitedEmail").val();
			param.projId = $("#projId").val();
			doPost("auth/proj/mem/json/invite.htm",param,function(data){
				notice("已发出邀请");
				$("#inviteModal").modal("hide");
			});
		}
	});
}

//新增成员
function addMember(userId){
	var $this = $(this.event.currentTarget);
	var roleId = $this.closest("tr").find("select").val();
	var projNickName = $this.closest("tr").find("input").val();
	
	var param = new Object();
	param.userId = userId;
	param.projId = $("#projId").val();
	param.projRoleId = roleId;
	param.projNickName = projNickName;
	doPost("auth/proj/mem/json/add.htm",param,function(data){
		notice("已添加",function(){
			location.reload();
		});
	});
}