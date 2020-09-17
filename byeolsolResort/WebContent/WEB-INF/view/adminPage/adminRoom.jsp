<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		location.href="/index/adminRoom"
	}
})
</script>
</head>
<div class="allwrap">
	<div class="wrap">
		<header class="header">
			<jsp:include page="../header/sub.jsp" />
		</header>
		<img class="subbanner"
			src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>">
		<section>
			<div class="writ">
				<div class="route">
					<ul>
						<li>HOME</li>
						<li>》</li>
						<li>관리자페이지</li>
						<li>》</li>
						<li>객실예약관리</li>
					</ul>
				</div>
				<div class="sibtitle">
					<h3 class="stitle">객실예약관리</h3>
				</div>
			</div>
			<form onsubmit="return check()" id="regis">
				<div class="content_foot">
					<ul class="flotBox_my_tap">
						<li><a href="/cus/adminUserInfo" class="li2">유저정보관리</a></li>
						<li><a href="/index/adminRoom" class="li1">객실예약관리</a></li>
						<li><a href="/index/adminQnA" class="li3">Q&A관리</a></li>
						<li><a href="/reserv/adminRemovePage" class="li4">삭제정보관리</a></li>
					</ul>
				</div>
				<div class="user_form">
					<div class="keywordForm">
						이름 : <input type="text" id="keyword_name"> 컨셉 : <input
							type="text" id="keyword_room">
					</div>
					<table id="user_list">
						<thead>
							<tr>
								<td>예약번호</td>
								<td>컨셉</td>
								<td>예약자명</td>
								<td>예약ID</td>
								<td>연락처</td>
								<td>인원몇명</td>
								<td>입실일</td>
								<td>퇴실일</td>
								<td>금액</td>
								<td>입금확인</td>
								<td>취소</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${reservInfoView.reservInfoList }">
								<tr>
									<td>${i.id }</td>
									<td>${i.concept }</td>
									<td>${i.userName }</td>
									<td>${i.userId }</td>
									<td>${i.phone }</td>
									<td>${i.peopleCount }</td>
									<td>${i.startDate }</td>
									<td>${i.endDate }</td>
									<td>${i.totalPrice }</td>
									<td onclick="clickEvent(${i.id})">${i.state }</td>
									<td><button type="button"
											onclick="goDeleteReserv(${i.id})">취소</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="textCenter">
						<!-- 현재 페이지가 총 페이지 수와 보다 작거나 같으면 -->
						<c:if
							test="${reservInfoView.currentPageNum<reservInfoView.pageTotalCount+1}">
							<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
							<c:if
								test="${reservInfoView.currentPageNum>1 && reservInfoView.currentPageNum<=reservInfoView.pageTotalCount}">
								<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
								<a
									href="/index/adminRoom?pageNum=${reservInfoView.currentPageNum-1}">이전</a>
							</c:if>

							<!-- 만약 현재 페이지가 1이면 -->
							<c:if test="${reservInfoView.currentPageNum==1}">

								<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
								<c:forEach var="k" begin="1"
									end="${reservInfoView.pageTotalCount }">
									<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
									<c:if test="${k<6}">
										<a href="/index/adminRoom?pageNum=${k}">${k}</a>
									</c:if>
								</c:forEach>

							</c:if>
							<!-- 현제 페이지가 1이 아니면 -->
							<c:if test="${reservInfoView.currentPageNum!=1}">

								<c:if
									test="${reservInfoView.pageTotalCount-reservInfoView.currentPageNum >=3 && reservInfoView.currentPageNum !=1 }">
									<c:forEach var="j" begin="${reservInfoView.currentPageNum-1}"
										end="${reservInfoView.currentPageNum+3}">
										<a href="/index/adminRoom?pageNum=${j}">${j}</a>
									</c:forEach>
								</c:if>

								<c:if
									test="${reservInfoView.pageTotalCount-reservInfoView.currentPageNum<3}">
									<c:if
										test="${(reservInfoView.currentPageNum-(4-(reservInfoView.pageTotalCount-reservInfoView.currentPageNum)))<0}">
										<c:forEach var="j" begin="0"
											end="${reservInfoView.pageTotalCount }">
											<c:if test="${j>0 }">
												<a href="/index/adminRoom?pageNum=${j}">${j}</a>
											</c:if>
										</c:forEach>
									</c:if>

									<c:if
										test="${(reservInfoView.currentPageNum-(4-(reservInfoView.pageTotalCount-reservInfoView.currentPageNum)))>=0}">
										<c:forEach var="j"
											begin="${reservInfoView.currentPageNum-(4-(reservInfoView.pageTotalCount-reservInfoView.currentPageNum))}"
											end="${reservInfoView.pageTotalCount }">
											<c:if test="${j>0 }">
												<a href="/index/adminRoom?pageNum=${j}">${j}</a>
											</c:if>
										</c:forEach>
									</c:if>
								</c:if>
							</c:if>
							<c:if
								test="${reservInfoView.currentPageNum <reservInfoView.pageTotalCount }">
								<a
									href="/index/adminRoom?pageNum=${reservInfoView.currentPageNum+1}">다음</a>
							</c:if>
						</c:if>
					</div>
				</div>
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