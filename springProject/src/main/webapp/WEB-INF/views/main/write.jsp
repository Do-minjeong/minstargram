<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minstargrma-글쓰기</title>
<link href="/resources/css/main.css?as" rel="stylesheet">
<link href="/resources/dropzone/dropzone.css" rel="stylesheet">
<script src="/resources/dropzone/dropzone.js" ></script>
<%@ include file="../includes/header.jsp"%>
<%@ include file="includes/header.jsp"%>
</head>
<body>
<div class="total-block writeblock">
	<div id="right-block ">
		<div class="post-input">
			<div class="list-style"></div>
			<label class="write-label">사진선택</label>
		</div>
		<form enctype="multipart/form-data" action="/main/write" class="dropzone"	id="uploader" style="background-color: whitesmoke;">
			<div class="dz-message" data-dz-message>
				<span>드래그 드랍하거나 클릭하여 이미지 선택</span>
			</div>
			<div class="fallback">
				<input name="file" type="file" multiple />
			</div>
		</form>
			<input type="hidden" name="${ _csrf.parameterName }" value="${_csrf.token}" id="csrf_token" >
		<div class="form-group">
			<div class="post-input">
				<div class="list-style"></div>
				<label class="write-label">아래에 글을 입력해주세요.</label>
			</div>
			<textarea class="form-control rounded-0" id="contents" rows="10"></textarea>
		</div>
		<div style="text-align: right;">
			<button type="button" class="btn btn-warning" id="upload">작성하기</button>
		</div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/write.js"></script>
</body>
</html>