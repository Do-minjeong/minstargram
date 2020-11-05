package mj.project.domain;

import lombok.Data;

@Data
public class RelationVO {
	
	private int relationship;
	// 내가 상대방을 팔로우
	private int following_cnt;
	// 상대방이 나를 팔로워 
	private int follower_cnt;
}
