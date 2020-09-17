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
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/event.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/event.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/event/list"
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
			<div id="headerblank"></div>
			<div class="fee_mainImg">
				<img class="subbanner"
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/event/byeolsolnewssub.jpg?<%=random.nextInt(500)%>">
			</div>
			<section>
				<div class="writ">
					<div id="secheader">
						<div class="route">
							<ul>
								<li>HOME</li>
								<li>》</li>
								<li>별솔소식</li>
								<li>》</li>
								<li>이벤트</li>
							</ul>
						</div>
						<div class="sibtitle">
							<h3 class="stitle">이벤트</h3>
						</div>
						<div class="buttonForm">
							<c:if test='${userId=="admin" }'>
								<button class="updelButton" onclick="updateEvent(${event.id})">수정</button>
								<button class="updelButton" onclick="deleteEvent(${event.id})">삭제</button>
							</c:if>
						</div>
						<div class="detailForm">
							<table class="detailTable">
								<tr>
									<td class="eventTitle" style="border:none;">${event.title}</td>
									<td class="eventRegDate" style="border:none;">${event.regDate }</td>
								</tr>
								<tr>
									<td colspan="2" style="border:none;"><img class="imgeSize"
										src="${event.imgPath}"></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("event","byeolsolnewssub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>