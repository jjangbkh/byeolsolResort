<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%Random random = new Random(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/findInfo.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/cus/findId"
	}
})
</script>
</head>
<body>
	<div class="allwrap">
		<div class="wrap">
			<header class="header">
				<jsp:include page="../header/sub.jsp"/>
			</header>
			<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>s">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>로그인</li>
							<li>》</li>
							<li>아이디/비밀번호 찾기</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3>아이디 / 비밀번호 찾기</h3>
					</div>
				</div>

				<div class="findForm">
					<form action="/cus/findId" method="post">
						<div class="findId_box">
							<h3>아이디 찾기</h3>
							<div class="findId_table">
								<table>
									<tr>
										<td>이름</td>
										<td><input type="text" name="name"></td>
									</tr>
									<tr>
										<td>이메일</td>
										<td><input type="email" name="email"></td>
									</tr>
								</table>
								<div>
									<input type="submit" value="아이디 찾기">
								</div>
							</div>
						</div>
					</form>

					<form action="/cus/findPassword" method="post">
						<div class="findPwd_box">
							<h3>비밀번호 찾기</h3>
							<table>
								<tr>
									<td>이름</td>
									<td><input type="text" name="name"></td>
								</tr>
								<tr>
									<td>아이디</td>
									<td><input type="text" name="userId"></td>
								</tr>
								<tr>
									<td>이메일</td>
									<td><input type="email" name="email"></td>
								</tr>
							</table>
							<div>
								<input type="submit" value="비밀번호 찾기">
							</div>
						</div>
					</form>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("myPage","myPageSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>