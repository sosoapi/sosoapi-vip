<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>项目管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
		                        <form class="form-horizontal" action="admin/proj/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-2">名称</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="name" value="${param.name}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-2">邮箱</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="email" value="${param.email}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">状态</label>
					                	<div class="col-lg-2">
					                		<select name="status" class="form-control" data-initValue="${param.status}">
				                				<option value="">全部</option>
			                                	<option value="open">开启</option>
			                                	<option value="close">关闭</option>
			                                </select>
					                	</div>
					                </div>
					                
					                <div class="form-group">
					                	<label class="control-label col-lg-2">成员最小值</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="memMinCount" value="${param.memMinCount}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-2">成员最大值</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="memMaxCount" value="${param.memMaxCount}" class="form-control">
					                	</div>
					                	
					                	<div class="col-lg-offset-1 col-lg-3">
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
			                   	<span>项目列表</span>
			                </div>
		                	<div class="panel-body">
		                		<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
										<table class="table table-hover table-bordered table-fixed">
			                        		<thead>
				                            	<tr>
				                                	<th class="table-index">#</th>
				                                    <th class="col-lg-2">名称</th>
				                                    <th class="col-lg-2">英文名称</th>
				                                    <th class="col-lg-1">状态</th>
				                                    <th class="col-lg-1">成员数目</th>
				                                    <th class="col-lg-2">创建者邮箱</th>
				                                    <th class="col-lg-2">创建时间</th>
				                                    <!-- <th class="col-lg-2">操作</th> -->
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="projInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${projInfo.name}</td>
				                                        	<td>${projInfo.code}</td>
				                                         	<td>${projInfo.status.displayName}</td>
				                                         	<td>${projInfo.memCount}</td>
				                                         	<td>${projInfo.createrEmail}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${projInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<%-- <td class="actions">
		                                         				<a href="auth/doc/inter/tree/list.htm?projId=${projInfo.projId}&docId=${projInfo.docId}" type="button" class="btn btn-sm btn-info">
		                                                			<i class="fa fa-info-circle"></i> 详情
		                                               			</a>
		                                               		</td> --%>
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
    <!-- END FOOTER SECTION -->
</body>
</html>
