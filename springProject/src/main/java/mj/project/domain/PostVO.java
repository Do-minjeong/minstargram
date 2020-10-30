package mj.project.domain;

import java.util.List;

import lombok.Data;

@Data
public class PostVO {
	private int post_no;
	private List<AttachFileVO> attachList;
	private String contents;
	private int member_no;
	private String reg_date;
	private String userid;
	private String profile_photo;
	
	private List<TagVO> tagList;
	
	private String o_total_url;
	// like_btn : 내가 좋아요를 눌렀는지 (true/false)
	// like_cnt : 게시글의 총 좋아요 수 
	private String like_btn;
	private int like_cnt;
	
	// 내가 북마크를 눌렀는지 (true/false) 
	private String bookmark_btn;
	
	// 총 댓글 수
	private int reply_cnt;
	// 댓글 리스트
	private List<ReplyVO> replyList;
	
	// 한 게시물에 사진이 한장인지 아닌지 여부
	private String multi_tf;
}
