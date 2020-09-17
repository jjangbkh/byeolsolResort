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
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/trip.css">
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
		location.href="/index/trip"
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
				<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/trip/tripsub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div id="secheader">
            <div class="route">
              <ul>
                <li>HOME</li>
                <li>》</li>
                <li>주변관광지</li>
                <li>》</li>
                <li>여행코스</li>
              </ul>
            </div>
            <div class="sibtitle">
              <h3 class="stitle">여행코스</h3>
            </div>
          </div>

          <div id="alltrip">
            <div class="triptitle">
              별솔과 더불어 알찬 여행을 꾸며줄 매력적인 관광지들
            </div>
            <div class="tripinfo" style="border-bottom: 1px solid #E4E1E1;">
              <p>별솔 회원권 보유객은 더욱 특별한 <span class="purple">혜택</span>을 즐기실 수 있습니다.</p><br>
              리조트 주변의 유명 관광지를 추천드립니다.<br>
              객실에 비치된 쿠폰을 이용하시거나 숙박권을 지참하고 방문하시면 할인된 가격이 제공됩니다.
            </div>
						<div class="triplist">
                <div class="trip">
                  <div class="tripimg">
                    <img src="https://gyonewproject.000webhostapp.com/byeolsolResort/trip/nami.PNG">
                  </div>
                  <div class="content">
                    <div class="title"><i class="fas fa-check"></i> 남이섬</div>
                    <div class="text">
                      한국 최초로 유니세프 어린이친화공원(Unicef Child Friendly Park)에 선정된 남이섬.<br>
                      아름다운 자연과 다양한 체험시설이 설치되어 있어 사계절 내내 즐거운 관광이 가능합니다.<br>
                      또한 독특한 <공화국> 컨셉으로 나라 안의 나라를 방문하는 기분을 느끼실 수 있습니다.<br>
                      전용 여권, 화폐, 우표 등 나미나라 공화국만의 특별한 물품들을 만나보세요!
                    </div>
                    <div class="tripadr">
                      <span><i class="fas fa-map-marker-alt"></i> 24464 강원도 춘천시 남산면 남이섬길 1</span>
                      <span style="color:#F2C4E0"> ｜ </span>
                      <span><i class="fas fa-phone"></i> 031-580-8114</span><br>
                    </div>
                    <a href="https://namisum.com/"> 바로가기</a>
                  </div>
                </div>

                <div class="trip">
                  <div class="tripimg">
                    <img src="https://gyonewproject.000webhostapp.com/byeolsolResort/trip/rail.PNG">
                  </div>
                  <div class="content">
                    <div class="title"><i class="fas fa-check"></i> 강촌레일파크</div>
                    <div class="text">
                      멈춘 경춘선에 새 숨을 불어넣어 꾸민 관광 명소.<br>
                      레일바이크에 올라타 북한강을 따라 난 기찻길을 달리며 소중한 추억을 만들어 보세요. <br>
                      경로는 김유정-강촌, 경강-가평 구간으로 이루어져 있습니다! <br>
                      또한, 현재 강촌레일파크에서는 세계 최초로 VR 레일바이크 서비스를 제공중입니다.
                    </div>
                    <div class="tripadr">
                      <span><i class="fas fa-map-marker-alt"></i> 강원도 춘천시 신동면 김유정로 1383</span>
                      <span style="color:#F2C4E0"> ｜ </span>
                      <span><i class="fas fa-phone"></i> 033-245-1000</span><br>
                    </div>
                    <a href="https://namisum.com/"> 바로가기</a>
                  </div>
                </div>

                <div class="trip">
                  <div class="tripimg">
                    <img src="https://gyonewproject.000webhostapp.com/byeolsolResort/trip/jade.PNG">
                  </div>
                  <div class="content">
                    <div class="title"><i class="fas fa-check"></i> 제이드가든</div>
                    <div class="text">
                      숲 속에서 만나는 작은 유럽, 제이드가든.<br>
                      마치 안데르센의 동화처럼 지어진 아기자기한 건물들과 아름다운 정원은 사진 명소로 유명합니다.<br>
                      줄지은 정원은 아름다울 뿐 아니라 다양한 식물들과 교감하는 좋은 기회가 될 수도 있습니다.<br>
                      사랑하는 가족, 친구, 연인과 함께 방문해 천천히 걸으며 동심으로 돌아가는 기분을 느껴 보세요.
                    </div>
                    <div class="tripadr">
                      <span><i class="fas fa-map-marker-alt"></i> 강원도 춘천시 남산면 햇골길 80</span>
                      <span style="color:#F2C4E0"> ｜ </span>
                      <span><i class="fas fa-phone"></i> 033-260-8300</span><br>
                    </div>
                    <a href="https://namisum.com/"> 바로가기</a>
                  </div>
                </div>
					  </div>
            <a class="more" style="color:white;" href="https://www.tripadvisor.co.kr/Attractions-g2018516-Activities-Hongcheon_gun_Gangwon_do.html">
              MORE <i class="fas fa-angle-right"></i></a>

          </div>


				</div>	<!-- writ -->
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("trip","tripsub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>