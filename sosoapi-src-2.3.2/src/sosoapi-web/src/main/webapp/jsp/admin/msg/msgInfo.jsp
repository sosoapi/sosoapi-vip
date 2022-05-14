<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>消息详情  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
			                    <span>发布系统消息</span>
			                </div>
		                	<div class="panel-body">
		                		<form id="msgInfoForm" method="post" action="${action}" class="form-horizontal">
                             		<input type="hidden" name="msgId" value="${msgInfo.id}" />
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">标题</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control" value="${msgInfo.title}">
					                    </div>
					                </div>
					         
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">消息类型</label>
					                    <div class="col-lg-8">
					                        <select name="msgType" class="form-control" data-initValue="${msgInfo.msgType}">
			                                	<option value="notice">公告</option>
			                                	<option value="version">版本升级</option>
			                                	<option value="other">其他</option>
			                                </select>
					                    </div>
					                </div>
									       
									<div class="form-group">
					                    <label class="control-label col-lg-2">对象类型</label>
					                    <div class="col-lg-8">
					                        <select name="roleId" class="form-control" data-initValue="${msgInfo.roleId}">
			                                	<c:if test="${not empty sysRoleList}">
													<c:forEach items="${sysRoleList}" var="roleInfo" varStatus="status">
														<option value="${roleInfo.code}">${roleInfo.name}</option>						
													</c:forEach>
												</c:if>
			                                </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">消息状态</label>
					                    <div class="col-lg-8">
					                        <select name="msgStatus" class="form-control" data-initValue="${msgInfo.msgStatus}">
			                                	<option value="pub">发布</option>
			                                	<option value="unpub">未发布</option>
			                                </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					                    <div class="col-lg-8">
					                    	<textarea id="msgContent" name="content">${msgInfo.content}</textarea>
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
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
   	<script type="text/javascript">
	   	$(function(){
	   		$('#msgContent').summernote({
				height: 300
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
	   				bootbox.confirm("确定发布当前消息？",function(){
	   					$("#msgInfoForm").bootstrapValidator('defaultSubmit');
		   			});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
