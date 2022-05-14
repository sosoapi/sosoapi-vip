<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/duallistbox/css/bootstrap-duallistbox.min.css" rel="stylesheet">
	<style type="text/css">
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="blank">
	<div class="content animate-panel">
		<div class="row">
            <div class="col-lg-8 col-lg-offset-2">
            	<select id="dualList" multiple="multiple" size="10">
			        <option value="option1">Option 1</option>
			        <option value="option2">Option 2</option>
			        <option value="option3" selected="selected">Option 3</option>
			        <option value="option4">Option 4</option>
			        <option value="option5">Option 5</option>
			        <option value="option6" selected="selected">Option 6</option>
			        <option value="option7">Option 7</option>
			        <option value="option8">Option 8</option>
			        <option value="option9">Option 9</option>
			        <option value="option10">Option 10</option>
			        <option value="option11">Option 11</option>
			        <option value="option12">Option 12</option>
			        <option value="option13">Option 13</option>
			        <option value="option14">Option 14</option>
			        <option value="option15">Option 15</option>
			        <option value="option16">Option 16</option>
			        <option value="option17">Option 17</option>
			        <option value="option18">Option 18</option>
			        <option value="option19">Option 19</option>
			        <option value="option20">Option 20</option>
			      </select>
            </div>
        </div>
        <div class="row m-t-md">
        	<div class="col-lg-4 col-lg-offset-2">
        		<button id="addOptBtn" type="button" class="btn btn-default btn-block">新增选项</button>
        	</div>
        	
        	<div class="col-lg-4">
        		<button id="submitOptBtn" type="button" class="btn btn-default btn-block">提交</button>
        	</div>
        </div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/duallistbox/js/jquery.bootstrap-duallistbox.min.js"></script>
    <script type="text/javascript">
    $(function () {
    	$('#dualList').bootstrapDualListbox({
    		filterTextClear: "全部展示",
    		filterPlaceHolder: "查询",
            nonSelectedListLabel: '未加入',
            selectedListLabel: '已加入',
            moveSelectedLabel: '移动选中',
            moveAllLabel: '全部移动',
            removeSelectedLabel: '移除选中',
            removeAllLabel: '全部移除',
            infoTextEmpty: '',
            
            preserveSelectionOnMove: 'moved',
            selectorMinimalHeight: 300,
            moveOnSelect: false,
            showFilterInputs: true,
            nonSelectedFilter: '',
            selectedFilter: '',
            infoText:false
          });
    	
    	$("#addOptBtn").click(function(){
    		$("#dualList").append('<option value="option21" selected="selected">Option 21</option>');
    		$("#dualList").bootstrapDualListbox('refresh', true);
    	});
    	
    	$("#submitOptBtn").click(function(){
    		alert($('#dualList').val());
    	});
    });
    
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
