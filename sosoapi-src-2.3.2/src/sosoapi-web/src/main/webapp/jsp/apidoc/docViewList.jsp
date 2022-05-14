<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>文档视图列表  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
        	    						<form class="form-horizontal" action="auth/doc/view/list.htm" method="get">
        	    							<input type="hidden" name="projId" value="${param.projId}" class="form-control">
        	    							<input type="hidden" name="docId" value="${param.docId}" class="form-control">
							                <div class="form-group">
							                	<label class="control-label col-lg-offset-2 col-lg-1">标题</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="title" value="${param.title}" class="form-control">
							                	</div>
							                	
							                	<label class="control-label col-lg-1">描述</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="description" value="${param.description}" class="form-control">
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
			                    	<sosoapi:hasPermission name="docView:add" projId="${param.projId}">
				                        <a href="#docViewFormModal" onclick="initBeforeAddOper();" data-toggle="modal" class="text-muted">
				                    		<i class="fa fa-plus"></i> 新增
				                    	</a>
			                    	</sosoapi:hasPermission>
			                    </div>
			                    <span>文档视图列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-2">文档标题</th>
		                                    <th class="col-lg-1">版本</th>
		                                    <th class="col-lg-1">项目名称</th>
		                                    <th class="col-lg-1">分享</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-5">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="viewInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${viewInfo.title}</td>
		                                         	<td>${viewInfo.version}</td>
		                                         	<td>${viewInfo.projName}</td>
		                                         	<td>${viewInfo.share ? '开启' : '关闭'}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${viewInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
		                                         		<sosoapi:hasPermission name="docView:forwardResSet" projId="${param.projId}">
		                                         			<a class="btn btn-sm btn-info" href="auth/doc/view/forwardResSet.htm?projId=${param.projId}&docId=${viewInfo.docId}&viewId=${viewInfo.id}">
		                                                   		<i class="fa fa-filter"></i> 视图配置
		                                                   	</a>
		                                         		</sosoapi:hasPermission>
			                                            
			                                            <sosoapi:hasPermission name="docView:update" projId="${param.projId}">	
		                                         			<button type="button" onclick="initUpdateOper(${viewInfo.id},${viewInfo.docId});" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#docViewFormModal">
		                                                   		<i class="fa fa-pencil"></i> 编辑
		                                                   	</button>
	                                                   	</sosoapi:hasPermission>
	                                                   	
	                                                   	<sosoapi:hasPermission name="docView:del" projId="${param.projId}">
		                                                   	<button type="button" onclick="initDelOper(${viewInfo.id},${viewInfo.docId});" class="btn btn-sm btn-danger">
		                                                   		<i class="fa fa-trash"></i> 删除
		                                                   	</button>
	                                                   	</sosoapi:hasPermission>
	                                                   	
	                                                	<a href="auth/doc/view/preview.htm?docId=${viewInfo.docId}&viewId=${viewInfo.id}" target="_bank" class="btn btn-sm btn-success">
	                                                   		<i class="fa fa-eye"></i> 预览
	                                                   	</a>
	                                                   	
	                                                   	<div class="btn-group">
													        <button class="btn btn-sm btn-default">
													        	<i class="fa fa-share"></i> 导出
													        </button>
													        
													        <button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle"><span class="caret"></span></button>
													        <ul class="dropdown-menu">
													        	<li>
													            	<a href="auth/doc/view/export.htm?docId=${viewInfo.docId}&viewId=${viewInfo.id}&exportType=html">
													            		<i class="fa fa-file-code-o"></i> html文档
													            	</a>
													            </li>
													            <li>
													            	<a href="auth/doc/view/export.htm?docId=${viewInfo.docId}&viewId=${viewInfo.id}&exportType=doc">
													            		<i class="fa fa-file-word-o"></i> word文档
													            	</a>
													            </li>
													            <li>
													            	<a href="auth/doc/view/export.htm?docId=${viewInfo.docId}&viewId=${viewInfo.id}&exportType=swagger">
													            		<i class="fa fa-file-text-o"></i> swagger文档
													            	</a>
													            </li>
													            <li>
													            	<a href="auth/doc/view/export.htm?docId=${viewInfo.docId}&viewId=${viewInfo.id}&exportType=postman">
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
				
				<!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="docViewFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">视图信息</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<input id="docId" type="hidden" name="docId" value="${param.docId}">
												<input id="projId" type="hidden" name="projId" value="${param.projId}">
												<form id="docViewForm" role="form" class="form-horizontal">
													<input id="viewId" type="hidden" name="viewId">
													<input id="operType" type="hidden" name="operType">
													<div class="form-group">
														<label class="control-label col-lg-3">标题</label> 
														<div class="col-lg-6">
															<input name="title" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
									                	<label class="control-label col-lg-3">分享</label>
								
								                        <div class="col-lg-6">
								                            <select name="share" class="form-control">
													            <option value="true">开启</option>
													            <option value="false">关闭</option>
													        </select>
								                        </div>
									                </div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">访问密码</label> 
														<div class="col-lg-6">
															<input name="sharePassword" type="password" class="form-control" placeholder="可选"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">访问url</label> 
														<div class="col-lg-6">
															<textarea name="shareUrl" readonly="readonly" class="form-control" rows="5"></textarea>
														</div>
													</div>
													
               										<div class="form-group">
														<label class="control-label col-lg-3">描述</label>
									                	<div class="col-lg-6">
									                		<textarea name="description" class="form-control" rows="4"></textarea>
									                	</div>
               										</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="saveViewBtn" type="button" class="btn btn-success">保存</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MODAL SECTION -->
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
	<script type="text/javascript" src="js/apidoc/docViewList.js"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
