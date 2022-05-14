<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>警报接收组管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
		                		<form class="form-horizontal" action="auth/monitor/alarm/group/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-offset-2 col-lg-1">名称</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="name" value="${param.name}" class="form-control">
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
			                    	<shiro:hasPermission name="monitorAlarmGroup:forwardAdd">
				                    	<a href="auth/monitor/alarm/group/forwardAdd.htm" class="text-muted">
				                    		<i class="fa fa-plus"></i> 新增
				                    	</a>
			                    	</shiro:hasPermission>
			                    </div>
			                   	<span>警报接收组列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-3">名称</th>
		                                    <th class="col-lg-1">邮箱报警</th>
		                                    <th class="col-lg-1">短信报警</th>
		                                    <th class="col-lg-1">邮箱时段</th>
		                                    <th class="col-lg-1">短信时段</th>
		                                    <th class="col-lg-1">状态</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-2">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="groupInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${groupInfo.name}</td>
		                                         	<td>${groupInfo.emailAlarm ? '是' : '否'}</td>
		                                         	<td>${groupInfo.phoneAlarm ? '是' : '否'}</td>
		                                         	<td>全天</td>
		                                         	<td>${groupInfo.startTime} - ${groupInfo.endTime}</td>
		                                         	<td>${groupInfo.status.displayName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${groupInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
		                                         		<shiro:hasPermission name="monitorAlarmGroup:forwardUpdate">
		                                         			<a type="button" href="auth/monitor/alarm/group/forwardUpdate.htm?groupId=${groupInfo.id}" class="btn btn-sm btn-primary">
		                                                 		<i class="fa fa-pencil"></i> 编辑
		                                                   	</a>
		                                         		</shiro:hasPermission>
	                                                   	
	                                                	<button onclick="delGroup(${groupInfo.id});" type="button" class="btn btn-sm btn-danger">
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
        
        <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
    </div>
    
    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript">
    	//初始化删除操作
    	function delGroup(groupId){
    		bootbox.confirm("确定执行删除操作？",function(){
    			var param = new Object();
    			param.groupId = groupId;
    			doPost("auth/monitor/alarm/group/json/del.htm",param,function(data){
    				notice("删除成功",function(){
    					location.reload();
    				});
    			});
    		});
    	}
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
