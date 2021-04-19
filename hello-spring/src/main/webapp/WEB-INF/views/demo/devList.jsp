<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 목록" name="title"/>
</jsp:include>
<table class="table w-75 mx-auto">
    <tr>
      <th scope="col">번호</th>
      <th scope="col">이름</th>
      <th scope="col">경력</th>
      <th scope="col">이메일</th>
      <th scope="col">성별</th>
      <th scope="col">개발가능언어</th>
      <th scope="col">수정</th>
      <th scope="col">삭제</th>
    </tr>
    <tr>
	   <c:forEach items="${list}" var="dev">
		   	<td>${dev.no}</td>
		   	<td>${dev.name}</td>
		   	<td>${dev.career}</td>
		   	<td>${dev.email}</td>
		   	<td>${dev.gender == 'M' ? '남' : (dev.gender == 'F' ? '여' : '')}</td>
		   	<td>
			   	<c:forEach items="${dev.lang}" var="lang" varStatus="vs">
				${lang}${vs.last ? '' : ','}
				</c:forEach>
			</td>
			<td>
			<button type="button" class="btn btn-outline-info" onclick="updateDev(${dev.no});">수정</button>
		</td>
		<td>
			<button type="button" class="btn btn-outline-danger" onclick="deleteDev(${dev.no});">삭제</button>
		</td>
	</tr>
	</c:forEach>
 
</table>
<script>
function updateDev(no){
	location.href = `${pageContext.request.contextPath}/demo/updateDev.do?no=\${no}`;
}

function deleteDev(no){
	location.href = `${pageContext.request.contextPath}/demo/deleteDev.do?no=\${no}`;
}
</script>	
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>