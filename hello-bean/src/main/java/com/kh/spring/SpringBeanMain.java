package com.kh.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kh.spring.tv.model.vo.LgTv;
import com.kh.spring.tv.model.vo.SamsungTv;
import com.kh.spring.tv.model.vo.Tv;
public class SpringBeanMain {

	public static void main(String[] args) {
		//1. spring ApplicationContext(BeanFactory)를 생성
		//의존라이브러리라고 부름
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		System.out.println("===================Spring Container 초기화 완료==========================");
		//2. ApplicationContext에 Bean요청
		//a.타입으로 요청하기
		Tv lgTv1 = applicationContext.getBean(LgTv.class);
		lgTv1.powerOn(); //어플리케이션 컨텍스트에 생성할떄 이미 객체로 생성이 되는구나
		
		Tv lgTv2 = applicationContext.getBean(LgTv.class);
		lgTv2.powerOn();
		
		//getBean으로 리턴된 객체는 동일한 객체를 재사용한다.
		System.out.println(lgTv1 == lgTv2);
		System.out.println(lgTv1.hashCode());
		System.out.println(lgTv2.hashCode());
		
		//b.이름(id)으로 요청하기
		SamsungTv samsungTv = (SamsungTv)applicationContext.getBean("samsungTv");
		samsungTv.powerOn();
		samsungTv.changeChannel(10);
//		순수자바
//		Tv lgtv = new LgTv();
//		lgtv.powerOn();
		
	}

}
