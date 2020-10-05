package mj.project.mapper;

import org.apache.ibatis.annotations.Param;

import mj.project.domain.MemberVO;


public interface MemberMapper {
	public MemberVO read(String username);
	
	public int insert(MemberVO vo);
	
	public int selectMemberNo(String username);

	public int authInsert(@Param("auth") String auth,  @Param("member_no") int member_no);

	public int selectUsername(String username);

	public int insertSocial(MemberVO member);

	public int insertSocialId(MemberVO member);

	public int updateUserID(@Param("username") String username, @Param("userid")  String userid);
	
}




		