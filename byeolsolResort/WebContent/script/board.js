
function check() {
	if ($("#text_title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#text_title").focus();
		return false;
	}

	if ($("#text_contents").val() == "") {
		alert("내용을 입력해주세요.");
		$("#text_contents").focus();
		return false;
	}
}

function addBoard() {
	location.href = "/board/addBoard";
}

function addBoardlogin() {
	location.href = "/cus/login";
}

function goUpdate(id) {
	location.href = "/board/updateBoard?id=" + id;
}

function goDelete(id) {
	location.href = "/board/deleteBoard?id=" + id;
}

function goDetail(id) {
	location.href = "/board/detailBoard?boardId=" + id;
}

function goAdminUpdate(id) {
	location.href = "/board/updateAdminBoard?id=" + id;
}

function goAdminDelete(id) {
	location.href = "/board/deleteAdminBoard?id=" + id;
}

function onUpdate(id , boardId) {
	location.href = "/board/updateComment?id="+id+"&boardId="+boardId;
}

function onDelte(id,boardId) {
	location.href = "/board/deleteComment?id="+id+"&boardId="+boardId;
}

$(function() {

	$('#manu>ul>li>a').css('color', '#000000');
	$('#manu>ul>li>a').css('font-size', '18px');

	$('#manu>ul>li>a').hover(function() {
		$(this).css('color', '#435F5A');
	}, function() {
		$(this).css('color', '#000000');
	});

	$('#manu>ul>li>ul>li>a').hover(
			function() {
				$(this).css('color', '#34756A');
				$(this).parent().parent().parent().children('#manu>ul>li>a')
						.css('color', '#435F5A');
			},
			function() {
				$(this).css('color', '#000000');
				$(this).parent().parent().parent().children('#manu>ul>li>a')
						.css('color', '#000000');
			});

	$("header").hover(
			function() {
				$(this).children().children().eq(1).children().children()
						.children().children("a").css("color", "#000000");
			},
			function() {
				$(this).children().children().eq(1).children().children()
						.children().children("a").css("color", "#000000");
				$(this).children().children().eq(1).children().children()
						.children().children("a").css("font-size", "18px");
			})
})