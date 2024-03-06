package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice.name}")
	private String name;
	
	@ModelAttribute("board")
	public String board() {
		return this.name;
	}
	
	@GetMapping("list")
	public String getList(Pager pager,Model model) throws Exception {
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list",ar);
		model.addAttribute("pager",pager);
		return "board/list";
	}
	
	@GetMapping("add")
	public String add()throws Exception {
		return "board/add";
	}
	@PostMapping("add")
	public String add(NoticeVO noticeVo,Model model)throws Exception {
		int result = noticeService.add(noticeVo);
		return "redirect:board/list";
	}
	
	
}
