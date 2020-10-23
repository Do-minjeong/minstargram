package mj.project.service;

public interface SubService {

	public Integer likeOnOff(int type, String post_no, int member_no);

	public void bookmarkOnOff(int type, String post_no, int member_no);

}
