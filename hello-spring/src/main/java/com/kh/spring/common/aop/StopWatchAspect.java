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

	//어느포인트에서 실행할것인가.
	//execution(* com.kh.spring.memo.controller..insert*(..)) 메소드가 insert인 모든 애들 컨트롤러에서만 보여줘 
	@Pointcut("execution(* com.kh.spring.memo.controller.MemoController.insertMemo(..))")
	public void pointcut() {}
	
	@Around("pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		//joinPoint 실행전
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.debug("----------START stopWatch----------");
		
		//joinPoint 실행전
		Object obj = joinPoint.proceed();
		
		stopWatch.stop();
	//stopWatch.getTotalTimeSeconds()를 사용하면 소요 시간을 초 단위로 출력
		log.debug("{} 소요선언 {}ms", methodName, stopWatch.getTotalTimeSeconds());
		
		return obj;
	}
}
