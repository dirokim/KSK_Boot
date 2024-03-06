package com.winter.app.board;

import java.util.List;

import org.springframework.stereotype.Service;

import com.winter.app.util.Pager;

@Service
public interface BoardService {
	
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	public int add(BoardVO boardVO)throws Exception;
}
