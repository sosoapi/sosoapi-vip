<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>版本管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
	    <jsp:include page="/jsp/common/top.jsp"/>
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
		                        <form class="form-horizontal" action="admin/goods/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-offset-1 col-lg-2">名称</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="name" value="${param.name}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-2">类型</label>
					                	<div class="col-lg-2">
					                		<select name="type" class="form-control" data-initValue="${param.type}">
				                				<option value="">全部</option>
			                                	<option value="software">软件</option>
			                                	<option value="src">源码</option>
			                                	<option value="war">war包</option>
			                                </select>
					                	</div>
					                </div>
					                
					                <div class="form-group">
					                	<label class="control-label col-lg-offset-1 col-lg-2">启用状态</label>
					                	<div class="col-lg-2">
					                		<select name="status" class="form-control" data-initValue="${param.status}">
				                				<option value="">全部</option>
			                                	<option value="on">开启</option>
			                                	<option value="off">关闭</option>
			                                </select>
					                	</div>
					                	
					                	<label class="control-label col-lg-2">发布状态</label>
					                	<div class="col-lg-2">
					                		<select name="pubStatus" class="form-control" data-initValue="${param.pubStatus}">
				                				<option value="">全部</option>
			                                	<option value="on">开启</option>
			                                	<option value="off">关闭</option>
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
                					<shiro:hasPermission name="goodsMgr:forwardAdd">
                						<a href="admin/goods/forwardAdd.htm" class="text-muted">
	                                   		<i class="fa fa-plus"></i> 发布版本
	                                	</a>
                					</shiro:hasPermission>
                                	
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    
			                   	<span>版本列表</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
        	    					<div class="col-lg-12">
										<table class="table table-hover table-bordered table-fixed">
			                        		<thead>
				                            	<tr>
				                                	<th class="table-index">#</th>
				                                	<th class="col-lg-1">名称</th>
				                                    <th class="col-lg-1">版本号</th>
				                                    <th class="col-lg-1">类型</th>
				                                	<th class="col-lg-1">价格</th>
				                                	<th class="col-lg-1">会员价</th>
				                                    <th class="col-lg-1">版本说明</th>
				                                    <th class="col-lg-1">启用状态</th>
				                                    <th class="col-lg-1">发布状态</th>
				                                    <th class="col-lg-2">发布时间</th>
				                                    <th class="col-lg-2">操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="goodsInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${goodsInfo.name}</td>
				                                        	<td>${goodsInfo.description}</td>
				                                        	<td>${goodsInfo.type.displayName}</td>
				                                        	<td>
				                                        		<fmt:formatNumber value="${goodsInfo.price/100}" type="currency" pattern="￥0"/>
				                                        	</td>
				                                        	<td>
				                                        		<fmt:formatNumber value="${goodsInfo.discount/100}" type="currency" pattern="￥0"/>
				                                        	</td>
				                                        	<td>
				                                        		<div class="hidden">${goodsInfo.detailInfo}</div>
				                                         		<a class="goods-detail-btn" data-toggle="modal" href="#goodsDetailModal">
                                									查看详情
                            									</a>
				                                        	</td>
				                                        	<td>${goodsInfo.status.displayName}</td>
				                                        	<td>${goodsInfo.pubStatus.displayName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${goodsInfo.pubDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="actions">
				                                         		<shiro:hasPermission name="goodsMgr:forwardUpdate">
				                                                   	<a class="btn btn-sm btn-primary" href="admin/goods/forwardUpdate.htm?goodsId=${goodsInfo.id}">
				                                                   		<i class="fa fa-pencil"></i> 编辑
				                                                   	</a>
			                                                   	</shiro:hasPermission>
			                                                   	
			                                                	<button onclick="delOper(${goodsInfo.id});" type="button" class="btn btn-sm btn-danger">
			                                                   		<i class="fa fa-trash"></i> 删除
			                                                   	</button>
			                                                   	
			                                                   	<button onclick="copyGoods(${goodsInfo.id});" type="button" class="btn btn-sm btn-success">
			                                                   		<i class="fa fa-plus"></i> 复制
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
						<div class="modal fade" id="goodsDetailModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
			                <div class="modal-dialog">
			                    <div class="modal-content">
			                        <div class="modal-header">
			                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                            <h4 class="modal-title">版本说明</h4>
			                        </div>
			                        <div id="goodsDetailModalBody" class="modal-body">
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
        
        <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
    </div>
    
    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/admin/goodsList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
