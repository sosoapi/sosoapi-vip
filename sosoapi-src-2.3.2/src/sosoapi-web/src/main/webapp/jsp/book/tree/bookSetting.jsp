<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
<div class="hpanel nodeInfo" id="bookSettingPanel">
	<ul class="nav nav-tabs">
		<li class="active">
	    	<a id="bookSettingTab" href="#bookSetting" data-toggle="tab">文档设置</a>
	 	</li>
	</ul>
	                            
  	<div class="tab-content">
    	<div class="tab-pane active" id="bookSetting">
    		<div class="panel-body">
				<form id="bookSettingForm" class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-lg-2">标题</label>
						<div class="col-lg-8">
							<input type="text" name="title" class="form-control">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-lg-2">标签</label>
						<div class="col-lg-8">
							<input type="text" name="tag" class="form-control">
						</div>
					</div>
					
					<!-- <div class="form-group">
                        <label class="control-label col-lg-2">公开状态</label>
                        <div class="col-lg-3">
                            <select name="open" class="form-control">
					            <option value="true">开启</option>
					            <option value="false" selected="selected">关闭</option>
					        </select>
                        </div>
                        
                        <label class="control-label col-lg-2">发布状态</label>
                        <div class="col-lg-3">
                            <select name="pubStatus" class="form-control">
					            <option value="on">开启</option>
					            <option value="off" selected="selected">关闭</option>
					        </select>
                        </div>
                    </div>
					
					<div class="form-group">
                        <label class="control-label col-lg-2">分享状态</label>
                        <div class="col-lg-3">
                            <select name="share" class="form-control">
					            <option value="true">开启</option>
					            <option value="false" selected="selected">关闭</option>
					        </select>
                        </div>
                        
                        <label class="control-label col-lg-2">分享密码</label>
						<div class="col-lg-3">
							<input type="text" name="sharePassword" class="form-control">
						</div>
                    </div>
					
					<div class="form-group">
						<label class="control-label col-lg-2">分享url</label>
						<div class="col-lg-8">
							<textarea name="shareUrl" class="form-control" rows="2" readonly="readonly"></textarea>
						</div>
					</div> -->
					
					<div class="form-group">
						<label class="control-label col-lg-2">简介</label>
						<div class="col-lg-8">
							<textarea id="bookBrief" name="brief" class="form-control" rows="2"></textarea>
						</div>
					</div>
					
					<div class="form-group">
		                <div class="col-lg-2 col-lg-offset-2">
	                		<button id="saveBookSettingBtn" class="btn btn-success" type="button">
		                    	<i class="fa fa-floppy-o"></i> 保存
		                    </button>
		                </div>
		            </div>
				</form>
			</div>
       	</div>
	</div>
</div>