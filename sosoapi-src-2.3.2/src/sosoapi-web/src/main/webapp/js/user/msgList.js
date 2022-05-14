$(function() {
	//初始化消息详情
	initMsgContent();
	
	//初始化已读操作
	initUpdateOper();
	
	//初始化删除操作
	initDelOper();
});

//初始化消息详情
function initMsgContent(){
	$(".msg-content-btn").click(function(){
		$("#msgContentModalBody").html($(this).siblings("div").html());
	});
}

//初始化已读操作
function initUpdateOper(){
	$(".update-btn").click(function(){
		var trObj = $(this).closest("tr");
		var msgId = trObj.find(("td.msg-id-hidden")).html();
		var jqMsgStatusHidden = trObj.find("td.msg-status-hidden");
		var jqMsgStatus = trObj.find("td.msg-status");
		var jqUpdateBtn = trObj.find("button.update-btn");
		
		//处理未读
		if(jqMsgStatusHidden.html() == 'false'){
			var param = new Object();
			param.msgId = msgId;
			doGet("auth/msg/json/setRead.htm",param,function(data){
				jqMsgStatusHidden.html("true");
				jqMsgStatus.html("已读");
				jqUpdateBtn.attr("disabled",true);
			});
		}
	});
}

//初始化删除操作
function initDelOper(){
	$(".del-btn").click(function(){
		var msgId = $(this).closest("tr").find(("td.msg-id-hidden")).html();
		bootbox.confirm("确定执行删除操作？",function(){
			var param = new Object();
			param.msgId = msgId;
			doGet("auth/msg/json/del.htm",param,function(data){
				notice("删除成功",function(){
					location.reload();
				});
			});
		});
	});
}