package mj.project.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	@Test
	public void testRead() {
		MemberVO vo = mapper.read("domjjang@naver.com");
		
		log.info(vo);
		//vo.getAuthList().forEach(authVO -> log.info(authVO));
		
	}

}
