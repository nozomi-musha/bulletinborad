<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<form action="signup" method="post"><br />

	<label for="login_id">ID</label>
	<input name="login_id" value="${login_id}" id="login_id"/><br />


	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />


	<label for="name">名前</label>
	<input name="namel" id="name"/> <br />


		<label for="branch_id">支店</label>
	<input name="branch_id" id="branch_id"/> <br />

		<label for= "position_id">役職</label>
	<input name="position_id" id="position_id"/> <br />

		<label for="name">名前</label>
	<input name="namel" id="name"/> <br />

 <select name="is_stopped">
    <option>編集可</option>
    <option>編集不可</option>
</select>

<br>

	<input type="submit" value="登録" /> <br />


</body>
</html>