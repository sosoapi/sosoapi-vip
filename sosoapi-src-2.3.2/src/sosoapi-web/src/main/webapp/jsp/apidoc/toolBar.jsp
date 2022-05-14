<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
<div class="small-header transition animated">
	<div class="hpanel">
    	<div class="panel-body" style="vertical-align: middle;">
   			<div class="pull-left m-t-xs">
				<span>${projTempMap[param.projId].name}</span>
			</div>
			
			<div class="pull-right">
				<a href="auth/apidoc/preview.htm?docId=${param.docId}" target="_blank" class="btn btn-sm btn-success">
					<i class="fa fa-eye"></i> 预览
				</a>
			    
			    <sosoapi:hasPermission name="docShare:update" projId="${param.projId}">
			    	<a id="docShareBtn" href="#docShareFormModal" data-toggle="modal" class="btn btn-sm btn-success">
						<i class="fa fa-share-alt"></i> 分享设置
					</a>
			    </sosoapi:hasPermission>
				
				
				<a id="mockSettingBtn" href="#mockSettingFormModal" data-toggle="modal" class="btn btn-sm btn-success">
					<i class="fa fa-cogs"></i> mock地址
				</a>
				
				<sosoapi:hasPermission name="projMem:forwardSendNotice" projId="${param.projId}">
					<a href="auth/proj/mem/forwardSendNotice.htm?projId=${param.projId}&docId=${param.docId}" class="btn btn-sm btn-success">
						<i class="fa fa-envelope-square"></i> 变更通知
					</a>
				</sosoapi:hasPermission>
				
				<sosoapi:hasPermission name="docArchive:add" docId="${param.docId}">
					<a href="auth/doc/archive/forwardAdd.htm?projId=${param.projId}&docId=${param.docId}" class="btn btn-sm btn-success">
						<i class="fa fa-clock-o"></i> 接口归档
					</a>
				</sosoapi:hasPermission>
				
				<div class="btn-group">
			        <button class="btn btn-sm btn-success">
			        	<i class="fa fa-share"></i> 导出
			        </button>
			        
			        <button data-toggle="dropdown" class="btn btn-sm btn-success dropdown-toggle"><span class="caret"></span></button>
			        <ul class="dropdown-menu">
			            <li>
			            	<a href="auth/apidoc/export.htm?docId=${param.docId}&exportType=html">
			            		<i class="fa fa-file-code-o"></i> html文档
			            	</a>
			            </li>
			            <li>
			            	<a href="auth/apidoc/export.htm?docId=${param.docId}&exportType=doc">
			            		<i class="fa fa-file-word-o"></i> word文档
			            	</a>
			            </li>
			            <li>
			            	<a href="auth/apidoc/export.htm?docId=${param.docId}&exportType=swagger">
			            		<i class="fa fa-file-text-o"></i> swagger文档
			            	</a>
			            </li>
			            <li>
			            	<a href="auth/apidoc/export.htm?docId=${param.docId}&exportType=postman">
			            		<i class="fa fa-file-powerpoint-o"></i> postman文档
			            	</a>
			            </li>
			        </ul>
			    </div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="docShareFormModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">分享设置</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<form id="docShareForm" role="form" class="form-horizontal">
												<input id="toolBarDocId" name="docId" type="hidden" value="${param.docId}"/>
												<div class="form-group">
							                		<label class="control-label col-lg-2">状态</label>
							                		<div class="col-lg-9">
														<select name="share" class="form-control">
							                				<option value="true">开启</option>
							                				<option value="false">关闭</option>
						                                </select>
													</div>
							                	</div>
							                	
												<div class="form-group">
													<label class="control-label col-lg-2">访问密码</label> 
													<div class="col-lg-9">
														<input name="sharePassword" type="password" class="form-control" placeholder="可选"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-lg-2">访问url</label> 
													<div class="col-lg-9">
														<textarea name="shareUrl" readonly="readonly" class="form-control" rows="10"></textarea>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
								
								<div class="modal-footer">
									<sosoapi:hasPermission name="docShare:update" docId="${param.docId}">
										<button id="docShareSaveBtn" type="button" class="btn btn-success">保存</button>
									</sosoapi:hasPermission>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="modal fade" id="mockSettingFormModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">mock地址</h4>
								</div>
								<div class="alert alert-success">
					                <p>说明：</p>
					                <p>1、mock使用说明<a href="pass/faq/test.htm" target="_blank">（点击查看）</a>。</p>
					                <p>2、动态数据规则同mockjs<a href="http://mockjs.com/examples.html" target="_blank">（点击查看）</a>。</p>
					            </div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12">
											<form id="mockSettingForm" role="form" class="form-horizontal">
												<input name="docId" type="hidden" value="${param.docId}"/>
												<!-- <div class="form-group">
							                		<label class="control-label col-lg-2">状态</label>
							                		<div class="col-lg-9">
														<select name="mock" class="form-control">
							                				<option value="true">开启</option>
							                				<option value="false">关闭</option>
						                                </select>
													</div>
							                	</div> -->
							                	
												<div class="form-group">
													<label class="control-label col-lg-2">mock基地址</label> 
													<div class="col-lg-9">
														<textarea name="mockBaseUrl" readonly="readonly" class="form-control" rows="3">${projTempMap[param.projId].mockBaseUrl}
														</textarea>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-lg-2">mock静态基地址</label> 
													<div class="col-lg-9">
														<textarea name="mockStaticBaseUrl" readonly="readonly" class="form-control" rows="3">${projTempMap[param.projId].mockStaticBaseUrl}
														</textarea>
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-lg-2">mock动态基地址</label> 
													<div class="col-lg-9">
														<textarea name="mockDynamicBaseUrl" readonly="readonly" class="form-control" rows="3">${projTempMap[param.projId].mockDynamicBaseUrl}
														</textarea>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
								
								<div class="modal-footer">
									<!-- <button id="docShareSaveBtn" type="button" class="btn btn-success">保存</button> -->
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>              



