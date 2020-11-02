package mj.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mj.project.domain.PostVO;
import mj.project.domain.ProfileVO;
import mj.project.domain.RelationVO;
import mj.project.domain.TagVO;

public interface MainMapper {
	
	public int postWrite(PostVO vo);

	public List<PostVO> readPosts(int member_no);

	public ProfileVO readProfile(String member_no);

	public RelationVO readFollow(@Param("member_no") String member_no , @Param("member_no2") int member_no2);

	public PostVO getPost(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public List<PostVO> memberPosts(String member_no);

	public List<PostVO> bookmarkPosts(String member_no);

	public TagVO tagTest(String hashTag);

	public void tagInsert(TagVO tagvo);

		
}
