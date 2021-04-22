package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * #9. HandlerInteceptor
 * 
 * 1. handler 전처리 - preHandle => 로그인확인 인터셉터, 관리자권한 확인 인터셉터...
 * 2. handler 후처리 - postHandle (ModelAndView 참조가능) 
 * 3. view 후처리 - afterCompletion
 *
 */
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//무조건 실행되어야한다. return false면 절대안된다.
		//handler는 뒤이어 호출할 controller 객체를 말함.
		//반드시 super.preHandle을 호출해야함.
		//중간에 return false 하는 방식으로 전처리를 막을 수 있음.
		log.debug("====================================================");
		log.debug(request.getRequestURI());
		log.debug("----------------------------------------------------");
		return super.preHandle(request, response, handler); // 항상 true 리턴
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//handler호출
		super.postHandle(request, response, handler, modelAndView);
		
		log.debug("----------------------------------------------------");
		log.debug("mav = {}", modelAndView);
		log.debug("---------------------- view ------------------------");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
		log.debug("_____________________________________________________\n");
	
	}

	
	
}