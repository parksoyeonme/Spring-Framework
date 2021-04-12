package com.kh.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpDao {

	List<Map<String, Object>> selectAll(SqlSession session);

	List<Map<String, Object>> search1(SqlSession session, Map<String, String> param);

	List<Map<String, Object>> search2(SqlSession session, Map<String, String> param);

	List<Map<String, String>> selectJobList(SqlSession session);

	List<Map<String, Object>> search3(SqlSession session, Map<String, Object> param);

	List<Map<String, String>> selectdeptList(SqlSession session);

}