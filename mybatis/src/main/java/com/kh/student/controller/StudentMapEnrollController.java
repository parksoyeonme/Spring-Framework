package com.kh.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;

public class StudentMapEnrollController extends AbstractController {
	private IStudentService studentService = new StudentService();
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 사용자입력값
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		Map<String, Object> studentMap = new HashMap<>();
		studentMap.put("name", name);
		studentMap.put("tel", tel);
		System.out.println("studentMap@controller = " + studentMap);
		
		//2. 업무로직
		int result = studentService.insertStudentMap(studentMap);
		String msg = result > 0 ? "학생 등록 성공!" : "학생 등록 실패!";
		
		//3. 리다이렉트 & 사용자피드백
		request.getSession().setAttribute("msg", msg);
		
		return "redirect:/student/studentEnroll.do";
	}
	
	
}