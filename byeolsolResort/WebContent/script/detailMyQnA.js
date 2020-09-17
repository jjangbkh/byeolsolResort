function goUpdateAnswer(id){
	location.href="/question/updateAnswer?id="+id;
}

function goDeleteAnswer(id){
	location.href = "/question/deleteAnswer?id="+id;
}

function updateQuestion(id) {
	location.href = "/question/updateQuestion?id="+id; 
}

function deleteQuestion(id) {
	location.href = "/question/deleteQuestion?id="+id;
}