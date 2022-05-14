<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>用户管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
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
			                    <span>查询</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
        	    					<div class="col-lg-12">
        	    						<form class="form-horizontal" action="admin/user/list.htm" method="get">
							                <div class="form-group">
							                	<label class="control-label col-lg-offset-2 col-lg-1">昵称</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="nickName" value="${param.nickName}" class="form-control">
							                	</div>
							                	
							                	<label class="control-label col-lg-1">邮箱</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="email" value="${param.email}" class="form-control">
							                	</div>
							                </div>
							                
							                <div class="form-group">
							                	<label class="control-label col-lg-offset-2 col-lg-1">验证</label>
							                	<div class="col-lg-2">
							                		<select name="valid" class="form-control" data-initValue="${param.valid}">
						                				<option value="">全部</option>
					                                	<option value="true">是</option>
					                                	<option value="false">否</option>
					                                </select>
							                	</div>
							                	
							                	<label class="control-label col-lg-1">角色</label>
							                	<div class="col-lg-2">
							                		<select name="roleId" class="form-control" data-initValue="${param.roleId}">
							                			<option value="">全部</option>
					                                	<!-- <option value="admin">管理员</option>
					                                	<option value="vip">vip用户</option>
					                                	<option value="normal">普通用户</option> -->
					                                	<c:if test="${not empty sysRoleList}">
															<c:forEach items="${sysRoleList}" var="roleInfo" varStatus="status">
																<option value="${roleInfo.code}">${roleInfo.name}</option>						
															</c:forEach>
														</c:if>
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
			                    	<a href="#addFormModal" onclick="initBeforeAddOper();" data-toggle="modal" class="text-muted">
			                    		<i class="fa fa-plus"></i> 新增
			                    	</a>
			                    	
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>用户列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<c:choose>
		                        				<c:when test="${Cfg.PROD_FLAG}">
		                        					<th class="table-index">#</th>
				                                	<th class="col-lg-2">昵称</th>
				                                    <th class="col-lg-2">邮箱</th>
				                                    <th class="col-lg-1">角色</th>
				                                    <th class="col-lg-1">验证</th>
				                                    <th class="col-lg-1">锁定</th>
				                                    <th class="col-lg-2">注册时间</th>
				                                    <th class="col-lg-3">操作</th>
		                        				</c:when>
		                        				
		                        				<c:otherwise>
		                        					<th class="table-index">#</th>
				                                	<th class="col-lg-1">昵称</th>
				                                    <th class="col-lg-2">邮箱</th>
				                                    <th class="col-lg-1">角色</th>
				                                    <th class="col-lg-1">验证</th>
				                                    <th class="col-lg-1">锁定</th>
				                                    <th class="col-lg-1">消费金额</th>
				                                    <th class="col-lg-1">发票金额</th>
				                                    <th class="col-lg-2">注册时间</th>
				                                    <th class="col-lg-2">操作</th>
		                        				</c:otherwise>
		                        			</c:choose>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="userInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${userInfo.nickName}</td>
		                                         	<td>${userInfo.email}</td>
		                                         	<td>${userInfo.roleName}</td>
		                                         	<td>${userInfo.valid ? '是' : '否'}</td>
		                                         	<td>${userInfo.locked ? '是' : '否'}</td>
		                                         	
		                                         	<c:if test="${!Cfg.PROD_FLAG}">
		                                         		<td>
			                                         		<fmt:formatNumber value="${userInfo.feeAmount/100}" type="currency" pattern="￥0"/>
			                                         	</td>
			                                         	<td>
			                                         		<fmt:formatNumber value="${userInfo.invoiceAmount/100}" type="currency" pattern="￥0"/>
			                                         	</td>
		                                         	</c:if>
		                                         	
		                                         	<td>
		                                         		<fmt:formatDate value="${userInfo.registDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
	                                         			<button type="button" onclick="initUpdateOper(${userInfo.userId});" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#userFormModal">
	                                                   		<i class="fa fa-pencil"></i> 编辑
	                                                   	</button>
	                                                   	
	                                                   	<button type="button" onclick="initResetOper(${userInfo.userId});" class="btn btn-sm btn-success" data-toggle="modal" data-target="#resetFormModal">
	                                                   		<i class="fa fa-cog"></i> 重置密码
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
						<div class="modal fade" id="userFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">编辑用户信息</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="userForm" role="form" class="form-horizontal">
													<input id="userId" type="hidden" name="userId">
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
									                		<select name="roleId" class="form-control">
							                                	<!-- <option value="admin">管理员</option>
							                                	<option value="vip">VIP用户</option>
							                                	<option value="normal">普通用户</option> -->
							                                	<c:if test="${not empty sysRoleList}">
																	<c:forEach items="${sysRoleList}" var="roleInfo" varStatus="status">
																		<option value="${roleInfo.code}">${roleInfo.name}</option>						
																	</c:forEach>
																</c:if>
							                                </select>
									                	</div>
               										</div>
               										
               										<div class="form-group">
                										<label class="control-label col-lg-3">是否验证</label>
									                	<div class="col-lg-6">
									                		<select name="valid" class="form-control">
							                                	<option value="true">是</option>
							                                	<option value="false">否</option>
							                                </select>
									                	</div>
								                	</div>
								                	
								                	<div class="form-group">
									                	<label class="control-label col-lg-3">是否锁定</label>
									                	<div class="col-lg-6">
									                		<select name="locked" class="form-control">
							                                	<option value="true">是</option>
							                                	<option value="false">否</option>
							                                </select>
									                	</div>
								                	</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="saveUserBtn" type="button" class="btn btn-success">保存</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MODAL SECTION -->
				
				<!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="resetFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">重置密码</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="resetForm" role="form" class="form-horizontal">
													<input id="resetUserId" type="hidden" name="userId">
								                	
								                	<div class="form-group">
									                	<label class="control-label col-lg-3">新密码</label>
									                	<div class="col-lg-6">
									                		<input name="passwd" class="form-control"/>
									                	</div>
								                	</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="resetBtn" type="button" class="btn btn-success">保存</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MODAL SECTION -->
				
				<!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="addFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">新增用户</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="addForm" role="form" class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-lg-3">昵称</label> 
														<div class="col-lg-6">
															<input name="nickName" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">邮箱</label> 
														<div class="col-lg-6">
															<input name="email" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">角色</label>
									                	<div class="col-lg-6">
									                		<select name="roleId" class="form-control">
							                                	<!-- <option value="admin">管理员</option>
							                                	<option value="vip">VIP用户</option>
							                                	<option value="normal">普通用户</option> -->
							                                	<c:if test="${not empty sysRoleList}">
																	<c:forEach items="${sysRoleList}" var="roleInfo" varStatus="status">
																		<option value="${roleInfo.code}">${roleInfo.name}</option>						
																	</c:forEach>
																</c:if>
							                                </select>
									                	</div>
               										</div>
               										
               										<div class="form-group">
                										<label class="control-label col-lg-3">是否验证</label>
									                	<div class="col-lg-6">
									                		<select name="valid" class="form-control">
							                                	<option value="true">是</option>
							                                	<option value="false">否</option>
							                                </select>
									                	</div>
								                	</div>
								                	
								                	<div class="form-group">
									                	<label class="control-label col-lg-3">是否锁定</label>
									                	<div class="col-lg-6">
									                		<select name="locked" class="form-control">
							                                	<option value="false">否</option>
							                                	<option value="true">是</option>
							                                </select>
									                	</div>
								                	</div>
								                	
								                	<div class="form-group">
									                	<label class="control-label col-lg-3">登录密码</label>
									                	<div class="col-lg-6">
									                		<input name="passwd" class="form-control"/>
									                	</div>
								                	</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="addUserBtn" type="button" class="btn btn-success">保存</button>
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
    <script type="text/javascript" src="js/admin/userList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
