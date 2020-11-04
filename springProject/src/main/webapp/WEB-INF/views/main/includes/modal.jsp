<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 좋아요, 팔로우한 사람들 리스트  -->

 <div class="modal fade" id="info_Modal" tabindex="-1" role="dialog">
    <div class="modal-dialog info_dialog">
      <!-- Modal content-->
      <div class="modal-content ">
      	<div class="modal-header info_hd">
      		<div class="info_md_header">좋아요</div>
			<button type="button" class="close" data-dismiss="modal">×</button>
		</div>
        <div class="modal-body info_body">
	        <div class="lk_info_body">
	          <div class="lk_info">
	          	<div class="md-canvas canvas displayCenter">
					<div class="md-profile-pic profile-pic">
						<img src="https://travelblog.expedia.co.kr/wp-content/uploads/2016/06/03.jpg">
					</div>
				</div>
				<div class="lk_info_pf">
					<a class="ANone" href="/main/profile=?4">minjeong11</a>
					<span>이름이름이름</span>
				</div>
	          </div>
	          <div class="lk_info_fw">
					<button type="button" class="btnNone btn btn-primary">팔로우</button>
				</div>
	        </div>
        </div>
      </div>
    </div>
  </div>

<!-- follow취소 여부 물어보는 모달 -->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content cf-md-ct">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="confirm-md">
					<p>정말 팔로우를 취소하시겠습니까?</p>
				</div>
			</div>
			<div class="modal-footer cf-md-ft">
				<button type="button" class="btn btn-warning" data-dismiss="modal"
					id="confirm-ok">예</button>
				<button type="button" class="btn btn-warning" data-dismiss="modal"
					id="confirm-cancle">아니오</button>
			</div>
		</div>
	</div>
</div>

<!-- 게시글이 띄워지는  modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog md-modal-dialog" >
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header md-modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
			</div>
			<div class="modal-body md-modal-body">
				<div class="modal-ct">
					<div id="carouselExampleIndicators${post.post_no}"
						class="carousel slide" data-ride="carousel" data-interval="false">
						<ol class="carousel-indicators">
							<c:forEach items="${ post.attachList }" var="attachList"
								varStatus="status">
								<c:if test="${ status.first }">
									<li data-target="#carouselExampleIndicators${post.post_no}"
										data-slide-to="0" class="active"></li>
								</c:if>
								<c:if test="${ !status.first }">
									<li data-target="#carouselExampleIndicators${post.post_no}"
										data-slide-to="${ status.index }"></li>
								</c:if>
							</c:forEach>
						</ol>
						<!-- <div class="article-imgbox article-section"> -->
						<div class="carousel-inner">

							<div class="carousel-item active">
								<div class="md-article-img">
									<img class="d-block w-100"
										alt="${ post.post_no }-${status.index} Slide"
										src="https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1619,h_1080,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/gc6lil7ubeqeprjvs7ux/%EC%A0%9C%EC%A3%BC%EB%8F%84%ED%94%84%EB%9D%BC%EC%9D%B4%EB%B9%97%EC%B0%A8%EB%9F%89%ED%88%AC%EC%96%B4.jpg">
								</div>
							</div>
						</div>
						<a class="carousel-control-prev"
							href="#carouselExampleIndicators${post.post_no}" role="button"
							data-slide="prev"> <span class="carousel-control-prev-icon"
							aria-hidden="true"></span> <span class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleIndicators${post.post_no}" role="button"
							data-slide="next"> <span class="carousel-control-next-icon"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>

					<div class="modal-pf">
						<div class="md-article-header article-header">
							<div class="md-canvas canvas displayCenter">
								<div class="md-profile-pic profile-pic">
									<img
										src="https://travelblog.expedia.co.kr/wp-content/uploads/2016/06/03.jpg">
								</div>
							</div>
							<div class="md-smallheader smallheader">
								<div class="article-id font14">
									<a href="/main/profile?member_no=${post.member_no}">minjeong11</a>
								</div>
								<button class="btnNone">
									<div class="morebtn">
										<img alt="더보기"
											src="https://www.flaticon.com/svg/static/icons/svg/512/512142.svg">
									</div>
								</button>
							</div>
						</div>
						<div class="article-contents article-section md-article-contents">
							<div class="md-writer">
								<div class="modal-userInfo">
									<div class="md-canvas canvas displayCenter">
										<div class="md-profile-pic profile-pic">
											<img
												src="https://travelblog.expedia.co.kr/wp-content/uploads/2016/06/03.jpg">
										</div>
									</div>
								</div>
								<div class="writer-contents-box">
									<div class="md-writer-contents">
										<span class="md-article-id article-id"><a
											href="/main/profile?member_no=${post.member_no}">minjeong11</a></span>
										<span class="font14"> 게시글 내용이 들어감니당당당게시글 내용이 들어감니당당당게시글
											내용이 들어감니당당당 </span>
									</div>
									<div class="md-regdate">
										<span>1분전</span>
									</div>
								</div>
							</div>
							<div class="md-writer">
								<div class="modal-userInfo">
									<div class="md-canvas canvas displayCenter">
										<div class="md-profile-pic profile-pic">
											<img
												src="https://travelblog.expedia.co.kr/wp-content/uploads/2016/06/03.jpg">
										</div>
									</div>
								</div>
								<div class="writer-contents-box">
									<div class="md-box1">
										<div>
											<span class="md-article-id article-id"><a
												href="/main/profile?member_no=${post.member_no}">minjeong22</a></span>
											<span class="font14">댓글입니당</span>
										</div>
										<button class="btnNone md-replyLikeBtn"
											id="rpLikebtn${reply.reply_no}">
											<img alt="reply_like" class="on_rpLike"
												src="https://www.flaticon.com/svg/static/icons/svg/865/865974.svg">
										</button>
									</div>
									<div class="md-regdate">
										<span>1분전</span>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-ft">
							<div class="article-iconbox article-section">
								<button class="article-icon btnNone likebtn"
									id="likebtn${post.post_no}">
									<img alt="좋아요" class="on_like"
										src="https://www.flaticon.com/svg/static/icons/svg/1946/1946346.svg">
								</button>
								<button class="article-icon btnNone commentbtn"
									id="commentbtn${post.post_no}">
									<img alt="댓글"
										src="https://www.flaticon.com/svg/static/icons/svg/1946/1946412.svg">
								</button>
								<button class="article-icon btnNone messagebtn"
									id="messagebtn${post.post_no}">
									<img alt="메시지"
										src="https://www.flaticon.com/svg/static/icons/svg/1946/1946547.svg">
								</button>
								<button class="article-icon bookmark btnNone bookmarkbtn"
									id="bookmarkbtn${post.post_no}">
									<img alt="북마크" class="on_bookmark"
										src="https://www.flaticon.com/svg/static/icons/svg/1174/1174447.svg">
								</button>
							</div>
							<div class="article-likes article-section">
								<button class="likes_info btnNone font14">
									좋아요 <span id="likeval${post.post_no}">12</span>개
								</button>
							</div>
							<div class="md-regdate">
								<span>1분전</span>
							</div>
						</div>
						<div class="modal-rp">
							<form action=""
								class="md-comment-form comment-form displayCenter">
								<textarea id="cmt${post.post_no}" name="r_contents"
									wrap="virtual" cols="38" class="btnNone" placeholder="댓글 달기.."></textarea>
								<button type="submit" id="md-cf" class="btnNone" disabled>게시</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>