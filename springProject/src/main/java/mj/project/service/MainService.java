package mj.project.service;

import java.util.List;

import mj.project.domain.PostVO;

public interface MainService {
	
	public void postWrite(PostVO vo);

	public List<PostVO> readPosts(int member_no);
	
}
