<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>邀请成员  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
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
        	<div class="small-header transition animated fadeIn">
		        <div class="hpanel">
		            <div class="panel-body">
		                <div id="hbreadcrumb" class="pull-left">
		                    <ol class="hbreadcrumb breadcrumb">
		                        <li>
                                	<a href="auth/proj/mem/list.htm?projId=${param.projId}&docId=${param.docId}">项目成员</a>
                                </li>
                                <li class="active">邀请成员</li>
		                    </ol>
		                </div>
		            </div>
		        </div>
		    </div>
		    
			<div class="content animate-panel">
				<!-- TOOLBAR SECTION -->
             	<%-- <jsp:include page="/jsp/apidoc/toolBar.jsp">
					<jsp:param name="projId" value="${param.projId}" />
					<jsp:param name="docId" value="${param.docId}" />
				</jsp:include> --%>
				<!-- TOOLBAR SECTION -->
				
            	<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
            				<input id="projId" type="hidden" value="${param.projId}">
	                		<input id="docId" type="hidden" value="${param.docId}">
	                		
	                		<ul class="nav nav-tabs">
	                			<li class="active">
	                            	<a id="directInviteTab" href="#directInvite" data-toggle="tab">直接加入</a>
	                            </li>
	                            
	                    		<li class="">
	                    			<a id="emailInviteTab" href="#emailInvite" data-toggle="tab">邮件邀请</a>
	                           	</li>
	                     	</ul>
	                            
	                      	<div class="tab-content">
	                           	<div class="tab-pane active" id="directInvite">
	                           		<div class="panel-body">
	                           			<form action="auth/proj/mem/searchForAdd.htm" class="form-horizontal">
	                           				<input name="projId" type="hidden" value="${param.projId}" class="form-control" />
	                           				<input name="docId" type="hidden" value="${param.docId}" class="form-control" />
											<div class="form-group">
												<label class="control-label col-lg-3">已注册用户邮箱</label> 
												<div class="col-lg-6">
													<input name="email" class="form-control" value="${param.email}" placeholder="完整邮箱格式，不支持模糊查"/>
												</div>
												<div class="col-lg-3">
													<button type="submit" class="btn btn-success">查询</button>
												</div>
											</div>
										</form>

	                           			<table class="table table-hover table-bordered table-fixed">
					          				<thead>
								               	<tr>
								                	<th class="table-index">#</th>
								                	<th class="col-lg-2">昵称</th>
								                    <th class="col-lg-4">邮箱</th>
								                    <th class="col-lg-2">预设角色</th>
								                    <th class="col-lg-2">项目昵称</th>
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
				                                         	<td>
								                                <select class="form-control" name="projRoleId">
				                                         			<%-- <c:forEach items="${appProjRoleList}" var="selectInfo">
									                					<option value="${selectInfo.code}">${selectInfo.name}</option>
									                				</c:forEach> --%>
									                				<c:forEach items="${roleList}" var="selectInfo">
									                					<option value="${selectInfo.code}">${selectInfo.name}</option>
									                				</c:forEach>
								                                </select>
				                                         	</td>
				                                         	<td>
				                                         		<input type="text" class="form-control" name="projNickName" value="${memInfo.nickName}"/>
				                                         	</td>
				                                         	<td class="actions">
				                                         		<sosoapi:hasPermission name="projMem:add" projId="${param.projId}">
					                                         		<button onclick="addMember(${memInfo.userId});" type="button" class="btn btn-sm btn-success">
				                                                   		<i class="fa fa-plus"></i> <span>添加</span>
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
	                           	
	                           	<div class="tab-pane" id="emailInvite">
	                           		<div class="panel-body">
	                           			<form id="inviteForm" role="form" class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-lg-3">未注册用户邮箱</label> 
												<div class="col-lg-6">
													<input id="invitedEmail" name="invitedEmail" class="form-control" />
												</div>
												<div class="col-lg-3">
													<button id="inviteBtn" type="button" class="btn btn-success">邀请</button>
												</div>
											</div>
										</form>
	                           		</div>
	                           	</div>
	                    	</div>
            			</div>
            		</div>
            	</div>
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
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
    <script type="text/javascript" src="js/project/invite.js"></script>
    <script type="text/javascript" src="js/apidoc/toolBar.js"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
