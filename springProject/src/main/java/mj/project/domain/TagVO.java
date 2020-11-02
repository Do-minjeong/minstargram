package mj.project.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagVO {
	private int tag_no;
	private String tag_name;
	private int post_no;

	public TagVO(String tag_name) {
		super();
		this.tag_name = tag_name;
	}
	
}
