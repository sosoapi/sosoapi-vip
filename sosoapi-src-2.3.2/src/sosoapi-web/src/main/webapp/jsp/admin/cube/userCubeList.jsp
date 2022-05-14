<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>用户统计  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
			                    <span>今日统计</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
        	    					<div class="col-lg-12">
        	    						<div class="form-horizontal">
        	    							<div class="form-group">
	        	    							<label class="col-lg-2 text-right">统计时间：</label>
							                	<div class="col-lg-4">
							                		<fmt:formatDate value="${currentCube.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">在线用户：</label>
							                	<div class="col-lg-4">
							                		${currentCube.onlineCount}
							                	</div>
	        	    						</div>
	        	    						
	        	    						<div class="form-group">
							                	<label class="col-lg-2 text-right">总用户：</label>
							                	<div class="col-lg-4">
							                		${currentCube.totalRegistCount}
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">新增用户：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayRegistCount}
							                	</div>
				                			</div>
				                			
				                			<div class="form-group">
							                	<label class="col-lg-2 text-right">总vip：</label>
							                	<div class="col-lg-4">
							                		${currentCube.totalVipCount}
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">新增vip：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayVipCount}
							                	</div>
				                			</div>
				                			
				                			<div class="form-group">
							                	<label class="col-lg-2 text-right">登录用户：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayLoginCount}
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">登陆老用户：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayOldLoginCount}
							                	</div>
				                			</div>
				                			
				                			<div class="form-group">
							                	<label class="col-lg-2 text-right">总项目：</label>
							                	<div class="col-lg-4">
							                		${currentCube.totalProjCount}
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">新增项目：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayProjCount}
							                	</div>
				                			</div>
        	    						</div>
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
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>历史统计</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                            	<thead>
	                                	<tr>
	                                    	<th class="table-index">#</th>
	                                        <th class="col-lg-1">总用户</th>
	                                        <th class="col-lg-1">新增用户</th>
	                                        <th class="col-lg-1">总vip</th>
	                                        <th class="col-lg-1">新增vip</th>
	                                        <th class="col-lg-1">登录用户</th>
	                                        <th class="col-lg-1">登陆老用户</th>
	                                        <th class="col-lg-1">总项目</th>
	                                        <th class="col-lg-1">新增项目</th>
	                                        <th class="col-lg-2">统计时间</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="cubeInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                         	<td>${cubeInfo.totalRegistCount}</td>
		                                         	<td>${cubeInfo.dayRegistCount}</td>
		                                         	<td>${cubeInfo.totalVipCount}</td>
		                                         	<td>${cubeInfo.dayVipCount}</td>
		                                         	<td>${cubeInfo.dayLoginCount}</td>
		                                         	<td>${cubeInfo.dayOldLoginCount}</td>
		                                         	<td>${cubeInfo.totalProjCount}</td>
		                                         	<td>${cubeInfo.dayProjCount}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${cubeInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
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
