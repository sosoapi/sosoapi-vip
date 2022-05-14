<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>接口归档  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
			<div class="content animate-panel">
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <span>接口归档</span>
			                </div>
		                	<div class="panel-body">
		                		<form id="docArchiveForm" method="post" action="${action}" role="form" class="form-horizontal">
									<input id="docId" type="hidden" name="docId" value="${param.docId}"/>
									<input id="projId" type="hidden" name="projId" value="${param.projId}"/>
									<input id="archiveId" type="hidden" name="archiveId" value="${archiveInfo.id}"/>
									<div class="form-group">
										<label class="control-label col-lg-3">标题</label> 
										<div class="col-lg-6">
											<input name="title" class="form-control" value="${archiveInfo.title}"/>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-lg-3">标签</label> 
										<div class="col-lg-6">
											<input name="label" class="form-control" value="${archiveInfo.label}"/>
										</div>
									</div>
									
									<div class="form-group">
					                	<label class="control-label col-lg-3">分享</label>
				
				                        <div class="col-lg-6">
				                            <select name="share" class="form-control" data-initValue="${archiveInfo.share}">
									            <option value="true">开启</option>
									            <option value="false">关闭</option>
									        </select>
				                        </div>
					                </div>
									
									<div class="form-group">
										<label class="control-label col-lg-3">访问密码</label> 
										<div class="col-lg-6">
											<input name="sharePassword" type="password" class="form-control" placeholder="可选" value="${archiveInfo.sharePassword}"/>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-lg-3">访问url</label> 
										<div class="col-lg-6">
											<textarea name="shareUrl" readonly="readonly" class="form-control" rows="5">${empty archiveInfo.shareUrl ? '保存后自动计算，可通过接口归档列表进入编辑查看。' : archiveInfo.shareUrl}</textarea>
										</div>
									</div>
									
           							<div class="form-group">
										<label class="control-label col-lg-3">描述</label>
					                	<div class="col-lg-6">
					                		<textarea name="description" class="form-control" rows="4">${archiveInfo.description}</textarea>
					                	</div>
           							</div>
           							
           							<div class="form-group">
					                    <div class="col-lg-offset-3 col-lg-6">
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
	   		$("#docArchiveForm").bootstrapValidator({
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
	   			if(isFormValid("docArchiveForm")){
	   				bootbox.confirm("确定提交当前归档信息？",function(){
	   					$("#docArchiveForm").bootstrapValidator('defaultSubmit');
		   			});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
