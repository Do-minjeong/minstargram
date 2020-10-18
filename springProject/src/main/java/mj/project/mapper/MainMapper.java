package mj.project.mapper;

import java.util.List;

import mj.project.domain.PostVO;

public interface MainMapper {
	
	public int writePost(PostVO vo);

	public List<PostVO> readPosts();
	
}
