<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>版本详情  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/datetimepicker/css/jquery.datetimepicker.min.css" rel="stylesheet" />
	<link href="plugin/touchspin/css/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
   	<jsp:include page="/jsp/user/controlMenu.jsp" />
	<!-- END LEFT SECTION -->
		
	<!-- body content start-->
    <div class="body-content" >
    	<!-- TOP SECTION -->
	    <jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
  
  		<!--body wrapper start-->
        <div id="wrapper">
			<div class="content animate-panel">
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>版本信息</span>
			                </div>
		                	<div class="panel-body">
		                		
		                		<form id="goodsInfoForm" method="post" action="${action}" class="form-horizontal">
                             		<input type="hidden" name="goodsId" value="${goodsInfo.id}" />
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">名称</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="name" class="form-control" value="${goodsInfo.name}">
					                    </div>
					                </div>
					         
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">版本号</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="description" class="form-control" value="${goodsInfo.description}">
					                    </div>
					                </div>
					                
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">类型</label>
					                    <div class="col-lg-8">
					                        <select name="type" class="form-control" data-initValue="${goodsInfo.type}">
			                                	<option value="src">源码</option>
			                                	<option value="war">war包</option>
			                                	<option value="software">软件</option>
			                                </select>
					                    </div>
					                </div>
									
									<div class="form-group">
					                    <label class="control-label col-lg-2">价格</label>
					                    <div class="col-lg-8">
					                        <input type="text" id="price" name="price" class="form-control" value="${goodsInfo.price/100}">
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">会员价</label>
					                    <div class="col-lg-8">
					                        <input type="text" id="discount" name="discount" class="form-control" value="${goodsInfo.discount/100}">
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">文件名</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="fileName" class="form-control" value="${goodsInfo.fileName}">
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">下载目录</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="downloadUrl" class="form-control" value="${goodsInfo.downloadUrl}">
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">启用状态</label>
					                    <div class="col-lg-8">
					                        <select name="status" class="form-control" data-initValue="${goodsInfo.status}">
			                                	<option value="on">开启</option>
			                                	<option value="off">关闭</option>
			                                </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">发布状态</label>
					                    <div class="col-lg-8">
					                        <select name="pubStatus" class="form-control" data-initValue="${goodsInfo.pubStatus}">
			                                	<option value="on">开启</option>
			                                	<option value="off">关闭</option>
			                                </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">发布时间</label>
					                    <div class="col-lg-8">
					                        <input type="text" id="pubDate" name="pubDate" value="<fmt:formatDate value="${goodsInfo.pubDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>" class="form-control">
					                    </div>
					                </div>
					                
					                <div class="form-group ${operType != 'update' ? 'hidden' : ''}">
					                    <label class="control-label col-lg-2">支付链接</label>
					                    <div class="col-lg-8">
					                        <textarea class="form-control" readonly="readonly" rows="2">${Cfg.WEB_BASE_URL}auth/order/add.htm?goodsId=${goodsInfo.id}&redirectUrl=pass/goods/list.htm</textarea>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">版本说明</label>
					                    <div class="col-lg-8">
					                    	<textarea id="detailInfo" name="detailInfo">${goodsInfo.detailInfo}</textarea>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <div class="col-lg-offset-2 col-lg-8">
					                        <div class="form-actions">
		                                    	<input id="saveBtn" type="button" value="提交" class="btn btn-success" />
		                                	</div>
					                    </div>
					                </div>
                            	</form>
		                	</div>
		                </div>
				   	</div>
				</div>
				
				<!-- MODAL SECTION -->
				
				<!-- END MODAL SECTION -->
		   	</div>
        </div>
        <!--body wrapper end-->
        
        <!--footer section start-->
	    <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
	    <!--footer section end-->
	</div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/datetimepicker/js/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript" src="plugin/touchspin/js/jquery.bootstrap-touchspin.min.js"></script>
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
   	<script type="text/javascript">
	   	$(function(){
	   		$('#detailInfo').summernote({
				height: 300
			});
	   	
	   		$("#goodsInfoForm").bootstrapValidator({
	   			fields:{
	   				name:{
	   	                validators: {
	   	                    notEmpty: {
	   	                        message: '标题不能为空'
	   	                    }
	   	                }
	   				}
	   			}
	   		});
	   		
	   		$("#saveBtn").click(function(){
	   			if(isFormValid("goodsInfoForm")){
	   				bootbox.confirm("确定提交当前信息？",function(){
	   					$("#price").val(Number($("#price").val()) * 100);
	   					$("#discount").val(Number($("#discount").val()) * 100);
	   					
	   					$("#goodsInfoForm").bootstrapValidator('defaultSubmit');
		   			});
	   			}
	   		});
	   		
	   		//初始化时间控件
	   		$.datetimepicker.setLocale('zh');
			$('#pubDate').datetimepicker({
				format:'Y-m-d H:i:s',
				timepicker:true
			});
			
			//初始化金额控件
			$("#price,#discount").TouchSpin({
                min: 0,
                max: 999999,
                step: 1,
                decimals: 0,
                boostat: 5,
                maxboostedstep: 10
            });
		});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
