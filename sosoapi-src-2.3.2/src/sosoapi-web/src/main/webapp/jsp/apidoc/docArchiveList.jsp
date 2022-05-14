<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>接口归档列表  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<jsp:include page="/jsp/project/projMenu.jsp" />
	
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
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>查询</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
        	    					<div class="col-lg-12">
        	    						<form class="form-horizontal" action="auth/doc/archive/list.htm" method="get">
        	    							<input type="hidden" name="projId" value="${param.projId}" class="form-control">
        	    							<input type="hidden" name="docId" value="${param.docId}" class="form-control">
							                <div class="form-group">
							                	<label class="control-label col-lg-1">标题</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="title" value="${param.title}" class="form-control">
							                	</div>
							                	
							                	<label class="control-label col-lg-1">描述</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="description" value="${param.description}" class="form-control">
							                	</div>
							                	
							                	<label class="control-label col-lg-1">标签</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="label" value="${param.label}" class="form-control">
							                	</div>
							                	
							                	<div class="col-lg-3">
							                		<button type="submit" class="btn btn-default">
							                			<i class="fa fa-search"></i> 查询
							                		</button>
							                	</div>
							                </div>
							            </form>
	                            	</div>
	                            </div>
		                	</div>
		                </div>
		           	</div>
		      	</div>
		      	
        		<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                    	<sosoapi:hasPermission name="docArchive:add" projId="${param.projId}">
										<a href="auth/doc/archive/forwardAdd.htm?projId=${param.projId}&docId=${param.docId}" class="text-muted">
											<i class="fa fa-plus"></i> 新增
										</a>
									</sosoapi:hasPermission>
			                    </div>
			                    <span>归档列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-2">标题</th>
		                                    <th class="col-lg-3">标签</th>
		                                    <th class="col-lg-1">分享</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-4">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="archiveInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${archiveInfo.title}</td>
		                                         	<td>${archiveInfo.label}</td>
		                                         	<td>${archiveInfo.share ? '开启' : '关闭'}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${archiveInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
			                                            <sosoapi:hasPermission name="docArchive:update" projId="${param.projId}">
			                                            	<a type="button" href="auth/doc/archive/forwardUpdate.htm?projId=${param.projId}&docId=${param.docId}&archiveId=${archiveInfo.id}" class="btn btn-sm btn-primary">
		                                                   		<i class="fa fa-pencil"></i> 编辑
		                                                   	</a>
	                                                   	</sosoapi:hasPermission>
	                                                   	
	                                                   	<sosoapi:hasPermission name="docArchive:del" projId="${param.projId}">
		                                                   	<button type="button" onclick="initDelOper(${archiveInfo.id},${archiveInfo.docId});" class="btn btn-sm btn-danger">
		                                                   		<i class="fa fa-trash"></i> 删除
		                                                   	</button>
	                                                   	</sosoapi:hasPermission>
	                                                   	
	                                                	<a href="auth/doc/archive/preview.htm?docId=${archiveInfo.docId}&archiveId=${archiveInfo.id}" target="_bank" class="btn btn-sm btn-success">
	                                                   		<i class="fa fa-eye"></i> 预览
	                                                   	</a>
	                                                   	
	                                                   	<div class="btn-group">
													        <button class="btn btn-sm btn-default">
													        	<i class="fa fa-share"></i> 导出
													        </button>
													        
													        <button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle"><span class="caret"></span></button>
													        <ul class="dropdown-menu">
													            <li>
													            	<a href="auth/doc/archive/export.htm?docId=${archiveInfo.docId}&archiveId=${archiveInfo.id}&exportType=swagger">
													            		<i class="fa fa-file-text-o"></i> swagger文档
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
	<script type="text/javascript" src="js/apidoc/docArchiveList.js"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
