package mj.project.domain;

import lombok.Data;

@Data
public class AttachFileDTO {
	private String file_name;
	private String file_url;
	private long file_size;
}
