<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.sosoapi.com/tags" prefix="sosoapi"%>
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
		<link href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/chosen/1.4.2/chosen.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/summernote/0.6.16/summernote.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/jquery-jgrowl/1.4.3/jquery.jgrowl.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/animate.css/3.5.2/animate.min.css" rel="stylesheet">
		
		<link href="css/theme/theme.css" rel="stylesheet">
		<link href="css/theme/component.css?v=1.1.2" rel="stylesheet">
	</c:when>
	
	<c:otherwise>
		<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="plugin/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
		<link href="plugin/validator/css/bootstrapValidator.min.css" rel="stylesheet">
		<link href="plugin/chosen/chosen.min.css" rel="stylesheet"/>
		<link href="plugin/summernote/css/summernote.css" rel="stylesheet" />
		<link href="plugin/jGrowl/css/jquery.jgrowl.min.css" rel="stylesheet" />
		<link href="plugin/animate/css/animate.css" rel="stylesheet">
		
		<link href="css/theme/theme.css" rel="stylesheet">
		<link href="css/theme/component.css?v=1.1.2" rel="stylesheet">
	</c:otherwise>
</c:choose>