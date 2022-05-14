<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>新增变更日志  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
        	<div class="small-header transition animated fadeIn">
		        <div class="hpanel">
		            <div class="panel-body">
		                <div id="hbreadcrumb" class="pull-left">
		                    <ol class="hbreadcrumb breadcrumb">
		                        <li>
                                	<a href="auth/proj/log/list.htm?projId=${param.projId}&docId=${param.docId}">变更历史</a>
                                </li>
                                <li class="active">新增变更日志</li>
		                    </ol>
		                </div>
		            </div>
		        </div>
		    </div>
		    
			<div class="content animate-panel">
				<!-- TOOLBAR SECTION -->
             	<%-- <jsp:include page="/jsp/apidoc/toolBar.jsp">
					<jsp:param name="projId" value="${param.projId}" />
					<jsp:param name="docId" value="${param.docId}" />
				</jsp:include> --%>
				<!-- TOOLBAR SECTION -->
				
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>新增变更日志</span>
			                </div>
		                	<div class="panel-body">
		                		<form id="msgInfoForm" class="form-horizontal">
                             		<input id="projId" type="hidden" name="projId" value="${param.projId}"/>
                             		<input id="docId" type="hidden" name="docId" value="${param.docId}"/>
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">标题</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control">
					                    </div>
					                </div>
					         
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					                    <div class="col-lg-8">
					                    	<textarea id="msgContent" name="content"></textarea>
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
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
   	<script type="text/javascript">
	   	$(function(){
	   		$('#msgContent').summernote({
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
	   	
	   		$("#msgInfoForm").bootstrapValidator({
	   			fields:{
	   				title:{
	   	                validators: {
	   	                    notEmpty: {
	   	                        message: '标题不能为空'
	   	                    }
	   	                }
	   				}
	   			}
	   		});
	   		
	   		$("#saveBtn").click(function(){
	   			if(isFormValid("msgInfoForm")){
	   				var param = $("#msgInfoForm").find("*").getFieldsValue();
	   				//富文本内容
	   				param.content = $("#msgContent").code();
	   				doPost("auth/proj/log/json/add.htm",param,function(data){
	   					window.location.href = "auth/proj/log/list.htm?projId=" + $("#projId").val() + "&docId=" + $("#docId").val();
	   				});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
