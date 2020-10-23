package mj.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import mj.project.mapper.SubMapper;

@Service
public class SubServiceImpl implements SubService{

	@Setter(onMethod_ = @Autowired)
	private SubMapper mapper;
	
	@Override
	public Integer likeOnOff(int type, String post_no, int member_no) {
		
		if(type==0) mapper.likeOn(post_no, member_no);
		else mapper.likeOff(post_no, member_no);
		
		return mapper.likeCount(post_no);
	}

	@Override
	public void bookmarkOnOff(int type, String post_no, int member_no) {
		
		if(type == 0) mapper.bookmarkOn(post_no, member_no);
		else mapper.bookmarkOff(post_no, member_no);
		
	}
	
	

}
