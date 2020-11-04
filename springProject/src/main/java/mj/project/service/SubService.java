package mj.project.service;

import java.util.List;

import mj.project.domain.InfoVO;
import mj.project.domain.ReplyVO;

public interface SubService {

	public Integer likeOnOff(int type, String post_no, int member_no);

	public int bookmarkOnOff(int type, String post_no, int member_no);

	public void replyInsert(ReplyVO vo);

	public int rplikeOnOff(int type, String reply_no, int member_no);

	public int followOnOff(int type, String tg_no, int member_no);

	public List<InfoVO> likesInfo(String post_no, int member_no);

	public List<InfoVO> followersInfo(String member_no, int member_no2);

}
