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
		location.href="/index/operation"
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
							<li>영상정보처리기기운영관리방침</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">영상정보처리기기운영관리방침</h3>
					</div>
					별솔리조트는 영상정보처리기기<br> 운영·관리 방침을 통해 본사에서 처리하는 영상정보가 어떠한 용도와 방식으로
					이용·관리되고 있는지 알려 드립니다.<br> 1. 영상정보처리기기의 설치 근거 및 설치 목적<br>
					본사는 개인정보보호법 제25조 제1항에 따라 다음과 같은 목적으로 영상정보처리기기를 설치 · 운영합니다.<br>
					<br> 시설안전 및 화재 예방<br> 고객의 안전을 위한 범죄 예방<br> 차량도난 및
					파손방지<br> 2. 설치 대수, 설치 위치 및 촬영범위<br> 3. 영상정보 확인<br>
					확인 방법: 영상정보 관리책임자에게 사전 연락 후 방문<br> 확인 장소 : 시설팀<br> 4.
					정보주체의 영상정보 열람 등 요구에 대한 조치<br> 귀하는 개인영상정보에 관하여 열람 또는 존재확인·삭제를
					원하는 경우 언제든지 영상정보처리 기기 운영자에게 요구하실 수 있습니다.<br> 단, 귀하가 촬영된 개인영상정보
					및 명백히 정보주체의 급박한 생명, 신체, 재산의 이익을 위하여 필요한 개인영상정보에 한정됩니다.<br> <br>
					5. 영상정보의 안전성 확보 조치<br> 본사에서 처리하는 영상정보는 암호화 조치 등을 통하여 안전하게 관리되고
					있습니다.<br> 또한 본 사는 개인영상정보보호를 위한 관리적 대책으로서 개인정보에 대한 접근 권한을 차등
					부여하고 있고,<br> 개인영상정보의 위·변조 방지를 위하여 개인영상정보의 생성 일시, 열람 시 열람
					목적·열람자·열람 일시 등을 기록하여 관리하고 있습니다.<br> 이 외에도 개인영상정보의 안전한 물리적 보관을
					위하여 잠금장치를 설치하고 있습니다.<br> <br> 6. 개인정보 처리방침 변경에 관한 사항<br>
					이 영상정보처리기기 운영·관리방침은 2012년 3월 29일에 제정되었으며 법령·정책 또는 보안 기술의 변경에 따라<br>
					내용의 추가·삭제 및 수정이 있을 시에는 시행하기 최소 7일전에 본사 홈페이지를 통해 변경사유 및 내용 등을 공지하도록
					하겠습니다.<br>
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