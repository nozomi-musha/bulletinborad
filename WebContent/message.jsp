<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<title>新規投稿</title>
</head>

<body>


<div class="menu">
<div align="center"><font size=15><c:out value="新規投稿"/></font></div></div>

<div align="left"><a href="./">ホーム</a></div>


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

<br>
<!-- 入力フォーム -->

<div class="message_form">

	<form action="message" method="post">



 <p><label for="title">タイトル </label>
 <br>
  <input name="title" value="${messages.title}" id="title"/> 30文字以内<br><br>
  <label for="category">カテゴリー</label>
  <br> <input name="category" value="${messages.category}" id="category" /> 10文字以内<br><br>
 <label for="text">テキスト </label><br>
 <textarea name="text" rows="20" cols="80" wrap="hard">${messages.text}</textarea> 1000文字以内

<br> <input type="button" onclick="submit();" class="submit_btn" value="投稿" /><br><br>
</p>
</form>
</div>


</body>
</html>

