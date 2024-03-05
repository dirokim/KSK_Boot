package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;
@SpringBootTest
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void getList ()throws Exception {
		List<BoardVO> ar = noticeDAO.getList();
		assertEquals(10,ar.size());
		
	}
	
//	@Test
//	void addtest() throws Exception {
//		for(int i=0;i<60;i++) {
//			NoticeVO noticeVO = new NoticeVO();
//			noticeVO.setNoticeWriter("tester");
//			noticeVO.setNoticeHead("test Title");
//			noticeVO.setNoticeContents("test Contents");
//			int result = noticeDAO.add(noticeVO);
//		}
//		System.out.println("end");
//	}

}
