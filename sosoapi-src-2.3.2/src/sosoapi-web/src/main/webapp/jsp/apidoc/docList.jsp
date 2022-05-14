<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>Api文档列表  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/user/controlMenu.jsp" />
	<!-- END LEFT SECTION -->
	
	<div class="body-content">
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
			                        <!-- <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a> -->
			                    </div>
			                    Api文档列表
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-2">文档标题</th>
		                                    <th class="col-lg-1">版本</th>
		                                    <th class="col-lg-2">项目名称</th>
		                                    <th class="col-lg-2">项目英文名</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-3">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="docInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${docInfo.docTitle}</td>
		                                         	<td>${docInfo.version}</td>
		                                         	<td>${docInfo.projName}</td>
													<td>${docInfo.projCode}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${docInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
	                                                	<a href="auth/apidoc/preview.htm?docId=${docInfo.docId}" target="_bank" class="btn btn-sm btn-success">
	                                                   		<i class="fa fa-eye"></i> 预览
	                                                   	</a>
	                                                   	
	                                                   	<div class="btn-group">
													        <button class="btn btn-sm btn-default">
													        	<i class="fa fa-share"></i> 导出
													        </button>
													        
													        <button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle"><span class="caret"></span></button>
													        <ul class="dropdown-menu">
													        	<li>
													            	<a href="auth/apidoc/export.htm?docId=${docInfo.docId}&exportType=html">
													            		<i class="fa fa-file-code-o"></i> html文档
													            	</a>
													            </li>
													            <li>
													            	<a href="auth/apidoc/export.htm?docId=${docInfo.docId}&exportType=doc">
													            		<i class="fa fa-file-word-o"></i> word文档
													            	</a>
													            </li>
													            <li>
													            	<a href="auth/apidoc/export.htm?docId=${docInfo.docId}&exportType=swagger">
													            		<i class="fa fa-file-text-o"></i> swagger文档
													            	</a>
													            </li>
													            <li>
													            	<a href="auth/apidoc/export.htm?docId=${docInfo.docId}&exportType=postman">
													            		<i class="fa fa-file-powerpoint-o"></i> postman文档
													            	</a>
													            </li>
													        </ul>
													    </div>
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
        	</div>
        </div>
   	
   		<footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
   	</div>
        	
    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
	<!-- <script type="text/javascript" src=""></script> -->
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
