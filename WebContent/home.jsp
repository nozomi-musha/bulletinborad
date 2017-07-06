<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
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
			<c:remove var="errorMessages" scope="session" />
		</c:if>



		<a href="">戻る</a> <a href="">新規投稿</a> <a href="">ユーザー管理</a> <a href="">ログアウト</a>
	<br>

		<c:forEach items="${userMessage}" var="userMessage">
			<c:out value="●${userMessage.title}" />
			<c:out value="●${userMessage.category}" /><br>
			<c:out value="${userMessage.text}" /><br>
			<c:out value="${userMessage.name}" /><br>
		<c:out value="${userMessage.createdAt}" />
</c:forEach>



		<div class="comment-form">

				<form action="newMessage" method="post">

	<label for = "comment">コメント</label>
	<textarea name="message" cols="100" rows="5" class="tweet-box"><c:out value="${comment.text}" /></textarea>



					500字以内でコメントを入力<br />



					<br /> <input type="submit" value="投稿">
				</form>

		</div>
	</div>