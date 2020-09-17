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
<link rel="stylesheet"
	href="/css/roominfo.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
	
</script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/roomInfo.js"></script>
<script src="https://kit.fontawesome.com/c945c12587.js"
	crossorigin="anonymous">
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
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/gameRoom/R3subimg.jpg?<%=random.nextInt(500)%>">
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
									<li>》</li>
									<li>객실3</li>
								</ul>
							</div>
							<div class="sibtitle">
								<h3>Pastime Room</h3>
							</div>
						</div>
						<div class="img_slide">
							<ul>
								<li><img
									src="https://gyonewproject.000webhostapp.com/byeolsolResort/gameRoom/img9-1.jpg"
									alt=""></li>
								<li><img
									src="https://gyonewproject.000webhostapp.com/byeolsolResort/gameRoom/img9-2.jpg"
									alt=""></li>
								<li><img
									src="https://gyonewproject.000webhostapp.com/byeolsolResort/gameRoom/img9-3.jpg"
									alt=""></li>
							</ul>
							<div class="slidebtn" id="next">
								<i class="fas fa-angle-right"></i>
							</div>
							<div class="slidebtn" id="prev">
								<i class="fas fa-angle-left"></i>
							</div>
						</div>
						<div class="table">
							<div class="foot_right">
								<table class="map">
									<thead style="border-top: 2px solid #C1CCCB">
										<tr>
											<th class="tit" colspan="2">평면도</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><img
												src="http://tjteam.dothome.co.kr/byeolsolResort/gameRoom/room3.PNG"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="foot_price">
								<table class="price">
									<thead style="border-top: 2px solid #C1CCCB">
										<tr>
											<th class="tit" colspan="2">요금</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="bold">금·토·일요일, 성수기, 연휴</td>
											<td>150,000원</td>
										</tr>
										<tr>
											<td class="bold">비수기 주중(월~목)</td>
											<td>120,000원</td>
										</tr>
									</tbody>
								</table>
								<table>
									<thead>
										<tr>
											<th class="tit" colspan="2">객실시설 안내</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="bold">객실 수</td>
											<td>3실</td>
										</tr>
										<tr>
											<td class="bold">객실정원</td>
											<td>4명</td>
										</tr>
										<tr>
											<td class="bold">전용면적</td>
											<td>16평</td>
										</tr>
										<tr>
											<td class="bold">객실비품</td>
											<td>TV, 오디오, 전자레인지, 전기포트, 식기, 헤어드라이어, 샤워용품, 도서 등</td>
										</tr>
										<tr>
											<td class="bold">취사가능여부</td>
											<td>불가</td>
										</tr>
									</tbody>
								</table>
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
		<button onclick='updateImg("gameRoom","R3subimg.jpg")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>