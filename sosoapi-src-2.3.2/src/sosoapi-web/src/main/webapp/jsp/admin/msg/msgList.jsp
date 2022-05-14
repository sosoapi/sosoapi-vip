<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>消息管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
		                		<!-- SEARCH SECTION -->
		                        <form class="form-horizontal" action="admin/msg/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-1">标题</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="title" value="${param.title}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">类型</label>
					                	<div class="col-lg-2">
					                		<select name="msgType" class="form-control" data-initValue="${param.msgType}">
				                				<option value="">全部</option>
			                                	<option value="notice">公告</option>
			                                	<option value="version">版本升级</option>
			                                	<option value="other">其他</option>
			                                </select>
					                	</div>
					                	
					                	<label class="control-label col-lg-1">对象</label>
					                	<div class="col-lg-2">
					                		<select name="userRole" class="form-control" data-initValue="${param.userRole}">
			                                	<option value="">全部</option>
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
		                        <!-- END SEARCH SECTION -->
		                	</div>
		                </div>
				   	</div>
				</div>
				
				<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                    	<shiro:hasPermission name="msgMgr:forwardAdd">
				                        <a href="admin/msg/forwardAdd.htm" class="text-muted">
	                                   		<i class="fa fa-plus"></i> 发布新消息
	                                	</a>
                                	</shiro:hasPermission>
                                	
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                   	<span>消息列表</span>
			                </div>
		                	<div class="panel-body">
		                		<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
										<table class="table table-hover table-bordered table-fixed">
			                        		<thead>
				                            	<tr>
				                                	<th class="table-index">#</th>
				                                	<th class="col-lg-5">标题</th>
				                                    <th class="col-lg-1">内容</th>
				                                    <th class="col-lg-1">消息类型</th>
				                                    <th class="col-lg-1">对象类型</th>
				                                    <th class="col-lg-2">发布时间</th>
				                                    <th class="col-lg-2">操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="msgInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${msgInfo.title}</td>
				                                         	<td>
				                                         		<div class="hidden">${msgInfo.content}</div>
				                                         		<a class="msg-content-btn" data-toggle="modal" href="#msgContentModal">
                                									查看详情
                            									</a>
				                                         	</td>
				                                         	<td>${msgInfo.msgType.displayName}</td>
				                                         	<td>${msgInfo.roleName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${msgInfo.pubDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="actions">
				                                         		<shiro:hasPermission name="msgMgr:forwardUpdate">
					                                         		<a type="button" href="admin/msg/forwardUpdate.htm?msgId=${msgInfo.id}" class="btn btn-sm btn-primary">
				                                                   		<i class="fa fa-pencil"></i> 编辑
				                                                   	</a>
			                                                   	</shiro:hasPermission>
			                                                   	
			                                                   	<shiro:hasPermission name="msgMgr:del">
				                                                   	<a onclick="delOper(${msgInfo.id});" type="button" class="btn btn-sm btn-danger">
				                                                   		<i class="fa fa-trash"></i> 删除
				                                                   	</a>
			                                                   	</shiro:hasPermission>
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
				
				<!-- MODAL SECTION -->
				<div class="row">
        			<div class="col-lg-12">
						<div class="modal fade" id="msgContentModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
			                <div class="modal-dialog">
			                    <div class="modal-content">
			                        <div class="modal-header">
			                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                            <h4 class="modal-title">消息详情</h4>
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
    <script type="text/javascript" src="js/admin/msgList.js?version=1.0.0"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
