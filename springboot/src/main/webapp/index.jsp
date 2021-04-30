<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Springboooooooooot</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- bootstrap js: jquery load 이후에 작성할것.-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

<!-- 사용자작성 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" />

<style>
	div#menu-container{text-align:center;}
	div.menu-test{width:50%; margin:0 auto; text-align:center;}
	div.result{width:70%; margin:0 auto;}
</style>
<script>
function displayResultTable(selector, data){
	const $container = $(selector);
	const $table = $("<table></table>").addClass("table");
	const $header = $("<tr><th>번호</th><th>음식점</th><th>메뉴명</th><th>가격</th><th>타입</th><th>맛</th></tr>")
	$table.append($header);
	
	if(data.length > 0){
		$(data).each((i, {id, restaurant, name, price, type, taste}) =>{
			const tr = `<tr>
				<td>\${id}</td>
				<td>\${restaurant}</td>
				<td>\${name}</td>
				<td>\${price}</td>
				<td>\${type}</td>
				<td>\${taste}</td>
			</tr>`; 
			$table.append(tr);
		});		
	}
	else {
		$table.append("<tr><td colspan='6'>조회된 결과가 없습니다.</td></tr>");
	}
	
	$container.html($table);
}
</script>	
</head>
<body>
<div id="container">
	<header>
		<div id="header-container">
			<h2>Hello Springboooooooooot</h2>
		</div>
		<!-- https://getbootstrap.com/docs/4.0/components/navbar/ -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">
				<img src="${pageContext.request.contextPath }/resources/images/logo-spring.png" alt="스프링로고" width="50px" />
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
		  	</button>
		</nav>
	</header>
	<section id="content">
		<div id="menu-container">
	        <!-- 1. GET /menus-->
	        <div class="menu-test">
	            <h4>전체메뉴조회(GET)</h4>
	            <input type="button" class="btn btn-block btn-outline-success btn-send" id="btn-menus" value="전송" />
	        </div>
	        <div class="result" id="menus-result"></div>
	        <script>
	        $("#btn-menus").click(e => {
				$.ajax({
					url: "${pageContext.request.contextPath}/menus",
					method: "GET",
					dataType: "json",
					success: data => {
						console.log(data);
						displayResultTable("#menus-result", data);
					},
					/* error: (xhr, status, err) => console.log(xhr, status, err) */
					error: console.log
				});	        	
	        });
	        </script>
	        	
	        <!-- 2.GET /menus/kr /menus/ch /menus/jp 타입별 조회 -->
	        <div class="menu-test">
	            <h4>메뉴 타입별 조회(GET)</h4>
	            <select class="form-control" id="typeSelector">
	            <option value="" disabled selected>음식타입선택</option>
	            <option value="kr">한식</option>
	            <option value="ch">중식</option>
	            <option value="jp">일식</option>
	            </select>
	        </div>
	        <div class="result" id="menuType-result"></div>
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	    </div>
	</section>
	<footer>
		<p>&lt;Copyright 2017. <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>
	</footer>
	
</div>
</body>
</html>