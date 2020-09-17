<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% Random random = new Random(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/golf.css">
<script src="https://kit.fontawesome.com/c945c12587.js" crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/index/golf"
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
			<img class="subbanner"
				src="https://gyonewproject.000webhostapp.com/byeolsolResort/golf/golfsub.jpg?version=<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div id="secheader">
						<div class="route">
							<ul>
								<li>HOME</li>
								<li>》</li>
								<li>주변관광지</li>
								<li>》</li>
								<li>골프코스</li>
							</ul>
						</div>
						<div class="sibtitle">
							<h3 class="stitle">골프코스</h3>
						</div>
					</div>

					<div id="allcourse">
						<div class="golftitle">산과 하늘이 맞닿는 곳에서 즐기는 청량한 즐거움, 별솔골프장</div>
						<div class="golfinfo" style="border-bottom: 1px solid #E4E1E1;">
							<p>
								별솔 회원권 보유객은 별솔 골프장을 <span class="green">무료</span>로 이용하실 수 있습니다.
							</p>
							<br> 일반 투숙객 역시 별도의 비용을 지불하시면 골프장을 이용하실 수 있으며,<br> 별솔 골프
							회원권만을 구입해 사용하시는 것도 가능합니다.
						</div>
						<div class="course">
							<div class="text">
								<p class="texttitle">솔바람 코스</p>
								<p class="textsubtitle">
									<strong>For Beginners</strong>
								</p>
								<p class="textmain">
									초보자에게도 부담없는 평탄한 입문 장소입니다.<br> 리조트에서 오가기에 편리한 거리이므로 자유로이
									방문하셔서 골프를 즐기실 수 있습니다.<br> 별솔이 자랑하는 천연 잔디 위에서 전문 강사와 함께 색다른
									취미를 경험해 보십시오.<br> 프론트에서 기본 장비를 대여하고 있습니다. 자세한 사항은 프론트에 문의
									바랍니다.
								</p>
							</div>
							<div class="map">
								<img
									src="https://gyonewproject.000webhostapp.com/byeolsolResort/golf/golf3.jpg">
							</div>
						</div>

						<div class="leaf">
							<i class="fab fa-envira"></i><i class="fab fa-envira"></i><i
								class="fab fa-envira"></i>
						</div>

						<div class="course">
							<div class="map">
								<img
									src="https://gyonewproject.000webhostapp.com/byeolsolResort/golf/golf2.jpg">
							</div>
							<div class="text">
								<p class="texttitle">산바람 코스</p>
								<p class="textsubtitle">
									<strong>For Experts</strong>
								</p>
								<p class="textmain">
									숙련자가 즐기기에 적합하도록 꾸며진 장소입니다.<br> 골프에 능숙한 숙련자를 위한 코스입니다. 취미를
									공유하는 소중한 사람들과<br>즐거운 시간을 보내실 수 있습니다.<br> 아름답게 꾸며진 주변
									환경과 코스 주변에 설치된 편의시설을 마음껏 즐겨 보십시오.
							</div>
						</div>

						<div class="leaf">
							<i class="fab fa-envira"></i><i class="fab fa-envira"></i><i
								class="fab fa-envira"></i>
						</div>

						<div class="course">
							<div class="text">
								<p class="texttitle">별바람 코스</p>
								<p class="textsubtitle">
									<strong>For Masters</strong>
								</p>
								<p class="textmain">
									마스터 골퍼들을 위해 정교하게 꾸며진 장소입니다.<br> 프로 골퍼들을 위한 장소입니다. 바람, 잔디,
									지형 등 모두가 전문가인 당신을 위해<br>섬세하게 설계되어 있습니다.<br> 예약시 특정
									옵션을 부가로 신청하시면 보다 특별한 서비스를 만끽하실 수 있습니다.<br> 자세한 사항은 프론트에 문의
									바랍니다.
								</p>
							</div>
							<div class="map">
								<img
									src="https://gyonewproject.000webhostapp.com/byeolsolResort/golf/golf1.jpg">
							</div>
						</div>
					</div>
				</div>
				<!-- writ -->
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("golf","golftsub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>