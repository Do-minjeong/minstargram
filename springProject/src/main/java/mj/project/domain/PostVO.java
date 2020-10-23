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
	
	private String like_btn;
	private int like_cnt;
	
	private String bookmark_btn;
	
	private int reply_cnt;
	private List<ReplyVO> replyList;
}
