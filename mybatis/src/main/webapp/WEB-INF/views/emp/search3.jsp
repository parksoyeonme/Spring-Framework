<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String[] jobCodeArr = request.getParameterValues("jobCode");
	List<String> jobCodeList = null;
	if(jobCodeArr != null) jobCodeList = Arrays.asList(jobCodeArr);
	
	String[] deptCodeArr = request.getParameterValues("deptCode");
	List<String> deptCodeList = null;
	if(deptCodeArr != null) deptCodeList = Arrays.asList(deptCodeArr);
	
	//page속성에 저장 -> EL로 접근
	pageContext.setAttribute("jobCodeList", jobCodeList);
	pageContext.setAttribute("deptCodeList", deptCodeList);
%>
<fmt:setLocale value="ko_kr"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습</title>
<style>
div#emp-container{text-align:center;}
table.tbl-emp{
	margin:0 auto;
	border:1px solid; 
	border-collapse:collapse;
}
table.tbl-emp th, table.tbl-emp td{
	border:1px solid;
	padding:5px;
	background:lightseagreen;
}
div#search-container{
	padding:15px 0;
}
table#tbl-search{
	margin:0 auto;
}
table#tbl-search th,table#tbl-search td{padding:5px 15px;}
table#tbl-search td{text-align:left;}

</style>
</head>
<body>
<div id="emp-container">
	<h2>사원정보 </h2>
	
	<div id="search-container">
		<form>
			<table id="tbl-search">
				<tr>
					<th>직급</th>
					<td>
						<c:forEach items="${jobList}" var="job" varStatus="vs">
						<input type="checkbox" name="jobCode" id="jobCode${vs.index}" value="${job.jobCode}" ${jobCodeList.contains(job.jobCode) ? 'checked' : ''}/><label for="jobCode${vs.index}">${job.jobName}</label>
						&nbsp;
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>부서</th>
					<td>
						<c:forEach items="${deptList}" var="dept" varStatus="vs">
						<input type="checkbox" name="deptCode" id="deptCode${vs.count}" value="${dept.deptCode}" ${deptCodeList.contains(dept.deptCode) ? 'checked' : ''} /><label for="deptCode${vs.count}">${dept.deptTitle}</label>
						&nbsp;
						</c:forEach>
						
						<!-- 인턴도 조회가 가능하도록 할것 -->
						<input type="checkbox" name="deptCode" id="deptCode0" value="D0" ${deptCodeList.contains('D0') ? 'checked' : ''}/>
						<label for="deptCode0">인턴</label>
					</td>
				</tr>
				
				<tr>
					<th colspan="2">
						<input type="submit" value="검색" />
					</th>
				</tr>
			</table>
		</form>
	</div>
	
	<table class="tbl-emp">
		<tr>
			<th></th><!-- 1부터 넘버링 처리 -->
			<th>사번</th>
			<th>사원명</th>
			<th>주민번호</th><!--뒷6자리는 ******처리-->
			<th>성별</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>부서명</th>
			<th>직급명</th>
			<th>급여레벨</th>
			<th>급여</th><!--원화기호, 세자리마다 콤마표시-->
			<th>보너스율</th><!--percent로 표시-->
			<th>매니져 사번</th>
			<th>입사일</th><!--날짜형식 yyyy/MM/dd-->
			<th>퇴사여부</th>
		</tr>
		<!-- 조회된 데이터가 있는 경우와 없는 경우를 분기처리 하세요 -->
		<c:if test="${empty list}">
		<tr>
			<td colspan="15" style="text-align:center;">조회된 데이터가 없습니다.</td>
		</tr>
		</c:if>
		<c:if test="${not empty list}">
		<c:forEach items="${list}" var="emp" varStatus="vs">
		<tr>
			<td>${vs.count}</td>
			<td>${emp.empId}</td>
			<td>${emp.empName}</td>
			<td>${fn:substring(emp.empNo, 0, 8)}******</td>
			<td>${emp.gender}</td>
			<td>${emp.email}</td>
			<td>${emp.phone}</td>
			<td>${not empty emp.deptTitle ? emp.deptTitle : '인턴'}</td>
			<td>${emp.jobName}</td>
			<td>${emp.salLevel}</td>
			<td><fmt:formatNumber value="${emp.salary}" type="currency"/></td>
			<td><fmt:formatNumber value="${emp.bonus}" type="percent"/></td>
			<td>${emp.managerId}</td>
			<td><fmt:formatDate value="${emp.hireDate}" pattern="yyyy/MM/dd"/> </td>
			<td>${emp.quitYn}</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
</div>

</body>
</html>