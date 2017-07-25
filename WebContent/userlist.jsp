<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<title>ユーザー管理</title>
<script type="text/javascript">
	function stop() {
		if (window.confirm('アカウントを停止しますか？')) {
			return true;
		} else {
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false;
		}
	}
	function working() {
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

	<div class="menu">
		<div align="center"><font size=15><c:out value="ユーザー管理"/></font></div>
		</div>
			<div align="left"> <a href="./">ホーム</a> <a href="signup">新規ユーザー登録</a>
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
	 <br>
	<table>
  		<tr>
    		<th class="t_top">支店</th>
    		<th class="t_top">役職</th>
    		<th class="t_top">名前</th>
    		<th class="t_top">ログインID</th>
   		 	<th class="t_top">編集</th>
    		<th class="t_top">アカウント</th>
  		</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td class="t_line01"><c:forEach items="${branches}"	 var="branch">
						<c:if test="${user.branchId == branch.id}">
							<c:out value="${branch.name}" />
						</c:if>
			</c:forEach>
					</td>
		<td class="t_line01">
			<c:forEach items="${positions}" var="position">
				<c:if test="${user.positionId == position.id}">
					<c:out value="${position.name}" />
				</c:if>
			</c:forEach>
		</td>
		<td class="t_line01"><c:out value="${user.name}" /></td>
		<td class="t_line01"><c:out value="${user.loginId}" /></td>
		<td class="t_line01">
			<form action="edit" method="get">
				<input type="hidden" name="userId" value="${user.id}"> <input type="submit" class="edit_btn" value="編集">
			</form></td>
		<td class="t_line01">
			<c:if test="${user.isStopped==0}">
				<c:if test="${user.positionId !=1 || user.id != loginUser.id}">
					<form action="isstopped" method="post" onSubmit="return stop()">
						<INPUT type="hidden" name="userId" value="${user.id}">
						<INPUT type="hidden" name="isStopped" value="${user.isStopped}">
							<p>
								<input type="submit" class="stop_btn" value="停止する">
							</p>
					</form>
				</c:if>
			</c:if>
			 <!-- 停止していたアカウントを復活 -->
			 <c:if test="${user.isStopped == 1}">
				<form action="isstopped" method="post" onSubmit="return working()">
					<INPUT type="hidden" name="userId" value="${user.id}">
					<INPUT type="hidden" name="isStopped" value="${user.isStopped}">
						<p>
							<input type="submit" class="cure_btn" value="復活する">
						</p>
				</form>
			</c:if>
		</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>