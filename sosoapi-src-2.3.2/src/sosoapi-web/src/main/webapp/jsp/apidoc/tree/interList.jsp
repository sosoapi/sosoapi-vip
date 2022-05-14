<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="hpanel nodeInfo" id="interListPanel">
	<div class="panel-heading">
		<div class="panel-tools">
		</div>
		<span>接口列表</span>
	</div>
	<div class="panel-body">
		<div class="row">
        	<div class="col-lg-12">
        		<h4 class="font-bold">基本信息</h4>
				<hr>
		        <!-- SEARCH SECTION -->
                <form id="interListForm" class="form-horizontal">
                    <input type="hidden" name="projId" value="${param.projId}">
                    <input type="hidden" name="docId" value="${param.docId}">
			        <div class="form-group">
			        	<label  class="control-label col-lg-2">名称</label>
			        	<div class="col-lg-2">
			            	<input type="text" name="name" class="form-control">
			            </div>
			                	
		                <label  class="control-label col-lg-2">请求url</label>
		                <div class="col-lg-2">
		                	<input type="text" name="path" class="form-control">
		                </div>
			      	</div>
			                
		            <div class="form-group">
		               	<label  class="control-label col-lg-2">描述</label>
		               	<div class="col-lg-2">
		               		<input type="text" name="description" class="form-control">
		               	</div>
		                	
		               	<label class="control-label col-lg-2">模块</label>
		               	<div class="col-lg-2">
		               		<select class="form-control module-select" id="interListModuleId" name="moduleId">
	                			<option value="">全部</option>
                            </select>
		               	</div>
		                	
		               	<div class="col-lg-1">
		               		<button id="interListSearchBtn" type="button" class="btn btn-default">
		               			<i class="fa fa-search"></i> 查询
		               		</button>
		               	</div>
		            </div>
			   	</form>
		        <!-- END SEARCH SECTION -->
			</div>
		</div>
				
		<div class="form-horizontal m-t-lg">
			<div class="row">
				<div class="col-lg-12">
					<h4 class="font-bold">接口列表</h4>
					<hr>
      		
					<table id="interListTable">
                    	<!-- <thead>
                        	<tr>
                             	<th>#</th>
                                <th>名称</th>
                                <th>请求url</th>
                                <th>描述信息</th>
                                <th>所属模块</th>
                                <th>更新时间</th>
                                <th>操作</th>
                          	</tr>
                      	</thead>
                          
                        <tbody>
                        </tbody> -->
                  	</table>
                </div>
		   	</div>
		</div>
   	</div>
</div>
