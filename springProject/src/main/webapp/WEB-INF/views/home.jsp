<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
<title>Home</title>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" href="/resources/css/home.css?As">
</head>
<body style="background: rgba(var(--b3f,250,250,250),1);">
<div class="container">
	<div class="box displayCenter">
		<div class="displayCenter titlediv">
			<h1 class="titleText">MIN <span class="star text-yellow">★</span> GRAM</h1>
		</div>
		<div class="userInfo"></div>
		<div>
			<form action="/" method="POST" id="logoutForm">
				<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
				<input type="submit" class="btn btn-lg btn-success" value="Logout"/>
			</form>
			<a href="/main/mainHome">홈가기</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	console.log("hello");
</script>
<c:if test="${ g_userInfo ne null }">
	<script type="text/javascript">
		console.log("custom");
		$("#logoutForm").attr("action","/customLogout");
		var ap = '${g_userInfo.username}';
	</script> 
</c:if>
<c:if test="${ s_userInfo ne null }">
	<script type="text/javascript">
		console.log("s_userInfo")
		$("#logoutForm").attr("action","/socialLogout");
		var ap = '${ s_userInfo.username}';
	</script>
</c:if>
<script type="text/javascript">
		$(".userInfo").append("<span>"+ap+" 님</span>");
</script>
</body>
</html>
