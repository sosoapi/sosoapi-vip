<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>变更通知  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/project/projMenu.jsp" />
	<!-- END LEFT SECTION -->
		
	<!-- body content start-->
    <div class="body-content" >
    	<!-- TOP SECTION -->
	    <jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
  
  		<!--body wrapper start-->
        <div id="wrapper">
        	<!-- TOOLBAR SECTION -->
            <jsp:include page="/jsp/apidoc/toolBar.jsp">
				<jsp:param name="projId" value="${param.projId}" />
				<jsp:param name="docId" value="${param.docId}" />
			</jsp:include>
			<!-- TOOLBAR SECTION -->
				
			<div class="content animate-panel">
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                        <!-- <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a> -->
			                    </div>
			                    <span>邮件通知</span>
			                </div>
		                	<div class="panel-body">
		                		<form id="noticeForm" class="form-horizontal">
                             		<input type="hidden" name="projId" value="${param.projId}"/>
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">邮件标题</label>
					
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control" value='项目[${projTempMap[param.projId].code}] - 接口变更通知'>
					                    </div>
					                </div>
					         
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">加入变更历史</label>
					
					                    <div class="col-lg-8">
					                        <select name="addLog" class="form-control">
	                                			<option value="true">是</option>
	                                			<option value="false">否</option>
									        </select>
					                    </div>
					                </div>
					                
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">通知角色类型</label>
					
					                    <div class="col-lg-8">
					                        <select name="receiverRole" class="form-control">
									            <option value="">所有成员</option>
									            <%-- <c:forEach items="${appProjRoleList}" var="selectInfo">
				                					<option value="${selectInfo.code}">${selectInfo.name}</option>
				                				</c:forEach> --%>
				                				<c:forEach items="${roleList}" var="selectInfo">
				                					<option value="${selectInfo.code}">${selectInfo.name}</option>
				                				</c:forEach>
									        </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">其他通知邮箱</label>
					
					                    <div class="col-lg-8">
					                        <textarea name="otherReceivers" class="form-control" placeholder="多个邮箱以';'分隔"></textarea>
					                    </div>
					                </div>
									               
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					
					                    <div class="col-lg-8">
					                    	<div id="noticeContent"></div>
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
		
		<!--footer section start-->
	    <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
	    <!--footer section end-->
	</div>	

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/apidoc/toolBar.js"></script>
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
   	<script type="text/javascript">
	   	$(function(){
			$('#noticeContent').summernote({
				height: 300, 
				minHeight: null,
				maxHeight: null,
				focus: true,
				lang: 'zh-CN', // default: 'en-US'
				toolbar: [
			          ['style', ['style','fontname','fontsize','height']],
			          ['color', ['color']],
			          ['font', ['bold', 'italic', 'underline', 'clear']],
			          ['para', ['ul', 'ol', 'paragraph']],
			          ['insert', ['table','link','hr']],
			          ['view', ['fullscreen','preview']]
			  	]
			});
	   		
	   		$("#noticeForm").bootstrapValidator({
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
	   			if(isFormValid("noticeForm")){
	   				var param = $("#noticeForm").find("*").getFieldsValue();
	   				//富文本内容
	   				param.content = $("#noticeContent").code();
	   				doPost("auth/proj/mem/json/sendNotice.htm",param,function(data){
	   					notice("邮件发送成功!");
	   				});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
