package com.kh.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.vo.Student;

public class StudentDao implements IStudentDao {

	@Override
	public int insertStudent(SqlSession session, Student stdt) {
		return session.insert("student.insertStudent", stdt);
	}

	@Override
	public int insertStudentMap(SqlSession session, Map<String, Object> studentMap) {
		return session.insert("student.insertStudentMap", studentMap);
	}

	@Override
	public int selectTotalStudents(SqlSession session) {
		return session.selectOne("student.selectTotalStudents");
	}

	@Override
	public Student selectOne(SqlSession session, int no) {
		System.out.println("no@dao = " + no);
		return session.selectOne("student.selectOne", no);
	}

	@Override
	public int updateStudent(SqlSession session, Map<String, Object> map) {
		return session.update("student.updateStudent", map);
	}

	@Override
	public int deleteStudent(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return session.insert("student.deleteStudent", no);
	}

	@Override
	public Map<String, Object> selectStudentMap(SqlSession session, int no) {
		//한줄은 무조거 selectOne
		return session.selectOne("student.selectStudentMap", no);
	}

	@Override
	public List<Student> selectStudentList(SqlSession session) {
		// 한행이 아니라 여러행이기 때문에
		return session.selectList("student.selectStudentList");
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList(SqlSession session) {
		return session.selectList("student.selectStudentMapList");
	}
	
	

}