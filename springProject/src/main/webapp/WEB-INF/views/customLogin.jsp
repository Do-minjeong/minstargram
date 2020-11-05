<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet"  href="/resources/css/home.css?Adfs">
<title>Login</title>
</head>
<body style="background: rgba(var(--b3f,250,250,250),1);">
<div class="container">
	<c:if test="${ not empty error || not empty logout || not empty signup }">
		<div class="box box2">
			<div class="signup displayCenter font20" >
				<span style="color: red;"><c:out value="${ error }" />
				<c:out value="${ logout }" /> </span>
				<span style="color: blue;"><c:out value="${ signup }" /></span>
			</div>
		</div>
	</c:if>
	<div class="box displayCenter">
		<div class="displayCenter titlediv">
			<h1 class="titleText">MIN <span class="star text-yellow">★</span> GRAM</h1>
		</div>
		<div>
		</div>
		<form action="/login" method="post" >
			<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
			<div class="input-group">
				<input type="text" name="username" id="username" class="form-control" placeholder="휴대폰번호 또는 이메일">
			</div>
			<div class="input-group">
				<input type="password" name="password" class="form-control" placeholder="비밀번호">
			</div>
			<div class="input-group">
				<button type="submit" class="form-control btn btn-warning font20" id="submitBtn">로그인</button>
			</div>
		</form>
		<div class="orLine displayCenter">
			<div class="line"></div><div class="or">또는</div><div class="line"></div>
		</div>
		<div id="naver_id_login" class="input-group displayCenter" >
			<a href="${url}" class="displayCenter iconType">
				<img alt="" src="https://t1.daumcdn.net/cfile/tistory/99117C3F5D04CEE519" width="20px"><span class="font20 naverIDLogin">네이버 아이디로 로그인</span>
			</a>
		</div>
		<div class="input-group displayCenter">
			<a href=""><span class="displayCenter" id="fgpw">비밀번호를 잊으셨나요?</span></a>
		</div>
	</div>
	<div class="box box2">
		<div class="signup displayCenter font20">계정이 없으십니까?<a href="/signup" class="text-yellow goSignup">가입하기</a></div>
	</div>
</div>

</body>
</html>
