<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" href="/resources/css/home.css?Ass">
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
		<form action="/updateUserID" method="post" id="updateIdForm">
			<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
			<input type="hidden" name="username" value="${username}">
			<div class="input-group signupInput">
				<label class="signupLabel">
					<span>사용자이름(ID)</span>
					<input type="text" name="userid"  placeholder="사용자이름(ID)" required>				
				</label>
				<div class="imageDisplay displayCenter">
					<!-- INPUT 유효성 검사 후 SPAN태그 추가해서 이미지 삽입 -->
				</div>
			</div>
			<div class="input-group">
				<button type="submit" class="form-control btn btn-warning font20" id="submitBtn" disabled>확인</button>
			</div>
		</form>
		<div class="input-group signupBottomtext">
				<span class="regexError" style="color: red;"></span>
			</div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/signup.js"></script>
</body>
</html>
