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
		int insertResult = 0;
		int typeNo = member.getLogin_type_no();
		if(typeNo == 1) {
			String encodepwd = pwencoder.encode(member.getPassword());
			member.setPassword(encodepwd);
			insertResult = mapper.insert(member);			
		}else if (typeNo == 2) {
			insertResult = mapper.insertSocial(member);
		}
		
		member.setMember_no(mapper.selectMemberNo(member.getUsername()));
		log.info(">> service signup "+member.getUsername());
		
		 if( insertResult == 1) {
			 if(typeNo == 2) mapper.insertSocialId(member);
			 
			 mapper.authInsert("ROLE_USER", member.getMember_no());
			 
			 if(member.getUserid().startsWith("minstarAdmin")) mapper.authInsert("ROLE_ADMIN", member.getMember_no());
			 
			 return true;
		 }
		 
		return false;
	}

	@Override
	public boolean usernameCheck(String username) {
		
		return mapper.selectUsername(username)==1? true:false;		
	}

	@Override
	public boolean updateUserID(String username, String userid) {
		
		return mapper.updateUserID(username, userid)==1? true: false;
	}

}
