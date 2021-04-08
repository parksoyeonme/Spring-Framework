package com.kh.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.exception.ControllerNotFoundException;
import com.kh.common.exception.MethodNotAllowedException;

/**
 * .do로 끝나는 모든 요청을 처리할 대표 Servlet
 * 사용자요청을 최초 받아서 controller객체의 메소드를 대신 호출해준다.
 * controller는 AbstractController(규격)를 상속해 doGet/doPost를 override하도록 한다.
 * 
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Properties prop = new Properties();
	Map<String, AbstractController> urlMap = new HashMap<>();
	
	
	/**
	 * 1. url.properties를 읽어서 prop에 저장
	 * 2. prop내용을 가지고 urlMap에 요소를 추가
	 */
	//init 딱한번만 실행
	public void init(ServletConfig config) throws ServletException {
		System.out.println("---------------- DispatcherServlet.init start ---------------");
		//1. prop
		//src/main/resources/url.properties가 아닌
		//Buildpath의 /url.properties를 읽어온다.
		String fileName = DispatcherServlet.class.getResource("/url.properties").getPath();
		System.out.println("fileName@Servlet = " +fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2. prop -> ulrMap
		Set<String> keys = prop.stringPropertyNames();
		for(String key : keys) {
			String value = prop.getProperty(key);
			//values(class명)을 가지고 객체 생성
			try {
				//class객체를 통해 제어 : reflection api
				Class clazz = Class.forName(value);
				AbstractController controller =
						(AbstractController)clazz.newInstance(); // ~~= new AbstractController한것과 동일
				//urlMap에 요소로 추가
				urlMap.put(key, controller);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.out.println("urlMap = " + urlMap);//Map에 담긴거 문자열아님. 객체
		System.out.println("--------------- DispatcherServlet.init end ---------------");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 요청주소
		String uri = request.getRequestURI(); // /mybatis/student/studentEnroll.do
		int beginIndex = request.getContextPath().length();
		String url = uri.substring(beginIndex); //길이인덱스가 곧 시작?
		
		//2. controller 호출
		AbstractController controller = urlMap.get(url);
		if(controller == null)
			throw new ControllerNotFoundException(url + "에 해당하는 controller가 없습니다.");
		
		String viewName = null;
		String method = request.getMethod();
		if("GET".equals(method)) 
			viewName = controller.doGet(request, response);
		else if("POST".equals(method))
			viewName = controller.doPost(request, response);
		else 
			throw new MethodNotAllowedException(method);
		//3. 응답처리: forwarding | redirect | pass
		
		if(viewName != null) {
			//redirect
			if(viewName.startsWith("redirect:")){
				String location = request.getContextPath()
								+ viewName.replace("redirect:", "");
				response.sendRedirect(location);
			}
			//forwarding
			else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				viewName = prefix + viewName + suffix;
				request.getRequestDispatcher(viewName)
						.forward(request, response);
			}
			
		}
		else {
			//비동기 json응답을 직접 처리한 경우는 아무것도 하지 않는다.	
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}