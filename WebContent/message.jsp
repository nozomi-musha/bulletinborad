<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
</head>

<body>

<div class="main-contents">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>

	</c:if>
</div>



<form action="message" method="post"><br />

	<label for = "title">タイトル</label>
	<input name = "title" value = "${title}" id ="title"/><br />

		<label for = "text">テキスト</label>
	<input name = "text" value = "${text}" id ="text"/><br />

			<label for = "">カテゴリー</label>
	<input name = "category" value = "${category}" id ="category"/><br />
<br>

	<input type="submit" value="登録" /> <br />

</form>
</body>
</html>