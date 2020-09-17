function addAdminBoard() {
	location.href="/board/addAdminBoard";
}

function goDetail(id){
	location.href="/board/adminDetailBoard?boardId="+id;
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
		var temp = $(`#user_list > tbody > tr > td:nth-child(4n+3):contains('${r}')`);

		$(temp).parent().show();
	})
})