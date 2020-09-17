<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/error.css">
<link rel="stylesheet" href="/css/footer.css">
<link>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/board.js"></script>
</head>
<body>
	<div class="allwrap">
		<div class="wrap">
			<header class="header">
				<jsp:include page="../header/sub.jsp" />
			</header>
			<section>
				<div class="error_main">
					<div class="error_404">404</div>
					<div class="error_img">
						<img
							src="https://gyonewproject.000webhostapp.com/byeolsolResort/error/errorImg.png"
							alt="">
					</div>
					<div class="error_info">
						<div class="title">죄송합니다. 현재 찾을 수 없는 페이지를 요청 하셨습니다.</div>
						<p>존재하지 않는 주소를 입력하셨거나,</p>
						<p>요청하신 페이지의 주소가 변경,삭제되어 찾을 수 없습니다.</p>
						<p>궁금하신 점이 있으시면 언제든지 고객센터를 통해 문의해 주시기 바랍니다.</p>
						<p>감사합니다</p>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
	</div>
	</div>
</body>
</html>