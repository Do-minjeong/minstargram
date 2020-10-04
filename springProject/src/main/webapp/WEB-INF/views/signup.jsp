<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" href="/resources/home.css?Asaf">
</head>
<body style="background: rgba(var(--b3f,250,250,250),1);">
<div class="container">
	<div class="box displayCenter">
		<div class="displayCenter titlediv">
			<h1 class="titleText">MIN <span class="star text-yellow">★</span> GRAM</h1>
		</div>
		<div id="naver_id_login" class="input-group" >
			<a href="${url}" class="displayCenter completeType">
				<img alt="" src="/resources/images/naver_ID_login_icon_TYPE.png" height="40px"><span class="naverLoginText">네이버 아이디로 로그인</span>
			</a>
		</div>
		<div class="orLine displayCenter">
				<div class="line"></div><div class="or">또는</div><div class="line"></div>
		</div>
		<form action="/signup" method="post" class="signupForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="input-group signupInput">
				<label class="signupLabel">
					<span>휴대폰 번호 또는 이메일 주소</span>
					<input type="text" name="username" id="username" placeholder="휴대폰 번호 또는 이메일 주소" required>
				</label>
				<div class="imageDisplay displayCenter">
					<!-- <div class="signupImage inputError"></div>
					<div class="signupImage inputSuccess"></div> -->
				</div>
			</div>
			<div class="input-group signupInput">
				<label class="signupLabel">
					<span>성명</span>
					<input type="text" name="name"  placeholder="성명">
				</label>
				<div class="imageDisplay displayCenter">
					<!-- INPUT 유효성 검사 후 SPAN태그 추가해서 이미지 삽입 -->
				</div>
			</div>
			<div class="input-group signupInput">
				<label class="signupLabel">
					<span>사용자이름(ID)</span>
					<input type="text" name="userid"  placeholder="사용자이름(ID)" required>				
				</label>
				<div class="imageDisplay displayCenter">
					<!-- INPUT 유효성 검사 후 SPAN태그 추가해서 이미지 삽입 -->
				</div>
			</div>
			<div class="input-group signupInput">
				<label class="signupLabel notLabel">
					<span>비밀번호</span>				
					<input type="password" name="userpwd" placeholder="비밀번호" required>
				</label>
				<div class="imageDisplay displayCenter">
					<!-- INPUT 유효성 검사 후 SPAN태그 추가해서 이미지 삽입 -->
					<div>
						<button type="button" class="pwBtn">비밀번호표시</button>
					</div>
				</div>
			</div>
			<div class="input-group">
				<button type="submit" class="form-control btn btn-warning font20" id="submitBtn" disabled>가입</button>
			</div>
			<div class="input-group signupBottomtext">
				<span class="regexError" style="color: red;"></span>
			</div>
			<div class="input-group signupBottomtext">
				<span>가입하면 Minstargram의 <b>약관</b>, <b>데이터 정책</b> 및<br><b>쿠키 정책</b>에 동의하게 됩니다.</span>
			</div>
		</form>
	</div>
	<div class="box box2">
		<div class="signup displayCenter font20">계정이 있으십니까?<a href="/" class="text-yellow goSignup">로그인하기</a></div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/signup.js?asa"></script>
</body>
</html>