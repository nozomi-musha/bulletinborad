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
<a href="./">戻る</a>

<!-- 入力フォーム -->

	<form action="message" method="post">


 <label for="title">タイトル </label>  <input name="title" value="${messages.title}" id="title"/> 30文字以内<br>
  <label for="category">カテゴリー</label>  <input name="category" value="${messages.category}" id="category" /> 10文字以内<br>
 <label for="text">テキスト </label>
 <textarea name="text" rows="4" cols="80" id="text">${messages.text}</textarea><br> 1000文字以内
<br> <input type="button" onclick="submit();" value="投稿" />
</form>



<!--  	<form action="message" method="post">
		<br /> <label for="title">タイトル </label>
		<input name="title" value="${title}" id="title" /><br> <br>

			<label for="category">カテゴリー</label>
		<input name="category" value="${category}" id="category" /> <br><br>


		<label for="text">テキスト </label>
		<textarea name="text" rows="4" cols="40" id="text">${text}</textarea>
		<br> <input type="button" onclick="submit();" value="送信" />

	</form> -->

</body>
</html>