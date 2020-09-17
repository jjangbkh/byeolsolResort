var upload = document.querySelector('#upload');
var preview = document.querySelector('#preview');

upload.addEventListener('change', function(e) {
	var get_file = e.target.files;
	
	var image = document.createElement('img');

	/* FileReader 객체 생성 */
	var reader = new FileReader();
	
	/* reader 시작시 함수 구현 */
	reader.onload = (function(aImg) {
		return function(e) {
			/* base64 인코딩 된 스트링 데이터 */
			aImg.src = e.target.result
		}
	})(image)

	if (get_file) {
		/*
		 * get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 onload 에 설정했던
		 * return 으로 넘어간다. 이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
		 */
		reader.readAsDataURL(get_file[0]);
	}
	preview.innerHTML="";
	image.className='imgs';
	preview.appendChild(image);
})

var upload1 = document.querySelector('#upload1');
var preview1 = document.querySelector('#preview1');

upload1.addEventListener('change', function(e) {
	var get_file = e.target.files;

	var image = document.createElement('img');

	/* FileReader 객체 생성 */
	var reader = new FileReader();

	/* reader 시작시 함수 구현 */
	reader.onload = (function(aImg) {

		return function(e) {
			/* base64 인코딩 된 스트링 데이터 */
			aImg.src = e.target.result
		}
	})(image)

	if (get_file) {
		/*
		 * get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 onload 에 설정했던
		 * return 으로 넘어간다. 이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
		 */
		reader.readAsDataURL(get_file[0]);
	}
	preview1.innerHTML="";
	image.className='imgs';
	preview1.appendChild(image);
})

var upload2 = document.querySelector('#upload2');
var preview2 = document.querySelector('#preview2');

upload2.addEventListener('change', function(e) {
	var get_file = e.target.files;

	var image = document.createElement('img');

	/* FileReader 객체 생성 */
	var reader = new FileReader();

	/* reader 시작시 함수 구현 */
	reader.onload = (function(aImg) {

		return function(e) {
			/* base64 인코딩 된 스트링 데이터 */
			aImg.src = e.target.result
		}
	})(image)

	if (get_file) {
		/*
		 * get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 onload 에 설정했던
		 * return 으로 넘어간다. 이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
		 */
		reader.readAsDataURL(get_file[0]);
	}
	preview2.innerHTML="";
	image.className='imgs';
	preview2.appendChild(image);
})
