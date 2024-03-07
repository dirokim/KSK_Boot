package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.util.Pager;

@Service
public class QnaService implements BoardService{
	@Autowired
	private QnaDAO qnaDAO;
	
	
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		return qnaDAO.getList(pager);
	}

	@Override
	public int add(BoardVO boardVO, MultipartFile[] attach) throws Exception {
		int result = qnaDAO.add(boardVO);	
		result = qnaDAO.refUpdate(boardVO);
		return result;
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FileVO getFileDetail(FileVO fileVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
