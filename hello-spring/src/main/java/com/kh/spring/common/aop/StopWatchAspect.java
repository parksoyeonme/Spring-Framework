package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class StopWatchAspect {

	@Pointcut("execution(* com.kh.spring.memo.controller.MemoController.insertMemo(..))")
	public void pointcut() {}
	
	@Around("pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.debug("----------START stopWatch----------");
		
		Object obj = joinPoint.proceed();
		
		stopWatch.stop();
	//stopWatch.getTotalTimeSeconds()를 사용하면 소요 시간을 초 단위로 출력
		log.debug("stopWatch  = {}ms", stopWatch.getTotalTimeSeconds());
		
		return obj;
	}
}
