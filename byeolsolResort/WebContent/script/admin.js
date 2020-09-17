function goDeleteReserv(id) {
	location.href = "/reserv/deleteReserv?reservId=" + id;
}

function questionUser(id){
	location.href="/question/detailAdminQuestion?id="+id;
}

function goUpdateAnswer(id){
	location.href="/question/updateAnswer?id="+id;
}

function updateQuestion(id) {
	location.href = "/question/updateQuestion?id="+id; 
}

function clickEvent(id){
	var c = confirm("상태를 바꾸시겠습니까?")
	if (c) {
		location.href = "/reserv/updateReservState?id="+id;
	}
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

$(document).ready(function () {
	$("#keyword_name").keyup(function () {
		var r = $(this).val();
		$("#user_list > tbody > tr").hide();
		var temp = $(`#user_list > tbody > tr > td:nth-child(10n+3):contains('${r}')`);

		$(temp).parent().show();
	})
})

$(document).ready(function () {
	$("#keyword_room").keyup(function () {
		var r = $(this).val();
		$("#user_list > tbody > tr").hide();
		var temp = $(`#user_list > tbody > tr > td:nth-child(10n+2):contains('${r}')`);

		$(temp).parent().show();
	})
})

