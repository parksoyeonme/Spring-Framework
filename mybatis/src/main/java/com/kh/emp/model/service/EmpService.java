package com.kh.emp.model.service;

import java.util.List;
import java.util.Map;

public interface EmpService {

	List<Map<String, Object>> selectAll();

	List<Map<String, Object>> search1(Map<String, String> param);

	List<Map<String, Object>> search2(Map<String, String> param);

}