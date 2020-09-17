<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%Random random = new Random();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/delete.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/deleteForm.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/cus/deleteCustomer"
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
			<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>마이페이지</li>
							<li>》</li>
							<li>회원탈퇴</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">회원탈퇴</h3>
					</div>
				</div>
				<div class="memberDeleteForm">
					<form class="memberDelete" action="/cus/deleteCustomer" method="post">
						<div class="delete_box">
							<div>
								<div class="delete_ask">
									<h2>
										<p>${customer.name}님, 정말 탈퇴하시겠습니까?</p>
									</h2>
								</div>
								<br>
								<div class="delete_info">
									<p>
										탈퇴하시면 회원 전용 서비스를 이용하실 수 없으며,<br> <br> 기존 예약정보, 결제정보는
										미리 저장해 두셔야 합니다.<br> <br> 선입금 금액이 있을 시, 다음날 영업시간내에
										회원님께 연락 드리겠습니다.<br> <br>
									</p>
								</div>
								<div class="delete_input">
									<h4>아이디, 비밀번호를 입력해주세요.</h4>
									<br>
									<table>
										<tr>
											<td class="formTd1">아이디</td>
											<td class="formTd2"><input type="text" name="userId" value="${customer.userId }" readonly="readonly"></td>
										</tr>
										<tr>
											<td class="formTd1">비밀번호</td>
											<td class="formTd2"><input type="password"
												name="password"></td>
										</tr>
									</table>
								</div>
								<div class="delete_check">
									<br>
									<h5>
										<p>*정말 탈퇴하시겠습니까?</p>
									</h5>
									<br>
									<div class="btn">
											<input type="button" onclick="cancelButton()" value="취소">
											 <input type="submit" value="확인">
									</div>
								</div>
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