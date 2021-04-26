package com.kh.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.HelloSpringUtils;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList.do")
	public void boardList(@RequestParam(defaultValue ="1") int cPage, Model model, HttpServletRequest request) {
		//cpage가 없어도 default값을 줘서 오류가 나지 않는다
		
		//1. 사용자입력값
		int numPerPage = 5;
		log.debug("cPage = {}", cPage);
		Map<String, Object> param = new HashMap<>();
			param.put("numPerPage", numPerPage);
			param.put("cPage", cPage);
		
		//2. 업무로직
		//a. contents영역 : mybatis의 RowBounds
		List<Board> list = boardService.selectBoardList(param);
		log.debug("list = {}", list);
		
		//b. pagebar영역
		int totalContents = boardService.getTotalContents();
		String url = request.getRequestURI();
		log.debug("totalContents = {}",totalContents);
		log.debug("url = {}", url);
		String pageBar = HelloSpringUtils.getPageBar(totalContents, cPage, numPerPage, url);

		//3. jsp처리위임
		model.addAttribute("list", list);
		model.addAttribute("pageBar", pageBar);
	}
	
	@GetMapping("/boardForm.do")
	public void boardForm(HttpServletRequest request, Model model) {
		
	}
	
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(@ModelAttribute Board board, RedirectAttributes redirectAttr) {
		try {
			int result = boardService.boardEnroll(board);
			String msg = "게시글 등록 성공!";
			redirectAttr.addFlashAttribute("msg", msg);
			
		} catch (Exception e) {
			log.error("게시글 등록 오류!", e);
			throw e;
		}
		return "redirect:/board/boardList.do";
	}
	
	
	
	
}




