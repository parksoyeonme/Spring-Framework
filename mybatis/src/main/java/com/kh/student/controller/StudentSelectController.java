package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;
import com.kh.student.model.vo.Student;

public class StudentSelectController extends AbstractController {
	private IStudentService studentService = new StudentService();
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자입력값
		int no = 0;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException ignored) {
		
		}
		
		//2.업무로직
		//a.전체학생수 조회
		int totalStudents = studentService.selectTotalStudents();
		System.out.println("totalStudents@controller = " + totalStudents);
		
		//b.학생1명조회 vo
		if(no != 0) {
			Student student = studentService.selectOne(no);
			System.out.println("student@controller = " + student);
			request.setAttribute("student", student);
		}
		
		
		//3.jsp에 html작성 위임.
		request.setAttribute("count", totalStudents);
		
		return "student/selectStudent";
	}

	
}