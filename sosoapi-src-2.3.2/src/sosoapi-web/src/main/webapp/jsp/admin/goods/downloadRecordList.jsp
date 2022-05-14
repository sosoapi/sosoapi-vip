<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>下载记录  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/datetimepicker/css/jquery.datetimepicker.min.css" rel="stylesheet" />
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
		                        <form class="form-horizontal" action="admin/goods/listDownloadRecord.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-2">文件名</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="fileName" value="${param.fileName}" class="form-control">
					                	</div>
					  
					  					<label class="control-label col-lg-2">邮箱</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="email" value="${param.email}" class="form-control">
					                	</div>              	
					                </div>
					                
					                <div class="form-group">
					                	<label class="control-label col-lg-2">开始时间</label>
					                	<div class="col-lg-2">
					                		<input type="text" id="dateStart" name="dateStart" value="${param.dateStart}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-2">结束时间</label>
					                	<div class="col-lg-2">
					                		<input type="text" id="dateEnd" name="dateEnd" value="${param.dateEnd}" class="form-control">
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
			                   	<span>记录列表</span>
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
				                                    <th class="col-lg-1">版本号</th>
				                                	<th class="col-lg-2">文件名</th>
				                                    <th class="col-lg-2">下载ip</th>
				                                    <th class="col-lg-2">下载时间</th>
				                                    <th class="col-lg-3">用户邮箱</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="recordInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${recordInfo.goodsName}</td>
				                                        	<td>${recordInfo.goodsDesc}</td>
				                                        	<td>${recordInfo.fileName}</td>
				                                        	<td>${recordInfo.downloadIp}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${recordInfo.downloadDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td>${recordInfo.userEmail}</td>
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
    <script type="text/javascript" src="plugin/datetimepicker/js/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$.datetimepicker.setLocale('zh');
    		$('#dateStart,#dateEnd').datetimepicker({
    			format:'Y-m-d',
    			timepicker:false
    		});
    	});
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
