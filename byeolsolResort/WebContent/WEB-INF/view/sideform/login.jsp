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
<link rel="stylesheet" href="/css/login.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/login.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/cus/login"
	}
})
</script>
</head>
<body>
	<div class="allwrap">
		<div class="wrap">
			<header class="header">
				<jsp:include page="../header/sub.jsp" />
			</header>
				<img class="subbanner"
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>로그인</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">로그인</h3>
					</div>
					<div class="loginForm">
						<div>
							<form action="/cus/login" method="post">
								<div class="log_idpw">
									<div class="logo_img"></div>
									<div class="id_pwd">
										<table>
											<tr>
												<td class="firstTd"><strong>ID</strong></td>
												<td><input type="text" name="userId"></td>
											</tr>
											<tr>
												<td class="firstTd"><strong>PW</strong></td>
												<td><input type="password" name="password"></td>
											</tr>
										</table>
										<input type="submit" value="로그인">
									</div>
								</div>
							</form>
						</div>
						<div class="loginForm_foot">
							<button type="button" name="find_id_pwd" onclick="idpw()">ID
								/ 비밀번호 찾기</button>
							<button type="button" name="newregis" onclick="signup()">회원가입</button>
						</div>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
			<button onclick='updateImg("myPage","myPageSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>