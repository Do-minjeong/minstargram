package mj.project.service;

import mj.project.domain.ReplyVO;

public interface SubService {

	public Integer likeOnOff(int type, String post_no, int member_no);

	public int bookmarkOnOff(int type, String post_no, int member_no);

	public void replyInsert(ReplyVO vo);

}
