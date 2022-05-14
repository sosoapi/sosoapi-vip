<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
<div class="hpanel nodeInfo changed-tip" id="interPanel">
	<div class="panel-heading">
		<sosoapi:hasAnyPermission name="inter:addDetail,inter:updateDetail" projId="${param.projId}">
			<div class="panel-tools pannel-tools-fixed">
				<button id="saveInterBtn" type="button" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</sosoapi:hasAnyPermission>
		
		<span>接口信息</span><span class="text-danger" id="interOperNameId"></span>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-12">
				<h4 class="font-bold">基本信息</h4>
				<hr>
				<form id="basicInfoFrom" class="form-horizontal">
					<input type="hidden" name="interId" id="interId">
					<input type="hidden" name="interOperTypeId" id="interOperTypeId">
					<div class="form-group">
		              	<label class="control-label col-lg-2">接口名称</label>
		               	<div class="col-lg-8">
		                	<input type="text" class="form-control" name="name">
		               	</div>
		           	</div>
		           	
		           	<div class="form-group">
		               	<label class="control-label col-lg-2">请求url</label>
		                <div class="col-lg-8">
		                    <input type="text" class="form-control" name="path">
		                </div>
		           	</div>
		           	
					<div class="form-group">
						<label class="control-label col-lg-2">所属模块</label>
						<div class="col-lg-3">
							<select id="interModuleId" class="form-control" name="moduleId">
							</select>
						</div>
						
						<label class="control-label col-lg-2">是否弃用</label>
		                <div class="col-lg-3">
		                    <select class="form-control" name="deprecated">
		                    	<option value="false">否</option>
								<option value="true">是</option>
							</select>
		                </div>
					</div>
					
		           	<div class="form-group">
		            	<label class="control-label col-lg-2">请求方式</label>
		                <div class="col-lg-3">
		               		<select class="form-control" name="method">
								<option value="GET">GET</option>
								<option value="POST">POST</option>
								<option value="PUT">PUT</option>
								<option value="DELETE">DELETE</option>
								<option value="PATCH">PATCH</option>
								<option value="OPTIONS">OPTIONS</option>
							</select>
		                </div>
		                
		                <label class="control-label col-lg-2">请求协议</label>
		                <div class="col-lg-3">
		                    <select class="form-control" name="scheme">
								<option value="HTTP">HTTP</option>
								<option value="HTTPS">HTTPS</option>
								<!-- <option value="WS">WS</option>
								<option value="WSS">WSS</option> -->
							</select>
		                </div>
		            </div>
									                
		            <div class="form-group">
		                <label class="control-label col-lg-2">请求格式</label>
		                <div class="col-lg-3">
		                    <select class="form-control" name="consume">
								<option value="application/json">application/json</option>
								<!-- <option value="application/xml">application/xml</option> -->
								<option value="application/x-www-form-urlencoded">application/x-www-form-urlencoded</option>
								<option value="multipart/form-data">multipart/form-data</option>
							</select>
		                </div>
		                
		                <label class="control-label col-lg-2">响应格式</label>
		                <div class="col-lg-3">
		                    <select class="form-control" name="produce">
								<option value="application/json">application/json</option>
								<!-- <option value="application/xml">application/xml</option> -->
							</select>
		                </div>
		            </div>
								
		            <div class="form-group">
		                <label class="control-label col-lg-2">接口标识</label>
		                <div class="col-lg-3">
		                	 <input type="text" class="form-control" name="operationId" placeholder="可选，反向生成代码"/>
		                </div>
		                
		                <label class="control-label col-lg-2">忽略公共参数</label>
		                <div class="col-lg-3">
		                	 <select class="form-control" name="skipCommonParam">
								<option value="false">否</option>
								<option value="true">是</option>
							</select>
		                </div>
		            </div>
		            
		            <div class="form-group">
		                <label class="control-label col-lg-2">开发状态</label>
		                <div class="col-lg-3">
		                    <select class="form-control" name="devStatus">
								<option value="none">不显示</option>
								<option value="deving">开发中</option>
								<option value="finish">开发完成</option>
							</select>
		                </div>
		                
		                <label class="control-label col-lg-2">责任人</label>
		                <div class="col-lg-3">
		                    <input type="text" class="form-control" name="developer"/>
		                </div>
		            </div>		
		            
					<div class="form-group">
		                <label class="control-label col-lg-2">标签</label>
		                <div class="col-lg-8">
		                	 <input type="text" class="form-control" name="label" placeholder="可用于快速搜索"/>
		                </div>
		            </div>		
		                            
		            <div class="form-group">
		                <label class="control-label col-lg-2">描述信息</label>
		                <div class="col-lg-8">
		                    <textarea id="interDesc" class="form-control" name="description" rows="3"></textarea>
		                </div>
		            </div>
				</form>
			</div>
		</div>
		
		<div class="form-horizontal m-t-lg">
			<div class="row">
				<div class="col-lg-12">
					<h4 class="font-bold">请求参数</h4>
					<hr>
	            	<div class="dd" id="reqParamTable">
	            		<div class="dd-title form-group">
			           		<ul>
								<li class="col-lg-1">操作</li>
			                	<li class="col-lg-2">字段</li>
			                    <li class="col-lg-2">参数位置</li>
			                    <li class="col-lg-2">类型</li>
			                    <li class="col-lg-1">引用</li>
			                    <li class="col-lg-2 text-center">自定义</li>
			                    <li class="col-lg-1">更多</li>
							</ul>
			           	</div>
					</div>
		       	</div>
		   	</div>
		          
		   	<div class="row">
		       	<div class="col-md-6" style="margin-top: 20px;">
					<button id="addReqParamBtn" type="button" class="btn btn-success">
						<i class="fa fa-plus"></i> 新增
					</button>
					
					<a id="batchAddReqParamBtn" data-toggle="modal" data-target="#batchAddReqParamFormModal" class="btn btn-success">
						<i class="fa fa-cloud-upload"></i> 批量新增
					</a>
					
					<button id="clearReqParamBtn" type="button" class="btn btn-danger">
		            	<i class="fa fa-trash"></i> 清空
		            </button>
				</div>
			</div>
			
			<!-- MODAL SECTION -->
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="reqExtSchemaModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">自定义结构</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<textarea id="reqExtSchemaArea" style="resize: auto;height: auto;" name="extSchemaArea" class="form-control" rows="15"></textarea>
										</div>
									</div>
								</div>
								
								<div class="modal-footer">
									<div class="col-xs-3 text-left">
										<button id="reqFormatSchemaBtn" type="button" class="btn btn-primary">格式化</button>
									</div>
									<div class="col-xs-9 text-right">
										<button id="reqConfirmBtn" type="button" class="btn btn-success">确定</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END MODAL SECTION -->
			
			<!-- MODAL SECTION -->
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="moreModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">更多选项</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<form id="moreForm" role="form" class="form-horizontal">
												<div class="form-group">
													<label class="control-label col-lg-3">必输项</label> 
													<div class="col-lg-6">
														<select name="required" class="form-control">
															<option value="true">是</option>
															<option value="false">否</option>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-lg-3">默认值</label> 
													<div class="col-lg-6">
														<textarea name="defValue" class="form-control" rows="5"></textarea>
													</div>
												</div>
												
							                	<div class="form-group">
								                    <label class="control-label col-lg-3">描述</label>
								                    <div class="col-lg-6">
								                        <textarea name="description" class="form-control" rows="5"></textarea>
								                    </div>
								                </div>
											</form>
										</div>
									</div>
								</div>
								
								<div class="modal-footer">
									<button id="saveMoreBtn" type="button" class="btn btn-success">确定</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-horizontal m-t-lg">
			<div class="row">
				<div class="col-lg-12">
					<h4 class="font-bold">请求响应</h4>
					<h5 class="text-danger">注意：一个响应代表后端可能返回的一种结果。具体响应结构可以通过响应记录的编辑来创建。</h5>
					<hr>
					<div class="dd" id="reqRespTable">
	            		<div class="dd-title form-group">
			           		<ul>
								<li class="col-lg-1">操作</li>
			                	<li class="col-lg-2">名称</li>
			                    <li class="col-lg-2">描述</li>
			                    <li class="col-lg-2">类型</li>
			                    <li class="col-lg-2">更多</li>
							</ul>
			           	</div>
					</div>
				</div>
			</div>
			
		    <div class="row">
		       	<div class="col-md-6" style="margin-top: 20px;">
		       		<a id="addRespBtn" href="#moduleFormModal" data-toggle="modal" data-target="#respSchemaFormModal" data-form="respCustSchemaForm" class="btn btn-success">
		            	<i class="fa fa-plus"></i> 新增
		           	</a>
					
					<a id="importRespBtn" data-toggle="modal" data-target="#importRespFormModal" class="btn btn-success">
						<i class="fa fa-cloud-upload"></i> json导入
					</a>
					
					<button id="clearReqRespBtn" type="button" class="btn btn-danger">
		            	<i class="fa fa-trash"></i> 清空
		            </button>
				</div>
			</div>
			  	
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="respSchemaFormModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">请求响应信息</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<form id="respCustSchemaForm" role="form" class="form-horizontal">
												<input id="interRespOperTypeId" type="hidden" value="">
												<input name="docId" type="hidden" value="${param.docId}">
												<input name="interId" type="hidden" value="${param.interId}">
												
												<div class="form-group">
													<label class="control-label col-lg-3">名称</label> 
													<div class="col-lg-6">
														<input name="code" class="form-control" />
														<a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="若为默认响应信息则名称为'default'或'200'" class="form-control-tip text-muted">
							                             	<i class="fa fa-question-circle"></i>
							                            </a>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-lg-3">描述</label> 
													<div class="col-lg-6">
														<input name="description" class="form-control" />
													</div>
												</div>
							                	
							                	<!-- <div class="form-group">
							                		<label class="control-label col-lg-3">是否默认</label>
							                		<div class="col-lg-6">
														<select name="def" class="form-control">
															<option value="false">否</option>
															<option value="true">是</option>
							                            </select>
													</div>
							                	</div> -->
							                	
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
															<option value="cust_json">自定义</option>
							                          	</select>
													</div>
							                	</div>
								                	
							                	<div id="refSchemaDiv" class="form-group">
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
							                	
							                	<div id="respExtSchemaDiv" class="form-group">
							                		<label class="control-label col-lg-3">自定义结构</label>
							                		<div class="col-lg-6">
														<textarea id="respExtSchemaArea" style="resize: auto;height: auto;" name="extSchema" class="form-control" rows="10">
														</textarea>
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
											<button id="respFormatSchemaBtn" type="button" class="btn btn-primary">格式化</button>
											<button id="addCustSchemaRootNodeBtn" type="button" class="btn btn-primary">新增根节点</button>
										</div>
										<div class="col-xs-9 text-right">
											<button id="saveRespBtn" type="button" class="btn btn-success">确定</button>
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
			
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="mockFormModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">mock配置</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<form id="mockForm" role="form" class="form-horizontal">
												<input name="docId" type="hidden" value="${param.docId}">
												<input name="interId" type="hidden" value="${param.interId}">
							                	
							                	<ul class="nav nav-tabs">
						                    		<li class="active">
						                    			<a href="#mockDataInfo" data-toggle="tab">静态数据</a>
						                           	</li>
						                            <li class="">
						                            	<a href="#mockRuleInfo" data-toggle="tab">mock规则</a>
						                            </li>
						                     	</ul>
						                            
						                      	<div class="tab-content">
						                        	<div class="tab-pane fade active in" id="mockDataInfo">
						                        		<div class="form-group">
						                        			<div class="col-lg-12 text-left m-t-sm">
																说明:调用返回的mock数据为静态数据
															</div>
									                	</div>
									                	
						                        		<div class="form-group">
									                		<div class="col-lg-12">
																<textarea id="mockDataArea" style="resize: auto;height: auto;" name="mockData" class="form-control" rows="10">
																</textarea>
															</div>
									                	</div>
						                           	</div>
						                            
						                           	<div class="tab-pane fade" id="mockRuleInfo">
						                           		<div class="form-group">
						                        			<div class="col-lg-12 text-left m-t-sm">
																<p>说明:调用返回的mock数据为根据规则动态生成，规则同<a href="http://mockjs.com/examples.html" target="_blank">mockjs规则</a>。</p>
																<p>注意:输入框中配置的规则将作为mockjs中的Mock.mock()的参数。</p>
															</div>
									                	</div>
									                	
						                            	<div class="form-group">
									                		<div class="col-lg-12">
																<textarea id="mockRuleArea" style="resize: auto;height: auto;" name="mockRule" class="form-control" rows="10">
																</textarea>
															</div>
									                	</div>
									                	
									                	<div class="form-group">
															<div class="col-lg-12 text-center">
																<button id="mockPreviewBtn" type="button" class="btn btn-info">预览</button>
															</div>
									                	</div>
									                	
									                	<div class="form-group">
															<div class="col-lg-12">
																<textarea id="mockPreviewArea" style="resize: auto;height: auto;" name="mockPreview" class="form-control" rows="10" readonly="readonly">
																</textarea>
															</div>
									                	</div>
						                           	</div>
						                    	</div>
											</form>
										</div>
									</div>
								</div>
								
								<div class="modal-footer">
									<div class="row">
										<div class="col-xs-3 text-left">
											<button id="mockFormatBtn" type="button" class="btn btn-primary">格式化</button>
										</div>
										<div class="col-xs-9 text-right">
											<button id="saveMockBtn" type="button" class="btn btn-success">确定</button>
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 批量新增请求参数 -->
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="batchAddReqParamFormModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">批量新增请求参数</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<form id="batchAddReqParamForm" role="form" class="form-horizontal">
												<input name="docId" type="hidden" value="${param.docId}">
												<input name="interId" type="hidden" value="${param.interId}">
						                            
					                           <!-- <div class="form-group">
				                        			<div class="col-lg-12 text-left m-t-sm">
														<p>新增方式说明：</p>
														<p>追加：批量新增的请求参数直接添加到现有请求参数后面</p>
														<p>覆盖：批量新增的请求参数直接覆盖现有请求参数</p>
													</div>
							                	</div> -->
							                	
							                	<div class="form-group">
								                	<label class="col-lg-2 control-label">新增方式</label>
	
	                								<div class="col-lg-10">
	                									<label class="checkbox-inline"> 
	                										<input type="radio" name="reqParamAddType" checked="checked" value="append"> 追加 
	                									</label> 
	                									<label class="checkbox-inline">
	                    									<input type="radio" name="reqParamAddType" value="cover"> 覆盖
	                    								</label> 
	                    							</div>
	                    						</div>
							                	
				                            	<div class="form-group">
							                		<div class="col-lg-12">
														<textarea id="batchAddReqParamArea" style="resize: auto;height: auto;" name="batchAddReqParamArea" class="form-control" rows="5" placeholder="示例:name=sosoapi&age=3&passwd&login=true"></textarea>
													</div>
							                	</div>
											</form>
										</div>
									</div>
								</div>
								
								<div class="modal-footer">
									<div class="row">
										<div class="col-xs-12 text-right">
											<button id="parseReqParamBtn" type="button" class="btn btn-success">确定</button>
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 通过导入json创建对应响应 -->
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="importRespFormModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">json导入</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<form id="importRespForm" role="form" class="form-horizontal">
												<input name="docId" type="hidden" value="${param.docId}">
												<input name="interId" type="hidden" value="${param.interId}">
							                	
				                            	<div class="form-group">
							                		<div class="col-lg-12">
														<textarea id="importRespArea" style="resize: auto;height: auto;" name="importRespArea" class="form-control" rows="10" placeholder="请输入json格式数据"></textarea>
													</div>
							                	</div>
											</form>
										</div>
									</div>
								</div>
								
								<div class="modal-footer">
									<div class="row">
										<div class="col-xs-12 text-right">
											<button id="parseRespBtn" type="button" class="btn btn-success">确定</button>
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script id="reqParamContentTmpl" type="text/html">  
	<ul class="list-inline">
		<li class="hidden">
			<input name="description" type="text">
		</li>
		<li class="hidden">
			<input name="defValue" type="text">
		</li> 
    	<li class="hidden">
			<input name="required" type="text">
		</li>
		<li class="hidden">
			<textarea name="extSchema"/>
		</li>		

		<li class="col-lg-2">
			<input name="code" type="text" value="" class="form-control">
		</li>
		<li class="col-lg-2">
			<select name="position" class="form-control req-param-position">
				<option value="formData">formData</option>
				<option value="path">path</option>
				<option value="query">query</option>
				<option value="body">body</option>
				<option value="header">header</option>
				<option value="cookie">cookie</option>
			</select>
		</li>
		<li class="col-lg-2">
			<select name="type" class="form-control req-param-type chzn-select">
				<option value="sys_string">string</option>
				<option value="sys_string_date">date</option>
				<option value="sys_string_datetime">datetime</option>
				<option value="sys_boolean">boolean</option>
				<option value="sys_integer_int32">int</option>
				<option value="sys_integer_int64">long</option>
				<option value="sys_number_float">float</option>
				<option value="sys_number_double">double</option>
				<option value="sys_number_decimal">decimal</option>
				<option value="sys_file">file</option>
				<option value="sys_ref">ref</option>
				<option value="cust_json">自定义</option>
			</select>
		</li>
		<li class="col-lg-2">
			<select name="refSchemaId" class="form-control cust-ref-schema">
				<option value="">请选择</option>
				<c:if test="${not empty refSchemaList}">
					<c:forEach items="${refSchemaList}" var="refSchemaInfo" varStatus="status">
						<option value="${refSchemaInfo.code}">${refSchemaInfo.name}</option>						
					</c:forEach>
				</c:if>
	        </select>
		</li>
		<li class="col-lg-1">
            <button class="btn ext-schema-btn" type="button">
            	<!-- <i class="fa fa-ellipsis-h"></i> --> 自定义结构
            </button>
		</li>
		<li class="col-lg-2 text-center">
            <button class="btn more-btn" type="button">
				更多
            </button>
		</li>
	</ul>
