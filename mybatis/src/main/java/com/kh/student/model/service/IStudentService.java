package com.kh.student.model.service;

import java.util.List;
import java.util.Map;

import com.kh.student.model.vo.Student;

public interface IStudentService {

	int insertStudent(Student stdt);

	int insertStudentMap(Map<String, Object> studentMap);

	int selectTotalStudents();

	Student selectOne(int no);

	int updateStudent(Map<String, Object> map);

	int deleteStudent(int no);

	Map<String, Object> selectStudentMap(int no);

	List<Student> selectStudentList();

	List<Map<String, Object>> selectStudentMapList();

}