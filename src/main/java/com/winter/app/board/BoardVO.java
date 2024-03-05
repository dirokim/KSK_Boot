package com.winter.app.board;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Setter
@Getter
public class BoardVO {
	
	private Long noticeNum;
	private String noticeWriter;
	private String noticeHead;
	private String noticeContents;
	private Date noticeDate;
	private Long noticeViews;
	
	
	
	
}
