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
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<script type="text/javascript">
		//console.log("custom");
		$("#logoutForm").attr("action","/customLogout");
		var ap = '<span><sec:authentication property="principal.username"/> 님</span>';
	</script> 
</sec:authorize>
<c:if test="${apiResult ne null}">
	<script type="text/javascript">
		//console.log(${apiResult}.response.name);
		$("#logoutForm").attr("action","/socialLogout");
		var ap = "<span>"+${apiResult}.response.email+" 님</span>";
	</script>
</c:if>
<script type="text/javascript">
		$(".userInfo").append(ap);
</script>
</body>
</html>
