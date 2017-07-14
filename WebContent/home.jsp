<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<script type="text/javascript">
	function stop() {

		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if (window.confirm('アカウントを停止しますか？')) {
			return true;
		} else {
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false;
		}
	}

	function working() {

		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if (window.confirm('アカウントを復活しますか？')) {
			return true;
		} else {
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false;
		}
	}

</script>
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




<a href="message">新規投稿</a>  <a href="login">ログイン</a>  <a href="userlist">ユーザー管理</a>
		<a href="logout">ログアウト</a> <br>

<c:out value="${user.name}"></c:out>


<br>
<form action="index" method="get">

	<select name="categories">
			<optgroup label="カテゴリーを選択"></optgroup>
			<c:forEach items="${categories}" var="category">
				<option value="${category}">${category}
				</option>
			</c:forEach>
		</select>


<label>日付 <input type="date" name="start"></label>

<br>
<label>日付 <input type="date" name="end"></label>
<input type="submit" value="検索">
</form>
<br>


		<c:forEach items="${userMessage}" var="message">
			<c:out value="[${message.title}]" />
			<c:out value="${message.category}" />
			<br>
			<c:out value="${message.text}" />
			<br>
			<c:out value="${message.name}" />
			<br>
			<c:out value="${message.createdAt}" />



			<c:if test="${user. positionId==2 || user.id==message.userId || user.positionId==3 && message.branchId==user.branchId}">
			<form action="dropmessage" method="post">
				<INPUT type="hidden" name="messageId" value="${message.messageId}">
				<input type="submit" value="投稿削除">
			</form>
			</c:if>


			<div class="comment-form">

				<form action="comment" method="post">

					<label for="comment">コメント</label><br> <input name="comment"
						value="${comments.text}" id="comment" />500字以内でコメントを入力<br />

					<!--裏でmessageIdの取得  -->

					<INPUT type="hidden" name="messageId" value="${message.messageId}">

					<br /> <input type="submit" value="投稿">

				</form>

			</div>


			<c:forEach items="${userComments}" var="comment">
				<c:if test="${comment.messageId == message.messageId}">

					<c:out value="${comment.text}" />
					<c:out value="${comment.userName}" />
					<br>
					<c:out value="${comment.createdAt}" />
					<br>



			<c:if test="${user.positionId==2}">
			<form action="dropcomment" method="post">
				<INPUT type="hidden" name="commentId" value="${comment.id}">
				 <input type="submit" value="コメント削除">

			</form>
			</c:if>
			</c:if>
		</c:forEach>



		</c:forEach>







	</div>
</body>
</html>