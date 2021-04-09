<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습 - selectList</title>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:0 auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
}
</style>
</head>
<body>
<div id="student-container">
	<h2>selectList</h2>
	<p>SqlSession의 selectList메소드를 호출해서 List&lt;Student>를 리턴받음.</p>
	<table class="tbl-student">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>전화번호</th>
		</tr>
		<c:forEach items="${list}" var="student">
		<tr>
			<td>${student.no}</td>
			<td>${student.name}</td>
			<td>${student.tel}</td>
		</tr>
		</c:forEach>
	</table>
	
	<<hr />
	
	<p>SqlSession의 selectList메소드를 호출해서 List&lt;Map>를 리턴받음.</p>
	<table class="tbl-student">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>전화번호</th>
		</tr>
		<c:forEach items="${mapList}" var="studentMap">
		<tr>
			<td>${studentMap.studentNo}</td>
			<td>${studentMap.studentName}</td>
			<td>${studentMap.studentTel}</td>
		</tr>
		</c:forEach>
	</table>
</div>
	
	
</body>
</html>