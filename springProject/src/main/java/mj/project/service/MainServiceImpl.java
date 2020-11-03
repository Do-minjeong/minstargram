package mj.project.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.common.CommonFunction;
import mj.project.domain.PostVO;
import mj.project.domain.ProfileVO;
import mj.project.domain.RelationVO;
import mj.project.domain.TagVO;
import mj.project.mapper.MainMapper;

@Service
@Log4j
public class MainServiceImpl implements MainService{
	
	@Setter(onMethod_ = @Autowired)
	private MainMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private CommonFunction cf;
	
	@Override
	@Transactional
	public void postWrite(PostVO vo) {
		List<TagVO> tagList = cf.tagSplit(vo.getContents(), mapper);
		vo.setTagList(tagList);
		vo.setContents(cf.addTagContents(vo.getContents()));
		mapper.postWrite(vo);
	}
	
	
	@Override
	public List<PostVO> readPosts(int member_no) {
		List<PostVO> post_list = mapper.readPosts(member_no);
		/*
		Iterator<PostVO> post_ir = post_list.iterator();
		List<PostVO> post_list2 = new ArrayList<PostVO>();
		
		String contents = "";
		
		while (post_ir.hasNext()) {
			PostVO postVO = (PostVO) post_ir.next();
			contents = cf.addTagContents(postVO.getContents());
			postVO.setContents(contents);
			post_list2.add(postVO);
		}*/
		return post_list;
	}

	@Override
	public ProfileVO readProfile(String member_no, int member_no2) {
		log.info("readProfile 진입");
		ProfileVO profile = mapper.readProfile(member_no);
		profile.setRelationVO( mapper.readFollow(member_no, member_no2));
		log.info(profile);
		return profile;
	}

	@Override
	public PostVO getPost(String post_no, int member_no) {
		
		return mapper.getPost(post_no, member_no);
	}

	@Override
	public List<PostVO> memberPosts(String member_no) {
		return mapper.memberPosts(member_no);
	}

	@Override
	public List<PostVO> bookmarkPosts(String member_no) {
		return mapper.bookmarkPosts(member_no);
	}

}
