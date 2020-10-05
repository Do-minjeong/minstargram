package mj.project.service;

import org.json.simple.parser.ParseException;

import mj.project.domain.MemberVO;

public interface MemberService {

	public boolean signup(MemberVO member);

	public boolean usernameCheck(String username);

	public boolean updateUserID(String username, String userid);

}
