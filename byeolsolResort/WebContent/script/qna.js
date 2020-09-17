function check() {

	if ($("#qNa_title").val() == "") {
		alert("제목을 입력해주세요.");
		$("qNa_title").focus();
		return false;
	}
	if ($("#textarea").val() == "") {
		alert("내용을 입력해주세요.");
		$("#textare").focus();
		return false;
	}

}