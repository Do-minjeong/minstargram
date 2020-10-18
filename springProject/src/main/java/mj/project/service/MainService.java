package mj.project.service;

import java.util.List;

import mj.project.domain.PostVO;

public interface MainService {
	
	public void writePost(PostVO vo);

	public List<PostVO> readPosts();
	
}
