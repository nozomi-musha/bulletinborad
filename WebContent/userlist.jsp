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

<title>TAG index Webサイト</title>

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
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>

	</c:if>
</div>

	<div class="main-contents">

<a href="signup">新規ユーザー登録</a>


		<br>

		<c:forEach items="${users}" var="user">
			<c:out value="${user.name}" />
			<br>
			<c:out value="${user.loginId}" />

			<c:forEach items="${branches}" var="branch">
				<c:if test="${user.branchId == branch.id}">
					<c:out value="${branch.name}" />
				</c:if>
			</c:forEach>


			<br>
			<c:forEach items="${positions}" var="position">
				<c:if test="${user.positionId == position.id}">
					<c:out value="${position.name}" />
				</c:if>
			</c:forEach>


			<p>
			<form action="edit" method="get">
				<INPUT type="hidden" name="userId" value="${user.id}"> <input
					type="submit" value="編集">
			</form>
			<body>

				<!-- アカウントの停止 -->
				<c:if test="${user.isStopped==0}">

					<form action="isstopped" method="post" onSubmit="return stop()">

						<INPUT type="hidden" name="userId" value="${user.id}"> <INPUT
							type="hidden" name="isStopped" value="${user.isStopped}">
						<p>
							<input type="submit" value="停止">
						</p>

					</form>
				</c:if>


				<!-- 停止していたアカウントを復活 -->
				<c:if test="${user.isStopped == 1}">
					<form action="isstopped" method="post" onSubmit="return working()">
						<INPUT type="hidden" name="userId" value="${user.id}"> <INPUT
							type="hidden" name="isStopped" value="${user.isStopped}">
						<p>
							<input type="submit" value="復活">
						</p>

					</form>

				</c:if>

			</body>
		</c:forEach>

	</div>
</body>
</html>