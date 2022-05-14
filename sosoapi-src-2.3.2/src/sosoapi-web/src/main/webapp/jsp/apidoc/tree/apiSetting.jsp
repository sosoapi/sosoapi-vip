<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
<div class="hpanel nodeInfo" id="docInfoPanel">
	<ul class="nav nav-tabs">
		<li class="active">
	    	<a id="apiDocInfoTab" href="#apiDocInfo" data-toggle="tab">文档设置</a>
	 	</li>
	 	
	   	<li>
	    	<a id="baseUrlInfoTab" href="#baseUrlInfo" data-toggle="tab">请求基路径</a>
	   	</li>
	   	
	   	<li>
	    	<a id="commonParamInfoTab" href="#commonParamInfo" data-toggle="tab">公共请求参数</a>
	   	</li>
	</ul>
	                            
  	<div class="tab-content">
    	<div class="tab-pane active" id="apiDocInfo">
    		<div class="panel-body">
				<form id="docInfoFrom" class="form-horizontal">
					<input type="hidden" name="docId" value="${param.docId}">
					<div class="form-group">
						<label class="control-label col-lg-2">文档标题</label>
						<div class="col-lg-8">
							<input type="text" name="title" class="form-control">
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-lg-2">文档版本</label>
						<div class="col-lg-3">
							<input type="text" name="version" class="form-control">
						</div>
						
						<label class="control-label col-lg-2">是否发布</label>
						<div class="col-lg-3">
							<select name="pub" class="form-control">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
							<a href="javascript:void(0);" data-toggle="popover" data-container="body" data-trigger="hover" data-placement="top" data-content='只有发布后才能在"API文档管理"列表中展示' class="form-control-tip text-muted">
                             	<i class="fa fa-question-circle"></i>
                            </a>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-lg-2">文档说明</label>
						<div class="col-lg-8">
							<textarea id="docDesc" name="description" class="form-control" rows="10"></textarea>
						</div>
					</div>
					
					<div class="form-group">
		                <div class="col-lg-2 col-lg-offset-2">
		                	<sosoapi:hasPermission name="docInfo:update" projId="${param.projId}">
		                		<button id="saveDocBtn" class="btn btn-success" type="button">
			                    	<i class="fa fa-floppy-o"></i> 保存
			                    </button>
		                	</sosoapi:hasPermission>
		                </div>
		            </div>
				</form>
			</div>
       	</div>
        
       	<div class="tab-pane" id="commonParamInfo">
        	<div class="panel-body">
			   	<div class="row">
					<div class="col-lg-12">
						<form id="commonParamForm" class="form-horizontal">
			            	<div class="dd" id="commonParamTable">
			            		<div class="dd-title form-group">
					           		<ul>
										<li class="col-lg-1 text-center">操作</li>
					                	<li class="col-lg-1 text-center">字段</li>
					                    <li class="col-lg-3 text-center">参数位置</li>
					                    <li class="col-lg-1 text-center">类型</li>
					                    <li class="col-lg-2 text-right">默认值</li>
					                    <li class="col-lg-2 text-center">必输项</li>
					                    <li class="col-lg-1 text-center">描述</li>
									</ul>
					           	</div>
							</div>
			     		</form>
			       	</div>
			   	</div>
			          
			   	<div class="row">
			   		<sosoapi:hasPermission name="docCommonParam:add" projId="${param.projId}">
				       	<div class="col-md-6" style="margin-top: 20px;">
							<button id="addCommonParamBtn" type="button" class="btn btn-primary">
								<i class="fa fa-plus"></i> 新增
							</button>
						
							<button id="saveCommonParamBtn" type="button" class="btn btn-success">
								<i class="fa fa-floppy-o"></i> 保存
							</button>
							
							<button id="clearCommonParamBtn" type="button" class="btn btn-danger">
				            	<i class="fa fa-trash"></i> 清空
				            </button>
						</div>
					</sosoapi:hasPermission>
				</div>
			</div>
       	</div>
       	
       	<div class="tab-pane" id="baseUrlInfo">
        	<div class="panel-body">
			   	<div class="row">
					<div class="col-lg-12">
						<form id="baseUrlForm" class="form-horizontal">
			            	<div class="dd" id="baseUrlTable">
			            		<div class="dd-title form-group">
			            			<ul class="list-inline">
										<li class="col-lg-1 text-center">操作</li>
					                	<li class="col-lg-1 text-center">名称</li>
					                    <li class="col-lg-5 text-center">基路径</li>
					                    <li class="col-lg-2 text-center">描述</li>
					                    <li class="col-lg-3 text-center">状态</li>
									</ul>
					           	</div>
							</div>
			     		</form>
			       	</div>
			   	</div>
			          
			   	<div class="row">
			   		<sosoapi:hasPermission name="docEnv:add" projId="${param.projId}">
				       	<div class="col-md-6" style="margin-top: 20px;">
							<button id="addBaseUrlBtn" type="button" class="btn btn-primary">
								<i class="fa fa-plus"></i> 新增
							</button>
						
							<button id="saveBaseUrlBtn" type="button" class="btn btn-success">
								<i class="fa fa-floppy-o"></i> 保存
							</button>
							
							<button id="clearBaseUrlBtn" type="button" class="btn btn-danger">
				            	<i class="fa fa-trash"></i> 清空
				            </button>
						</div>
					</sosoapi:hasPermission>
				</div>
			</div>
       	</div>
	</div>
</div>

<script id="commonParamContentTmpl" type="text/html">  
	<ul class="list-inline">
		<li class="col-lg-2">
			<input name="code" type="text" class="form-control">
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
			</select>
		</li>
		<li class="col-lg-2">
			<input name="defValue" type="text" class="form-control">
		</li> 
    	<li class="col-lg-1">
			<select name="required" class="form-control">
				<option value="false" selected="selected">否</option>
				<option value="true">是</option>
			</select>
		</li>
		<li class="col-lg-2">
			<input name="description" type="text" class="form-control">
		</li>
	</ul>
</script>

<script id="baseUrlContentTmpl" type="text/html">  
	<ul class="list-inline">
		<li class="col-lg-2">
			<input name="name" type="text" class="form-control">
		</li>
		<li class="col-lg-4">
			<input name="baseUrl" type="text" class="form-control">
		</li>
		<li class="col-lg-3">
			<input name="description" type="text" class="form-control">
		</li>
		<li class="col-lg-2">
			<select name="status" class="form-control chzn-select">
				<option value="on">启用</option>
				<option value="off">关闭</option>
			</select>
		</li>
	</ul>
</script>