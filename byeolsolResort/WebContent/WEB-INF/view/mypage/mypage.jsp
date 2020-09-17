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
<link rel="stylesheet" href="/css/mypage.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/mypage.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/cus/myPage"
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
							<li>개인정보수정</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">개인정보수정</h3>
						<c:if test='${customer.emailState!="인증"}'>
							<button onclick='location.href="/cus/mailCheck"'>미인증</button>
						</c:if>
					</div>
				</div>
				<form onsubmit="return check()" id="regis" action="/cus/updateCustomer" method="post">
					<div class="content_foot">
						<ul class="flotBox_my_tap">
							<li><a href="/cus/myPage" class="li1">개인정보</a></li>
							<li><a href="/question/list" class="li2">나의 Q&A</a></li>
							<li><a href="/cus/myReserv" class="li3">나의 예약</a></li>
						</ul>
					</div>
					<h3 class="tit_3">
						필수 정보 입력 <span class="spanText">(*항목은 필수 항목입니다)</span>
					</h3>

					<table>
						<tr class="tableTr">
							<td class="formTd1">아이디</td>
							<td class="formTd2"> <input type="text" value="${customer.userId}" readonly="readonly" name="userId"></td>
						</tr>
						<tr class="tableTr">
							<td onclick="password()" class="formTd1">* 비밀번호</td>
							<td class="formTd2"><input type="password" name="password"
								id="password">
								<p>대,소문자와 숫자를이용하여 9~12자로 입력하세요</p></td>
						</tr>
						<tr class="tableTr">
							<td onclick="passwordCheck()" class="formTd1">* 비밀번호 확인</td>
							<td class="formTd2"><input type="password"
								name="passwordCheck" id="passwordCheck"></td>
						</tr>
						<tr class="tableTr">
							<td onclick="name()" class="formTd1">* 이름</td>
							<td class="formTd2"><input type="text" name="name" id="name" value="${customer.name }"></td>
						</tr>
						<tr class="tableTr">
							<td class="formTd1">이메일</td>
							<td class="formTd2">
							<c:if test='${customer.emailState!="인증"}'>
							<input type="email" value="${customer.email}" name="email"> 
							</c:if>
							<c:if test='${customer.emailState=="인증"}'>
							<input type="email" value="${customer.email}" name="email" readonly="readonly">
							</c:if>
							</td>
						</tr>
						<tr class="tableTr">
							<td onclick="address()" class="formTd1">* 주소</td>
							<td class="formTd2"><input type="text" id="sample6_postcode"
								placeholder="우편번호" value="${customer.zipCode}" name="zipCode"> <input id="address" type="button"
								onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> <br>
								<input type="text" id="sample6_address" placeholder="주소"
								name="address" value="${customer.address}"> <input type="text"
								id="sample6_detailAddress" placeholder="상세주소"
								name="addressDetail" value="${customer.addressDetail }"></td>
						</tr>
						<tr class="tableTr">
							<td onclick="phone()" class="formTd1">* 전화번호</td>
							<td class="formTd2"><input type="text" name="phone"
								id="phone" value="${customer.phone }"></td>
						</tr>
						<tr class="tableTr">
							<td id="birth1" class="formTd1" onclick="dbclick()">* 생일</td>
							<td class="formTd2">
							<input type="date"  name="birth" id="birth" readonly="readonly" value="${customer.birthDate }">
							</td>
						</tr>
					</table>

					<div class="btn">
						<input type="reset" value="취소"> <input type="submit"
							value="수정">
					</div>
					<br> <br> <br>
					<div class="ptag">
						<p>
							*별솔을 더이상 이용하지 않으시려면 <a href="/cus/deleteCustomer"> <input
								type="button" value="회원탈퇴 바로가기>">
							</a>
						</p>
					</div>
				</form>
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