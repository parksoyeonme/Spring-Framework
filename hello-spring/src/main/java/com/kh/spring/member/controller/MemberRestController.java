package com.kh.spring.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.common.HelloSpringUtils;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * @RestController : 모든 handler가 @ResponseBody 처리된다.
 * (@Controller + @Component)
 * 
 * 
 *
 */
@RestController
@Slf4j
@RequestMapping("/member")
public class MemberRestController {

	@Autowired
	private MemberService memberService;
	
	/**
	 * 모든회원을 조회하는 요청
	 * 페이징 처리 필수
	 * cPage = 1(기본값)
	 * numPerPage = 5
	 * 
	 * Mybatis RowBounds 사용할 것
	 * 
	 * 
	 * 
	 * */
//	@GetMapping("/")
//	public List<Member> memberList(@RequestParam(defaultValue ="1") int cPage, Model model){
//		
//		log.debug("/member 요청!");
//		
//		//1. 사용자입력값
//		int numPerPage = 5;
//		log.debug("cPage = {}", cPage);
//		Map<String, Object> param = new HashMap<>();
//			param.put("numPerPage", numPerPage);
//			param.put("cPage", cPage);
//			
//		
//		//2. 업무로직
//		List<Member> list = memberService.selectAll(param);
//		log.debug("list = {}", list);
//		
//		//3. jsp처리위임
//		model.addAttribute("list", list);
//		
//		
//		return list;
//	}
	
	@GetMapping("/")
	public List<Member> memberList(@RequestParam(defaultValue = "1") int cPage){
		final int numPerPage = 5;
		
		int offset = (cPage - 1) * numPerPage;
		int limit = numPerPage;
		
		Map<String, Object> param = new HashMap<>();
		param.put("offset", offset);
		param.put("limit", limit);
		
		List<Member> list = memberService.selectAll(param);
	
		return list;
	}
	
	//@PathVariable : URL 경로에 변수를 넣어주는거
	@GetMapping("/{id}")
	public ResponseEntity<?> member(@PathVariable String id) {
		log.debug("id = {}", id);
		Member member = memberService.selectOneMember(id);
		if(member == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(member);
	}
	
}