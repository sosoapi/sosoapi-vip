<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="jquery.plugin.tmpl.js"></script>
<script src="jquery.changed.tip.js"></script>
<script type="text/javascript">
$(function() {       
    //$('form').enable_changed_form_confirm( "您确定不保存就离开页面吗?" );     
    $('form').changedTip();
    $('form').changedTip('init');
    
    $("#recoverBtn").click(function(){
    	$('form').changedTip('recoverVal');
    });
    
    $("#unBindBtn").click(function(){
    	$('form').changedTip('unbindWindow');
    });
    
    $("#bindBtn").click(function(){
    	$('form').changedTip('bindWindow');
    });
    
    $("#clearBtn").click(function(){
    	$('form').changedTip('clearCache');
    });
    
    $("#changedBtn").click(function(){
    	alert($('form').changedTip('isChanged'));
    });
    
    /* $("#ajaxBtn").click(function(){
    	var result = $('form').changedTip('isChanged');
    	if(result){
    		alert("not save");
    	}
    	else {
    		alert("hello,this is ajax");
    	}
    }); */
    
    $("#ajaxBtn").mousedown(function(event){
    	/* var result = $('form').changedTip('isChanged');
    	if(result){
    		//return confirm('当前有更改，是否跳转??');
    		alert("当前有更改，是否跳转??");
    		event.preventDefault();
    		event.stopPropagation();
    	}
    	else {
    		return true;
    	} */
    	//alert("this is mousedown");
    	/* console.log("this is mousedown")
    	return false; */
    });
    
    $("#ajaxBtn").click(function(){
    	alert("this is click");
    	return false;
    });
});

</script>

</head>
<body>
	<div style="margin-bottom: 20px;">
		<span>未保存离开提示</span>
	</div>
	
	<div>
		<button id="clearBtn">清空</button>
		<button id="recoverBtn">恢复</button>
		<button id="unBindBtn">取消绑定</button>
		<button id="bindBtn">绑定</button>
		<button id="changedBtn">检测</button>
		
		<a href="javascript:;" id="ajaxBtn">ajax 请求测试</a>
	</div>
	
	<form action="">
		<input type="text" value="123"></input>
		<input type="password" value="456"></input>
		<textarea rows="2" cols="10">789</textarea>
		<input type="checkbox" name="game" checked="checked" value="1" />
		<input type="checkbox" name="game" value="2" />
		<select>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
	</form>
</body>
</html>