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



<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="profile-image">
			<img src="./icon?user_id=${loginUser.id}" width="100" height="100" />
		</div>
		<div class="name"><h2><c:out value="${loginUser.name}" /></h2></div>
		<div class="account">
			@<c:out value="${loginUser.account}" />
		</div>
		<div class="account">
			<c:out value="${loginUser.description}" />
		</div>
	</div>
</c:if>

<a href="">戻る</a>
<a href="">新規投稿</a>
<a href="">ユーザー管理</a>
<a href="">新規投稿</a>
<a href="">ログアウト</a>

<div class="comment-form">
	<c:if test="${ isShowMessageForm }">
		<form action="newMessage" method="post">
			500字以内でコメントを入力<br />
			<textarea name="message" cols="100" rows="5" class="tweet-box"></textarea>
			<br />
			<input type="submit" value="投稿">
		</form>
	</c:if>
</div>
</div>