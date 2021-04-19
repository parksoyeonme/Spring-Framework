package com.kh.spring.demo.model.dao;

import java.util.List;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoDao {

	int insertDev(Dev dev);

	List<Dev> selectDevList();

	int updateDev(Dev dev);

	Dev selectOneDev(int no);

	int deleteDev(int no);

	


}
