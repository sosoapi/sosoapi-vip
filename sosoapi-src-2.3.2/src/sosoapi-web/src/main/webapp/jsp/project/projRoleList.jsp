<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>项目角色管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
        	    						<form class="form-horizontal" action="auth/proj/role/list.htm" method="get">
        	    							<input type="hidden" name="projId" value="${param.projId}">
							                <div class="form-group">
							                	<label class="control-label col-lg-1">名称</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="name" value="${param.name}" class="form-control">
							                	</div>
							                	
							                	<label class="control-label col-lg-1">编码</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="code" value="${param.code}" class="form-control">
							                	</div>
							                	
							                	<label class="control-label col-lg-1">状态</label>
							                	<div class="col-lg-2">
							                		<select name="status" class="form-control" data-initValue="${param.status}">
							                			<option value="">全部</option>
					                                	<option value="on">开启</option>
					                                	<option value="off">关闭</option>
					                                </select>
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
			                    	<a href="#roleFormModal" onclick="initBeforeAddOper();" data-toggle="modal" class="text-muted">
			                    		<i class="fa fa-plus"></i> 新增
			                    	</a>
			                    	
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>角色列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                	<th class="col-lg-2">名称</th>
		                                    <th class="col-lg-2">编码</th>
		                                    <th class="col-lg-1">排序权重</th>
		                                    <th class="col-lg-1">状态</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-4">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="roleInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${roleInfo.name}</td>
		                                         	<td>${roleInfo.code}</td>
		                                         	<td>${roleInfo.sortWeight}</td>
		                                         	<td>${roleInfo.status.displayName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${roleInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
		                                         		<sosoapi:hasPermission name="projRole:forwardPrivSet" projId="${param.projId}">
			                                         		<a class="btn btn-sm btn-success" href="auth/proj/role/forwardPrivSet.htm?roleId=${roleInfo.id}&projId=${param.projId}&docId=${param.docId}">
		                                                   		<i class="fa fa-filter"></i> 权限配置
		                                                   	</a>
			                                            </sosoapi:hasPermission>
			                                            
	                                         			<button type="button" onclick="initUpdateOper(${param.projId},${roleInfo.id});" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#roleFormModal">
	                                                   		<i class="fa fa-pencil"></i> 编辑
	                                                   	</button>
	                                                   	
	                                                   	<button type="button" onclick="initDelOper(${param.projId},${roleInfo.id});" class="btn btn-sm btn-danger" ${roleInfo.delDisabled ? 'disabled="disabled"' : ''}>
	                                                   		<i class="fa fa-trash"></i> 删除
	                                                   	</button>
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
						<div class="modal fade" id="roleFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">项目角色信息</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="roleForm" role="form" class="form-horizontal">
													<input type="hidden" name="projId" value="${param.projId}">
													<input id="roleId" type="hidden" name="roleId">
													<input id="operType" type="hidden" name="operType">
													<div class="form-group">
														<label class="control-label col-lg-3">名称</label> 
														<div class="col-lg-6">
															<input name="name" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">编码</label> 
														<div class="col-lg-6">
															<input name="code" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">主页url</label> 
														<div class="col-lg-6">
															<input name="homeUrl" class="form-control" placeholder="以'/'开头"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">排序权重</label> 
														<div class="col-lg-6">
															<input name="sortWeight" class="form-control" value="0"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">状态</label>
									                	<div class="col-lg-6">
									                		<select name="status" class="form-control">
							                                	<option value="on">启用</option>
							                                	<option value="off">关闭</option>
							                                </select>
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
										<button id="saveRoleBtn" type="button" class="btn btn-success">保存</button>
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
		
		<!--footer section start-->
	    <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
	    <!--footer section end-->
	</div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/project/projRoleList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
