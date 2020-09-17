// 이미지 정보들을 담을 배열
var sel_files = [];

$(document).ready(function() {
	$("#input_imgs").on("change", handleImgFileSelect);
});

function fileUploadAction() {
	console.log("fileUploadAction");
	$("#input_imgs").trigger('click');
}

function handleImgFileSelect(e) {

	// 이미지 정보들을 초기화
	sel_files = [];
	$(".imgs_wrap").empty();

	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	var index = 0;

	if (filesArr.length < 4) {
		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
			sel_files.push(f);

			var reader = new FileReader();
			reader.onload = function(e) {
				var html = "<img src=\"" + e.target.result + "\" data-file='"
						+ f.name
						+ "' class='selProductFile' title='Click to remove'>";
				$(".imgs_wrap").append(html);
				index++;
			}
			reader.readAsDataURL(f);
		});
	} else {
		alert("파일은 최대 3개 까지만 선택 가능합니다.")
		document.getElementById("input_imgs").value = "";
	}
}