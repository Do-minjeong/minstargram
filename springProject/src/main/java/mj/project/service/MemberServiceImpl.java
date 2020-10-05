package mj.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;
import mj.project.mapper.MemberMapper;

@Log4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;
	
	@Setter(onMethod_= @Autowired)
	private MemberMapper mapper;
	
	@Override
	@Transactional
	public boolean signup(MemberVO member) {
		log.info("MemberService signup Method");
		String encodepwd = pwencoder.encode(member.getPassword());
		member.setPassword(encodepwd);
		int insertResult = mapper.insert(member);
		
		member.setMember_no(mapper.selectMemberNo(member.getUsername()));
		log.info(">> service signup "+member.getUsername());
		
		 if( insertResult == 1) {
			 mapper.authInsert("ROLE_USER", member.getMember_no());
			 if(member.getUserid().startsWith("minstarAdmin")) mapper.authInsert("ROLE_ADMIN", member.getMember_no());
			 return true;
		 }
		 
		return false;
	}

}
