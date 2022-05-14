<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>项目成员  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>查询</span>
			                </div>
		                	<div class="panel-body">
		                		<!-- SEARCH SECTION -->
		                        <form class="form-horizontal" action="auth/proj/mem/list.htm" method="get">
		                        	<input type="hidden" name="projId" value="${param.projId}">
		                        	<input type="hidden" name="docId" value="${param.docId}">
					                <div class="form-group">
					                	<label class="control-label col-lg-1">昵称</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="nickName" value="${param.nickName}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">邮箱</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="email" value="${param.email}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">角色</label>
					                	<div class="col-lg-2">
			                                <select class="form-control" name="roleId" data-initValue="${param.role}">
				                				<option value="">全部</option>
				                				<%-- <c:forEach items="${appProjRoleList}" var="selectInfo">
				                					<option value="${selectInfo.code}">${selectInfo.name}</option>
				                				</c:forEach> --%>
				                				<c:forEach items="${roleList}" var="selectInfo">
				                					<option value="${selectInfo.code}">${selectInfo.name}</option>
				                				</c:forEach>
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
				
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                    	<sosoapi:hasPermission name="projMem:listForAdd" projId="${param.projId}">
			                    		<a href="auth/proj/mem/searchForAdd.htm?projId=${param.projId}&docId=${param.docId}" class="text-muted">
	                                  		<i class="fa fa-plus"></i> 邀请成员
	                               		</a>
			                    	</sosoapi:hasPermission>
			                        
			                        <!-- <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a> -->
			                    </div>
			                   	<span>成员列表</span>
			                </div>
		                	<div class="panel-body">
		                		<input id="projId" name="projId" type="hidden" value="${param.projId}"/>
		                		<table class="table table-hover table-bordered table-fixed">
	                            	<thead>
	                                	<tr>
	                                    	<th class="table-index">#</th>
	                                        <th class="col-lg-2">昵称</th>
	                                        <th class="col-lg-3">邮箱</th>
	                                        <th class="col-lg-1">角色</th>
	                                        <th class="col-lg-2">项目昵称</th>
	                                        <th class="col-lg-2">加入时间</th>
	                                        <th class="col-lg-2">操作</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="memInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                         	<td>
		                                         		${memInfo.nickName}
		                                         	</td>
		                                         	<td>${memInfo.email}</td>
		                                         	<td>${memInfo.projRoleName}</td>
		                                         	<td>${memInfo.projNickName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${memInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
		                                         		<sosoapi:hasPermission name="projMem:update" projId="${param.projId}">
		                                         			<button onclick="initUpdateMember(${memInfo.userId});" type="button" class="btn btn-sm btn-primary">
		                                                   		<i class="fa fa-pencil"></i> 编辑
		                                                   	</button>
		                                         		</sosoapi:hasPermission>
	                                                   	
	                                                   	<sosoapi:hasPermission name="projMem:del" projId="${param.projId}">
		                                                	<button onclick="removeMember(${memInfo.userId})" type="button" class="btn btn-sm btn-danger">
		                                                   		<i class="fa fa-trash"></i> 移除
		                                                   	</button>
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
				
				<!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="updateFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">成员信息</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="updateForm" role="form" class="form-horizontal">
													<input type="hidden" name="projId" value="${param.projId}">
													<input type="hidden" name="userId">
													<div class="form-group">
														<label class="control-label col-lg-3">昵称</label> 
														<div class="col-lg-6">
															<input name="nickName" class="form-control" readonly/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">邮箱</label> 
														<div class="col-lg-6">
															<input name="email" class="form-control" readonly/>
														</div>
													</div>
													
								                	<div class="form-group">
									                    <label class="control-label col-lg-3">角色</label>
									                    <div class="col-lg-6">
									                        <select class="form-control" name="projRoleId">
							                                	<%-- <c:forEach items="${appProjRoleList}" var="selectInfo">
								                					<option value="${selectInfo.code}">${selectInfo.name}</option>
								                				</c:forEach> --%>
								                				<c:forEach items="${roleList}" var="selectInfo">
								                					<option value="${selectInfo.code}">${selectInfo.name}</option>
								                				</c:forEach>
							                                </select>
							                                <a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="对方重新登录后生效" class="form-control-tip text-muted">
							                                	<i class="fa fa-question-circle"></i>
							                                </a>
									                    </div>
									                </div>
									                
									                <div class="form-group">
														<label class="control-label col-lg-3">项目昵称</label> 
														<div class="col-lg-6">
															<input name="projNickName" class="form-control"/>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="saveMemBtn" type="button" class="btn btn-success">保存</button>
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
        <!--body wrapper end-->
        
        <!--footer section start-->
	    <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
	    <!--footer section end-->
	</div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/project/memList.js?version=1.1.0"></script>
    <script type="text/javascript" src="js/apidoc/toolBar.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