</script> 

<script id="reqRespContentTmpl" type="text/html">  
	<ul class="list-inline">
		<li class="hidden">
			<input name="refSchemaId" type="text">
		</li>
		<li class="hidden">
			<input name="def" type="text">
		</li>
		<li class="hidden">
			<textarea name="extSchema"/>
		</li>
		<li class="hidden">
			<textarea name="custSchema"/>
		</li>
		<li class="hidden">
			<textarea name="mockData"/>
		</li>
		<li class="hidden">
			<textarea name="mockRule"/>
		</li>
		<li class="col-lg-2">
			<input name="code" type="text" value="" class="form-control" readonly>
		</li>
		<li class="col-lg-2">
			<input name="description" type="text" value="" class="form-control" readonly>
		</li>
		<li class="col-lg-2">
			<select name="type" class="form-control" disabled>
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
				<option value="cust_json">自定义</option>
           	</select>
		</li>
		<li class="col-lg-4">
            <button type="button" class="btn btn-sm btn-primary resp-oper-update" data-toggle="modal" data-target="#respSchemaFormModal">
            	<i class="fa fa-pencil"></i> 编辑
			</button>

			<button type="button" class="btn btn-sm btn-success resp-oper-update-mock" data-toggle="modal" data-target="#mockFormModal">
            	<i class="fa fa-cog"></i> mock设置
			</button>
		</li>
	</ul>
</script> 

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
