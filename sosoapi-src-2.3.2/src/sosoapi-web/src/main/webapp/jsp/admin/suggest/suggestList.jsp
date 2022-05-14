<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>用户反馈  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
		                        <form class="form-horizontal" action="admin/suggest/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-1">邮箱</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="email" value="${param.email}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">状态</label>
					                	<div class="col-lg-2">
					                		<select name="status" class="form-control" data-initValue="${param.status}">
				                				<option value="">全部</option>
			                                	<option value="dealing">待处理</option>
			                                	<option value="dealed">已处理</option>
			                                </select>
					                	</div>
					                	
					                	<label class="control-label col-lg-1">类型</label>
					                	<div class="col-lg-2">
					                		<select name="type" class="form-control" data-initValue="${param.type}">
				                				<option value="">全部</option>
			                                	<option value="bug">Bug</option>
			                                	<option value="demand">功能需求</option>
			                                	<option value="other">其他</option>
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
			                        
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                   	<span>反馈列表</span>
			                </div>
		                	<div class="panel-body">
		                		<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
										<table class="table table-hover table-bordered table-fixed">
			                        		<thead>
				                            	<tr>
				                                	<th class="table-index">#</th>
				                                	<th class="col-lg-2">标题</th>
				                                    <th class="col-lg-1">内容</th>
				                                	<th class="col-lg-2">邮箱</th>
				                                    <th class="col-lg-1">类型</th>
				                                    <th class="col-lg-2">反馈时间</th>
				                                    <th class="col-lg-1">状态</th>
				                                    <th class="col-lg-3">操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="suggestInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${suggestInfo.title}</td>
				                                        	<td>
				                                        		<div class="hidden">${suggestInfo.content}</div>
				                                         		<a class="suggest-content-btn" data-toggle="modal" href="#suggestContentModal">
                                									查看详情
                            									</a>
				                                        	</td>
				                                        	<td>${suggestInfo.email}</td>
				                                         	<td>${suggestInfo.type.displayName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${suggestInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td>${suggestInfo.status.displayName}</td>
				                                         	<td class="actions">
				                                         		<shiro:hasPermission name="suggestMgr:forwardReply">
					                                         		<a href="admin/suggest/forwardReply.htm?suggestId=${suggestInfo.suggestId}" type="button" class="btn btn-sm btn-success">
				                                                   		<i class="fa fa-reply"></i> 回复
				                                                   	</a>
																</shiro:hasPermission>
																
				                                         		<c:if test="${suggestInfo.status == 'dealing'}">
				                                         			<button type="button" onclick="dealOper(${suggestInfo.suggestId});" class="btn btn-sm btn-primary">
				                                                   		<i class="fa fa-pencil"></i> 处理
				                                                   	</button>
				                                         		</c:if>
			                                                   	
			                                                   	<button onclick="delOper(${suggestInfo.suggestId});" type="button" class="btn btn-sm btn-danger">
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
				   	</div>
				</div>
				
				<!-- MODAL SECTION -->
				<div class="row">
        			<div class="col-lg-12">
						<div class="modal fade" id="suggestContentModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
			                <div class="modal-dialog">
			                    <div class="modal-content">
			                        <div class="modal-header">
			                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                            <h4 class="modal-title">反馈详情</h4>
			                        </div>
			                        <div id="suggestContentModalBody" class="modal-body">
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
    <script type="text/javascript" src="js/admin/suggestList.js?version=1.0.1"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
