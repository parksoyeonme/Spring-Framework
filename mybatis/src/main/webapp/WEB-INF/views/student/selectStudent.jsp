<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 실습</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:10px auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
	line-height: 2em; /*input태그로 인해 cell이 높이가 길어져 border 두줄현상방지 */
}
table.tbl-student th{text-align:right;}
table.tbl-student td{text-align:left;}
table.tbl-student tr:last-of-type td:first-child{text-align:center;}
</style>
<c:if test="${not empty msg}">
<script>
alert("${msg}");
<%-- msg 1회 사용후 삭제 --%>
<c:remove var="msg" scope="session"/>
</script>
</c:if>
</head>
<body>
	<div id="student-container">
		<h2>학생정보 검색</h2>
		<p>총 학생수는 ${requestScope.count}명입니다.</p>
		<form>
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="${param.no}" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		
		<hr />
		<%-- 조회된 결과가 있는 경우 --%>
		<c:if test="${not empty student}">
		<h1>학생 정보 수정</h1>
		<form 
			name="studentUpdateFrm"
			action="${pageContext.request.contextPath}/student/updateStudent.do"
			method="POST">
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="${student.no}" required readonly/>
					</td>
				</tr>
				<tr>
					<th>학생이름</th>
					<td>
						<input type="text" name="name" value="${student.name}" required/>
					</td>
				</tr>
				<tr>
					<th>학생전화번호</th>
					<td>
						<input type="tel" name="tel" value="${student.tel}" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정" />
						<input type="button" value="삭제" onclick="deleteStudent();" />
					</td>
				</tr>
			</table>
		</form>
		</c:if>
		
		<%-- 조회된 결과가 없는 경우 --%>
		<c:if test="${empty student && not empty param.no}">
		<p>${param.no}번 학생은 존재하지 않습니다.</p>
		</c:if>
		
		<hr />
		<h1>학생 정보 조회(ajax)</h1>
		<table class="tbl-student">
			<tr>
				<th>학생번호</th>
				<td>
					<input type="number" id="studentNo" required/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="ajaxSelectStudentBtn" value="검색" />
				</td>
			</tr>
		</table>
		<table id="ajaxSelectStudent" class="tbl-student">
			<tr>
				<th>학생번호</th>
				<td>
					<input type="number" name="no" readonly/>
				</td>
			</tr>
			<tr>
				<th>학생이름</th>
				<td>
					<input type="text" name="name" readonly/>
				</td>
			</tr>
			<tr>
				<th>학생전화번호</th>
				<td>
					<input type="tel" name="tel" readonly/>
				</td>
			</tr>
		</table>
		
		
		
		
	</div>
	
<script>
$("#ajaxSelectStudentBtn").click(function(){
	var no = $("#studentNo").val();
	if(!no) return;
	
	$.ajax({
		url: "${pageContext.request.contextPath}/student/selectStudentMap.do",
		data: {
			no : no
		},
		dataType: "json",
		success: function(data){
			console.log(data);
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
	
});


function deleteStudent(){
	$(document.studentUpdateFrm)
		.attr("action", "${pageContext.request.contextPath}/student/deleteStudent.do")
		.submit();
}
</script>
</body>
</html>