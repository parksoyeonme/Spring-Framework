package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;
import com.kh.student.model.vo.Student;

public class StudentEnrollController extends AbstractController {

	private IStudentService studentService = new StudentService();
	
	/**
	 * 학생 등록페이지 요청
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp포워딩용
		return "student/studentEnroll";
	}

	/**
	 * 학생정보 DB 등록
	 */
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		Student stdt = new Student();
		stdt.setName(name);
		stdt.setTel(tel);
		System.out.println("student@controller = " + stdt);
		

		//2. 업무로직
		int result = studentService.insertStudent(stdt);
		String msg = (result > 0) ? "학생 등록 성공!" : "학생 등록 실패!";
		System.out.println(msg);
		
		
		//3. 리다이렉트 및 사용자피드백
		request.getSession().setAttribute("msg", msg);
		
		return "redirect:/student/studentEnroll.do";
	}

	
}