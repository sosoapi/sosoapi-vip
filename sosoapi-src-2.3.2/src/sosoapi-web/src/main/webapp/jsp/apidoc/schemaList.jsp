<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>数据结构  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/nestable/ext/css/nestable.ext.css?v=1.0.1" rel="stylesheet">
	
	<style type="text/css">
		#schemaFormModal .modal-dialog {
			width: 800px;
			margin: 30px auto;
		}
		
		.dd-handle-drag{
			height:47px;
		}
		
		.dd-handle-drag:before{
			top: 14px;
		}
		
		.dd-item > button{
			margin-top: 15px;
		}
		
		/* #custSchemaTable .form-control{
			width:100px;
		}
		
		#custSchemaTable .dd-title ul li{
			width:100px;
		} */
		
    </style>
	<!-- END PAGE LEVEL  STYLES -->
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
        	<!-- TOOLBAR SECTION -->
            <jsp:include page="/jsp/apidoc/toolBar.jsp">
				<jsp:param name="projId" value="${param.projId}" />
				<jsp:param name="docId" value="${param.docId}" />
			</jsp:include>
			<!-- TOOLBAR SECTION -->
				
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
		                        <form class="form-horizontal" action="auth/doc/schema/list.htm" method="get">
		                        	<input type="hidden" name="projId" value="${param.projId}">
		                        	<input type="hidden" name="docId" value="${param.docId}">
					                <div class="form-group">
					                	<label class="control-label col-lg-1">名称</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="code" value="${param.code}" class="form-control">
					                	</div>
					                	
					                	<label class="control-label col-lg-1">模块</label>
					                	<div class="col-lg-2">
					                		<select class="form-control module-select" name="moduleId" data-initValue="${param.moduleId}">
					                			<option value="">全部</option>
					                			<c:if test="${not empty moduleList}">
													<c:forEach items="${moduleList}" var="moduleInfo" varStatus="status">
														<option value="${moduleInfo.code}">${moduleInfo.name}</option>						
													</c:forEach>
												</c:if>
				                            </select>
					                	</div>
					                	
					                	<label class="control-label col-lg-1">描述</label>
					                	<div class="col-lg-2">
					                		<input type="text" name="description" value="${param.description}" class="form-control">
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
			                    	<sosoapi:hasPermission name="docSchema:add" projId="${param.projId}">
			                    		<a id="addBtn" href="#schemaFormModal" data-toggle="modal" data-form="custSchemaForm" class="text-muted">
		                                	<i class="fa fa-plus"></i> 新增
		                               	</a>
			                    	</sosoapi:hasPermission>
			                        
			                        <!-- <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a> -->
			                    </div>
			                   	<span>数据结构列表</span>
			                </div>
		                	<div class="panel-body">
		                		<table class="table table-hover table-bordered table-fixed">
	                            	<thead>
	                                	<tr>
	                                    	<th class="table-index">#</th>
	                                    	<th class="col-lg-2">名称</th>
	                                        <th class="col-lg-3">描述</th>
	                                        <th class="col-lg-1">类型</th>
	                                        <th class="col-lg-1">所属模块</th>
	                                        <th class="col-lg-2">创建时间</th>
	                                        <th class="col-lg-3">操作</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:if test="${not empty pager.list}">
                                 			<c:forEach items="${pager.list}" var="schemaInfo" varStatus="status">
                                 				<tr>
		                                        	<td>${status.index + 1}</td>
		                                        	<td>${schemaInfo.code}</td>
		                                         	<td>${schemaInfo.description}</td>
		                                         	<td>${schemaInfo.type.code}</td>
		                                         	<td>${schemaInfo.moduleName}</td>
		                                         	<td>
		                                         		<fmt:formatDate value="${schemaInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
		                                         	</td>
		                                         	<td class="actions">
		                                         		<sosoapi:hasPermission name="docSchema:copy" projId="${param.projId}">
			                                         		<button onclick="initCopyOper(${schemaInfo.id});" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#schemaFormModal">
				                                             	<i class="fa fa-plus"></i> 复制
				                                           	</button>
			                                           	</sosoapi:hasPermission>
			                                                   
			                                            <sosoapi:hasPermission name="docSchema:update" projId="${param.projId}">
		                                                	<button onclick="initUpdateOper(${schemaInfo.id});" type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#schemaFormModal">
		                                                   		<i class="fa fa-pencil"></i> 编辑
		                                                   	</button>
	                                                   	</sosoapi:hasPermission>
	                                                   	
	                                                   	<sosoapi:hasPermission name="docSchema:del" projId="${param.projId}">
		                                                	<button onclick="initDelOper(${schemaInfo.id});" type="button" class="btn btn-sm btn-danger">
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
						<div class="modal fade" id="schemaFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">数据结构信息</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="custSchemaForm" role="form" class="form-horizontal">
													<input id="operTypeId" type="hidden" value="">
													<input id="schemaId" name="schemaId" type="hidden" value="">
													<input id="docId" name="docId" type="hidden" value="${param.docId}">
													<div class="form-group">
														<label class="control-label col-lg-3">名称</label> 
														<div class="col-lg-6">
															<input name="code" class="form-control" />
														</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">模块</label>
									                	<div class="col-lg-6">
									                		<select class="form-control module-select" name="moduleId">
									                			<c:if test="${not empty moduleList}">
																	<c:forEach items="${moduleList}" var="moduleInfo" varStatus="status">
																		<option value="${moduleInfo.code}">${moduleInfo.name}</option>						
																	</c:forEach>
																</c:if>
								                            </select>
									                	</div>
													</div>
													
													<div class="form-group">
														<label class="control-label col-lg-3">描述</label> 
														<div class="col-lg-6">
															<textarea name="description" class="form-control"></textarea>
														</div>
													</div>
													
								                	<div class="form-group">
								                		<label class="control-label col-lg-3">类型</label>
								                		<div class="col-lg-6">
															<select id="schemaTypeSelect" name="type" class="form-control">
								                				<option value="sys_string">string</option>
								                				<option value="sys_string_date">date</option>
							                					<option value="sys_string_datetime">datetime</option>
								                				<option value="sys_array">array</option>
																<option value="sys_object">object</option>
																<option value="sys_boolean">boolean</option>
																<option value="sys_integer_int32">int</option>
																<option value="sys_integer_int64">long</option>
																<option value="sys_number_float">float</option>
																<option value="sys_number_double">double</option>
																<option value="sys_number_decimal">decimal</option>
																<option value="sys_ref">ref</option>
							                                </select>
														</div>
								                	</div>
								                	
								                	<div id="refSchemaSelectId" class="form-group">
								                		<label class="control-label col-lg-3">已有结构</label>
								                		<div class="col-lg-6">
															<select id="refSchemaSelect" name="refSchemaId" class="form-control">
																<c:if test="${not empty refSchemaList}">
																	<c:forEach items="${refSchemaList}" var="refSchemaInfo" varStatus="status">
																		<option value="${refSchemaInfo.code}">${refSchemaInfo.name}</option>						
																	</c:forEach>
																</c:if>
							                                </select>
														</div>
								                	</div>
												</form>
											</div>
										</div>
										
										<div class="row">
											<div class="col-lg-12 form-horizontal">
												<div class="dd" id="custSchemaTable">
								            		<div class="dd-title form-group">
										           		<ul class="list-inline">
															<li class="col-lg-1 text-center">操作</li>
					                                    	<li class="col-lg-2 text-center">字段</li>
					                                        <li class="col-lg-3 text-center">描述</li>
					                                        <li class="col-lg-1 text-left">非空</li>
					                                        <li class="col-lg-2 text-center">类型</li>
					                                        <li class="col-lg-2 text-center">引用</li>
														</ul>
										           	</div>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<div class="row">
											<div class="col-xs-3 text-left">
												<button id="addCustSchemaRootNodeBtn" type="button" class="btn btn-primary">新增根节点</button>
											</div>
											<div class="col-xs-9 text-right">
												<button id="saveSchemaBtn" type="button" class="btn btn-success">保存</button>
												<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MODAL SECTION -->
				
				<script id="custSchemaContentTmpl" type="text/html">
					<ul class="list-inline">
						<li class="col-lg-2">
							<input name="code" class="form-control"/>
						</li>
						<li class="col-lg-3">
							<input name="description" class="form-control" />
						</li>
						<li class="col-lg-1 m-r">
							<select name="required" class="form-control" style="width:130%;">
								<option value="false" selected="selected">否</option>
								<option value="true">是</option>
			            	</select>
						</li>
						<li class="col-lg-2">
							<select name="type" class="form-control cust-schema-type">
			            		<option value="sys_string">string</option>
                                <option value="sys_string_date">date</option>
							    <option value="sys_string_datetime">datetime</option>
			            		<option value="sys_array">array</option>
								<option value="sys_object">object</option>
								<option value="sys_boolean">boolean</option>
								<option value="sys_integer_int32">int</option>
								<option value="sys_integer_int64">long</option>
								<option value="sys_number_float">float</option>
								<option value="sys_number_double">double</option>
								<option value="sys_number_decimal">decimal</option>
								<option value="sys_ref">ref</option>
			            	</select>
						</li>	
						<li class="col-lg-3">
							<select name="refSchemaId" class="form-control cust-ref-schema">
								<c:if test="${not empty refSchemaList}">
									<c:forEach items="${refSchemaList}" var="refSchemaInfo" varStatus="status">
										<option value="${refSchemaInfo.code}">${refSchemaInfo.name}</option>						
									</c:forEach>
								</c:if>
					        </select>
						</li>		
			    	</ul>
    			</script>
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
    <script type="text/javascript" src="plugin/jquery.query.js"></script>
	<script type="text/javascript" src="plugin/nestable/ext/js/jquery.nestable.ext.js"></script>
    <script type="text/javascript" src="js/apidoc/schemaList.js"></script>
    <script type="text/javascript" src="js/apidoc/toolBar.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
