package mj.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import mj.project.domain.PostVO;
import mj.project.domain.ReplyVO;
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
	public int bookmarkOnOff(int type, String post_no, int member_no) {
		
		if(type == 0) return mapper.bookmarkOn(post_no, member_no);
		else return mapper.bookmarkOff(post_no, member_no);
		
	}

	@Override
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}

	@Override
	public int rplikeOnOff(int type, String reply_no, int member_no) {
		if(type == 0) return mapper.rpLikeOn(reply_no, member_no);
		else return mapper.rpLikeOff(reply_no, member_no);
	}

	@Override
	public int followOnOff(int type, String tg_no, int member_no) {
		if(type == 0) return mapper.followOn(tg_no, member_no);
		else return mapper.followOff(tg_no, member_no);
	}


	

}
