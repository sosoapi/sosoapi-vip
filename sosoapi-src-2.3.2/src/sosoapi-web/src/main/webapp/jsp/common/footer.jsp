<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<script src="//cdn.bootcss.com/bootstrap-hover-dropdown/2.0.10/bootstrap-hover-dropdown.min.js"></script>
		<script src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
		<script src="//cdn.bootcss.com/jquery.form/3.51/jquery.form.min.js"></script>
		<script src="//cdn.bootcss.com/chosen/1.4.2/chosen.jquery.min.js"></script>
		<script src="//cdn.bootcss.com/summernote/0.6.16/summernote.min.js"></script>
		<script src="//cdn.bootcss.com/jquery-jgrowl/1.4.3/jquery.jgrowl.min.js"></script>
		<script src="//cdn.bootcss.com/jquery_lazyload/1.9.7/jquery.lazyload.min.js"></script>
		
		<script type="text/javascript" src="plugin/summernote/lang/summernote-zh-CN.js"></script>
		<script type="text/javascript" src="plugin/summernote/plugin/summernote-ext-preview.js"></script>
		<script type="text/javascript" src="plugin/bootbox/ext/js/bootbox.ext.js"></script>
		<script type="text/javascript" src="plugin/spin/spin.min.js"></script>
		<script type="text/javascript" src="plugin/modernizr.min.js"></script>
		
		<script type="text/javascript" src="js/common/theme.js?v=1.0.1"></script>
		<script type="text/javascript" src="js/common/common.js?v=1.0.1"></script>
		<script type="text/javascript" src="js/common/setting.js?v=1.0.2"></script>
		<script type="text/javascript" src="js/common/validate.js?v=1.0.0"></script>
		
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
		<script type="text/javascript" src="plugin/bootstrap/js/bootstrap-hover-dropdown.min.js"></script>
		<script type="text/javascript" src="plugin/validator/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="plugin/form/js/jquery.form.js"></script>
		<script type="text/javascript" src="plugin/chosen/chosen.jquery.min.js"></script>
		<script type="text/javascript" src="plugin/summernote/js/summernote.min.js"></script>
		<script type="text/javascript" src="plugin/jGrowl/js/jquery.jgrowl.min.js"></script>
		<script type="text/javascript" src="plugin/lazyload/js/jquery.lazyload.min.js"></script>
		
		<script type="text/javascript" src="plugin/summernote/lang/summernote-zh-CN.js"></script>
		<script type="text/javascript" src="plugin/summernote/plugin/summernote-ext-preview.js"></script>
		<script type="text/javascript" src="plugin/bootbox/ext/js/bootbox.ext.js"></script>
		<script type="text/javascript" src="plugin/spin/spin.min.js"></script>
		<script type="text/javascript" src="plugin/modernizr.min.js"></script>
		
		<script type="text/javascript" src="js/common/theme.js?v=1.0.1"></script>
		<script type="text/javascript" src="js/common/common.js?v=1.0.2"></script>
		<script type="text/javascript" src="js/common/setting.js?v=1.0.2"></script>
		<script type="text/javascript" src="js/common/validate.js?v=1.0.0"></script>
	</c:otherwise>
</c:choose>





