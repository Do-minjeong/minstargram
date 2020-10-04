package mj.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;
import mj.project.mapper.MemberMapper;
import mj.project.security.domain.CustomUser;

@Log4j
public class CustomUserDetailService implements UserDetailsService{
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("Load User By UserName : " + username);

		// userName means userid
		MemberVO vo = mapper.read(username);

		log.warn("queried by member mapper: " + vo);
		
		return vo == null ? null : new CustomUser(vo);

	}

}
