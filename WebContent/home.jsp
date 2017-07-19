<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<title>ホーム</title>

<script type="text/javascript">
	function editMessage() {

		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if (window.confirm('投稿を削除しますか？')) {
			return true;
		} else {
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false;
		}
	}

	function editComment() {

		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if (window.confirm('コメントを削除しますか？')) {
			return true;
		} else {
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false;
		}
	}

	function logout() {

		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if (window.confirm('コメントを削除しますか？')) {
			return true;
		} else {
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false;
		}
	}

</script>
</head>
<body>

	<div class="message">

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
</div>

	<div class="main-contents">


	<div class="menu">
			<c:out value="${user.name}" /> <a href="message">新規投稿</a> <a href="userlist">ユーザー管理</a> <a href="logout">ログアウト</a>
		</div>

		<br>

		<div class="search">
			<form action="index.jsp" method="get">

<label>カテゴリー</label>
	<select name="category">
			<optgroup label="カテゴリーを選択"></optgroup>
 			<option value=></option>
			<c:forEach items="${categories}" var="category">
				<option value="${category}" <c:if test= "${category == selectCategory}">selected</c:if>>${category}</option>
			</c:forEach>
		</select>


<label>日付 <input type="date" name="start" value="${selectStart}"></label>
<label> ～ <input type="date" name="end" value="${selectEnd}"></label>
<input type="submit" value="検索">
</form>




		</div>

		<br>


			<c:forEach items="${userMessage}" var="message">
			<div class="message">

				<div>

					<c:out value="${message.category}" />
					<c:out value="${message.title}" />
					<br>
					<c:out value="${message.text}" />
					<br>
					<c:out value="${message.createdAt}" />

										<c:out value="${user.name}" />


			<c:if test="${user.positionId==2 || user.id==message.userId || (user.positionId==3 && message.branchId==user.branchId)}">
						<form action="dropmessage" method="post" onSubmit="return editMessage()">
							<INPUT type="hidden" name="messageId"
								value="${message.messageId}"> <input type="submit"
								value="削除">
						</form>
					</c:if>
				</div>
				<div class="comment-form">
					<form action="comment" method="post">

						<label for="comment">コメント</label><br>
						<textarea name="comment" rows="4" cols="80">${comments.text}</textarea>
						<br>
						 500字以内でコメントを入力<br />



						<!--裏でmessageIdの取得  -->

						<INPUT type="hidden" name="messageId" value="${message.messageId}">

						<br /> <input type="button" onclick="submit();" value="投稿" />
					</form>
				</div>


				<div>
					<c:forEach items="${userComments}" var="comment">


						<c:if test="${comment.messageId == message.messageId}">

							<c:out value="${comment.text}" />
							<c:out value="${comment.userName}" />
							<br>
							<c:out value="${comment.createdAt}" />
							<br>

			<c:if test="${user.positionId==2 || user.id==comment.userId || (user.positionId==3 && comment.branchId==user.branchId)}">
								<form action="dropcomment" method="post" onSubmit="return editComment()">
									<INPUT type="hidden" name="commentId" value="${comment.id}">
									<input type="submit" value="削除">
								</form>
							</c:if>
						</c:if>
					</c:forEach>
				</div>
				</div>
			</c:forEach>

		</div>

</body>
</html>