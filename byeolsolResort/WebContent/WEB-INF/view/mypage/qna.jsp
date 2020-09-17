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
<link rel="stylesheet" href="/css/qna.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/qna.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/question/addQuestion"
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
		<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/question/questionSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>고객센터</li>
							<li>》</li>
							<li>고객의 소리</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">고객의 소리</h3>
					</div>
				</div>
				<div class="qNa_form">
					<div class="qNa_info">
						<br> 고객의 소리함을 통해 고객 여러분의 문의, 제안, 칭찬 등을 접수합니다.<br>
						<br> 접수된 글은 마이페이지 나의 Q&A 확인 가능하며,<br>
						<br> 메일 또는 유선을 통해 빠른 시간 내에 답변해 드립니다.<br>
						<br>
					</div>
					<form onsubmit="return check()" name="qNa_write" id="qNa_write" action="/question/addQuestion" method="post">
						<table>
							<tr>
								<td class="formTd1">제목</td>
								<td><input type="text" id="qNa_title" name="title"></td>
							</tr>
							<tr>
								<td class="formTd1">분류</td>
								<td><select name="division">
										<option value="기타">기타</option>
										<option value="예약">예약문의</option>
										<option value="객실">객실문의</option>
										<option value="식음시설">식음시설</option>
										<option value="관광정보">관광정보</option>
									</select></td>
							</tr>
							<tr>
								<td class="formTd1">내용</td>
								<td><textarea name="message" id="textarea" rows="8" cols="80"></textarea></td>
							</tr>
						</table>
						<input type="submit" id="qNa_submit" value="등록">
					</form>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("question","questionSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>