<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	Random random = new Random();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c945c12587.js"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="/script/main.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
	window.addEventListener("DOMContentLoaded", function() {

		var errorMessage = "${errorMessage}";
		if (errorMessage != "") {
			if ("${userId}" != "") {
				if (errorMessage == "이메일 인증이 되어 있지 않은 계정입니다.") {
					var isEm = confirm("이메일 인증 창으로 이동 하시겠습니까?")
					if (isEm) {
						location.href = "/cus/mailCheck";
					} else {
						location.href = "/index/main"
					}
				} else {
					alert(errorMessage)
					location.href = "/index/main"
				}

			} else {
				if (errorMessage == "로그인이 되어 있지 않습니다.") {
					var isLog = confirm("로그인 창으로 이동 하시겠습니까?");
					if (isLog) {
						location.href = "/cus/login";
					}else{
						alert(errorMessage)
						location.href = "/index/main"
					}
				} else {
					alert(errorMessage)
					location.href = "/index/main"

				}

			}
		}
	})
</script>
</head>
<body>
	<div class="allwrap">
		<div class="wrap">
			<header class="header">
				<jsp:include page="../header/header.jsp"></jsp:include>
			</header>
			<div class="mainImg"
				style="background: url('https://gyonewproject.000webhostapp.com/byeolsolResort/mainImg/main.jpg?<%=random.nextInt(500)%>');">
				<div id="center">
					<div class="img1info">
						<div>
							<div class="img1title">CONCEPT 1</div>
							<br>
							<div>힐링룸 입니다.</div>
							<div class="centerlink">
								More Info<br> Related
									Event
							</div>
						</div>

					</div>
					<div id="img1"></div>
				</div>
			</div>
			<div id="roomInfo">
				<ul>
					<li id="room1"><a href="/index/roomInfo_01">
							<p>
								<span><i class="fas fa-door-closed"></i></span> <br> <span>힐링룸</span>
							</p>
					</a></li>
					<li id="room2"><a href="/index/roomInfo_02">
							<p>
								<span><i class="fas fa-door-closed"></i></span><br> <span>키즈룸</span>
							</p>
					</a></li>
					<li id="room3"><a href="/index/roomInfo_03">
							<p>
								<span><i class="fas fa-door-closed"></i></span><br> <span>게임룸</span>
							</p>
					</a></li>
				</ul>
			</div>
			<div class="scrolldown">SCROLL DOWN</div>
			<div id="mainNotice">
				<div class="mainNotic_list">
					<a href="/board/adminList">별솔뉴스</a>
				</div>
				<div class="more">
					<span style="color: lightgrey">｜</span> <a
						style="padding-left: 300px;" href="/event/list"> 이벤트</a>
				</div>
			</div>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
	</div>
	<!--  -->
	<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("mainImg","main")'>메인이미지 수정</button>
		<button onclick='updateImg("logo","title1")'>로고이미지 수정(마우스
			안올렸을 때)</button>
		<button onclick='updateImg("logo","title2")'>로고이미지 수정(마우스 올렸을
			때)</button>
	</c:if>
</body>
</html>