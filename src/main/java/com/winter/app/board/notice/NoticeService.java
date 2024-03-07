package com.winter.app.board.notice;

import java.util.List;import org.eclipse.tags.shaded.org.apache.xpath.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.util.FileManager;
import com.winter.app.util.Pager;



@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService{
	
	@Autowired
	private NoticeDAO noticeDAO; 
	@Value("${app.upload.board.notice}")
	private String upload;
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makePage(noticeDAO.getTotalCount(pager));
		return noticeDAO.getList(pager);
	}

	@Override
	public int add(BoardVO boardVO,MultipartFile[]attach) throws Exception {
		int result = noticeDAO.add(boardVO);
		for(MultipartFile m:attach) {
			if(m.isEmpty()) {
				continue;
			}
			String fileName = fileManager.fileSave(upload, m);
			FileVO fileVO = new FileVO();
			fileVO.setNoticeNum(boardVO.getNoticeNum());
			fileVO.setFileName(fileName);
			fileVO.setOriName(m.getOriginalFilename());
			result = noticeDAO.addFile(fileVO);
			
		}
		return result;
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getDetail(boardVO);
	}

	@Override
	public FileVO getFileDetail(FileVO fileVO) {
		// TODO Auto-generated method stub
		return null;
	}
}
