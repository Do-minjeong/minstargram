<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minstargrma-글쓰기</title>
<link href="/resources/css/main.css" rel="stylesheet">
<link href="/resources/dropzone/dropzone.css" rel="stylesheet">
<script src="/resources/dropzone/dropzone.js" ></script>
<%@ include file="../includes/header.jsp"%>
<%@ include file="includes/header.jsp"%>
</head>
<body>
글쓰기 페이지입니다. dropzone 사용
<form action="/file-upload"
      class="dropzone"
      id="my-awesome-dropzone">
</form>
</body>
</html>