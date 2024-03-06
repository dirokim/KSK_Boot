package com.winter.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {
	
	private Long page;
	private Long perPage;
	private Long startIndex;
	
	private Long totalPage;
	private Long totalCount;
	private Long startNum;
	private Long lastNum;

	private boolean start;
	private boolean last;
	
	private String search;
	private String kind;
	
	
	public void makePage(Long totalCount)throws Exception {
		if(totalCount<1) {
			totalCount=1L;				
		}
		totalPage=totalCount/this.getPerPage();
		if(totalCount%this.getPerPage()!=0) {
			totalPage++;
		}
		
		Long totalBlock = 0L;
		Long perBlock = 10L;
		totalBlock = totalPage/perBlock;
		if(totalPage%perBlock!=0) {
			totalBlock++;
		}
		Long curBlock = 0L;
		curBlock = this.getPage()/perBlock;
		if(this.getPage()%perBlock!=0) {
			curBlock++;
		}
		
		Long startNum = 0L;
		Long lastNum = curBlock*perBlock;
		startNum = lastNum-perBlock+1;

		if(totalPage<perBlock) {
			lastNum =totalPage;
		}

		this.setStartNum(startNum);
		this.setLastNum(lastNum);
		
		
		if(curBlock==1) {
			this.setStart(true);
		}
		if(curBlock==totalBlock) {
			this.setLastNum(totalPage);
			this.setLast(true);
		}
	}
	
	
	
	
	
	
	public void makeRow() {
		this.startIndex = (this.getPage()-1)*this.getPerPage();
		
	}
	
	
	//getter
	//public 리턴타입 get멤버변수명(){}
	public Long getPage() {
		if(this.page==null||this.page<1) {
			this.page =1L;
		}
	  return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage==null||this.perPage<1) {
			this.perPage=10L;
		}
		return this.perPage;
	}
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return this.search;
	}
	
}
