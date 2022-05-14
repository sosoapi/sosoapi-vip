<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>发票申请管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/datetimepicker/css/jquery.datetimepicker.min.css" rel="stylesheet" />
	<link href="plugin/touchspin/css/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />
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
		                		<div class="row">
        	    					<div class="col-lg-12">
        	    						<form class="form-horizontal" action="auth/invoice/apply/list.htm" method="get">
							                <div class="form-group">
							                	<label class="control-label col-lg-2">发票类型</label>
							                	<div class="col-lg-2">
							                		<select name="type" class="form-control" data-initValue="${param.type}">
						                				<option value="">全部</option>
					                                	<option value="common">增值税普通发票</option>
					                                	<option value="eleCommon">增值税普通电子发票</option>
					                                	<!-- <option value="special">增值税专用发票</option> -->
					                                </select>
							                	</div>
							                	
							                	<label class="control-label col-lg-2">处理状态</label>
							                	<div class="col-lg-2">
							                		<select name="dealStatus" class="form-control" data-initValue="${param.dealStatus}">
						                				<option value="">全部</option>
					                                	<option value="undeal">待处理</option>
					                                	<option value="dealing">处理中</option>
					                                	<option value="dealed">已处理</option>
					                                	<option value="reject">驳回</option>
					                                </select>
							                	</div>
							                	
							                	<div class="col-lg-3">
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
		           	</div>
		      	</div>
		      	
		      	<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                    	<a href="#applyFormModal" onclick="initBeforeAddOper();" data-toggle="modal" class="text-muted">
			                    		<i class="fa fa-plus"></i> 新增
			                    	</a>
			                    	
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>申请列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-1">发票类型</th>
		                                    <th class="col-lg-1">发票金额</th>
		                                    <th class="col-lg-2">公司名称</th>
		                                    <th class="col-lg-2">申请时间</th>
		                                    <th class="col-lg-1">处理状态</th>
		                                    <th class="col-lg-2">处理时间</th>
		                                    <th class="col-lg-3">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="applyInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${applyInfo.type.displayName}</td>
		                                         	<td>
		                                         		<fmt:formatNumber value="${applyInfo.totalAmount/100}" type="currency" pattern="￥0"/>
		                                         	</td>
		                                         	<td>${applyInfo.companyName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${applyInfo.applyDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td>${applyInfo.dealStatus.displayName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${applyInfo.dealDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
		                                         		<button type="button" onclick="initInfoOper(${applyInfo.id},'${applyInfo.userEmail}');" class="btn btn-sm btn-info" data-toggle="modal" data-target="#applyFormModal">
	                                                   		<i class="fa fa-info-circle"></i> 详情
	                                                   	</button>
	                                                   	
		                                         		<c:if test="${applyInfo.dealStatus == 'undeal' or applyInfo.dealStatus == 'reject'}">
		                                         			<button type="button" onclick="initUpdateOper(${applyInfo.id},'${applyInfo.userEmail}');" 
	                                         					class="btn btn-sm btn-primary" data-toggle="modal" data-target="#applyFormModal"
	                                         					${applyInfo.dealStatus != 'dealing' ? '' : 'disabled="disabled"' }>
		                                                   		<i class="fa fa-pencil"></i> 编辑
		                                                   	</button>
		                                                   	
		                                                   	<button type="button" onclick="initDelOper(${applyInfo.id});" class="btn btn-sm btn-danger"
	                                                   			${applyInfo.dealStatus != 'dealing' ? '' : 'disabled="disabled"' }>
		                                                   		<i class="fa fa-trash"></i> 删除
		                                                   	</button>
		                                                   	
		                                                   	<%-- <button type="button" onclick="initSubmitForApproveOper(${applyInfo.id});" class="btn btn-sm btn-success" 
		                                                   			${applyInfo.dealStatus != 'dealing' ? '' : 'disabled="disabled"' }>
		                                                   		<i class="fa fa-clock-o"></i> 提交
		                                                   	</button> --%>
		                                         		</c:if>
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
		      	
		      	<!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="applyFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">发票申请信息</h4>
									</div>
									<div class="modal-body">
										<div class="row sum-info">
											<div class="col-lg-12" style="padding-left: 25px;margin-top: -10px; margin-bottom: 10px;">
												<p><i class="fa fa-info-circle"></i>说明：当前总消费金额<span id="feeAmountSpan" class="text-danger"></span>，已开发票金额<span id="invoiceAmountSpan" class="text-danger"></span>，可开发票金额<span id="availableInvoiceAmountSpan" class="text-danger"></span></p>
											</div>
										</div>
									
										<div class="row">
											<div class="col-lg-12">
												<form id="applyForm" role="form" class="form-horizontal">
													<input id="applyId" type="hidden" name="applyId">
													<input id="operType" type="hidden" name="operType">
													<input id="submitFlag" type="hidden" name="submitFlag" value="false">
													<div class="form-group apply-info-group">
														<label class="control-label col-lg-2">发票类型</label> 
														<div class="col-lg-10 valid-group">
															<select id="typeId" name="type" class="form-control apply-info">
							                                	<option value="common">增值税普通发票</option>
							                                	<option value="eleCommon">增值税普通电子发票</option>
							                                	<option value="special">增值税专用发票</option>
							                                </select>
														</div>
													</div>
													
													<div class="form-group apply-info-group">
														<label class="control-label col-lg-2">产品名称</label> 
														<div class="col-lg-4 valid-group">
															<input name="productName" class="form-control apply-info"/>
														</div>
														
														<label class="control-label col-lg-2">发票金额</label> 
														<div class="col-lg-4 valid-group">
															<input id="totalAmount" name="totalAmount" class="form-control apply-info"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group">
														<label class="control-label col-lg-2">公司名称</label> 
														<div class="col-lg-10 valid-group">
															<input name="companyName" class="form-control apply-info"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group">
														<label class="control-label col-lg-2">公司税号</label> 
														<div class="col-lg-10 valid-group">
															<input name="companyTaxNo" class="form-control apply-info"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group apply-special-info">
														<label class="control-label col-lg-2">公司地址</label> 
														<div class="col-lg-10 valid-group">
															<input name="companyAddr" class="form-control apply-info" placeholder="专用发票必填"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group apply-special-info">
														<label class="control-label col-lg-2">公司电话</label> 
														<div class="col-lg-10 valid-group">
															<input name="companyTel" class="form-control apply-info" placeholder="专用发票必填"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group apply-special-info">
														<label class="control-label col-lg-2">开户银行</label> 
														<div class="col-lg-10 valid-group">
															<input name="companyBankName" class="form-control apply-info" placeholder="专用发票必填"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group apply-special-info">
														<label class="control-label col-lg-2">银行账号</label> 
														<div class="col-lg-10 valid-group">
															<input name="companyBankAccount" class="form-control apply-info" placeholder="专用发票必填"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group">
														<label class="control-label col-lg-2">收件姓名</label> 
														<div class="col-lg-4 valid-group">
															<input name="receiverName" class="form-control apply-info"/>
														</div>
														
														<label class="control-label col-lg-2">收件电话</label> 
														<div class="col-lg-4 valid-group">
															<input name="receiverTel" class="form-control apply-info"/>
														</div>
													</div>
													
													<div class="form-group apply-info-group">
														<label class="control-label col-lg-2">邮寄地址</label> 
														<div class="col-lg-10 valid-group">
															<textarea name="mailingAddr" class="form-control apply-info" rows="2"></textarea>
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-2">申请备注</label> 
														<div class="col-lg-10 valid-group">
															<textarea name="applyRemark" class="form-control apply-info" rows="2"></textarea>
														</div>
													</div>
													
													<hr class="approve-info-group"/>
													
													<div class="form-group approve-info-group">
														<label class="control-label col-lg-2">处理状态</label> 
														<div class="col-lg-4">
															<select name="dealStatus" class="form-control approve-info">
							                                	<option value="undeal">待处理</option>
							                                	<option value="dealing">处理中</option>
							                                	<option value="dealed">已处理</option>
							                                	<option value="reject">驳回</option>
							                                </select>
														</div>
														
														<label class="control-label col-lg-2">寄送时间</label> 
														<div class="col-lg-4">
															<input id="mailingDate" name="mailingDate" class="form-control approve-info"/>
														</div>
													</div>
													
													<div class="form-group approve-info-group">
														<label class="control-label col-lg-2">快递名称</label> 
														<div class="col-lg-4">
															<input name="mailingServName" class="form-control approve-info" value="申通"/>
														</div>
														
														<label class="control-label col-lg-2">快递单号</label> 
														<div class="col-lg-4">
															<input name="mailingNo" class="form-control approve-info"/>
														</div>
													</div>
													
													<div class="form-group approve-info-group">
														<label class="control-label col-lg-2">审批备注</label> 
														<div class="col-lg-10">
															<textarea name="remark" class="form-control approve-info" rows="2"></textarea>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="saveBtn" type="button" class="btn btn-success apply-info-group">保存</button>
										<button id="saveAndSubmitBtn" type="button" class="btn btn-success apply-info-group">保存并提交</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MODAL SECTION -->
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
    <script type="text/javascript" src="plugin/datetimepicker/js/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript" src="plugin/touchspin/js/jquery.bootstrap-touchspin.min.js"></script>
    <script type="text/javascript" src="js/order/invoiceApplyList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
