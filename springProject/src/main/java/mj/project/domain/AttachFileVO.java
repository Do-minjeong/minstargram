package mj.project.domain;

import lombok.Data;

@Data
public class AttachFileVO {
	private String file_name;
	private String file_url;
	private String total_url;
	private long file_size;
}
