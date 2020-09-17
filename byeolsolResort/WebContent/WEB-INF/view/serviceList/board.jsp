<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://sargue.net/jsptags/time" prefix="jt" %>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>   
 <%Random random = new Random(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/board.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/board.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/board/list"
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
			<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/board/byeolsolnewssub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>회원서비스</li>
							<li>》</li>
							<li>후기게시판</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">후기게시판</h3>
					</div>
					<div>
						<c:if test="${userId!=null }">
						<button id="addBoard" onclick="addBoard()">글쓰기</button>
						</c:if>
						<c:if test="${userId==null }">
						<button id="addBoard" onclick="addBoardlogin()">글쓰기</button>
						</c:if>
					</div>
					<!-- 만약 정보의 총 수가 0 보다 크면 -->
					<c:if test="${boardView.boardCnt>0 }">
						<!-- request로 받은 boardView의 정보리스트를 item으로 잡고 -->
						<table>
							<tr class="review_title">
								<td class="td2">제목</td>
								<td class="td3">작성자</td>
								<td class="td4">작성날짜</td>
							</tr>
							<c:forEach var="i" items="${boardView.boardList}">
								<!-- 정보를 출력하게 하는데 삭제 버튼을 누르면 deleteGuest메서드(인자로 이 정보의 아이디를 줌)를 실행
							수정 버튼을 누르면 updateGuest메서드 (인자로 이 정보의 아이디를 줌)를 실행 하게 함. -->
								<tr onclick="goDetail(${i.id})" class="review_content">
									<td class="td2">${i.title}</td>
									<td class="td3">${i.userId}</td>
									<td class="td4"><jt:format value="${i.wDate}" pattern="YYYY-MM-dd HH:mm:ss"/></td>
								</tr>
							</c:forEach>
						</table>
						<!-- 현재 페이지가 총 페이지 수와 보다 작거나 같으면 -->
						<c:if
							test="${boardView.currentPageNum<boardView.pageTotalCount+1}">
							<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
							<c:if
								test="${boardView.currentPageNum>1 && boardView.currentPageNum<=boardView.pageTotalCount}">
								<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
								<a href="/board/list?pageNum=${boardView.currentPageNum-1}">이전</a>
							</c:if>
							<!-- 만약 현재 페이지가 1이면 -->
							<c:if test="${boardView.currentPageNum==1}">
								<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
								<c:forEach var="k" begin="1" end="${boardView.pageTotalCount }">
									<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
									<c:if test="${k<6}">
										<a href="/board/list?pageNum=${k}">${k}</a>
									</c:if>
								</c:forEach>
							</c:if>
							<!-- 현제 페이지가 1이 아니면 -->
							<c:if test="${boardView.currentPageNum!=1}">
								<c:if
									test="${boardView.pageTotalCount-boardView.currentPageNum >=3 && boardView.currentPageNum !=1 }">
									<c:forEach var="j" begin="${boardView.currentPageNum-1}"
										end="${boardView.currentPageNum+3}">
										<a href="/board/list?pageNum=${j}">${j}</a>
									</c:forEach>
								</c:if>
								<c:if
									test="${boardView.pageTotalCount-boardView.currentPageNum<3}">
									<c:if
										test="${(boardView.currentPageNum-(4-(boardView.pageTotalCount-boardView.currentPageNum)))<0}">
										<c:forEach var="j" begin="0"
											end="${boardView.pageTotalCount }">
											<c:if test="${j>0 }">
												<a href="/board/list?pageNum=${j}">${j}</a>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if
										test="${(boardView.currentPageNum-(4-(boardView.pageTotalCount-boardView.currentPageNum)))>=0}">
										<c:forEach var="j"
											begin="${boardView.currentPageNum-(4-(boardView.pageTotalCount-boardView.currentPageNum))}"
											end="${boardView.pageTotalCount }">
											<c:if test="${j>0 }">
												<a href="/board/list?pageNum=${j}">${j}</a>
											</c:if>
										</c:forEach>
									</c:if>
								</c:if>
							</c:if>
							<c:if
								test="${boardView.currentPageNum <boardView.pageTotalCount }">
								<a href="/board/list?pageNum=${boardView.currentPageNum+1}">다음</a>
							</c:if>
						</c:if>
					</c:if>
					<c:if test="${boardView.boardCnt<=0 }">
					정보가 없습니다.
					</c:if>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("board","byeolsolnewssub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>