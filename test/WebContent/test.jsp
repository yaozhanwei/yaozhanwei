<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="hao" uri="/hao-tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"> 
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible">
</head>
<!-- 新闻列表 -->
<body>
<hao:listOut record="10" url="/newsList"></hao:listOut>
</body>