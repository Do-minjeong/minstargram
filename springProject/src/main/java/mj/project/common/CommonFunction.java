package mj.project.common;

import javax.servlet.http.HttpSession;

import lombok.Data;
import mj.project.domain.MemberVO;

@Data
public class CommonFunction {
	public MemberVO getSession(HttpSession session) throws Exception {
		MemberVO member = new MemberVO();
		if(session.getAttribute("g_userInfo")!=null) member = (MemberVO) session.getAttribute("g_userInfo");
		else if(session.getAttribute("s_userInfo")!=null) member = (MemberVO) session.getAttribute("s_userInfo");
		else throw new Exception();
		
		return member;
	}
}
