package mj.project.domain;

import java.util.List;

import lombok.Data;

@Data
public class ProfileVO {
	
	private int member_no;
	private String profile_photo;
	private String userid;
	private String name;
	private String introduce;
	
	private int post_cnt;
	private RelationVO relationVO;

	private List<PostVO> post_List;
	
	
}
