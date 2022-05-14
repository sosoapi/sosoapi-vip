<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
<div class="hpanel nodeInfo" id="errorCodePanel">
	<div class="panel-heading">
		<div class="panel-tools">
			<sosoapi:hasAnyPermission name="docCode:add,docCode:update" projId="${param.projId}">
				<button id="saveErrorCodeBtn" type="button" class="btn btn-primary">保存</button>
			</sosoapi:hasAnyPermission>
			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		</div>
                
		<span>返回码信息</span>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-12">
				<form id="errorCodeForm" role="form" class="form-horizontal">
					<input type="hidden" name="docId" value="${param.docId}"> 
					<input type="hidden" name="codeId" id="codeId">
					<input type="hidden" name="errorCodeOperType" id="errorCodeOperTypeId">
					
					<div class="form-group">
						<label class="control-label col-lg-3">返回码</label>
						<div class="col-lg-6">
							<input name="code" class="form-control" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-lg-3">返回信息</label>
						<div class="col-lg-6">
							<input name="msg" class="form-control" />
						</div>
					</div>
					
					<!-- <div class="form-group">
						<label class="control-label col-lg-3">所属模块</label>
						<div class="col-lg-6">
							<select id="errorCodeModuleId" class="form-control" name="moduleId">
							</select>
						</div>
					</div> -->
					
					<div class="form-group">
						<label class="control-label col-lg-3">说明</label>
						<div class="col-lg-6">
							<textarea name="description" class="form-control" rows="5"></textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>