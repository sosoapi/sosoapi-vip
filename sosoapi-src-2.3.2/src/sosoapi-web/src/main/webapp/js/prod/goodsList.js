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