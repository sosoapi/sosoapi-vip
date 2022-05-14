<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>错误提示  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
</head>
<body class="blank">
	<div class="error-container">
	    <i class="fa fa-frown-o text-success big-icon"></i>
	    <h1>Error !</h1>
	    <p>${errorMsg}</p>
	    <a href="" class="btn btn-xs btn-success">Return Website</a>
	</div>
</body>
</html>
