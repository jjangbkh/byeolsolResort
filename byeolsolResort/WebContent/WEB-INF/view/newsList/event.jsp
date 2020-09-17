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
								<li>이벤트</li>
							</ul>
						</div>
						<div class="sibtitle">
							<h3 class="stitle">이벤트</h3>
						</div>
						<div class="allevent">
							<div class="event_area">
								<c:set var="x" value="0" />
								<c:set var="y" value="3" />
								<table class="eventTable">
									<c:forEach var="i" items="${eventView.eventWithThumbList}">
										<c:if test="${x%y==0 }">
											<tr>
										</c:if>
										<td class="bottomNone">
											<div class="event">
												<!-- 반복문 돌릴것 -->
												<ul>
													<li>
														<p class="thmb">
															<a href='/event/detailEvent?id=${i.event.id }'>
															<img src="${i.imgPath}"></a>
														</p> <a class="margin" href='/event/detailEvent?id=${i.event.id }'> <strong>${i.event.title}</strong></a>
														<p class="tx_brief">
														<c:if test='${i.event.state=="미상시"}'><br>
														${i.event.startDate}~
														${i.event.endDate}
														</c:if>
														<c:if test='${i.event.state== "상시" }'><br>
														${i.event.state }
														</c:if>
														</p>
													</li>
												</ul>
											</div>
										</td>
										<c:if test="${x%y==y-1 }">
										</c:if>
										<c:set var="x" value="${x+1 }" />
									</c:forEach>
								</table>
							</div>
						</div>
						<!-- 현재 페이지가 총 페이지 수와 보다 작거나 같으면 -->
						<div class="pageNum">
							<c:if
								test="${eventView.currentPageNum<eventView.pageTotalCount+1}">
								<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
								<c:if
									test="${eventView.currentPageNum>1 && eventView.currentPageNum<=eventView.pageTotalCount}">
									<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
									<a href="/event/list?pageNum=${eventView.currentPageNum-1}">이전</a>
								</c:if>

								<!-- 만약 현재 페이지가 1이면 -->
								<c:if test="${eventView.currentPageNum==1}">

									<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
									<c:forEach var="k" begin="1" end="${eventView.pageTotalCount }">
										<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
										<c:if test="${k<6}">
											<a href="/event/list?pageNum=${k}">${k}</a>
										</c:if>
									</c:forEach>

								</c:if>
								<!-- 현제 페이지가 1이 아니면 -->
								<c:if test="${eventView.currentPageNum!=1}">

									<c:if
										test="${eventView.pageTotalCount-eventView.currentPageNum >=3 && eventView.currentPageNum !=1 }">
										<c:forEach var="j" begin="${eventView.currentPageNum-1}"
											end="${eventView.currentPageNum+3}">
											<a href="/event/list?pageNum=${j}">${j}</a>
										</c:forEach>
									</c:if>

									<c:if
										test="${eventView.pageTotalCount-eventView.currentPageNum<3}">
										<c:if
											test="${(eventView.currentPageNum-(4-(eventView.pageTotalCount-eventView.currentPageNum)))<0}">
											<c:forEach var="j" begin="0"
												end="${eventView.pageTotalCount }">
												<c:if test="${j>0 }">
													<a href="/event/list?pageNum=${j}">${j}</a>
												</c:if>
											</c:forEach>
										</c:if>

										<c:if
											test="${(eventView.currentPageNum-(4-(eventView.pageTotalCount-eventView.currentPageNum)))>=0}">
											<c:forEach var="j"
												begin="${eventView.currentPageNum-(4-(eventView.pageTotalCount-eventView.currentPageNum))}"
												end="${eventView.pageTotalCount }">
												<c:if test="${j>0 }">
													<a href="/event/list?pageNum=${j}">${j}</a>
												</c:if>
											</c:forEach>
										</c:if>
									</c:if>
								</c:if>

								<c:if
									test="${eventView.currentPageNum <eventView.pageTotalCount }">
									<a href="/event/list?pageNum=${eventView.currentPageNum+1}">다음</a>
								</c:if>
							</c:if>
							<br>
							<div>
							<c:if test='${userId=="admin" }'>
								<button class="addEventButton" onclick="adEvent()">글쓰기</button>
							</c:if>
						</div>
						</div>
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
</html>