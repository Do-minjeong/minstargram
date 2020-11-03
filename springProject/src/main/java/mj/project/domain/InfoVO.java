package mj.project.domain;

import lombok.Data;

@Data
public class InfoVO {
	private int member_no;
	private String userid;
	private String name;
	private String profile_photo;
	
	private int relationship;
}
