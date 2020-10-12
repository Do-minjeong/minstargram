package mj.project.domain;


import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private int member_no;
	private String username;
	private String name;
	private String userid;
	private String password;
	private String auth;
	
	private String social_id;
	
	private List<AuthVO> authList;
	
	private int login_type_no;
	
	
	public MemberVO() {	}
	
	public MemberVO(String username, String name, String userid, int login_type_no, String social_id) {
		this.username = username;
		this.name = name;
		this.userid = userid;
		this.login_type_no = login_type_no;
		this.social_id = social_id;
	}

	
	

}
