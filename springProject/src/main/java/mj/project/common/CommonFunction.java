package mj.project.common;

import javax.servlet.http.HttpSession;

import lombok.Data;
import mj.project.domain.MemberVO;

@Data
public class CommonFunction {
	public MemberVO getSession(HttpSession session) throws Exception {
		MemberVO member = new MemberVO();
		if(session.getAttribute("userInfo")!=null) member = (MemberVO) session.getAttribute("userInfo");
		else throw new Exception();
		
		return member;
	}
}
