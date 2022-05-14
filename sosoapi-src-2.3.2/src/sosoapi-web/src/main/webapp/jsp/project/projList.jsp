<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>项目管理  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<style type="text/css">
		.radio {
		    padding-left: 20px;
		}
		
		.radio, .checkbox {
		    position: relative;
		    display: block;
		    margin-top: 10px;
		    margin-bottom: 10px;
		}
	</style>
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
		                		<form class="form-horizontal" action="auth/proj/list.htm" method="get">
					                <div class="form-group">
					                	<label class="control-label col-lg-1">名称</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="name" value="${param.name}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">英文名</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="code" value="${param.code}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">状态</label>
					                	<div class="col-lg-2">
					                		<select name="status" class="form-control" data-initValue="${param.status}">
				                				<option value="">全部</option>
			                                	<option value="open">启用</option>
			                                	<option value="close">关闭</option>
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
			                    	<a href="#projFormModal" data-toggle="modal" class="text-muted">
			                    		<i class="fa fa-plus"></i> 新增
			                    	</a>
			                    	<a href="#projImportModal" data-toggle="modal" class="text-muted m-r-sm">
                                   		<i class="fa fa-cloud-upload"></i> 导入
                                	</a>
			                        
			                        <!-- <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a> -->
			                    </div>
			                   	<span>项目列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                        		<thead>
		                            	<tr>
		                                	<th class="table-index">#</th>
		                                    <th class="col-lg-2">名称</th>
		                                    <th class="col-lg-2">英文名称</th>
		                                    <th class="col-lg-1">角色</th>
		                                    <th class="col-lg-2">创建时间</th>
		                                    <th class="col-lg-1">状态</th>
		                                    <th class="col-lg-4">操作</th>
		                               </tr>
		                            </thead>
	                            
                                 	<tbody>
                                 		<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="projInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>
		                                         		<a href="auth/proj/home.htm?projId=${projInfo.projId}&docId=${projInfo.docId}">${projInfo.name}</a>
		                                         	</td>
		                                         	<td>${projInfo.code}</td>
		                                         	<td>${projInfo.projRoleName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${projInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td>${projInfo.status.displayName}</td>
		                                         	<td class="actions">
		                                         		<a href="auth/proj/home.htm?projId=${projInfo.projId}&docId=${projInfo.docId}" type="button" class="btn btn-sm btn-info">
		                                                	<i class="fa fa-info-circle"></i> 详情
		                                               	</a>
		                                                   	
		                                         		<button onclick="quitProj(${projInfo.projId});" type="button" class="btn btn-sm btn-primary">
	                                                   		<i class="fa fa-frown-o"></i> 退出
	                                                   	</button>
	                                                   	
	                                                   	<sosoapi:hasPermission name="projInfo:copy" projId="${projInfo.projId}">
		                                                   	<button onclick="copyProj(${projInfo.projId},'${projInfo.name}');" type="button" class="btn btn-sm btn-success">
		                                                   		<i class="fa fa-plus"></i> 复制
		                                                   	</button>
	                                                   	</sosoapi:hasPermission>
	                                                   	
	                                                   	<sosoapi:hasPermission name="projInfo:del" projId="${projInfo.projId}">
	                                                   		<button onclick="delProj(${projInfo.projId});" type="button" class="btn btn-sm btn-danger">
		                                                   		<i class="fa fa-trash"></i> 删除
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
				   	</div>
				</div>
				
				<!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="projFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h6 class="modal-title">项目信息</h6>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="projForm" role="form" class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-lg-3">名称</label> 
														<div class="col-lg-6">
															<input name="name" class="form-control" />
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">英文名称</label> 
														<div class="col-lg-6">
															<input name="code" class="form-control" placeholder="选填"/>
														</div>
													</div>
													
								                	<div class="form-group">
								                		<label class="control-label col-lg-3">状态</label>
								                		<div class="col-lg-6">
															<select name="status" class="form-control">
								                				<option value="open">开启</option>
								                				<option value="close">关闭</option>
							                                </select>
														</div>
								                	</div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="saveProjBtn" type="button" class="btn btn-success">保存</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MODAL SECTION -->
				
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="projImportModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">导入</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-left m-b-sm">
													说明:导入格式只支持标准的或sosoapi扩展的swagger json。
												</div>
							                </div>
							           	</div>
										
										<div class="row">
											<div class="col-lg-12">
												<ul class="nav nav-tabs">
						                			<li class="active">
						                            	<a id="importFileTab" href="#importFile" data-toggle="tab">本地导入</a>
						                            </li>
						                            
						                    		<li class="">
						                    			<a id="importUrlTab" href="#importUrl" data-toggle="tab">url导入</a>
						                           	</li>
						                     	</ul>
						                     	
						                     	<div class="tab-content">
						                           	<div class="tab-pane active" id="importFile">
						                           		<div class="panel-body">
						                           			<form id="importFileForm" role="form" class="form-horizontal" method="post" action="auth/apidoc/importByFile.htm" enctype="multipart/form-data">
																<div class="form-group">
																	<label class="control-label col-lg-2">json文件</label> 
																	<div class="col-lg-8">
																		<input name="jsonFile" class="form-control" type="file"/>
																	</div>
																	<button type="submit" class="btn btn-success">导入</button>
																</div>
															</form>
						                           		</div>
						                           	</div>
						                           	
						                           	<div class="tab-pane" id="importUrl">
						                           		<div class="panel-body">
						                           			<form id="importUrlForm" role="form" class="form-horizontal" method="post" action="auth/apidoc/importByUrl.htm">
																<div class="form-group">
																	<label class="control-label col-lg-2">url地址</label> 
																	<div class="col-lg-8">
																		<input name="jsonUrl" class="form-control" type="text" placeholder="请输入能直接访问swagger json的url地址"/>
																	</div>
																	<button type="submit" class="btn btn-success">导入</button>
																</div>
															</form>
						                           		</div>
						                           	</div>
						                    	</div>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="cancelImportBtn" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
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
    <script type="text/javascript" src="plugin/jquery.query.js"></script>
    <script type="text/javascript" src="js/project/projList.js?v=1.0.1"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
