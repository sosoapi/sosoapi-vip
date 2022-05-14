<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>反馈回复  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
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
			                    <span>反馈回复</span>
			                </div>
		                	<div class="panel-body">
		                		<form id="replyForm" class="form-horizontal">
                             		<input type="hidden" name="suggestId" value="${suggestInfo.suggestId}"/>
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">收件人</label>
					
					                    <div class="col-lg-8">
					                        <input type="text" class="form-control" name="toEmail" value="${suggestInfo.email}">
					                    </div>
					                </div>

                             		<div class="form-group">
					                    <label class="control-label col-lg-2">邮件标题</label>
					
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control" value='SosoApi - 反馈回复'>
					                    </div>
					                </div>
					         
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					
					                    <div class="col-lg-8">
					                    	<div id="content">
					                    		<p>您好，</p>
					                    		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢使用SosoApi。</p>
					                    		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您之前的反馈"${suggestInfo.title}"已收到。</p>
					                    	</div>
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
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
   	<script type="text/javascript">
	   	$(function(){
			$('#content').summernote({
				height: 300
			});
	   		
	   		$("#replyForm").bootstrapValidator({
	   			fields:{
	   				title:{
	   	                validators: {
	   	                    notEmpty: {
	   	                        message: '邮件标题不能为空'
	   	                    }
	   	                }
	   				}
	   			}
	   		});
	   		
	   		$("#saveBtn").click(function(){
	   			if(isFormValid("replyForm")){
	   				var param = $("#replyForm").find("*").getFieldsValue();
	   				//富文本内容
	   				param.content = $("#content").code();
	   				doPost("admin/suggest/json/reply.htm",param,function(data){
	   					notice("邮件发送成功!");
	   				});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
