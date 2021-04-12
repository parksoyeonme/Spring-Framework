package com.kh.emp.model.service;

import static com.kh.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.emp.model.dao.EmpDao;
import com.kh.emp.model.dao.EmpDaoImpl;
public class EmpServiceImpl implements EmpService {
	private EmpDao empDao = new EmpDaoImpl();

	@Override
	public List<Map<String, Object>> selectAll() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.selectAll(session);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search1(Map<String, String> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.search1(session, param);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search2(Map<String, String> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.search2(session, param);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, String>> selectJobList() {
		SqlSession session = getSqlSession();
		List<Map<String, String>> jobList = empDao.selectJobList(session);
		session.close();
		return jobList;
	}

	@Override
	public List<Map<String, Object>> search3(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.search3(session, param);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, String>> selectdeptList() {
		SqlSession session = getSqlSession();
		List<Map<String, String>> deptList = empDao.selectdeptList(session);
		session.close();
		return deptList;
	}

	
	
	
}