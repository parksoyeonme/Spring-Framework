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
	        <script>
	        $("#typeSelector").change(e => {
	        	const type = $(e.target).val();
	        	//console.log(type);
	        	
	        	$.ajax({
	        		url: `${pageContext.request.contextPath}/menus/\${type}`,
	        		dataType: "json",
	        		success(data){
	        			console.log(data);
	        			displayResultTable("#menuType-result", data);
	        		},
	        		error(xhr, status, err){
	        			console.log(xhr, status, err);
	        		}
	        	})
	        });
	        
	        </script>
	        
	        <!-- 2.POST -->
			<div class="menu-test">
				<h4>메뉴 등록하기(POST)</h4>
				<form id="menuEnrollFrm">
					<input type="text" name="restaurant" placeholder="음식점" class="form-control" />
					<br />
					<input type="text" name="name" placeholder="메뉴" class="form-control" />
					<br />
					<input type="number" name="price" placeholder="가격" class="form-control" />
					<br />
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="type" id="post-kr" value="kr" checked>
						<label for="post-kr" class="form-check-label">한식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="post-ch" value="ch">
						<label for="post-ch" class="form-check-label">중식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="post-jp" value="jp">
						<label for="post-jp" class="form-check-label">일식</label>&nbsp;
					</div>
					<br />
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="taste" id="post-hot" value="hot" checked>
						<label for="post-hot" class="form-check-label">매운맛</label>&nbsp;
						<input type="radio" class="form-check-input" name="taste" id="post-mild" value="mild">
						<label for="post-mild" class="form-check-label">순한맛</label>
					</div>
					<br />
					<input type="submit" class="btn btn-block btn-outline-success btn-send" value="등록" >
				</form>
			</div>
			<script>
			$("#menuEnrollFrm").submit(e => {
				//폼제출 방지
				e.preventDefault();
				
				const restaurant = $("[name=restaurant]", e.target).val();
				const name = $("[name=name]", e.target).val();
				const price = Number($("[name=price]", e.target).val());
				const type = $("[name=type]:checked", e.target).val();
				const taste = $("[name=taste]:checked", e.target).val();
				
				//유효성검사
				if(!restaurant || !name || !price){
					alert("입력폼을 모두 작성하세요.");
					return;
				}
				
				//하나하나찍는것이 아니라 담아서 한꺼번에
				//{restaurant: "abc", name: "def", price: 9800, type: "kr", taste: "hot"}
				const menu = {restaurant, name, price, type, taste};
				console.log(menu);
				
				//서버에 json형식으로 데이터 전달(요청메세지 body)
				$.ajax({
					url: `${pageContext.request.contextPath}/menu`,
					method: "POST",
					data: JSON.stringify(menu),
					contentType: "application/json; charset=utf-8",
					success({msg}){
						//console.log(data);
						//구조분해 할당
						alert(msg);
					},
					error(xhr, status, err){
						alert("메뉴 등록 실패  : " + status);
						console.log(xhr, status, err);
					},
					complete(){
						//폼 초기화
						$(e.target)[0].reset();
					}
				})
				
			});
			
			</script>
	        
	        <!-- #3.PUT -->
	        <!-- 
	        	@실습문제 : GET /menu/3  조회후, #menuUpdateFrm에 데이터 시각화할 것
	        	존재하지 않는 메뉴인경우, 404을 응답하고, 사용자 피드백줄것.
	        -->
			<div class="menu-test">
				<h4>메뉴 수정하기(PUT)</h4>
				<p>메뉴번호를 사용해 해당메뉴정보를 수정함.</p>
				<form id="menuSearchFrm">
					<input type="text" name="id" placeholder="메뉴번호" class="form-control" /><br />
					<input type="submit" class="btn btn-block btn-outline-primary btn-send" value="검색" >
				</form>
			
				<hr />
				<form id="menuUpdateFrm">
					<!-- where조건절에 사용할 id를 담아둠 -->
					<input type="hidden" name="id" />
					<input type="text" name="restaurant" placeholder="음식점" class="form-control" />
					<br />
					<input type="text" name="name" placeholder="메뉴" class="form-control" />
					<br />
					<input type="number" name="price" placeholder="가격" step="1000" class="form-control" />
					<br />
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="type" id="put-kr" value="kr" checked>
						<label for="put-kr" class="form-check-label">한식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="put-ch" value="ch">
						<label for="put-ch" class="form-check-label">중식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="put-jp" value="jp">
						<label for="put-jp" class="form-check-label">일식</label>&nbsp;
					</div>
					<br />
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="taste" id="put-hot" value="hot" checked>
						<label for="put-hot" class="form-check-label">매운맛</label>&nbsp;
						<input type="radio" class="form-check-input" name="taste" id="put-mild" value="mild">
						<label for="put-mild" class="form-check-label">순한맛</label>
					</div>
					<br />
					<input type="submit" class="btn btn-block btn-outline-success btn-send" value="수정" >
				</form>
			</div>
			<script>
			$(menuUpdateFrm).submit(e => {
				e.preventDefault();
				const $frm = $(e.target);	
				const id = Number($frm.find("[name=id]").val());
				const restaurant = $frm.find("[name=restaurant]").val();
				const name = $frm.find("[name=name]").val();
				const price = Number($frm.find("[name=price]").val());
				const type = $frm.find("[name=type]:checked").val();
				const taste = $frm.find("[name=taste]:checked").val();
				const menu = {id, restaurant, name, price, type, taste};
				console.log("menu = ", menu);
				
				$.ajax({
					url: `${pageContext.request.contextPath}/menu`,
					data: JSON.stringify(menu),
					contentType: "application/json; charset=utf-8",
					method: "PUT",
					success(data){
						console.log(data);
						const {msg} = data;
						alert(msg);
					},
					error(xhr, status, err){
						alert("메뉴 수정 오류 : " + status);
						console.log(xhr, status, err);
					},
					complete(){
						$frm[0].reset(); 
						$(menuSearchFrm)[0].reset();
					}
				});
				
				
			});
			
			$(menuSearchFrm).submit(e => {
				e.preventDefault(); // 폼 제출방지
				const id = $("[name=id]", e.target).val();
				if(!id) return;
				$.ajax({
					url: `${pageContext.request.contextPath}/menu/\${id}`,
					success(data){
						console.log(data);
						const $frm = $(menuUpdateFrm);
						const {id, restaurant, name, price, type, taste} = data;
						$frm.find("[name=id]").val(id);
						$frm.find("[name=restaurant]").val(restaurant);
						$frm.find("[name=name]").val(name);
						$frm.find("[name=price]").val(price);
						$frm.find(`[name=type][value=\${type}]`).prop("checked", true);
						$frm.find(`[name=taste][value=\${taste}]`).prop("checked", true);
						//.prop() 지정한 선택자를 가진 첫번째 요소의 속성값을 가져오거나 속성값을 추가
						
					},
					error(xhr, statusText, err){
						console.log(xhr, statusText, err);
						const {status} = xhr;
						status === 404 && alert("조회한 메뉴는 존재하지 않습니다.");
						$("[name=id]", e.target).select();
					}
				});
			});
			</script>
			
			<!-- @실습문제 : DELETE /menu/3 -->
			<div class="menu-test">
		    	<h4>메뉴 삭제하기(DELETE)</h4>
		    	<p>메뉴번호를 사용해 해당메뉴정보를 삭제함.</p>
		    	<form id="menuDeleteFrm">
		    		<input type="text" name="id" placeholder="메뉴번호" class="form-control" /><br />
		    		<input type="submit" class="btn btn-block btn-outline-danger btn-send" value="삭제" >
		    	</form>
		    </div>
		    <script>
		    $(menuDeleteFrm).submit(e => {
		    	e.preventDefault();
		    	const id = $("[name=id]", e.target).val();
		    	if(!id) return;
		    	
		    	$.ajax({
		    		url: `${pageContext.request.contextPath}/menu/\${id}`,
		    		method: "DELETE",
		    		success(data){
		    			console.log(data);
		    			const {msg} = data;
		    			alert(msg);
		    		},
		    		error(xhr, statusText, err){
		    			const {status} = xhr;
		    			switch(status){
			    			case 404: alert("해당 메뉴는 존재하지 않습니다."); break;
			    			default: alert("메뉴 삭제 실패 : " + statusText );
		    			}
		    			console.log(xhr, statusText, err);
		    		},
					complete(){
		    			e.target.reset(); //폼 초기화
		    			//$(menuDeleteFrm)[0].reset();
		    			//$(e.target)[0].reset();
		    		}		    		
		    	});
		    	
		    });
		    </script>	
		    
								        
	        
	        
	        
	        
	        
	        
	        
	        
	    </div>
	</section>
	<footer>
		<p>&lt;Copyright 2017. <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>
	</footer>
	
</div>
</body>
</html>