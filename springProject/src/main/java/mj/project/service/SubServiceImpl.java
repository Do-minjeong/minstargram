package mj.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import mj.project.mapper.SubMapper;

@Service
public class SubServiceImpl implements SubService{

	@Setter(onMethod_ = @Autowired)
	private SubMapper mapper;
	
	@Override
	public Integer likeOn(String post_no, int member_no) {
		
		return mapper.likeOn(post_no, member_no);
	}
	
	

}
