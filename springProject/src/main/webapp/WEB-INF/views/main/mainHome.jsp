<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minstargram</title>
<link href="/resources/css/main.css" rel="stylesheet">
<%@ include file="includes/header.jsp"%>
</head>
<body>
<input type="hidden" name="${ _csrf.parameterName }" value="${_csrf.token}" id="csrf_token" >
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
				<c:if test="${ posts ne null }">
					<c:forEach items="${ posts }" var="post">
						<article class="article box-setting">
						<header>
							<div class="article-header">
								<div class="aticle-canvas canvas displayCenter">
									<div class="profile-pic">
										<img src="${ post.profile_photo }">
									</div>
								</div>
								<div class="smallheader">
									<div class="article-id"><a href="/main/profile?member_no=${post.member_no}">${post.userid}</a></div>
									<button class="btnNone">
										<div class="morebtn">
											<img alt="더보기" src="https://www.flaticon.com/svg/static/icons/svg/512/512142.svg">
										</div>
									</button>
								</div>
							</div>
						</header>
						<div id="carouselExampleIndicators${post.post_no}" class="carousel slide" data-ride="carousel" data-interval="false">
							<ol class="carousel-indicators">
								<c:forEach items="${ post.attachList }" var="attachList" varStatus="status">
									<c:if test="${ status.first }">
										<li data-target="#carouselExampleIndicators${post.post_no}" data-slide-to="0" class="active"></li>							
									</c:if>
									<c:if test="${ !status.first }">
										<li data-target="#carouselExampleIndicators${post.post_no}" data-slide-to="${ status.index }"></li>										
									</c:if>
								</c:forEach>
							</ol>
							<!-- <div class="article-imgbox article-section"> -->
								<div class="carousel-inner">
									<c:forEach items="${ post.attachList }" var="attachList" varStatus="status">
										<c:if test="${ status.first }">
											<div class="carousel-item active">
												<div class="article-img"><img class="d-block w-100" alt="${ post.post_no }-${status.index} Slide" src="${ attachList.total_url }"></div>
											</div>										
										</c:if>
										<c:if test="${ !status.first }">
											<div class="carousel-item">
												<div class="article-img"><img class="d-block w-100"  alt="${ post.post_no }-${status.index} Slide" src="${ attachList.total_url }"></div>							
											</div>
										</c:if>
									</c:forEach>
								</div>
								  <a class="carousel-control-prev" href="#carouselExampleIndicators${post.post_no}" role="button" data-slide="prev">
								    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
								    <span class="sr-only">Previous</span>
								  </a>
								  <a class="carousel-control-next" href="#carouselExampleIndicators${post.post_no}" role="button" data-slide="next">
								    <span class="carousel-control-next-icon" aria-hidden="true"></span>
								    <span class="sr-only">Next</span>
								  </a>
							<!-- </div> -->
						</div>
						<div class="article-body">
							<div class="article-iconbox article-section">
								<button class="article-icon btnNone likebtn" id="likebtn${post.post_no}">
									<c:if test="${post.like_btn}">
										<img alt="좋아요" class="on_like" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946346.svg">	
									</c:if>
									<c:if test="${!post.like_btn}">
										<img alt="좋아요" class="off_like" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg">
									</c:if>
								</button>
								<button class="article-icon btnNone commentbtn" id="commentbtn${post.post_no}">
									<img alt="댓글" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946412.svg">
								</button>
								<button class="article-icon btnNone messagebtn" id="messagebtn${post.post_no}"><img alt="메시지" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946547.svg"></button>
								<button class="article-icon bookmark btnNone bookmarkbtn" id="bookmarkbtn${post.post_no}">
									<c:if test="${post.bookmark_btn}">
										<img alt="북마크" class="on_bookmark" src="https://www.flaticon.com/svg/static/icons/svg/1174/1174447.svg">							
									</c:if>
									<c:if test="${!post.bookmark_btn}">
										<img alt="북마크" class="off_bookmark" src="https://www.flaticon.com/svg/static/icons/svg/1174/1174410.svg">
									</c:if>
								</button>
							</div>
							<div class="article-likes article-section"><button class="likes_info btnNone">좋아요 <span id="likeval${post.post_no}">${post.like_cnt}</span>개</button></div>
							<div class="article-contents article-section">
								<span class="article-id"><a href="">${post.userid}</a></span>
								<span class="id-contents">
									${post.contents} 
								</span>
							</div>
							<div class="article-reply article-section">
								<c:if test="${ post.replyList ne null }">
									<c:if test="${ post.reply_cnt > 2 }">
										<div class="commentMore"><a href="">댓글  <span>${post.reply_cnt}</span>개 모두 보기</a></div>								
									</c:if>
									<div class="replys_box" id="replybox${post.post_no}">
										<c:forEach items="${ post.replyList }" var="reply">
											<div class="reply">
												<div class="reply-top">
													<span class="article-id reply-id"><a href="">${reply.userid}</a></span>
													<span class="id-contents">${reply.r_contents}</span>
												</div>
												<button class="btnNone replyLikeBtn" id="rpLikebtn${reply.reply_no}">
													<c:if test="${!reply.r_like_btn}">
														<img alt="reply_like" class="off_rpLike" src="https://www.flaticon.com/svg/static/icons/svg/865/865991.svg">
													</c:if>
													<c:if test="${reply.r_like_btn }">
														<img alt="reply_like" class="on_rpLike" src="https://www.flaticon.com/svg/static/icons/svg/865/865974.svg">												
													</c:if>
												</button>
											</div>								
										</c:forEach>
									</div>
									</c:if>
							</div>
							<div class="article-time article-section">
								<span>${post.reg_date}</span>
							</div>
							<div class="comment article-section">
								<div class="comment-input">
									<form action="" class="comment-form displayCenter">
										<textarea id="cmt${post.post_no}" name="r_contents" wrap="virtual" cols="38" class="btnNone" placeholder="댓글 달기.."></textarea>
										<button type="submit" class="btnNone" disabled>게시</button>
									</form>
								</div>
							</div>
						</div>
					</article>
					</c:forEach>
				</c:if>
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