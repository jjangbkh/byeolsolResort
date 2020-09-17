<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%Random random = new Random();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/byeolsolInfo.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/index/byeolsolInfo"
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
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/byeolsolinfo/infosub.jpg?<%=random.nextInt(500)%>">
			</div>
			<section>
				<div class="writ">
					<div id="secheader">
						<div class="route">
							<ul>
								<li>HOME</li>
								<li>》</li>
								<li>별솔리조트</li>
								<li>》</li>
								<li>별솔리조트란?</li>
							</ul>
						</div>
						<div class="sibtitle">
							<h3 class="stitle">리조트 소개</h3>
						</div>
						<div class="byeolsolInfo">
							<div class="infoImg">
								<img
									src="https://gyonewproject.000webhostapp.com/byeolsolResort/byeolsolinfo/info1.jpg">
							</div>
							<div class="infoTitle">
								<div class="infotitle">
									<h4>대자연과 가까이 맞닿아있는 힐링 스페이스</h4>
								</div>
								<div class="infoTxt">
									<p>소란한 일상으로부터 벗어나 여유를 만끽할 수 있는 편안한 장소</p>
									<p>탁 트인 숲의 경치와 맑은 공기, 다양한 편의시설이 당신의 방문을 기다리고 있습니다.</p>
									<p>재충전을 위한 휴가, 연인 혹은 가족과 함께하는 여행, 친밀한 사람들과의 돈독한 교류. 어떤 목적으로
										방문하셔도 만족하실 것을 약속드립니다.</p>
								</div>
								<div class="infoImg__2">
									<div class="concept">
										<img
											src="https://gyonewproject.000webhostapp.com/byeolsolResort/byeolsolinfo/nature.jpg" />

										<p class="padding">
											<strong>에코힐링 리조트</strong>
										</p>
										<p class="fontsize">
											자연과의 생생한 교감을 통해<br> 건강한 삶의 방식을 배우고 누릴 수 있는 곳
										</p>
									</div>
									<div class="concept">
										<img
											src="https://gyonewproject.000webhostapp.com/byeolsolResort/byeolsolinfo/spa.jpg" />
										<p class="padding">
											<strong>스파 리조트</strong>
										</p>
										<p class="fontsize">
											해발고도 500m 숲 한가운데서<br> 노천 스파를 즐길 수 있는 곳
										</p>
									</div>
									<div class="concept">
										<img
											src="https://gyonewproject.000webhostapp.com/byeolsolResort/byeolsolinfo/infof.jpg" />
										<p class="padding">
											<strong>가족사랑 리조트</strong>
										</p>
										<p class="fontsize">
											사랑하는 가족들과 <br> 편안한 시간을 보낼 수 있는 곳
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("byeolsolinfo","infosub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>