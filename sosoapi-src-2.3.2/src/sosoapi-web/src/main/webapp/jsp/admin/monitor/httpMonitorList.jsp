<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>http监控管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/datetimepicker/css/jquery.datetimepicker.min.css" rel="stylesheet" />
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
		                		<form class="form-horizontal" action="admin/monitor/http/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-2">名称</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="name" value="${param.name}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-2">监控url</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="sub" value="${param.sub}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">邮箱</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="email" value="${param.email}" class="form-control">
					                	</div>
					                </div>
					                
					                <div class="form-group">
					                	<label class="control-label col-lg-2">监控状态</label>
					                	<div class="col-lg-2">
					                		<select name="subStatus" class="form-control" data-initValue="${param.subStatus}">
				                				<option value="">全部</option>
				                				<option value="none">待测</option>
			                                	<option value="error">异常</option>
			                                	<option value="normal">正常</option>
			                                </select>
					                	</div>
					                	
					                	<label class="control-label col-lg-2">启用状态</label>
					                	<div class="col-lg-2">
					                		<select name="status" class="form-control" data-initValue="${param.status}">
				                				<option value="">全部</option>
			                                	<option value="on">启用</option>
			                                	<option value="off">关闭</option>
			                                </select>
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
					                	
					                	<div class="col-lg-offset-1 col-lg-2">
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
			                   	<span>http监控列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-2">名称</th>
		                                    <th class="col-lg-2">监控url</th>
		                                    <th class="col-lg-1">监控频率</th>
		                                    <th class="col-lg-1">监控状态</th>
		                                    <th class="col-lg-1">状态</th>
		                                    <th class="col-lg-2">用户邮箱</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-1">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="monitorInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${monitorInfo.name}</td>
		                                         	<td>${monitorInfo.sub}</td>
		                                         	<td>${monitorInfo.rate}</td>
		                                         	<td>${monitorInfo.subStatus.displayName}</td>
		                                         	<td>${monitorInfo.status.displayName}</td>
		                                         	<td>${monitorInfo.userEmail}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${monitorInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
														<c:choose>
															<c:when test="${monitorInfo.status eq 'on'}">
																<button type="button" onclick="updateStatus(${monitorInfo.id},'off');" class="btn btn-sm btn-danger">
			                                                   		<i class="fa fa-frown-o"></i> 关闭
			                                                   	</button>
															</c:when>
															<c:otherwise>
																<button type="button" onclick="updateStatus(${monitorInfo.id},'on');" class="btn btn-sm btn-success">
			                                                   		<i class="fa fa-smile-o"></i> 开启
			                                                   	</button>
															</c:otherwise>
														</c:choose>
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
        
        <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
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
	    
	    function updateStatus(monitorId,status){
	    	var param = new Object();
	    	param.monitorId = monitorId;
	    	param.status = status;
			doPost("admin/monitor/http/json/updateStatus.htm",param,function(data){
				notice("状态更新成功",function(){
					location.reload();
				});
			});
	    }
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
