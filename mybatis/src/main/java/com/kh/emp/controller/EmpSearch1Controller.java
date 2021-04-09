package com.kh.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpSearch1Controller extends AbstractController {

	private EmpService empService = new EmpServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@controller = " + param);
		
		//2. 업무로직
		List<Map<String, Object>> list = null;
		if(searchType == null || searchKeyword == null)
			list = empService.selectAll();
		else
			list = empService.search1(param);
		
		System.out.println("list@controller = " + list);
		
		//3. jsp 위임
		request.setAttribute("list", list);
		
		return "emp/search1";
	}
	
	
}