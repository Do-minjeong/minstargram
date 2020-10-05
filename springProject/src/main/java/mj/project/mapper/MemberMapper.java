package mj.project.mapper;

import org.apache.ibatis.annotations.Param;

import mj.project.domain.MemberVO;


public interface MemberMapper {
	public MemberVO read(String username);
	
	public int insert(MemberVO vo);
	
	public int selectMemberNo(String username);

	public int authInsert(@Param("auth") String auth,  @Param("member_no") int member_no);
	
}




		