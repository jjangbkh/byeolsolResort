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
<link rel="stylesheet" href="/css/test1.css">
<link rel="stylesheet" href="/css/footer.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/index/about"
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
			<img class="subbanner"
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>회사소개</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">회사소개</h3>
					</div>
					<div>
						나를 찾는 아날로그의 시간,<br> 그 곁에 별솔 세상의 속도에 묻힌 나를 찾아 돌아보는 시간,<br>
						기계에 종속된 삶을 벗어나 사람들을 만나는 시간,<br> 디지털의 편리만큼 아날로그의 편안을 찾는 당신을 위해
						어머니의 품처럼 따스한 별솔의 자연이 있습니다.<br> 어디에나 있지만 어디에도 없는 곳 힐링이 무엇인지
						설명해야 했던 그 시절, <br> 별솔은 처음으로 힐링 리조트를 선보였습니다. <br> 한 곳 한 곳
						잊지 못할 경험을 위해 앞으로도 선별하고 차별하여 특별하게 만들어 갈 것입니다.<br> 다시 만날 수 없는
						대자연과 상생하며 가치 있는 휴식과 가슴에 남는 특별한 경험을 선사할 리조트 잊지 못할 추억,<br> 감동의
						울림을 선사하겠습니다.<br>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("myPage","myPageSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>