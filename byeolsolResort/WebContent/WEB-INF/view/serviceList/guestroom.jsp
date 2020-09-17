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
<link rel="stylesheet" href="/css/guestroom.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/gustroom.js"></script>
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
		location.href="/index/guestroom"
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
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/reserv/reservSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>회원서비스</li>
							<li>》</li>
							<li>객실현황</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">객실현황</h3>
					</div>
					<div class="room_now">
						<div>
							<div class="seDate">
								입실날짜<input type="date" name="startDate" id="date1"
									readonly="readonly"><br> 퇴실날짜<input type="date"
									name="endDate" id="date2" readonly="readonly">
							</div>
							<div class="seDate1">
								<input type="button" value="객실조회" onclick="dateBtn()"
									class="dateBtn">
							</div>
							<div class="seDate2">※예약 가능한 객실만 표시됩니다.</div>
						</div>
						<br>
						<br>
						<br>
						<br>
						<div class="gr">
							<div class="colorgr">
								<div class="colorGreen"></div>
								<div class="colorGreen1">예약가능</div>
								<div class="colorgr"></div>
								<div class="colorGray"></div>
								<div class="colorGray1">예약불가</div>
							</div>
						</div>
						<table>
							<thead>
								<tr>
									<td colspan="6" class="titleTd">객실</td>
								</tr>
							</thead>
							<tbody>
								<tr class="room">
									<td class="titleTd">힐링룸</td>
									<c:forEach var="r" items="${roomList }">
										<c:if test="${r.roomNum < 201 }">
											<td class="hide t${r.roomNum }"><a
												href="/reserv/addReserv">${r.roomNum }</a></td>
										</c:if>
									</c:forEach>
								</tr>
								<tr class="room">
									<td class="titleTd">키즈룸</td>
									<c:forEach var="r" items="${roomList }">
										<c:if test="${r.roomNum < 301 && r.roomNum > 105 }">
											<td class="hide t${r.roomNum }"><a
												href="/reserv/addReserv">${r.roomNum }</a></td>
										</c:if>
									</c:forEach>
								</tr>
								<tr class="room">
									<td class="titleTd">게임룸</td>
									<c:forEach var="r" items="${roomList }">
										<c:if test="${r.roomNum > 205}">
											<td class="hide t${r.roomNum }"><a
												href="/reserv/addReserv">${r.roomNum }</a></td>
										</c:if>
									</c:forEach>
								</tr>
								<tr>
									<td class="titleTd">인원수</td>
									<c:forEach var="r" items="${roomList }">
										<c:if test="${r.roomNum > 205}">
											<td>${r.minPeople }~${r.maxPeople }</td>
										</c:if>
									</c:forEach>
								</tr>
								<tr>
									<td class="titleTd">평일가격</td>
									<c:forEach var="r" items="${roomList }">
										<c:if test="${r.roomNum > 205}">
											<td>${r.dayPrice }</td>
										</c:if>
									</c:forEach>
								</tr>
								<tr>
									<td class="titleTd">주말가격</td>
									<c:forEach var="r" items="${roomList }">
										<c:if test="${r.roomNum > 205}">
											<td>${r.weekendPrice }</td>
										</c:if>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
	</div>
</body>
</html>