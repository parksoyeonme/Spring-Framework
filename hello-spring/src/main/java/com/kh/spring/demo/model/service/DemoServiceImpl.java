package com.kh.spring.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;
import com.kh.spring.demo.model.vo.Dev;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	//인터페이스 타입으로 제어
	private DemoDao demoDao;

	@Override
	public int insertDev(Dev dev) {
		//1.SqlSession객체 생성
		//2.dao 업무요청
		//3.트랜잭션처리(DML)
		//4.SqlSession반납
		int result = demoDao.insertDev(dev);
		return result;
	}

	@Override
	public List<Dev> selectDevList() {
		//1.SqlSession객체 생성
		//2.dao 업무요청
		List<Dev> list = demoDao.selectDevList();
		//3.트랜잭션처리(DML)
		//4.SqlSession반납
		return list;
	}

	@Override
	public int updateDev(Dev dev) {
		//1.SqlSession객체 생성
		//2.dao 업무요청
		//3.트랜잭션처리(DML)
		//4.SqlSession반납
		int result = demoDao.updateDev(dev);
		return result;
	}

	@Override
	public Dev selectOneDev(int no) {
		return demoDao.selectOneDev(no);
	}

	@Override
	public int deleteDev(int no) {
		//1.SqlSession객체 생성
		//2.dao 업무요청
		//3.트랜잭션처리(DML)
		//4.SqlSession반납
		int result = demoDao.deleteDev(no);
		return result;
	}

	

	

}
