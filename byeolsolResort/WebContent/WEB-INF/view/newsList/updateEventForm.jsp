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
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/event.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/event.js"></script>
<script type="text/javascript" src="/script/eventDate.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/event/list"
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
			<div id="headerblank"></div>
			<div class="fee_mainImg">
				<img class="subbanner"
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/event/byeolsolnewssub.jpg?<%=random.nextInt(500)%>">
			</div>
			<section>
				<div class="writ">
					<div id="secheader">
						<div class="route">
							<ul>
								<li>HOME</li>
								<li>》</li>
								<li>별솔소식</li>
								<li>》</li>
								<li>이벤트수정</li>
							</ul>
						</div>
						<div class="sibtitle">
							<h3 class="stitle">이벤트수정</h3>
						</div>
						<form action="/event/updateEvent" method="post"
							enctype="multipart/form-data">
							<input class="hide" type="number" name="id" value="${event.id}"
								readonly="readonly">
							<table>
								<tr>
									<td class="tdTitle">제목</td>
									<td><input type="text" name="title" value="${event.title}"></td>
								</tr>
								<tr>
									<td class="tdTitle">이벤트 기간</td>
									<td>
									<input type="date" id="date1" name="start" value="${event.startDate}" readonly="readonly">
									<input type="date" id="date2" name="end" value="${event.endDate}" readonly="readonly">
									<c:if test='${event.state == "미상시" }'>
										미상시<input type="radio" name="state" value="미상시" checked="checked">
										상시<input type="radio" name="state" value="상시">
									</c:if>
									
									<c:if test='${event.state == "상시" }'>
										미상시<input type="radio" name="state" value="미상시">
										상시<input type="radio" name="state" value="상시" checked="checked">
									</c:if>
									</td>
								</tr>
								<tr>
									<td class="tdTitle">썸네일이미지</td>
									<td><input type="file" name="thumbnail" id="upload"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="preview"></div>
									</td>
								</tr>
								<tr>
									<td class="tdTitle">내용이미지</td>
									<td><input type="file" name="uploadFile" id="upload1"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="preview1"></div>
									</td>
								</tr>
								
								
							</table>
							<div class="submitBtn_mom">
							<input type="submit" class="submitBtn" value="수정">
						</div>
						</form>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("event","byeolsolnewssub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
<script type="text/javascript" src="/script/imgView.js"></script>
</html>