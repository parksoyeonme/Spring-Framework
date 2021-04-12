package com.kh.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpSearch3Controller extends AbstractController {

	private EmpService empService = new EmpServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자입력값
		String[] jobCodeArr = request.getParameterValues("jobCode");
		String[] deptCodeArr = request.getParameterValues("deptCode");
		
		Map<String, Object> param = new HashMap<>();
		param.put("jobCodeArr", jobCodeArr);
		param.put("deptCodeArr", deptCodeArr);
		System.out.println("jobCodeArr@controller = " + Arrays.toString(jobCodeArr));
		System.out.println("deptCodeArr@controller = " + Arrays.toString(deptCodeArr));
				
		//2.업무로직
		//a.jsp에 job목록을 나열
		List<Map<String, String>> jobList = empService.selectJobList();
		System.out.println("jobList@controller = " + jobList);
		
		//b.jsp에 dept 목록을 나열
		List<Map<String, String>> deptList = empService.selectdeptList();
		System.out.println("deptList@controller = " + deptList);
		
		//c.직급/부서 검색
		List<Map<String, Object>> list = empService.search3(param);
		System.out.println("list@controller = " + list);
		
		
		//3.jsp위임
		request.setAttribute("jobList", jobList);
		request.setAttribute("list", list);
		request.setAttribute("deptList", deptList);
		
		return "emp/search3";
	}
	
	
	
	
}