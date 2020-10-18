package mj.project.domain;

import java.util.List;

import lombok.Data;

@Data
public class PostVO {
	private int post_no;
	private List<AttachFileDTO> attachList;
	private String contents;
	private int member_no;
	private String reg_date;
	private String userid;
}
