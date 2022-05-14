<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>监控日志详情  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
			                    <span>警报日志详情</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
									<div class="col-lg-12">
				                		<div class="form-horizontal">
				                			<h4 class="font-bold">基本信息</h4>
											<hr>
		                             		<div class="form-group">
							                    <label class="control-label col-lg-2">监控名称</label>
							                    <div class="col-lg-8">
							                        <input type="text" name="name" class="form-control" value="${logInfo.monitorName}" readonly="readonly">
							                    </div>
							                </div>
							         		
							         		<div class="form-group">
							                    <label class="control-label col-lg-2">监控url</label>
							                    <div class="col-lg-8">
							                        <input type="text" name="sub" class="form-control" value="${logInfo.sub}" readonly="readonly">
							                    </div>
							                </div>
							                
							                <div class="form-group">
							                    <label class="control-label col-lg-2">监控状态</label>
							                    <div class="col-lg-3">
							                        <select name="subStatus" class="form-control" data-initValue="${logInfo.subStatus}" disabled="disabled">
					                                	<option value="normal">正常</option>
					                                	<option value="error">异常</option>
					                                </select>
							                    </div>
							                    
							                    <label class="control-label col-lg-2">响应时间</label>
							                    <div class="col-lg-3">
							                        <input type="text" name="spendTime" class="form-control" value="${logInfo.spendTime} ms" readonly="readonly">
							                    </div>
							                </div>
							                
							                <div class="form-group">
							                    <label class="control-label col-lg-2">异常概要</label>
							                    <div class="col-lg-8">
							                    	<textarea name="errorBrief" class="form-control" rows="4">${logInfo.errorBrief}</textarea>
							                    </div>
							                </div>
							                
							                <div class="form-group">
							                    <label class="control-label col-lg-2">异常详情</label>
							                    <div class="col-lg-8">
							                    	<textarea name="errorDetail" class="form-control" rows="4">${logInfo.errorDetail}</textarea>
							                    </div>
							                </div>
							                
							                <div class="form-group">
							                    <label class="control-label col-lg-2">响应信息</label>
							                    <div class="col-lg-8">
							                    	<textarea name="respContent" class="form-control" rows="4">${logInfo.respContent}</textarea>
							                    </div>
							                </div>
		                            	</div>
		                            </div>
	                            </div>
	                            
	                            <div class="row">
									<div class="col-lg-12">
				                		<div class="form-horizontal">
				                			<h4 class="font-bold">接收者列表</h4>
											<hr>
											
										</div>
									</div>
								</div>
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
    
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
