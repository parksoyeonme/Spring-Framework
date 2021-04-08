package com.kh.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.exception.MethodNotAllowedException;

/**
 * controller의 규격을 제시하는 추상클래스
 * 
 *
 */
public abstract class AbstractController {
	
	/**
	 * controller에서 필요한 메소드를 override해서 사용하도록한다. 
	 * override없이 호출하면, 예외를 던진다.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @return jsp경로 또는 redirect의 location 변수 
	 */
	public String doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		throw new MethodNotAllowedException("GET");
	}
	
	public String doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		throw new MethodNotAllowedException("POST");
	}
	
	
}