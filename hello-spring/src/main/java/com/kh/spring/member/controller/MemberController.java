package com.kh.spring.member.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/member")
@SessionAttributes(value = {"loginMember", "anotherValue"})
//하나일때는 @SessionAttributes("loginMember")라고쓰면댐
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder; 
	
	@GetMapping("/memberEnroll.do")
	public void memberEnroll() {
		//ViewTranslator에 의해서 요청url에서 view단 jsp주소를 추론한다.
		
//		return "member/memberEnroll";
	}
	
	@PostMapping("/memberEnroll.do")
	public String memberEnroll(Member member, RedirectAttributes redirectAttr) {
		log.info("member = {}", member);
		
		try {
			//0. 암호화처리
			String rawPassword = member.getPassword();
			String encodedPassword = bcryptPasswordEncoder.encode(rawPassword);
			log.info("rawPassword = {}", rawPassword);
			log.info("encodedPassword = {}",encodedPassword);
			member.setPassword(encodedPassword);
			
			//1. 업무로직	
			int result = memberService.insertMember(member);
			String msg = result > 0 ? "회원 등록 성공" : "회원 등록 실패";
			//2.사용자피드백 준비 및 리다이렉트
			redirectAttr.addFlashAttribute("msg", msg);
		} catch(Exception e) {
			//1.로깅작업
			log.error(e.getMessage(),e);
			//2.다시spring container에 던질것.
			throw e;
		}
		return "redirect:/";
	}
	
	
	/*
	 * 커맨드객체 이용시 사용자 입력값(String)을 특정필드타입으로 변환할editor 객체를 설정
	 * 
	 * 
	 * */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Member.birthday:java.sql.Date 타입처리
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//커스텀에디터 생성 : allowEmpty - true(빈문자열을 null로 변환처리하여 허용)
		PropertyEditor editor = new CustomDateEditor(sdf, true);
		binder.registerCustomEditor(java.sql.Date.class, editor);
	}
	
	@GetMapping("/login.do")
	public void login() {
		// /WEB-INF/views/member/login.jsp
	}
	
	@PostMapping("/login.do")
	public String login(
			@RequestParam String id,
			@RequestParam String password,
			Model model,
			RedirectAttributes redirectAttr
	) {
		try {
			log.info("id = {}, password = {}", id, password);
			//1. 업무로직 : 해당 id의 member조회
			Member member = memberService.selectOneMember(id);
			log.info("member = {}", member);
			
			//2. 로그인 여부처리
			if(member != null && bcryptPasswordEncoder.matches(password, member.getPassword())) {
				//로그인 성공
				//기본값으로 request scope 속성에 저장.
				//클래스레벨에 @SessionAttributes("loginMember") 지정하면, session scope에 저장
				model.addAttribute("loginMember", member);
			}
			else {
				//로그인 실패
				redirectAttr.addFlashAttribute("msg","아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			//3. 사용자피드백 및 리다이렉트
		} catch(Exception e) {
			//1.로깅작업
			log.error(e.getMessage(),e);
			//2.다시spring container에게 예외를 다시 던져서 error페이지로 이동시킨다.
			throw e;
		}
		return "redirect:/";
	}
	
	
}