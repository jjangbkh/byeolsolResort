<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c945c12587.js"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="/script/main.js"></script>
</head>
<body>
	<div class="main">
		<div id="logo">
			<a id="logoimg" href="/index/main"> <img class="logo1"
				src="https://gyonewproject.000webhostapp.com/byeolsolResort/logo/title1.png"> <img
				class="logo2"
				src="https://gyonewproject.000webhostapp.com/byeolsolResort/logo/title2.png">
			</a>
		</div>
		<div class="navi">
			<div id="manu">
				<ul>
					<li><a href="/index/byeolsolInfo">별솔리조트</a>
						<ul>
							<li><a class="topmargin" href="/index/byeolsolInfo">별솔리조트란?</a></li>
							<li><a href="/index/roomMain">객실소개</a></li>
							<li><a href="/index/fee">이용안내</a></li>
							<li><a href="/index/map">오시는길</a></li>
						</ul></li>
					<li><a href="/board/adminList">별솔소식</a>
						<ul>
							<li><a class="topmargin" href="/board/adminList">별솔뉴스</a></li>
							<li><a href="/event/list">이벤트</a></li>
						</ul></li>
					<li><c:if test="${userId==null }">
							<a href="/cus/login">회원 서비스</a>
						</c:if> <c:if test="${userId!=null }">
							<a href="/index/leftover">회원 서비스</a>
						</c:if>
						<ul>
							<li><c:if test="${userId==null }">
									<a class="topmargin" href="/cus/login">객실예약</a>
								</c:if> <c:if test="${userId!=null }">
									<a class="topmargin" href="/index/leftover">객실예약</a>
								</c:if></li>
							<li><a href="/index/guestroom">객실현황</a></li>
							<li><a href="/board/list">후기게시판</a></li>
						</ul></li>
					<li><a href="/index/trip">주변관광지</a>
						<ul>
							<li><a class="topmargin" href="/index/trip">여행코스</a></li>
							<li><a href="/index/golf">골프코스</a></li>
							<li><a href="/index/mount">등산코스</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div id="side">
			<ul>
				<li><c:if test="${userId==null}">
						<a href="/cus/login">고객센터</a>
					</c:if> <c:if test='${userId!=null && userId!="admin"}'>
						<a href="/question/addQuestion">고객센터</a>
					</c:if>
					<c:if test='${userId=="admin"}'>
						<a href="/index/adminQnA">Q&A관리</a>
					</c:if>
					</li>
				<li><span>｜</span></li>
				<li><c:if test="${userId==null}">
						<a href="/cus/login">로그인</a>
					</c:if> <c:if test="${userId!=null}">
						<a href="/cus/logout">로그아웃</a>
					</c:if></li>
				<li><span>｜</span></li>
				<li><c:if test="${userId==null}">
						<a href="/cus/regis">회원가입</a>
					</c:if> <c:if test='${userId!=null && userId!="admin"}'>
						<a href="/cus/myPage">마이페이지</a>
					</c:if> <c:if test='${userId=="admin"}'>
						<a href="/cus/adminUserInfo">관리자페이지</a>
					</c:if></li>
			</ul>
		</div>
	</div>
</body>
</html>