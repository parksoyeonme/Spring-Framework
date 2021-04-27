package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
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
	
	/**
	 * 1. form[enctype=multipart/form-data] =>jsp
	 * 2. handler - @RequestParam MultipartFile upFile => controller
	 * 3. 서버컴퓨터에 파일 저장  
	 * 		- saveDirectory (/resources/upload/board/20210427_12345_234.jpg)
	 * 		- 파일명 재지정 
	 * 4. DB attachment에 저장된 파일정보 등록  
	 * 		- Attachment객체생성
	 * 
	 * 
	 * @param board
	 * @param redirectAttr
	 * @return
	 */
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(@ModelAttribute Board board,
			  @RequestParam(value="upFile", required = false) MultipartFile[] upFiles,
			  HttpServletRequest request,
			  RedirectAttributes redirectAttr) {
		
		
		try {
			//0. 파일저장 및 Attachment객체 생성
			//저장경로
			String saveDirectory = 
					request.getServletContext().getRealPath("/resources/upload/board");
			//getServletContext = 생명주기가 제일 길었던 application의 type이다
			//File객체를 통해서 directory가능
			File dir = new File(saveDirectory);
			if(!dir.exists()) 
				dir.mkdirs(); // 복수개 폴더 생성 가능
			
			//복수개의 Attachment객체를 담을 list생성
			List<Attachment> attachList = new ArrayList<>();
			
			for(MultipartFile upFile : upFiles) {	
				//무조건 2개가 넘어오기 떄문에 
				if(upFile.isEmpty() || upFile.getSize() == 0)
					continue;
				log.debug("upFile = {}", upFile);
				log.debug("upFile.name = {}", upFile.getOriginalFilename());
				log.debug("upFile.size = {}", upFile.getSize());
				//저장할 파일명 생성
				File renamedFile = HelloSpringUtils.getRenamedFile(saveDirectory, upFile.getOriginalFilename());
				//파일 저장
				upFile.transferTo(renamedFile);
				//Attachment객체생성
				Attachment attach = new Attachment();
				attach.setOriginalFileName(upFile.getOriginalFilename());
				attach.setRenamedFileName(renamedFile.getName());
				attachList.add(attach);
			}
			
			//1. 업무로직
			board.setAttachList(attachList);
			int result = boardService.insertBoard(board);
			
			//2. 사용자피드백
			String msg = result > 0 ? "게시물 등록 성공!" : "게시물 등록 실패!";
			redirectAttr.addFlashAttribute("msg", msg);
		
		} catch (IOException | IllegalStateException e) {
			log.error("첨부파일 저장 오류!", e);
			throw new BoardException("첨부파일 저장 오류!");
		} catch (Exception e) {
			log.error("게시물 등록 오류!", e);
			throw e; // spring container에게 예외 전파
		}
			
		return "redirect:/board/boardList.do";
	}
	
	@GetMapping("/boardDetail.do")
	public void boardDetail(@RequestParam int no, Model model) {
		try {
			
			Board board = boardService.selectOneBoard(no);
			log.info("board = {}", board);

		model.addAttribute("board", board);
		} catch(Exception e) {
			//1.로깅작업
			log.error(e.getMessage(),e);
		
			throw e;
		}
		
		
	}
	
	
	
	
	
}
