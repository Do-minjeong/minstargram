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
	
	private List<AuthVO> authList;

}
