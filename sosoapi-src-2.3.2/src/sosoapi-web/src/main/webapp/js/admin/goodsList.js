$(function() {
	//初始化版本说明
	initGoodsDetail();
});

//初始化版本说明
function initGoodsDetail(){
	$(".goods-detail-btn").click(function(){
		$("#goodsDetailModalBody").html($(this).siblings("div").html());
	});
}

//初始化删除操作
function delOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.goodsId = id;
		doGet("admin/goods/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}

//初始化复制操作
function copyGoods(goodsId){
	bootbox.confirm("确定复制当前版本？",function(){
		var param = new Object();
		param.goodsId = goodsId;
		doPost("admin/goods/json/copy.htm",param,function(data){
			notice("复制并创建成功",function(){
				var url = window.location.href;
				location.reload();
			});
		});
	});
}