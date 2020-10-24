package mj.project.domain;

import lombok.Data;

@Data
public class ReplyVO {
	
	private int reply_no;
	private int member_no;
	private String r_contents;
	
	private String userid;
	
	private String post_no;
	
	// r_like_cnt : 댓글별 좋아요 수
	private int r_like_cnt;
	// r_like_btn: 내가 이 댓글에 좋아요를 눌렀는지(true/false)
	private String r_like_btn;
	
	
}
