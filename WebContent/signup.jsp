<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<title>ユーザー新規登録</title>
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

<form action="signup" method="post"><br />

	<label for = "loginId">ログインID</label>
	<input name = "loginId" value = "${user.loginId}" id ="loginId"/>半角英数字6文字以上20文字以下<br />


	<label for = "password">パスワード</label>
	<input name = "password" type = "password" id = "password"/>半角英数字6文字以上20文字以下 <br />

	<label for = "confirmation	">パスワード確認</label>
	<input name = "confirmation"  type ="password" id = "confirmation"/> <br />

<!--   passwordと入力されたconfirmationを比較して合っていなかったらエラー -->

	<label for="name">名前</label>
	<input name="name" value = "${user.name}" id="name"/>10文字以内 <br />



	<select name="branchId">
			<optgroup label="支店を選択"></optgroup>
			<c:forEach items="${branches}" var="branch">
				<option value="${branch.id }"
					<c:if test="${user.branchId==branch.id}">selected</c:if>><c:out
						value="${branch.name}" /></option>
			</c:forEach>
		</select>


		<select name="positionId">
			<optgroup label="役職を選択"></optgroup>
			<c:forEach items="${positions}" var="position">
				<option value="${position.id }"
					<c:if test="${user.positionId==position.id}">selected</c:if>>
					<c:out value="${position.name}" /></option>
			</c:forEach>
		</select> <br />



<br>

	    <input type="button" onclick="submit();" value="登録" />
 <br />
</form>

	<a href="userlist">戻る</a>

</body>

</html>