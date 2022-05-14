<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>系统参数  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	
	<style type="text/css">
		.pannel-tools-fixed{
			position: fixed;
		    z-index: 1;
		    top: 134px;
		    right: 3%;
		}
		
		.nodeInfo .panel-tools .btn {
			padding: 4px 12px;
		}
		
		#mainContent.content {
			padding: 20px;
		}
		
		#treeSearch .input-group-btn button {
			margin-top: -15px;
			padding: 2px 5px;
		}
		
		#treeSearch .form-control {
			margin-top: -10px;
			height: 25px;
			font-size:12px;
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
			<div id="mainContent" class="content animate-panel">
				<div class="row">
				    <div class="col-lg-4">
				        <div class="hpanel">
				            <div class="panel-heading hbuilt">
				                <div class="panel-tools">
				                	<a id="reloadAppCfgBtn" href="javascript:;">
					                    <span class="fa fa-check-circle fa-2x"></span>
					               	</a>
					               	
				                    <a id="addSysCfgBtn" href="javascript:;">
					                    <span class="fa fa-plus-circle fa-2x"></span>
					               	</a>
				                </div>
				                <span>系统参数</span>
				            </div>
				            <div class="panel-body">
			            		<div id="treeSearch" class="input-group">
	                            	<input id="condition" type="text" class="form-control" placeholder="名称  /  属性名">
	                            	<span class="input-group-btn">
	                                	<button id="searchBtn" type="button" class="btn btn-white">
	                                		<i class="fa fa-search"></i>
	                                	</button>
	                              	</span>
	                            </div>
				            	
								<div class="row">
									<ul id="sysCfgTree" class="ztree"></ul>
								</div>
				            </div>
				        </div>
				    </div>
				    
				    <div class="col-lg-8">
				    	<div class="hpanel nodeInfo" id="sysCfgPanel" style="display: none;">
							<div class="panel-heading">
								<div class="panel-tools">
									<button id="saveSysCfgBtn" type="button" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
						                
								<span>参数信息</span>
								<span class="text-danger" id="sysCfgOperNameId"></span>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-12">
										<form id="sysCfgForm" role="form" class="form-horizontal">
											<input type="hidden" name="cfgId" id="cfgId">
											<input type="hidden" name="sysCfgOperType" id="sysCfgOperTypeId">
											
											<div class="form-group">
												<label class="control-label col-lg-3">名称</label>
												<div class="col-lg-6">
													<input name="name" class="form-control" />
												</div>
											</div>
											
											<div class="form-group">
												<label class="control-label col-lg-3">类型</label>
												<div class="col-lg-6">
													<select id="typeId" class="form-control" name="type">
														<option value="cate">分组</option>
														<option value="item" selected="selected">配置项</option>
													</select>
												</div>
											</div>
											
											<div class="form-group cfg-item">
												<label class="control-label col-lg-3">属性名</label>
												<div class="col-lg-6">
													<input name="code" class="form-control" />
												</div>
											</div>
											
											<div class="form-group cfg-item">
												<label class="control-label col-lg-3">属性值</label>
												<div class="col-lg-6">
													<textarea name="value" class="form-control" rows="2"></textarea>
												</div>
											</div>
											
											<div class="form-group cfg-item">
												<label class="control-label col-lg-3">属性默认值</label>
												<div class="col-lg-6">
													<textarea name="defValue" class="form-control" rows="2"></textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="control-label col-lg-3">排序权重</label>
												<div class="col-lg-6">
													<input name="sortWeight" class="form-control" value="0"/>
												</div>
											</div>
											
											<div class="form-group" id="parentSelectDiv">
												<label class="control-label col-lg-3">所属分组</label>
												<div class="col-lg-6">
													<select id="parentSelectId" class="form-control" name="parentId">
														<c:if test="${not empty sysCfgCateList}">
															<c:forEach items="${sysCfgCateList}" var="cfgInfo">
																<option value="${cfgInfo.code}">${cfgInfo.name}</option>
															</c:forEach>
														</c:if>
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<label class="control-label col-lg-3">说明</label>
												<div class="col-lg-6">
													<textarea name="description" class="form-control" rows="4"></textarea>
												</div>
											</div>
										</form>
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

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/zTree/ext/js/jquery.ztree.all.ext.js"></script>
    <script type="text/javascript" src="js/common/ztree.js"></script>
    <script type="text/javascript" src="js/admin/sysCfgTree.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
