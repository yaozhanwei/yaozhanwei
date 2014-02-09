<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	登录失败<br>
	${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
</div>
<form method="post" action="${pageContext.request.contextPath }/j_spring_security_check" style="width:260px;text-align:center;">
	<fieldset>
		<legend>登录</legend>
		用户:<input name="j_username" style="width:150px;" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME'] }"/><br>
		密码:<input name="j_password" style="width:150px;" /><br>
		<input type="checkbox" id="_spring_security_remember_me"/>两周之内不必登录<br>
		<input value="登录" type="submit"/>
		<input value="重置"/>
	</fieldset>
</form>
</body>
</html>