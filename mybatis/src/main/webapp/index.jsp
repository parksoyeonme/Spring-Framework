<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mybatis</title>
</head>
<body>
	<h1>Mybatis</h1>
	<h2>student</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/student/studentEnroll.do">학생 등록</a></li>
		<li><a href="${pageContext.request.contextPath}/student/selectStudent.do">학생 조회 & 수정/삭제</a></li>
		<li><a href="${pageContext.request.contextPath}/student/selectStudentList.do">학생 목록 조회</a></li>
	</ul>

	<h2>emp</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/emp/searchEmp1.do">사원 조회1</a></li>
		<li><a href="${pageContext.request.contextPath}/emp/searchEmp2.do">사원 조회2</a></li>
		<li><a href="${pageContext.request.contextPath}/emp/searchEmp3.do">사원 조회3</a></li>
	</ul>
	
	
	
	
	
</body>
</html>