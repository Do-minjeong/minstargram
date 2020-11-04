package mj.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import mj.project.common.CommonFunction;
import mj.project.domain.InfoVO;
import mj.project.domain.PostVO;
import mj.project.domain.ReplyVO;
import mj.project.domain.TagVO;
import mj.project.mapper.MainMapper;
import mj.project.mapper.SubMapper;

@Service
public class SubServiceImpl implements SubService{

	@Setter(onMethod_ = @Autowired)
	private SubMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private MainMapper m_mapper;
	
	@Setter(onMethod_ = @Autowired)
	private CommonFunction cf;
	
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
		List<TagVO> tagList = cf.tagSplit(vo.getR_contents(), m_mapper);
		vo.setTagList(tagList);
		vo.setR_contents(cf.addTagContents(vo.getR_contents()));
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

	@Override
	public List<InfoVO> likesInfo(String post_no, int member_no) {
		
		return mapper.likesInfo(post_no, member_no);
	}

	@Override
	public List<InfoVO> followersInfo(String member_no, int member_no2) {
		return mapper.followersInfo(member_no, member_no2);
	}



}
