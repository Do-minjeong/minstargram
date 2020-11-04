package mj.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mj.project.domain.InfoVO;
import mj.project.domain.ReplyVO;

public interface SubMapper {

	public int likeOn(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public void likeOff(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public Integer likeCount(String post_no);

	public int bookmarkOn(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public int bookmarkOff(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public void replyInsert(ReplyVO vo);

	public int rpLikeOn(@Param("reply_no") String reply_no, @Param("member_no") int member_no);

	public int rpLikeOff(@Param("reply_no") String reply_no, @Param("member_no") int member_no);

	public int followOn(@Param("tg_no") String tg_no, @Param("member_no") int member_no);

	public int followOff(@Param("tg_no") String tg_no, @Param("member_no") int member_no);

	public List<InfoVO> likesInfo(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public List<InfoVO> followersInfo(@Param("member_no") String member_no, @Param("member_no2") int member_no2);


}
