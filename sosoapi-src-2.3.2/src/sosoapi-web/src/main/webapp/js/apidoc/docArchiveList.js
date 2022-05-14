$(function() {

});

//初始化删除操作
function initDelOper(archiveId,docId){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.archiveId = archiveId;
		param.docId = docId;
		doPost("auth/doc/archive/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}
