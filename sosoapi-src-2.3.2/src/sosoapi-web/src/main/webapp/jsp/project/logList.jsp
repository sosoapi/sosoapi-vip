<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>变更历史  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
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
			                    	<sosoapi:hasPermission name="projLog:forwardAddLog" projId="${param.projId}">
			                    		<a href="auth/proj/log/forwardAddLog.htm?projId=${param.projId}&docId=${param.docId}" class="text-muted">
	                                   		<i class="fa fa-plus"></i> 新增日志
	                                	</a>
			                    	</sosoapi:hasPermission>
                                	
			                        <!-- <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a> -->
			                    </div>
			                    <span>变更日志</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                	<th class="col-lg-5">标题</th>
		                                    <th class="col-lg-1">内容</th>
		                                    <th class="col-lg-2">发布者</th>
		                                    <th class="col-lg-2">发布时间</th>
		                                    <th class="col-lg-2">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="logInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${logInfo.title}</td>
		                                         	<td>
		                                         		<div class="hidden">${logInfo.content}</div>
		                                         		<a class="msg-content-btn" data-toggle="modal" href="#msgContentModal">
                              								查看详情
                          								</a>
		                                         	</td>
		                                         	<td>${logInfo.pubNickName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${logInfo.pubDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
		                                         		<sosoapi:hasPermission name="projLog:del" projId="${logInfo.projId}">
		                                         			<a onclick="delOper(${logInfo.projId},${logInfo.logId});" type="button" class="btn btn-sm btn-danger">
		                                                   		<i class="fa fa-trash"></i> 删除
		                                                   	</a>
		                                         		</sosoapi:hasPermission>
		                                           </td>
		                                     	</tr>
                                 			</c:forEach>
                                 		</c:if>
                                 	</tbody>
	                            </table>
		                	
		                		<jsp:include page="/jsp/common/paginate.jsp"/>
		                	</div>
		                </div>
		           	</div>
		       	</div>
		       	
		       	<div class="row">
        			<div class="col-lg-12">
				       	<div class="modal fade" id="msgContentModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
			                <div class="modal-dialog">
			                    <div class="modal-content">
			                        <div class="modal-header">
			                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                            <h4 class="modal-title">日志详情</h4>
			                        </div>
			                        <div id="msgContentModalBody" class="modal-body">
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                        </div>
			                    </div>
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
    <script type="text/javascript" src="js/project/logList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
