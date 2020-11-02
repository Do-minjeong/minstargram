package mj.project.service;

import java.util.List;

import mj.project.domain.PostVO;
import mj.project.domain.ProfileVO;

public interface MainService {
	
	public void postWrite(PostVO vo);

	public List<PostVO> readPosts(int member_no);

	public ProfileVO readProfile(String member_no, int member_no2);

	public PostVO getPost(String post_no, int member_no);

	public List<PostVO> memberPosts(String member_no);

	public List<PostVO> bookmarkPosts(String member_no);
	
}
