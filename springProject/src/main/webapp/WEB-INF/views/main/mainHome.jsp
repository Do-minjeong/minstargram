<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minstargram</title>
<link href="/resources/css/main.css" rel="stylesheet">
<%@ include file="../includes/header.jsp"%>
<%@ include file="includes/header.jsp"%>
</head>
<body>
<script type="text/javascript">
	$("img[alt='home']").attr("src","https://www.flaticon.com/svg/static/icons/svg/1946/1946436.svg")
</script>
<div class="total-block displayCenter">
	<div class="right-block">
		<div class="livebox box-setting">
			<div class="live-profile-box">
				<ul class="live-list">
					<li>
						<button class="btnNone">
							<div class="canvas live-canvas displayCenter">
								<div class="live-profile-pic">
									<img alt="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSw1gQ3kuXFIpGaAuXc_QyaPBaSpbQQXhs1FA&usqp=CAU">
								</div>
							</div>
							<div class="live-id">domjjang</div>
						</button>
					</li>
					<li>
						<button class="btnNone">
							<div class="canvas live-canvas displayCenter">
								<div class="live-profile-pic">
									<img alt="" src="https://pgnqdrjultom1827145.cdn.ntruss.com/img/ac/d9/acd9e51301a4b61d17e6ce3fe8660dd0166a7aaf0fde2da09196e5ce019ce311_v1.jpg">
								</div>
							</div>
							<div class="live-id">minjeong97</div>
						</button>
					</li>
				</ul>
			</div>
		</div>
	<div class="articlebox">
			<div class="articles">
				<article class="article box-setting">
					<header>
						<div class="article-header">
							<div class="aticle-canvas canvas displayCenter">
								<div class="profile-pic">
									<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSw1gQ3kuXFIpGaAuXc_QyaPBaSpbQQXhs1FA&usqp=CAU">
								</div>
							</div>
							<div class="smallheader">
								<div class="article-id"><a href="">domjjang</a></div>
								<button class="btnNone">
									<div class="morebtn">
										<img alt="더보기" src="https://www.flaticon.com/svg/static/icons/svg/512/512142.svg">
									</div>
								</button>
							</div>
						</div>
					</header>
					<div class="article-imgbox article-section">
						<div class="article-img"><img  alt="" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20110608_238%2Fdiablo1015_1307526886736MpPBf_JPEG%2F015.JPG&type=sc960_832"></div>					
					</div>
					<div class="article-body">
						<div class="article-iconbox article-section">
							<div class="article-icon"><img alt="좋아요" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg"></div>
							<div class="article-icon"><img alt="댓글" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946412.svg"></div>
							<div class="article-icon"><img alt="메시지" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946547.svg"></div>
							<div class="article-icon bookmark"><img alt="북마크" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946422.svg"></div>
						</div>
						<div class="article-likes article-section"><button class="likes_info btnNone">좋아요 <span>11</span>개</button></div>
						<div class="article-contents article-section">
							<span class="article-id"><a href="">domjjang</a></span>
							<span class="id-contents">
								<a href="" >#안뇽</a>
								<a href="" >#덥다할리스</a>
								<br>
								난 오늘도 공부를 한다.. 하기가싫다...
							</span>
						</div>
						<div class="article-time article-section">
							<span>48분 전</span>
						</div>
						<div class="comment article-section">
							<div class="comment-input">
								<form action="" class="comment-form displayCenter">
									<textarea wrap="virtual" cols="38" class="btnNone" placeholder="댓글 달기.."></textarea>
									<button type="submit" class="btnNone" disabled>게시</button>
								</form>
							</div>
						</div>
					</div>
				</article>
				<article class="article box-setting">
					<header></header>
				</article>
			</div>
		
		</div>
	
	</div>
	<div class="left-block">
		<div>
			<button class="write-btn btnNone btn-warning" onclick="location.href='/main/write'">글쓰기</button>
		</div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/mainHome.js"></script>
</body>
</html>