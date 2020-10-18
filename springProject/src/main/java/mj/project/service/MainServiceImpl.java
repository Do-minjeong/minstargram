package mj.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import mj.project.domain.PostVO;
import mj.project.mapper.MainMapper;

@Service
public class MainServiceImpl implements MainService{
	
	@Setter(onMethod_ = @Autowired)
	private MainMapper mapper;
	
	@Override
	@Transactional
	public void writePost(PostVO vo) {
		mapper.writePost(vo);
	}

	@Override
	public List<PostVO> readPosts() {
		return mapper.readPosts();
	}

}
