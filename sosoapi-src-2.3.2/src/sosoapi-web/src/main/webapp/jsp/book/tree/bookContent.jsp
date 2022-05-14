<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
<div class="hpanel nodeInfo changed-tip" id="bookContentPanel">
	<div class="panel-heading">
		<div class="panel-tools">
			<button id="saveBookContentBtn" type="button" class="btn btn-primary">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		</div>
                
		<span>详情</span><span class="text-danger" id="contentOperNameId"></span>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-12">
				<form id="bookContentForm" role="form" class="form-horizontal">
					<input type="hidden" name="chapterId" id="chapterId">
					<input type="hidden" name="contentId" id="contentId">
					<input type="hidden" name="contentOperType" id="contentOperTypeId">

					<div class="form-group text-left">
						<label class="control-label col-lg-1">标题</label>
						<div class="col-lg-7">
							<input id="contentTitle" type="text" name="title" class="form-control">
						</div>
						
						<label class="control-label col-lg-2">编辑方式</label>
						<div class="col-lg-2">
							<select id="contentTypeId" name="type" class="form-control">
								<option value="markdown">markdown</option>
					            <option value="html">富文本</option>
					        </select>
						</div>
					</div>
					
					<div class="form-group">
						<div id="markdownWrapId" class="col-lg-12">
							<div id="markdownEditor">
							</div>
						</div>
						
						<div id="richTextWrapId" class="col-lg-12">
							<textarea id="richTextEditor" name="richTextContent" class="form-control" rows="2"></textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

