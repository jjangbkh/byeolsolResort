<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%Random random = new Random(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/map.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/index/map"
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
			<div id="headerblank"></div>
			<div class="fee_mainImg">
				<img class="subbanner"
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/roomMain/RMsubimg.jpg?<%=random.nextInt(500)%>">
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
								<li>객실소개</li>
							</ul>
						</div>
						<!-- <div class="border">

                </div> -->
						<div class="sibtitle">
							<h3 class="stitle">오시는길</h3>
						</div>
						<div id="mapinfo">
							<div class="mapinfo">
								주소 : 강원 홍천군 서면 한치골길 262<br> 대표전화 : 1588-4888
							</div>
							<div class="maptitle">
								<div class="con">
									<strong>버스 이용 시</strong> <br>
									<p>홍천군 버스터미널에서 하차 후 택시 이용</p>
									<p class="btnArea_right">
										<a href="https://www.kobus.co.kr/main.do" target="_blank"
											class="downTxtBtn">고속버스 승차권예매</a> <a
											href="https://txbus.t-money.co.kr/intro/intro.html"
											target="_blank" class="downTxtBtn">시외버스 승차권예매</a>
									</p>
								</div>
								<div class="con">
									<strong>열차 이용 시</strong> <br>
									<p>홍천역에서 하차 후 택시이용</p>
									<p class="btnArea_right">
										<a href="http://info.korail.com/mbs/www/index.jsp"
											target="_blank" class="downTxtBtn">코레일 바로가기</a>
									</p>
								</div>
							</div>
							<div class="map">
								<a
									href="https://map.naver.com/v5/search/%EC%86%8C%EB%85%B8%EB%B2%A8%20%EB%B9%84%EB%B0%9C%EB%94%94%ED%8C%8C%ED%81%AC?c=14213184.3730046,4529682.1127625,16,0,0,0,dh"
									target="_blank"><iframe
										src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3159.107890705158!2d127.67952615341216!3d37.64666745983495!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x356319cab3692455%3A0x78694b445a04806!2z6rCV7JuQ64-EIO2ZjeyynOq1sCDshJzrqbQg7ZWc7LmY6rOo6ri4IDI2Mg!5e0!3m2!1sko!2skr!4v1593958433438!5m2!1sko!2skr"
										width="600" height="450" frameborder="0" style="border: 0;"
										allowfullscreen="" aria-hidden="false" tabindex="0"></iframe></a>
							</div>
						</div>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("byeolsolinfo","infosub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>