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
	
	private int login_type_no;
	
	private List<AuthVO> authList;
	
	public MemberVO() {	}
	
	public MemberVO(String username, String name, String userid, int login_type_no) {
		this.username = username;
		this.name = name;
		this.userid = userid;
		this.login_type_no = login_type_no;
	}

	
	

}
