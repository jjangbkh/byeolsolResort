function deleteRemove(id) {
	location.href = "/reserv/adminDeleteRemove?id="+id;
}

$(document).ready(function () {
	$("#trash_name").keyup(function () {
		var r = $(this).val();
		$("#trash_list > tbody > tr").hide();
		var temp = $("#trash_list > tbody > tr > td:nth-child(10n+7):contains('"+r+"')");

        $(temp).parent().show();
    })
})