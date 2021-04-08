package com.kh.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.vo.Student;

public interface IStudentDao {

	int insertStudent(SqlSession session, Student stdt);

	int insertStudentMap(SqlSession session, Map<String, Object> studentMap);

	int selectTotalStudents(SqlSession session);

	Student selectOne(SqlSession session, int no);

	int updateStudent(SqlSession session, Map<String, Object> map);

	int deleteStudent(SqlSession session, int no);

	Map<String, Object> selectStudentMap(SqlSession session, int no);

}