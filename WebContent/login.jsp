<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="menu">
<div align="center"><font size=15><c:out value="ログイン"/></font></div></div>


<div class="message_center">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
				<c:forEach items="${errorMessages}" var="message">
					<p class=center><c:out value="${message}" /></p>
				</c:forEach>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
</div>

<div class=center>
<form action="login" method="post"><br />
	<label for="login_id">ログインID</label><br>
	<input name="login_id" id="login_id"/> <br />

	<label for="password">パスワード</label><br>
	<input name="password" type="password" id="password"/> <br /><br>

	<input type="submit" class="submit_btn" value="ログイン" /> <br />
</form>
</div>
</body>
</html>
