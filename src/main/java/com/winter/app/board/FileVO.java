package com.winter.app.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FileVO {

	private Long fileNum;
	private Long noticeNum;
	private String fileName;
	private String oriName;
}
