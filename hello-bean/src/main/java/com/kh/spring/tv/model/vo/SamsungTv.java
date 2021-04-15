package com.kh.spring.tv.model.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 빈으로 대신 등록한 것
public class SamsungTv implements Tv {

	//RemoteControl빈을 생성해서 대신 setting 요청
	//해당타입의 bean을 찾아 의존객체 주입(Dependency Injection)
	//타입 - bean id 순으로 container에서 조회
//	@Autowired
	private RemoteControl remocon;

	//2번방법
//	@Autowired
	public void setRemocon(RemoteControl remocon) {
		this.remocon = remocon;
	}
	
	
	public SamsungTv() {
		System.out.println("SamsungTv 객체 생성!");
	}
	
	//3번방법
	@Autowired
	public SamsungTv(RemoteControl remocon) {
		System.out.println("SamsungTv(RomoteControl) 객체 생성!"); //SamsungTv 객체 생성!이거 대신 생성됨
		this.remocon = remocon;
	}
	
	public void powerOn() {
		System.out.println("SamsungTv의 전원을 켰습니다.");
	}
	
	public void changeChannel(int no) {
		System.out.println(remocon); //com.kh.spring.tv.model.vo.RemoteControl@3f6b0be5 spirng이 해준일
		this.remocon.changeChannel(no);
	}

}