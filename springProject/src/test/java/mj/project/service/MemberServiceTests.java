package mj.project.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})	
@Log4j
public class MemberServiceTests {
	
	@Setter(onMethod_= @Autowired)
	private MemberService service;
	
	@Setter(onMethod_= @Autowired)
	private PasswordEncoder pwencoder;
	
	@Test
	public void testSignup() {
		MemberVO vo = new MemberVO();
		vo.setUsername("minjeong11@naver.com");
		vo.setName("minjeong11");
		vo.setUserid("minjeong11");
		vo.setUserpwd(pwencoder.encode("minjeong11"));
		
		boolean answer = service.signup(vo);
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "+answer);
	}

}
