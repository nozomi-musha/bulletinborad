<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

</script>

</head>
<body>


<h2><font size=30>わったい菜</font></h2>





<Div Align="left">
			<font size=5><c:out value="${user.name}さん         " /></font>
			<a href="message">新規投稿</a>
			<c:if test="${user.positionId==1 }">
				<a href="userlist">ユーザー管理</a>
			</c:if>

			<a href="logout">ログアウト</a>
			</Div>

		<br>
<br>
<br>

		<div class="search">
			<form action="index.jsp" method="get">

				<label>カテゴリー</label> <select name="category">
					<optgroup label="カテゴリーを選択"></optgroup>
					<option value=></option>
					<c:forEach items="${categories}" var="category">
						<option value ="${category}"
							<c:if test= "${category == selectCategory}">selected</c:if>>${category}</option>
					</c:forEach>
				</select>
				<br><br>
				<label>日付 <input type="date" name="start"
					value="${selectStart}"></label> <label> ～ <input
					type="date" name="end" value="${selectEnd}"></label> <input
					type="submit" value="検索">
			</form>
		</div>

			<div class="main-contents">
		<c:if test="${ not empty errorMessages }">
					<c:forEach items="${errorMessages}" var="message">
						<Div Align="center">
						<c:out value="${message}" />
						</Div>
					</c:forEach>
			<c:remove var="errorMessages" scope="session" />
		</c:if>



	</div>



		<br>
					<c:forEach items="${userMessage}" var="message">



			<div class="message"> <!-- メッセージ、コメントの大枠 -->
				<div>

<div class="one_message">


			<div class="menu">
					<font color=#0000a0 size =5>【<c:out value="${message.category}" />】</font> <font size=5><c:out value="${message.title}" /></font></div>
					<br>

					<c:forEach
						items="${fn:split(message.text, '
                                                    ') }"
						var="text">
						<c:out value="${text}"></c:out>
							<br>
					</c:forEach>

					<Div Align="right">
						<c:forEach items="${users}" var="user">
							<c:if test="${message.userId==user.id}">
								<c:out value="投稿者: ${user.name}" />
							</c:if>
						</c:forEach>

					  <fmt:formatDate value="${message.createdAt}" pattern="yyyy/MM/dd HH:mm" /><br>

						<c:if test="${user.positionId==2 || user.id==message.userId || (user.positionId==3 && message.branchId==user.branchId)}">
							<form action="dropmessage" method="post" onSubmit="return editMessage()">
								<INPUT type="hidden" name="messageId"value="${message.messageId}"> <input type="submit" class="stop_btn" value="削除">
							</form>
						</c:if>
					</Div>

					<form action="comment" method="post">
					<Div Align="center">
					<label for="comment">コメント(500字以内)</label></Div>
					<Div Align="center">
					<textarea name="comment" rows="4" cols="75" wrap="hard"><c:if test="${userComment.messageId == message.messageId}">${userComment.text}</c:if></textarea>

					</Div>


						<!--裏でmessageIdの取得  -->
					<Div Align="center">
						<INPUT type="hidden" name="messageId" value="${message.messageId}">
						 <input type="button" onclick="submit();" class="cure_btn" value="投稿" />
						 </Div>
					</form>

				</div>
</div>
					<c:forEach items="${userComments}" var="comment">




						<c:if test="${comment.messageId == message.messageId}">
							<div class="comment">
							<c:forEach
								items="${fn:split(comment.text, '
                                                    ') }"
								var="str">
								<c:out value="${str}"></c:out>
								<br>
							</c:forEach>

						<Div Align="right"><c:out value="投稿者: ${comment.userName}" />   <fmt:formatDate value="${comment.createdAt}" pattern="yyyy/MM/dd HH:mm" />
							<c:if
								test="${user.positionId==2 || user.id==comment.userId || user.positionId==3 && comment.branchId==user.branchId}">
								<form action="dropcomment" method="post"
									onSubmit="return editComment()">
									<INPUT type="hidden" name="commentId" value="${comment.id}">
									<input type="submit" class="stop_btn" value="削除">
								</form>
							</c:if>
							</Div>
							</div>
						</c:if>

					</c:forEach>

				</div>
		</c:forEach>
		<c:remove var="userComment" scope="session" />

</body>
</html>