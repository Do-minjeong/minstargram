package mj.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;
import mj.project.mapper.MemberMapper;

@Log4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Setter(onMethod_= @Autowired)
	private MemberMapper mapper;
	
	@Override
	@Transactional
	public boolean signup(MemberVO member) {
		log.info("service");
		int insertResult = mapper.insert(member);
		log.info(">> service signup "+member.getUsername());
		
		 if( insertResult == 1) {
			 String username = member.getUsername();
			 return mapper.authInsert(username, "ROLE_USER")==1? true: false; 
		 }
		 
		return false;
	}

}
