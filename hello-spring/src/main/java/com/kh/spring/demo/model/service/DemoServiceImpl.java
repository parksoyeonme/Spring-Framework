package com.kh.spring.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	//인터페이스 타입으로 제어
	private DemoDao demodao;

}
