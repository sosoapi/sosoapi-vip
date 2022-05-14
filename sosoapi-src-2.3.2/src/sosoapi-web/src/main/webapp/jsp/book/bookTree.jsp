<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>其他文档  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
	<link href="plugin/editormd/css/editormd.css" rel="stylesheet">
	
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
<body class="sticky-header sidebar-collapsed">
	<input type="hidden" id="docId" value="${param.docId}"/>
	<input type="hidden" id="projId" value="${param.projId}"/>
	<input type="hidden" id="bookId" value="${empty param.bookId ? bookInfo.id: param.bookId}"/>
	
	<!-- LEFT SECTION -->
    <jsp:include page="/jsp/project/projMenu.jsp" />
	<!-- END LEFT SECTION -->
		
	<!-- body content start-->
    <div class="body-content" >
    	<!-- TOP SECTION -->
	    <jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
  
  		<!--body wrapper start-->
        <div id="wrapper">
        	<div class="small-header transition animated">
				<div class="hpanel">
			    	<div class="panel-body" style="vertical-align: middle;">
			   			<div class="pull-left m-t-xs">
							<span></span>
						</div>
						
						<div class="pull-right">
							<a href="auth/book/preview/index.htm?projId=${param.projId}&bookId=${empty param.bookId ? bookInfo.id: param.bookId}" target="_bank" class="btn btn-sm btn-success">
								<i class="fa fa-eye"></i> 预览
							</a>
						    
							<!-- <div class="btn-group">
						        <button class="btn btn-sm btn-success">
						        	<i class="fa fa-share"></i> 导出
						        </button>
						        
						        <button data-toggle="dropdown" class="btn btn-sm btn-success dropdown-toggle"><span class="caret"></span></button>
						        <ul class="dropdown-menu">
						            <li>
						            	<a href="javascript:void(0);">
						            		<i class="fa fa-file-code-o"></i> html文档
						            	</a>
						            </li>
						            <li>
						            	<a href="javascript:void(0);">
						            		<i class="fa fa-file-word-o"></i> word文档
						            	</a>
						            </li>
						        </ul>
						    </div> -->
						</div>
					</div>
				</div>
			</div>              
        	
			<div id="mainContent" class="content animate-panel">
				<div class="row">
				    <div class="col-lg-3">
				        <div class="hpanel">
				            <div class="panel-heading hbuilt">
				                <div class="panel-tools">
				                	<a href="javascript:;" id="bookSettingBtn">
					                    <span class="fa fa-cog fa-2x"></span>
					               	</a>
					               	
				                    <a href="javascript:;" data-toggle="dropdown">
					                    <span class="fa fa-plus-circle fa-2x"></span>
					               	</a>
				                	<ul class="dropdown-menu">
				                		<li>
				                     		<a href="javascript:void(0);" id="addFolderBtn">
				                     			<i class="fa fa-folder"></i> 
			            						<span>新增文件夹</span>
				                     		</a>
				                     	</li>
				                     	<li class="divider"></li>
				                		
				                		<li>
				                     		<a href="javascript:void(0);" id="addFileBtn">
												<i class="fa fa-list-alt"></i> 
												<span>新增文件</span>
				                     		</a>
				                     	</li>
				                 	</ul>
				                </div>
				                <span>目录管理</span>
				                <a href="javascript:void(0);" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="目录可通过拖拽排版。" class="text-muted">
                                	<i class="fa fa-question-circle"></i>
                                </a>
				            </div>
				            <div class="panel-body">
			            		<div id="treeSearch" class="input-group">
	                            	<input id="condition" type="text" class="form-control" placeholder="">
	                            	<span class="input-group-btn">
	                                	<button id="searchBtn" type="button" class="btn btn-white">
	                                		<i class="fa fa-search"></i>
	                                	</button>
	                              	</span>
	                            </div>
				            	
								<div class="row">
									<ul id="bookTree" class="ztree"></ul>
								</div>
				            </div>
				        </div>
				    </div>
				    
				    <div class="col-lg-9">
				    	<jsp:include page="/jsp/book/tree/bookSetting.jsp"/>
				    	<jsp:include page="/jsp/book/tree/bookContent.jsp"/>
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
    <script type="text/javascript" src="plugin/editormd/editormd.js"></script>
	<script type="text/javascript" src="plugin/extend/jquery.changed.tip.js"></script>
    <script type="text/javascript" src="js/common/ztree.js?v=1.0.2"></script>
    <script type="text/javascript" src="js/book/bookTree.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
