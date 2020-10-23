package mj.project.mapper;

import org.apache.ibatis.annotations.Param;

public interface SubMapper {

	public int likeOn(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public void likeOff(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public Integer likeCount(String post_no);

	public void bookmarkOn(@Param("post_no") String post_no, @Param("member_no") int member_no);

	public void bookmarkOff(@Param("post_no") String post_no, @Param("member_no") int member_no);

}
