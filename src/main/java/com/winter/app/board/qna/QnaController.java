package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;

@Controller
@RequestMapping("qna")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public String getList(Pager pager,Model model)throws Exception {
		List<BoardVO> ar = qnaService.getList(pager);
		model.addAttribute("pager",pager);
		model.addAttribute("list",ar);
		return "board/list";
	}
	
	
}
