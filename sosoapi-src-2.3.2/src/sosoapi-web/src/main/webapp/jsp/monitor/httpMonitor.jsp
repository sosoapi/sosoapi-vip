<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>http监控详情  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/nestable/ext/css/nestable.ext.css?v=1.0.1" rel="stylesheet">
	<style type="text/css">
		.pannel-tools-fixed{
			position: fixed;
		    z-index: 1;
		    top: 92px;
		    right: 4.1%;
		}
		
		.dd-handle-drag {
			height: 47px;
		}
		
		.dd-handle-drag:before {
			top: 14px;
		}
		
		.dd-item>button {
			margin-top: 15px;
		}
	</style>
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
			                    <div class="panel-tools pannel-tools-fixed">
									<button id="saveMonitorBtn" type="button" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
			                    <span>http监控信息</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
									<div class="col-lg-12">
										<!-- <h4 class="font-bold">基本信息</h4>
										<hr> -->
				                		<form id="monitorInfoForm" method="post" action="${action}" class="form-horizontal">
				                			<input type="hidden" id="operType" name="operType" value="${operType}" />
		                             		<input type="hidden" name="monitorId" value="${monitorInfo.id}" />
		                             		<textarea id="reqParam" name="reqParam" class="hidden">${monitorInfo.reqParam}</textarea>
		                             		<div class="form-group">
							                    <label class="control-label col-lg-2">名称</label>
							                    <div class="col-lg-3 valid-group">
							                        <input type="text" name="name" class="form-control" value="${monitorInfo.name}">
							                    </div>
							                    
							                    <label class="control-label col-lg-2">状态</label>
							                    <div class="col-lg-3 valid-group">
							                        <select name="status" class="form-control" data-initValue="${monitorInfo.status}">
					                                	<option value="on">启用</option>
					                                	<option value="off">关闭</option>
					                                </select>
							                    </div>
							                </div>
							         
							         		<div class="form-group">
							         			<label class="control-label col-lg-2">监控url</label>
							                    <div class="col-lg-3 valid-group">
							                        <input type="text" name="sub" class="form-control" value="${monitorInfo.sub}">
							                        <a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="目前仅支持http协议" class="form-control-tip text-muted">
					                                	<i class="fa fa-question-circle"></i>
					                                </a>
							                    </div>
							                    
							                    <label class="control-label col-lg-2">请求方法</label>
							                    <div class="col-lg-3 valid-group">
							                        <select name="reqMethod" class="form-control" data-initValue="${monitorInfo.reqMethod}">
					                                	<option value="GET">GET</option>
														<option value="POST">POST</option>
														<!-- <option value="PUT">PUT</option>
														<option value="DELETE">DELETE</option>
														<option value="PATCH">PATCH</option> -->
					                                </select>
							                    </div>
							         		</div>
							                
							         		<div class="form-group">
							         			<label class="control-label col-lg-2">监控频率</label>
							                    <div class="col-lg-3 valid-group">
							                    	<select name="rate" class="form-control" data-initValue="${monitorInfo.rate}">
					                                	<!-- <option value="10">10分钟</option>
					                                	<option value="20">20分钟</option> -->
					                                	<option value="30">30分钟</option>
					                                </select>
							                    </div>
							                    
							         			<label class="control-label col-lg-2">最大响应时间(毫秒)</label>
							                    <div class="col-lg-3 valid-group">
							                        <input type="text" name="maxSpendTime" class="form-control" value="${monitorInfo.maxSpendTime}">
							                        <a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="超过响应时间则为发生错误。如果为0，则不验证。" class="form-control-tip text-muted">
					                                	<i class="fa fa-question-circle"></i>
					                                </a>
							                    </div>
							         		</div>
							         		
							         		<div class="form-group">
							         			<label class="control-label col-lg-2">最大连续错误次数</label>
							                    <div class="col-lg-3 valid-group">
							                        <input type="text" name="maxErrorCount" class="form-control" value="${monitorInfo.maxErrorCount}">
							                        <a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="连续错误超过设置值则发出警报" class="form-control-tip text-muted">
					                                	<i class="fa fa-question-circle"></i>
					                                </a>
							                    </div>
							                    
							         			<label class="control-label col-lg-2">最大预警次数</label>
							                    <div class="col-lg-3 valid-group">
							                        <input type="text" name="maxAlarmCount" class="form-control" value="${monitorInfo.maxAlarmCount}">
							                        <a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="超过预警次数后不再发出警报，如果为0，则不限制" class="form-control-tip text-muted">
					                                	<i class="fa fa-question-circle"></i>
					                                </a>
							                    </div>
							         		</div>
							         		
							         		<div class="form-group">
							         			<label class="control-label col-lg-2">验证响应</label>
							                    <div class="col-lg-3 valid-group">
							                        <select id="validResp" name="validResp" class="form-control" data-initValue="${monitorInfo.validResp}">
					                                	<option value="false">否</option>
					                                	<option value="true">是</option>
					                                </select>
					                                <a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="响应内容完全一致才算验证成功" class="form-control-tip text-muted">
					                                	<i class="fa fa-question-circle"></i>
					                                </a>
							                    </div>
							                    
							                    <label class="control-label col-lg-2">警报接收组</label>
							                    <div class="col-lg-3 valid-group">
							                        <select name="alarmGroupId" class="form-control" data-initValue="${monitorInfo.alarmGroupId}">
							                        	<c:if test="${not empty alarmGroupList}">
															<c:forEach items="${alarmGroupList}" var="groupInfo" varStatus="status">
																<option value="${groupInfo.code}">${groupInfo.name}</option>						
															</c:forEach>
														</c:if>
					                                </select>
							                    </div>
							         		</div>
							         		
							         		<div class="form-group" id="respContentDiv" ${monitorInfo.validResp ? '' : 'style="display:none;"'}>
							                    <label class="control-label col-lg-2">验证内容</label>
							                    <div class="col-lg-8 valid-group">
							                    	<textarea name="respContent" class="form-control" rows="5" placeholder="json格式">${monitorInfo.respContent}</textarea>
							                    	<a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content="目前仅支持json返回值" class="form-control-tip text-muted" style="margin-top: -70px;">
					                                	<i class="fa fa-question-circle"></i>
					                                </a>
							                    </div>
							                </div>
							                
							                <div class="form-group">
							                    <label class="control-label col-lg-2">描述</label>
							                    <div class="col-lg-8 valid-group">
							                    	<textarea name="description" class="form-control" rows="5">${monitorInfo.description}</textarea>
							                    </div>
							                </div>
		                            	</form>
		                            	
		                            	<div class="form-horizontal">
		                            		<div class="form-group">
                    							<label class="control-label col-lg-2">请求参数</label>
                    							<div class="col-lg-8">
	                    							<div class="row">
														<div class="col-lg-12">
															<div class="dd" id="paramTable">
											            		<div class="dd-title form-group">
													           		<ul>
																		<li class="col-lg-1">操作</li>
													                	<li class="col-lg-2 text-center">字段</li>
													                    <li class="col-lg-2">参数位置</li>
													                    <li class="col-lg-2">类型</li>
													                    <li class="col-lg-3 text-center">值</li>
													                    <!-- <li class="col-lg-2">描述</li> -->
																	</ul>
													           	</div>
															</div>
												       	</div>
												   	</div>
												          
												   	<div class="row">
												       	<div class="col-md-6" style="margin-top: 20px;">
															<button id="addParamBtn" type="button" class="btn btn-success">
																<i class="fa fa-plus"></i> 新增
															</button>
															
															<button id="clearParamBtn" type="button" class="btn btn-danger">
												            	<i class="fa fa-trash"></i> 清空
												            </button>
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
		   	</div>
        </div>
        <!--body wrapper end-->
        
        <!--footer section start-->
	    <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
	    <!--footer section end-->
	</div>

	<script id="paramContentTmpl" type="text/html">  
		<ul class="list-inline">
			<li class="col-lg-2">
				<input name="code" type="text" class="form-control">
			</li>
			<li class="col-lg-2">
				<select name="position" class="form-control">
					<option value="formData">formData</option>
					<option value="path">path</option>
					<option value="query">query</option>
					<!-- <option value="body">body</option> -->
					<option value="header">header</option>
				</select>
			</li>
			<li class="col-lg-2">
				<select name="type" class="form-control">
					<option value="sys_string">string</option>
                    <option value="sys_string_date">date</option>
					<option value="sys_string_datetime">datetime</option>
					<option value="sys_boolean">boolean</option>
					<option value="sys_integer_int32">int</option>
					<option value="sys_integer_int64">long</option>
					<option value="sys_number_float">float</option>
					<option value="sys_number_double">double</option>
					<option value="sys_number_decimal">decimal</option>
				</select>
			</li>
			<li class="col-lg-4">
				<input name="defValue" type="text" class="form-control">
			</li> 
		</ul>
	</script>
	
    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/nestable/ext/js/jquery.nestable.ext.js"></script>
    <script type="text/javascript" src="plugin/icheck/js/icheck.min.js"></script>
    <script type="text/javascript" src="js/monitor/httpMonitor.js?v=1.0.0"></script>
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
