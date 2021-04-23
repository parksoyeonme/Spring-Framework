package com.kh.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Log4j
@Controller
@RequestMapping("/memo")
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	/**
	 * AOP의 실행 구조
	 * 
	 * MemoController.memo ---------------> MemberService.selectMemoList
	 * 
	 * MemoController.memo ------ Proxy객체 -----> Target객체(MemberService.selectMemoList)
	 * 
	 * @param model
	 */
	@GetMapping("/memo.do")
	public void memo(Model model) {
		//proxy확인
		log.debug("proxy = {}", memoService.getClass());
		
		try {
			//1.업무로직
			List<Memo> list = memoService.selectMemoList();
			log.debug("list = {}", list);
			
			//2.jsp위임처리
			model.addAttribute("list", list);
		} catch(Exception e) {
			log.error("메모 조회 오류!", e);
			throw e;
		}
		
	}
	
	@PostMapping("/insertMemo.do")
	public String insertMemo(@ModelAttribute Memo memo, 
							 RedirectAttributes redirectAttr) {
		try {
			log.debug("memo = {}", memo);
			//1. 업무로직
			int result = memoService.insertMemo(memo);
			String msg = "메모 등록 성공!";
			redirectAttr.addFlashAttribute("msg", msg);
			
		} catch(Exception e) {
			log.error("메모 등록 오류!", e);
			throw e;
		}
		
		return "redirect:/memo/memo.do";
	}
	
}
