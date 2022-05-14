<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<title>预览  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="${Cfg.WEB_PAGE_META_DESCRIPTION}">
	<meta name="Keywords" content="${Cfg.WEB_PAGE_META_KEYWORDS}">
	<c:if test="${Cfg.PROD_FLAG}">
	<meta name="robots" content="none,noarchive">
	</c:if>

	<link rel="shortcut icon" href="favicon.ico">

	<c:choose>
		<c:when test="${Cfg.WEB_CDN_ENABLE}">
			<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
			<link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
			<link href="css/theme/theme.css" rel="stylesheet">
			<link href="css/theme/component.css?v=1.1.2" rel="stylesheet">
			<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
			<link href="plugin/editormd/css/editormd.preview.css" rel="stylesheet">
		</c:when>
		
		<c:otherwise>
			<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
			<link href="plugin/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
			<link href="css/theme/theme.css" rel="stylesheet">
			<link href="css/theme/component.css?v=1.1.2" rel="stylesheet">
			<link href="plugin/zTree/ext/css/ztree.ext.css" rel="stylesheet">
			<link href="plugin/editormd/css/editormd.preview.css" rel="stylesheet">
		</c:otherwise>
	</c:choose>

	<!-- PAGE LEVEL STYLES -->
	<style type="text/css">
		.sidebar-left {
			background: #fff;
			border-right: 1px solid #e4e5e7;
		}
		
		.sidebar-left-info {
			padding-left:20px;
		}
		
		.sidebar-collapsed .body-content {
		    margin-left: 0;
		}
		
		.sticky-header.sidebar-collapsed .header-section {
		    left: 0;
		}
		
		.sidebar-collapsed .chapter span,
		.sidebar-collapsed .sidebar-left {
		    display: none;
		}
		
		.sidebar-collapsed .chapter {
			border-bottom: 0;
			padding-left: 0;
		}
		
		.sidebar-collapsed .chapter .toggle-btn{
			float:left;
		}
		
		.sticky-header .header-section {
		    position: absolute;
		    left: 0;
		    height: 60px;
		    
		    width:0;
		}

		.sticky-header .chapter {
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 240px;
		    z-index: 100;
		}
		
		.chapter {
		    height: 60px;
		    line-height: 60px;
		    position: absolute;
		    top: 0;
		    left: 0;
		    width: 240px;
		    z-index: 100;
		    border-bottom: 1px solid #e4e5e7;
		    padding-left:10px;
		}

		.chapter .toggle-btn{
			float: right;
			border-right: 1px solid #e4e5e7;
		}
		
		.book-title {
			font-size: 18px;
			color: #6a6c6f;
			padding-top: 20px;
			padding-left: 100px;
		}
		
		#wrapper {
			padding-left: 14px;
			top: -85px;
		}
		
		.content-info .blog-article-box .panel-heading {
			padding: 20px 10px;
			height: 60px;
			text-align: left;
		}
		
		.content-info .blog-article-box .panel-heading span {
		    font-size: 18px;
		    font-weight: 550;
		    color: #515355;
		}
		
		.editormd-html-preview {
             width: 90%;
             margin: 0 auto;
         }
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<input type="hidden" id="bookId" value="${param.bookId}"/>
	
	<!-- LEFT SECTION -->
	<div class="sidebar-left sticky-sidebar">
	    <div class="sidebar-left-info">
	    	<div class="row">
				<ul id="bookTree" class="ztree"></ul>
			</div>
	    </div>
	</div>
	<!-- END LEFT SECTION -->
	
	<div class="body-content">
		<!-- TOP SECTION -->
		<div class="header-section">
			<div class="chapter">
				<span>目录</span>
				<a class="toggle-btn">
			    	<i class="fa fa-outdent"></i>
			    </a>
		    </div>
    
		    <!-- <div class="book-title">
		    	<p>left notification start</p>
		    </div> -->
		</div>
	    <!-- END TOP SECTION -->
    
  		<!--body wrapper start-->
        <div id="wrapper">
        	<div class="content animate-panel">
        		<div class="row content-info" style="display: none;">
        			<div class="col-lg-12">
            			<div class="hpanel blog-article-box">
		                    <div class="panel-heading">
		                        <span id="chapterTitle"></span>
		                    </div>
		                    <div class="panel-body">
		                        <div id="markdownContentWrap">
		                        </div>
		                        
		                        <div id="richTextContentWrap">
		                        </div>
		                    </div>
		                    <!-- <div class="panel-footer">
				                <span class="pull-right">
				                    <i class="fa fa-comments-o"> </i> 22 comments
				                </span>
				               	<i class="fa fa-eye"> </i> 142 views
				          	</div> -->
		                </div>
				   	</div>
				</div>
				
        		<div class="row book-info">
        			<div class="col-lg-12">
            			<div class="hpanel blog-article-box">
		                    <div class="panel-heading">
		                        <h4>${bookInfo.title} </h4>
		                        <small>${bookInfo.tag}</small>
		                        <div class="text-muted small">
		                            
		                        </div>
		                    </div>
		                    <div class="panel-body">
		                        ${bookInfo.brief}
		                    </div>
		                    <!-- <div class="panel-footer">
				                <span class="pull-right">
				                    <i class="fa fa-comments-o"> </i> 22 comments
				                </span>
				               	<i class="fa fa-eye"> </i> 142 views
				          	</div> -->
		                </div>
				   	</div>
				</div>
        	</div>
        </div>
    </div>
    
    <!-- FOOTER SECTION -->
    <%-- <jsp:include page="/jsp/common/footer.jsp" /> --%>
    <input type="hidden" id="sysReqToken" value="${Cfg.SYS_REQ_TOKEN}"></input>
	<input type="hidden" id="copyFlag" value="${Cfg.COPY_FLAG}"></input>
	
	<c:choose>
		<c:when test="${Cfg.WEB_CDN_ENABLE}">
			<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
			<!--[if lt IE 9]>
			   <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			   <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
			<![endif]-->
			    
			<script src="//cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>
			<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
			
			<script src="plugin/zTree/ext/js/jquery.ztree.all.ext.js" type="text/javascript"></script>
			<script src="plugin/editormd/lib/marked.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/prettify.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/raphael.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/underscore.min.js" type="text/javascript"></script>
	        <!-- <script src="plugin/editormd/lib/sequence-diagram.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/flowchart.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/jquery.flowchart.min.js" type="text/javascript"></script> -->
	        <script src="plugin/editormd//editormd.js" type="text/javascript"></script>
				
			<script type="text/javascript" src="js/common/theme.js?v=1.0.1"></script>
			<script type="text/javascript" src="js/common/common.js?v=1.0.1"></script>
			<script type="text/javascript" src="js/common/ztree.js?v=1.0.2"></script>
			<script type="text/javascript" src="js/book/bookPreview.js"></script>
			
			<!-- cnzz统计 -->
			<script type="text/javascript">
			var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1263206566'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1263206566%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
			$("#cnzz_stat_icon_1263206566").hide();
			</script>
							
			<!-- baidu统计 -->
			<script>
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "https://hm.baidu.com/hm.js?f2d73569c8bccbec048875092fe0591f";
			  var s = document.getElementsByTagName("script")[0]; 
			  s.parentNode.insertBefore(hm, s);
			})();
			</script>
		</c:when>
		
		<c:otherwise>
			<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
			<!--[if lt IE 9]>
			   <script type="text/javascript" src="plugin/html5shiv.js"></script>
			   <script type="text/javascript" src="plugin/respond.min.js"></script>
			<![endif]-->
			    
			<script type="text/javascript" src="plugin/jquery-2.0.3.min.js"></script>
			<script type="text/javascript" src="plugin/bootstrap/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="plugin/zTree/ext/js/jquery.ztree.all.ext.js"></script>
			<script src="plugin/zTree/ext/js/jquery.ztree.all.ext.js" type="text/javascript"></script>
			<script src="plugin/editormd/lib/marked.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/prettify.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/raphael.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/underscore.min.js" type="text/javascript"></script>
	        <!-- <script src="plugin/editormd/lib/sequence-diagram.min.js" type="text/javascript"></script>
	       	<script src="plugin/editormd/lib/flowchart.min.js" type="text/javascript"></script>
	        <script src="plugin/editormd/lib/jquery.flowchart.min.js" type="text/javascript"></script> -->
	        <script src="plugin/editormd//editormd.js" type="text/javascript"></script>
	        
			<script type="text/javascript" src="js/common/theme.js?v=1.0.1"></script>
			<script type="text/javascript" src="js/common/common.js?v=1.0.2"></script>
    		<script type="text/javascript" src="js/common/ztree.js?v=1.0.2"></script>
			<script type="text/javascript" src="js/book/bookPreview.js"></script>
		</c:otherwise>
	</c:choose>
    <!-- END FOOTER SECTION -->
</body>
</html>
