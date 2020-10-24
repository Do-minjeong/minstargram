package mj.project.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.PostVO;
import mj.project.domain.TagVO;
import mj.project.mapper.MainMapper;

@Service
@Log4j
public class MainServiceImpl implements MainService{
	
	@Setter(onMethod_ = @Autowired)
	private MainMapper mapper;
	
	@Override
	@Transactional
	public void postWrite(PostVO vo) {
		List<TagVO> tagList = tagSplit(vo.getContents());
		vo.setTagList(tagList);
		mapper.postWrite(vo);
	}
	
	private List<TagVO> tagSplit(String contents) {
		log.info("contents : "+contents);
		int hashStart = 0, hashEnd = 0;
		int hashSpace = 0, hashEnter = 0;
		int maxLength = contents.length();
		String hashTag = "";
		List<TagVO> tag_list = new ArrayList<TagVO>();
		
		while(true) {
			hashStart = contents.indexOf("#", hashStart);
			if(hashStart == -1) break;
			
			hashSpace = contents.indexOf(" ", hashStart)==-1? maxLength: contents.indexOf(" ", hashStart);
			hashEnter = contents.indexOf("<br/>", hashStart)==-1? maxLength : contents.indexOf("<br/>", hashStart);
			hashEnd = Math.min(hashSpace, hashEnter);
			
			hashTag = contents.substring(hashStart, hashEnd);
			System.out.println(">>"+hashTag);
			TagVO tagvo = new TagVO(hashTag);
			
			tag_list.add(tagvo);
			hashStart = hashEnd;
		}
		
		return tag_list;
	}
	
	@Override
	public List<PostVO> readPosts(int member_no) {
		List<PostVO> post_list = mapper.readPosts(member_no);
		Iterator<PostVO> post_ir = post_list.iterator();
		List<PostVO> post_list2 = new ArrayList<PostVO>();
		
		String contents = "";
		
		while (post_ir.hasNext()) {
			PostVO postVO = (PostVO) post_ir.next();
			contents = addTagContents(postVO.getContents());
			postVO.setContents(contents);
			post_list2.add(postVO);
		}
		return post_list2;
	}

	private String addTagContents(String contents) {
		StringBuffer sb = new StringBuffer(contents);
		int hashStart = 0, hashEnd = 0;
		int hashSpace = 0, hashEnter = 0;
		String hashTag = "";
		String newString = "";
		while(true) {
			hashStart = sb.indexOf("#", hashStart);
			if(hashStart == -1) break;
			hashSpace = sb.indexOf(" ", hashStart)==-1? sb.length(): sb.indexOf(" ", hashStart);
			hashEnter = sb.indexOf("<br/>", hashStart)==-1? sb.length() : sb.indexOf("<br/>", hashStart);
			hashEnd = Math.min(hashSpace, hashEnter);
			
			hashTag = sb.substring(hashStart, hashEnd);
			newString = "<a href=''>"+hashTag+"</a>";
			sb = sb.replace(hashStart, hashEnd, newString);
			hashStart = hashEnd+14;
		}
		return sb.toString();
	}

}
