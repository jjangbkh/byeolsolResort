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
<link rel="stylesheet" href="/css/roommain.css">
<script src="https://kit.fontawesome.com/c945c12587.js"
	crossorigin="anonymous">
</script>
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
		location.href="/index/roomMain"
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
			<div class="roomInfo_01_mainImg">
				<img class="subbanner"
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/roomMain/RMsubimg.jpg?<%=random.nextInt(500)%>">
			</div>
			<section>
				<div class="writ">
					<div class="roomInfo_01_info">
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
							<div class="sibtitle">
								<h3 class="stitle">객실소개</h3>
							</div>
							<div class="roomInfo_01_info">
								<div class="content">
									<!--방 사진이 배경으로, h4마크업이 사진위로오게. 자세히보기가 객실글자 밑으로.-->
									<ul class="room_content">
										<li>
											<div class="roomname">Healing room</div> <img
											src="https://gyonewproject.000webhostapp.com/byeolsolResort/healingRoom/room1main.png"
											alt="">
											<div class="roomcom">
												<i class="fab fa-pagelines"></i> 조용하고 편안한 분위기를 만끽하며 나 자신을
												돌아볼 수 있는 장소<br> <a href="/index/roomInfo_01">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자세히
													보기</a>
											</div>
										</li>
										<li>
											<div class="roomname">Kids room</div> <img
											src="https://gyonewproject.000webhostapp.com/byeolsolResort/kidsRoom/room2main.png"
											alt="">
											<div class="roomcom">
												<i class="fab fa-pagelines"></i> 아이친화적 가구와 물품들로 사랑하는 가족과 함께
												행복한 시간을 보낼 수 있는 장소<br> <a href="/index/roomInfo_02">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자세히
													보기</a>
											</div>
											<div class="roomname">Pastime room</div> <img
											src="https://gyonewproject.000webhostapp.com/byeolsolResort/gameRoom/room3main.png"
											alt="">
											<div class="roomcom">
												<i class="fab fa-pagelines"></i> 고급스러운 인테리어와 구비된 보드게임들로 친구들과
												즐거운 시간을 보낼 수 있는 장소<br> <a href="/index/roomInfo_03">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자세히
													보기</a>
											</div>
										</li>
									</ul>
								</div>
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
		<button onclick='updateImg("roomMain","RMsubimg.jpg")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>