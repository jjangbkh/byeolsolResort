<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/adminupdate.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/footer.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	function select(dumpImg) {
		$("#dump").val(dumpImg)
	}
</script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/admin.js"></script>


<meta charset="UTF-8">
<title>별솔리조트</title>

</head>
<body>
	<header class="header">
		<jsp:include page="../header/sub.jsp" />
	</header>
	<img class="subbanner"
		src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%= new Random().nextInt()*400+1%>">
	<section>
		<form action="/index/imgUpdate" method="post"
			enctype="multipart/form-data">
		<input type="text" value="${value}" name="value" style="display: none">
			<div class="div">
				<input type="text" value="${classification}" name="classification"
					readonly="readonly"> <input type="file"
					style="width: 200px;" name="uploadFile"> <span
					style="color: red"> *</span>서버에 저장할 이미지
			</div>
			<div class="div">
				<input type="text" name="dumpImg" id="dump" readonly="readonly">
				<input type="submit" value="제출" style="padding:2px 4px 2px 4px;">
				<div></div>
				<span style="color: red; padding-left: 27px;"> *</span>서버에 이미지 저장 및
				저장된 이미지 적용
			</div>

		</form>
		<div class="imglist">

			<c:forEach var="i" items="${imgList}">
				<div class="img">
					<img src="${i}?=<%=new Random().nextInt()*400+1 %>" width="300px"
						height="auto">
					<button onclick='select("${i}")'>선택</button>
				</div>
			</c:forEach>

		</div>
	</section>
	<footer>
		<jsp:include page="../footer/footer.jsp" />
	</footer>


</body>
</html>