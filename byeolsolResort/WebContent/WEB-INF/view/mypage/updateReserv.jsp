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
<link rel="stylesheet" href="/css/myQnA.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/myQnA.js"></script>
<script type="text/javascript" src="/script/detailMyQnA.js"></script>
<script type="text/javascript" src="/script/updateLeftover.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/cus/myReserv"
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
			<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/reserv/reservSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>마이페이지</li>
							<li>》</li>
							<li>나의예약</li>
							<li>》</li>
							<li>예약수정</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">예약수정</h3>
					</div>
				</div>
				<form action="/reserv/updateReserv" method="post">
					<table>
						<tr>
							<td>
								<input type="number" readonly="readonly" class="hide" name="reservId" value="${reserv.id }">
							</td>
						</tr>
						<tr>
							<td class="tdForm">입실날짜</td>
							<td class="tdForm1">
							<input id="date1" type="date" name="startDate" readonly="readonly" value="${reserv.startDate}">
							</td>
						</tr>
						<tr>
							<td class="tdForm">퇴실날짜</td>
							<td class="tdForm1">
							<input id="date2" type="date" name="endDate" readonly="readonly" value="${reserv.endDate }">
							</td>
						</tr>
						<tr>
							<td class="tdForm">객실</td>
							<td class="tdForm1">
							<input type="number" readonly="readonly" name="roomNum" value="${roomNum}"></td>
						</tr>
						<tr>
							<td class="tdForm">인원</td>
							<td class="tdForm1"><input type="number" name="peopleCount" value="${reserv.peopleCount}"></td>
						</tr>
						<tr style="border-style: none">
							<td colspan="2">
								<button type="submit">예약</button>
							</td>
						</tr>
					</table>
				</form>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("updateReserv","reservSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>