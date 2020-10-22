package mj.project.mapper;

import org.apache.ibatis.annotations.Param;

public interface SubMapper {

	public int likeOn(@Param("post_no") String post_no, @Param("member_no") int member_no);

}
