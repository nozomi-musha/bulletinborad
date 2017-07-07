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
						<li><c:out value="${message}" /></li>
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>



		<a href="">戻る</a> <a href="message">新規投稿</a> <a href="userlist">ユーザー管理</a> <a href="">ログイン</a>
		<a href="">ログアウト</a> <br>

		<c:forEach items="${userMessage}" var="message">
			<c:out value="[${message.title}]" />
			<c:out value="${message.category}" />
			<br>
			<c:out value="${message.text}" />
			<br>
			<c:out value="${message.name}" />
			<br>
			<c:out value="${message.createdAt}" />


			<c:forEach items="${userComments}" var="comment">
				<c:if test="${comment.messageId == message.messageId}">
					<c:out value="${comment.text}" />
					<c:out value="${comment.userName}" />
					<br>
					<c:out value="${comment.createdAt}" />
					<br>
				</c:if>
			</c:forEach>


			<div class="comment-form">

				<form action="comment" method="post">

					<label for="comment">コメント</label> <input name="comment"
						value="${comments.text}" id="comment" /> 500字以内でコメントを入力<br />




					<!--裏でmessageIdの取得  -->

					<INPUT type="hidden" name="messageId" value="${message.messageId}">



					<br /> <input type="submit" value="投稿">

				</form>

			</div>
		</c:forEach>
	</div>
</body>
</html>