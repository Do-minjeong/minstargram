package mj.project.domain;

import lombok.Data;

@Data
public class ReplyVO {
	
	private int reply_no;
	private int post_no;
	private String userid;
	private String contents;
	
	
}
