<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
<div class="hpanel nodeInfo" id="modulePanel">
	<div class="panel-heading">
		<div class="panel-tools">
			<sosoapi:hasAnyPermission name="docModule:add,docModule:update" projId="${param.projId}">
				<button id="saveModuleBtn" type="button" class="btn btn-primary">保存</button>
			</sosoapi:hasAnyPermission>
			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		</div>
                
		<span>模块信息</span>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-12">
				<form id="moduleForm" role="form" class="form-horizontal">
					<input type="hidden" name="docId" value="${param.docId}"> 
					<input type="hidden" name="moduleId" id="moduleId">
					<input type="hidden" name="moduleOperType" id="moduleOperTypeId">
					
					<!-- <div class="form-group">
						<label class="control-label col-lg-3">编码</label> 
						<div class="col-lg-6">
							<input name="code" class="form-control" />
						</div>
					</div> -->

					<div class="form-group">
						<label class="control-label col-lg-3">名称</label>
						<div class="col-lg-6">
							<input name="name" class="form-control" />
						</div>
					</div>

					<!-- <div class="form-group">
						<label class="control-label col-lg-3">排序权重</label>
						<div class="col-lg-6">
							<input name="sortWeight" value="0" class="form-control" />
						</div>
					</div> -->

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
</div>