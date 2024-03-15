package com.winter.app.board.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.util.Pager;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.extern.java.Log;

@RestController
@CrossOrigin
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
	
	@GetMapping("list/{page}")
	public Map<String,Object> getList(@PathVariable Long page,Model model) throws Exception {
		System.out.println(page);
		Pager pager = new Pager();
		pager.setPage(page);
		List<BoardVO> ar = noticeService.getList(pager);
		Map<String,Object> map = new HashMap<>();
		map.put("list",ar);
		map.put("pager",pager);
		return map;
	}

//	@GetMapping("add")
	public String add()throws Exception {
		return "board/add";
	}
	
	@GetMapping("add")
	public String add(HttpSession session, NoticeVO noticeVo,Model model,MultipartFile [] attach)throws Exception {
		if(session.getAttribute("member") !=null) {
			int result = noticeService.add(noticeVo,attach);			
		};
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO,Model model) throws Exception {
		boardVO = noticeService.getDetail(boardVO);
		model.addAttribute("vo",boardVO);
		return "board/detail";
	}
	@GetMapping("fileDown")
	public String fileDown (FileVO fileVO,Model model)throws Exception{
		fileVO = noticeService.getFileDetail(fileVO);
		model.addAttribute("fileVO",fileVO);
		return "fileDownView";
	}
	
}
