<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Random random = new Random();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/admin.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/admin.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/index/adminQnA"
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
				src="https://gyonewproject.000webhostapp.com/byeolsolResort/question/questionSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>관리자페이지</li>
							<li>》</li>
							<li>Q&A현황</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">Q&A현황</h3>
					</div>
				</div>
				<form onsubmit="return check()" id="regis">
					<div class="content_foot">
						<ul class="flotBox_my_tap">
							<li><a href="/cus/adminUserInfo" class="li3">유저정보관리</a></li>
							<li><a href="/index/adminRoom" class="li2">객실예약관리</a></li>
							<li><a href="/index/adminQnA" class="li1">Q&A관리</a></li>
							<li><a href="/reserv/adminRemovePage" class="li4">삭제정보관리</a></li>
						</ul>
					</div>
					<table id="user_list">
						<thead>
							<tr>
								<td>질문번호</td>
								<td>분류</td>
								<td>내용</td>
								<td>name</td>
								<td>작성일</td>
								<td class="hide">답변여부</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="q" items="${questionView.questionList }">
								<tr onclick="questionUser(${q.id})">
									<td>${q.id}</td>
									<td>${q.division }</td>
									<td>${q.message }</td>
									<td>${q.writer }</td>
									<td>${q.regDate }</td>
									<td class="hide">${q.state }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
			<button onclick='updateImg("myPage","myPageSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>