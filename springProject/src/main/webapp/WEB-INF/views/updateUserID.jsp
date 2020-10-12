<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" href="/resources/home.css?Ass">
</head>
<body style="background: rgba(var(--b3f,250,250,250),1);">
<c:if test="${id_update==0}">
	<script type="text/javascript">
		alert("사용자id 변경에 실패하였습니다.");
	</script>
</c:if>
<div class="container">
	<div class="box displayCenter">
		<div class="displayCenter titlediv">
			<h1 class="titleText">MIN <span class="star text-yellow">★</span> GRAM</h1>
		</div>
		<div>
		</div>
		<form action="/updateUserID" method="post" >
			<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
			<input type="hidden" name="username" value="${username}">
			<div class="input-group">
				<input type="text" name="userid" id="userid" class="form-control" placeholder="사용자이름을 설정하세요." required>
			</div>
			<div class="input-group">
				<button type="submit" class="form-control btn btn-warning font20" id="submitBtn">확인</button>
			</div>
		</form>
	</div>

</div>

</body>
</html>
