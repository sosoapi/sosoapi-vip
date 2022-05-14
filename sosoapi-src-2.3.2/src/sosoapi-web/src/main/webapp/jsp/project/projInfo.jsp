<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>项目基本信息  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
			                    <span>基本信息</span>
			                </div>
		                	<div class="panel-body">
		                		<form id="projInfoForm" class="form-horizontal">
                                	<input type="hidden" name="projId" value="${projInfo.projId}">
					                <div class="form-group">
					                    <label class="control-label col-lg-3">名称</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="name" value="${projInfo.name}" class="form-control">
					                    </div>
					                </div>
					
									<div class="form-group">
					                    <label class="control-label col-lg-3">英文名称</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="code" value="${projInfo.code}" class="form-control">
					                    </div>
					                </div>
					                
					                <!-- <div class="form-group">
									    <label class="control-label col-lg-4">语言</label>
									
									    <div class="col-lg-4">
									        <select data-placeholder="请选择项目中使用的语言" multiple class="form-control chzn-select  chzn-rtl" tabindex="10">
									            <option>java</option>
									            <option>php</option>
									            <option>python</option>
									        </select>
									    </div>
									</div> -->
									
									<div class="form-group">
				                        <label class="control-label col-lg-3">状态</label>
				
				                        <div class="col-lg-6">
				                            <select name="status" class="form-control" data-initValue="${projInfo.status}">
									            <option value="open">开启</option>
									            <option value="close">关闭</option>
									        </select>
				                        </div>
				                    </div>
				                    
					                <div class="form-group">
					                    <label class="control-label col-lg-3">描述</label>
					
					                    <div class="col-lg-6">
					                        <textarea id="projDesc" name="description" class="form-control" rows="10">${projInfo.description}</textarea>
					                    </div>
					                </div>
				                    
				                    <div class="form-group">
				                        <div class="col-lg-1 col-lg-offset-3">
				                        	<sosoapi:hasPermission name="projInfo:update" projId="${projInfo.projId}">
				                        		<button id="saveProjInfoBtn" class="btn btn-success btn-lg" type="button">保存</button>
				                        	</sosoapi:hasPermission>
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
    <script type="text/javascript" src="js/apidoc/toolBar.js"></script>
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
    <script type="text/javascript" src="js/project/projInfo.js?version=1.0.1"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
