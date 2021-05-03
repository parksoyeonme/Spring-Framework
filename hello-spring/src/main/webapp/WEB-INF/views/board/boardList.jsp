<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style>
/*글쓰기버튼*/
input#btn-add{float:right; margin: 0 0 15px;}
</style>
<script>
function goBoardForm(){
	location.href = "${pageContext.request.contextPath}/board/boardForm.do";
}

$(() => {
	$("tr[data-no]").click(e => {
		//e.target -> td
		var $tr = $(e.target).parent();
		var no = $tr.data("no");
		//console.log(no);
		
		location.href = `${pageContext.request.contextPath}/board/boardDetail.do?no=\${no}`;
	});
	
    $( "#searchTitle" ).autocomplete({
      source: function(request, response){
    	  //서버통신 이후 success메소드에서 response를 호출할 것!
    	  //console.log(request); // 사용자 입력값
    	  //console.log(response); // response([{label:?, value:?}, {label:?, value:?},....])
    	  
    	  //ajax호출
    	  $.ajax({
    		  url: `${pageContext.request.contextPath}/board/searchTitle.do`,
    		  data: {
    			  searchTitle: request.term
    		  },
    		  //method: "GET",
    		  //dataType: "json"
    		  success(data){
    			  //console.log(data);
    			  //화살표 함수 객체로 사용시에는 ()한번더 감싸줘야한다
    			  var res = $.map(data, (board) => ({
    				  label: board.title,
    				  value: board.title,
    				  no: board.no
    			  }));
    			  //console.log(res);
    			  response(res);
    		  },
    		  error(xhr, status, err){
    			  console.log(xhr, status, err);
    		  }
    		  
    	  })
    	  
      },
      focus: function(){ return false },
      select: function(e, selected){
    	  //console.log(e);
    	  //console.log(selected.item.no);
    	  const no = selected.item.no;
    	  location.href = `${pageContext.request.contextPath}/board/boardDetail.do?no=\${no}`;
      }
    });
});


</script>
<section id="board-container" class="container">
	<input type="search" placeholder="제목 검색..." id="searchTitle" class="form-control col-sm-3 d-inline"/>
	<input type="button" value="글쓰기" id="btn-add" class="btn btn-outline-success" onclick="goBoardForm();"/>
	<table id="tbl-board" class="table table-striped table-hover">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th> <!-- 첨부파일 있을 경우, /resources/images/file.png 표시 width: 16px-->
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="board">
		<tr data-no="${board.no}">
			<td>${board.no}</td>
			<td>${board.title}</td>
			<td>${board.memberId}</td>
			<td><fmt:formatDate value="${board.regDate}" pattern="yy/MM/dd"/></td>
			<td>
				<c:if test="${board.attachCount gt 0}">
				<img alt="file" src="${pageContext.request.contextPath}/resources/images/file.png" width="16px">
				</c:if>
			</td>
			<td>${board.readCount}</td>
		</tr>
		</c:forEach>
		
	</table>
	${pageBar}
</section> 

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>