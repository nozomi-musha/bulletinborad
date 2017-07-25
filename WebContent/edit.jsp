<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<title>ユーザー編集</title>
<script type="text/javascript">
	function edit_btn() {
		if (window.confirm('この内容で登録しますか？')) {
			return true;
		} else {
			window.alert('キャンセルされました');
			return false;
		}
	}
</script>
</head>
<body>
	<div class="menu">
		<div align="center"><font size=15><c:out value="ユーザー編集"/></font>
		</div>
	</div>

	<div align="left"><a href="./">ホーム</a> <a href="userlist">ユーザー管理</a>
	</div>

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
	</div>

	<form action="edit" method="post" onSubmit="return edit_btn()">
		<br />
			<INPUT type="hidden" name="userId" value="${user.id}">
				<table class="type03">
					<tr>
						<th scope="row"><label for="loginId">ログインID</label></th>
							<td><input name="loginId" value="${user.loginId}" id="loginId" /> 半角英数字6文字以上20文字以下</td>
					</tr>
					<tr>
						<th scope="row"><label for="password">パスワード</label></th>
							<td><input name="password" type="password" id="password" /> 半角英数字6文字以上20文字以下</td>
					</tr>
					<tr>
						<th scope="row"><label for="confirmation"> パスワード確認</label></th>
							<td><input name="confirmation" type="password" id="confirmation" /></td>
			        </tr>
					<tr>
						<th scope="row"><label for="name">名前</label></th>
							<td><input name="name" value="${user.name}" id="name" /> 10文字以内</td>
					</tr>
						<c:if test="${user.positionId !=1 || user.id != loginUser.id}">
					<tr>
						<th scope="row">支店
						</th>
							<td>
								<select name="branchId">
									<optgroup label="支店を選択">
										</optgroup>
											<c:forEach items="${branches}" var="branch">
												<option value="${branch.id }"
													<c:if test="${user.branchId==branch.id}">selected
													</c:if>>
														<c:out value="${branch.name}" /></option>
											</c:forEach>
								</select>
							</td>
						</tr>
					<tr>
						<th scope="row">役職
					</th>
					<td>
						<select name="positionId">
							<optgroup label="役職を選択"></optgroup>
							<c:forEach items="${positions}" var="position">
								<option value="${position.id }"
									<c:if test="${user.positionId==position.id}">selected</c:if>>
										<c:out value="${position.name}" /></option>
							</c:forEach>
						</select>
					</td>
					</tr>
						</c:if>
				</table>
				<br />
				<div align="center">
					<input type="button" onclick="submit();" class="submit_btn"  value="編集" />
				</div>
	</form>
</body>