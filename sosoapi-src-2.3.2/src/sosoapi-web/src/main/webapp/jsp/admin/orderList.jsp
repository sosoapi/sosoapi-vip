<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>项目管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
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
		                        <form class="form-horizontal" action="admin/order/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-2">邮箱</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="email" value="${param.email}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-2">状态</label>
					                	<div class="col-lg-2">
					                		<select name="tradeStatus" class="form-control" data-initValue="${param.tradeStatus}">
				                				<option value="">全部</option>
			                                	<option value="success">支付成功</option>
			                                	<option value="notpay">未支付</option>
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
			                   	<span>订单列表</span>
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
				                                	<th class="col-lg-1">产品类型</th>
				                                    <th class="col-lg-1">金额</th>
				                                    <th class="col-lg-1">状态</th>
				                                    <th class="col-lg-2">下单时间</th>
				                                    <th class="col-lg-2">支付时间</th>
				                                    <th class="col-lg-2">用户邮箱</th>
				                                    <th class="col-lg-1">操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="orderInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${orderInfo.orderName}-${orderInfo.orderDesc}</td>
				                                        	<td>${orderInfo.prodType.displayName}</td>
				                                        	<td>
				                                        		<fmt:formatNumber value="${orderInfo.totalFee/100}" type="currency" pattern="￥0"/>
				                                        	</td>
				                                         	<td>${orderInfo.tradeStatus.displayName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${orderInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${orderInfo.payDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td>
				                                         		<a href="admin/user/list.htm?email=${orderInfo.userEmail}">${orderInfo.userEmail}</a>
				                                         	</td>
				                                         	<td class="actions">
			                                                   	<button onclick="delOper(${orderInfo.orderId});" ${orderInfo.tradeStatus != 'success' ? '' : 'disabled="disabled"' } type="button" class="btn btn-sm btn-danger">
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
    	
    	//初始化删除操作
    	function delOper(id){
    		bootbox.confirm("确定执行删除操作？",function(){
    			var param = new Object();
    			param.orderId = id;
    			doGet("admin/order/json/del.htm",param,function(data){
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
