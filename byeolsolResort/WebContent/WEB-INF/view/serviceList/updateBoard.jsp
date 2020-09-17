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
							<li>》</li>
							<li>후기수정</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">후기수정</h3>
					</div>
					<form action="/board/updateBoard" method="post"
						enctype="multipart/form-data">
						<input type="number" value="${board.id}" name="id" readonly="readonly" style="display:none">
						
						<div class="titleClass">
							<div class="titleName">제목</div>
							<input type="text" value="${board.title}" class="inputTitle" name="title">
						</div>
						<table>
							<tr>
								<td colspan="2" class="contentTitle">내용</td>
							</tr>
							<tr>
								<td colspan="2"><textarea class="content" name="content" rows="10" cols="160">${board.content}</textarea></td>
							</tr>
							<tr>
								<td colspan="2"><input type="file" name="uploadFile01" id="upload"></td>
							</tr>
							<tr>
								<c:if test="${board.firstPath!=null }">
								<td class="width50">원본이미지<img alt="" src="${board.firstPath }" height="50px"></td>
								</c:if>
								<td class="width50">
									변경이미지 <div id="preview"></div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><input type="file" name="uploadFile02" id="upload1"></td>
							</tr>
							<tr>
								<c:if test="${board.secondPath!=null }">
								<td class="width50">원본이미지<img alt="" src="${board.secondPath }" height="50px"></td>
								</c:if>
								<td class="width50">
									변경이미지  <div id="preview1"></div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><input type="file" name="uploadFile03" id="upload2"></td>
							</tr>
							<tr>
								<c:if test="${board.thirdPath!=null }">
								<td class="width50">원본이미지<img alt="" src="${board.thirdPath }" height="50px"></td>
								</c:if>
								<td class="width50">
									변경이미지  <div id="preview2"></div>
								</td>
							</tr>
						</table>
						<input type="submit" value="수정" id="update_subBtn">
					</form>
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
<script type="text/javascript" src="/script/imgView.js"></script>
</html>