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
		int typeNo = member.getLogin_type_no();
		
		if(typeNo == 1 && member.getUserid().startsWith("minstarAdmin")) member.setAuth("ROLE_ADMIN");
		else member.setAuth("ROLE_USER");
		
		mapper.insert(member);
		
		if(typeNo == 1) member.setPassword(pwencoder.encode(member.getPassword()));
		
		mapper.insertDetailInfo(member);
		
		log.info(">> service signup : "+member.getUsername());
		
		return true ;
	}

	@Override
	public boolean usernameCheck(String username) {
		
		return mapper.selectUsername(username)==1? true:false;		
	}

	@Override
	public boolean updateUserID(String username, String userid) {
		
		return mapper.updateUserID(username, userid)==1? true: false;
	}

	@Override
	public boolean userIdCheck(String username) {
		String id = mapper.userIdCheck(username);
		// ID등록이 필요한가? YES = true
		return id.startsWith("<REGISTER");
	}

	@Override
	public MemberVO readMemberInfo(String username) {
		MemberVO vo = mapper.read(username);
		vo.setPassword("");
		return vo;
	}

}
