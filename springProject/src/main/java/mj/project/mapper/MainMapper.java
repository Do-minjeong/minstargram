package mj.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mj.project.domain.PostVO;

public interface MainMapper {
	
	public int postWrite(PostVO vo);

	public List<PostVO> readPosts(int member_no);
		
}
