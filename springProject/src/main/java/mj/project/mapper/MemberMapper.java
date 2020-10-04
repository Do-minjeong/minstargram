package mj.project.mapper;

import org.apache.ibatis.annotations.Param;

import mj.project.domain.MemberVO;


public interface MemberMapper {
	public MemberVO read(String username);
	
	public int insert(MemberVO vo);

	public int authInsert(@Param("username") String username, @Param("auth")String auth);
	
}




		