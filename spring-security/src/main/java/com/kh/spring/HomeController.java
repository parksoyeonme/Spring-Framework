package com.kh.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "forward:/index.jsp";
	}
	
	@GetMapping("/board/boardList.do")
	public void boardList() {
		
	}
	
	@GetMapping("/admin/memberList.do")
	public void memberList() {
		
		
	}
	
	@GetMapping("/error/accessDenied.do")
	public void accesDenied() {}
	
	
}
