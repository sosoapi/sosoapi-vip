<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>警报接收者管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                   	<span>查询</span>
			                </div>
		                	<div class="panel-body">
		                		<form class="form-horizontal" action="auth/monitor/alarm/receiver/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-offset-1 col-lg-1">邮箱</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="email" value="${param.email}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">手机号</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="phone" value="${param.phone}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">状态</label>
					                	<div class="col-lg-2">
					                		<select name="status" class="form-control" data-initValue="${param.status}">
				                				<option value="">全部</option>
			                                	<option value="on">启用</option>
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
				
        		<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                    	<a href="#receiverFormModal" onclick="initBeforeAddOper();" data-toggle="modal" class="text-muted">
			                    		<i class="fa fa-plus"></i> 新增
			                    	</a>
			                    </div>
			                   	<span>警报接收者列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-2">姓名</th>
		                                    <th class="col-lg-3">邮箱</th>
		                                    <th class="col-lg-2">手机号</th>
		                                    <!-- <th class="col-lg-1">手机验证</th>
		                                    <th class="col-lg-1">邮箱验证</th> -->
		                                    <th class="col-lg-1">状态</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-2">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="receiverInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${receiverInfo.name}</td>
		                                        	<td>${receiverInfo.email}</td>
		                                         	<td>${receiverInfo.phone}</td>
		                                         	<%-- <td>${receiverInfo.phoneValid ? '是' : '否'}</td>
		                                         	<td>${receiverInfo.emailValid ? '是' : '否'}</td> --%>
		                                         	<td>${receiverInfo.status.displayName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${receiverInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
	                                                   	<button type="button" onclick="initUpdateOper(${receiverInfo.id});" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#receiverFormModal">
	                                                   		<i class="fa fa-pencil"></i> 编辑
	                                                   	</button>
	                                                   	
	                                                	<button onclick="delReceiver(${receiverInfo.id});" type="button" class="btn btn-sm btn-danger">
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
						<div class="modal fade" id="receiverFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h6 class="modal-title">警报接收者信息</h6>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="receiverForm" role="form" class="form-horizontal">
													<input id="operType" type="hidden" name="operType"/>
													<input id="receiverId" type="hidden" name="receiverId"/>
													<div class="form-group">
														<label class="control-label col-lg-3">名称</label> 
														<div class="col-lg-6">
															<input name="name" class="form-control" />
														</div>
													</div>
													
													<div class="form-group">
								                		<label class="control-label col-lg-3">邮箱</label>
								                		<div class="col-lg-6">
															<input name="email" class="form-control"/>
														</div>
								                	</div>
								                	
													<div class="form-group">
														<label class="control-label col-lg-3">手机号</label> 
														<div class="col-lg-6">
															<input name="phone" class="form-control" placeholder="选填"/>
														</div>
													</div>
													
													<!-- <div id="authCode" class="form-group" style="dispaly:none;">
														<label class="control-label col-lg-3">验证码</label> 
														<div class="col-lg-3">
															<input name="validCode" class="form-control"/>
														</div>
														<div class="col-lg-3">
															<button id="smsCodeBtn" type="button" class="btn btn-primary">获取验证码</button>
														</div>
													</div> -->
								                	
								                	<div class="form-group">
								                		<label class="control-label col-lg-3">状态</label>
								                		<div class="col-lg-6">
															<select name="status" class="form-control">
								                				<option value="on">开启</option>
								                				<option value="off">关闭</option>
							                                </select>
														</div>
								                	</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="saveReceiverBtn" type="button" class="btn btn-success">保存</button>
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
    <script type="text/javascript" src="js/monitor/alarmReceiverList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
