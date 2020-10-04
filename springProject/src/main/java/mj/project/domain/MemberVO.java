package mj.project.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String username;
	private String name;
	private String userid;
	private String userpwd;
	
	private List<AuthVO> authList;

}
