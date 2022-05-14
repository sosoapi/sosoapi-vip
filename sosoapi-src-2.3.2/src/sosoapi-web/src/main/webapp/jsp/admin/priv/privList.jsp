<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>权限管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
        	    						<form class="form-horizontal" action="admin/priv/list.htm" method="get">
							                <div class="form-group">
							                	<label class="control-label col-lg-offset-1 col-lg-2">名称</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="name" value="${param.name}" class="form-control">
							                	</div>
							                	
							                	<label class="control-label col-lg-2">菜单url</label>
							                	<div class="col-lg-2">
							                		<input type="text" name="url" value="${param.url}" class="form-control">
							                	</div>
							                </div>
							                
							                <div class="form-group">
							                	<label class="control-label col-lg-offset-1 col-lg-2">类型</label>
							                	<div class="col-lg-2">
							                		<select name="type" class="form-control" data-initValue="${param.type}">
							                			<option value="">全部</option>
					                                	<option value="cate">菜单组</option>
					                                	<option value="menu">菜单</option>
					                                	<option value="page">页面</option>
					                                	<option value="oper">操作</option>
					                                </select>
							                	</div>
							                	
							                	<label class="control-label col-lg-2">父菜单</label>
							                	<div class="col-lg-2">
							                		<select name="parentId" class="form-control" data-initValue="${param.parentId}">
							                			<option value="">全部</option>
							                			<c:if test="${not empty sysPrivParentList}">
															<c:forEach items="${sysPrivParentList}" var="parentInfo" varStatus="status">
																<option value="${parentInfo.code}">${parentInfo.name}</option>						
															</c:forEach>
														</c:if>
					                                </select>
							                	</div>
							                </div>
							                
							                <div class="form-group">
							                	<label class="control-label col-lg-offset-1 col-lg-2">访问授权</label>
							                	<div class="col-lg-2">
							                		<select name="accessVerify" class="form-control" data-initValue="${param.status}">
							                			<option value="">全部</option>
					                                	<option value="true">是</option>
					                                	<option value="false">否</option>
					                                </select>
							                	</div>
									            
									            <label class="control-label col-lg-2">状态</label>
							                	<div class="col-lg-2">
							                		<select name="status" class="form-control" data-initValue="${param.status}">
							                			<option value="">全部</option>
					                                	<option value="on">开启</option>
					                                	<option value="off">关闭</option>
					                                </select>
							                	</div>
							                	
							                	<div class="col-lg-2">
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
			                    	<a href="#privFormModal" onclick="initBeforeAddOper();" data-toggle="modal" class="text-muted">
			                    		<i class="fa fa-plus"></i> 新增
			                    	</a>
			                    	
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>权限列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                	<th class="col-lg-1">名称</th>
		                                    <th class="col-lg-2">菜单url</th>
		                                    <th class="col-lg-1">类型</th>
		                                    <th class="col-lg-1">父菜单</th>
		                                    <th class="col-lg-2">权限编码</th>
		                                    <th class="col-lg-1">访问授权</th>
		                                    <th class="col-lg-1">排序权重</th>
		                                    <th class="col-lg-1">状态</th>
		                                    <!-- <th class="col-lg-2">创建时间</th> -->
		                                    <th class="col-lg-2">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="privInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${privInfo.name}</td>
		                                         	<td>${privInfo.url}</td>
		                                         	<td>${privInfo.type.displayName}</td>
		                                         	<td>${privInfo.parentName}</td>
		                                         	<td>${privInfo.permission}</td>
		                                         	<td>${privInfo.accessVerify ? "是" : "否"}</td>
		                                         	<td>${privInfo.sortWeight}</td>
		                                         	<td>${privInfo.status.displayName}</td>
		                                         	<%-- <td>
		                                         		<fmt:formatDate value="${privInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td> --%>
		                                         	<td class="actions">
	                                         			<button type="button" onclick="initUpdateOper(${privInfo.id});" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#privFormModal">
	                                                   		<i class="fa fa-pencil"></i> 编辑
	                                                   	</button>
	                                                   	
	                                                   	<button type="button" onclick="initDelOper(${privInfo.id});" class="btn btn-sm btn-danger">
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
						<div class="modal fade" id="privFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">权限信息</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="privForm" role="form" class="form-horizontal">
													<input id="privId" type="hidden" name="privId">
													<input id="operType" type="hidden" name="operType">
													<div class="form-group">
														<label class="control-label col-lg-3">名称</label> 
														<div class="col-lg-6">
															<input name="name" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">菜单url</label> 
														<div class="col-lg-6">
															<input name="url" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">图标样式</label> 
														<div class="col-lg-6">
															<input name="iconClass" class="form-control"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">适用范围</label> 
														<div class="col-lg-6">
															<select name="scope" class="form-control" data-initValue="${param.scope}">
							                                	<option value="user">用户</option>
							                                	<option value="proj">项目</option>
							                                	<option value="common">公共</option>
							                                </select>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">类型</label> 
														<div class="col-lg-6">
															<select name="type" class="form-control" data-initValue="${param.type}">
							                                	<option value="cate">菜单组</option>
							                                	<option value="menu" selected="selected">菜单</option>
							                                	<option value="page">页面</option>
							                                	<option value="oper">操作</option>
							                                </select>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">父菜单</label> 
														<div class="col-lg-6">
															<select name="parentId" class="form-control" data-initValue="${param.parentId}">
							                                	<option value="0">无</option>
							                                	<c:if test="${not empty sysPrivParentList}">
																	<c:forEach items="${sysPrivParentList}" var="parentInfo" varStatus="status">
																		<option value="${parentInfo.code}">${parentInfo.name}</option>						
																	</c:forEach>
																</c:if>
							                                </select>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">权限编码</label> 
														<div class="col-lg-6">
															<input name="permission" class="form-control"/>
															<a href="javascript:void(0);" data-toggle="popover" data-container="body" 
																data-trigger="hover" data-placement="top" data-html="true" 
																data-content="【user:create：表示对用户资源进行create操作】</br>【user:create:01：表示对用户资源的01实例进行create操作】" 
																class="form-control-tip text-muted">
							                                	<i class="fa fa-question-circle"></i>
							                                </a>
														</div>
													</div>
        
													<div class="form-group">
														<label class="control-label col-lg-3">排序权重</label> 
														<div class="col-lg-6">
															<input name="sortWeight" class="form-control" value="0"/>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">访问授权</label>
									                	<div class="col-lg-6">
									                		<select name="accessVerify" class="form-control">
							                                	<option value="false">否</option>
							                                	<option value="true">是</option>
							                                </select>
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
										<button id="savePrivBtn" type="button" class="btn btn-success">保存</button>
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
    <script type="text/javascript" src="js/admin/priv/privList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
