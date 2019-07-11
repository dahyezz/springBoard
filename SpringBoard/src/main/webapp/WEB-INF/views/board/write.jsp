<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	$('#summernote').summernote({
		height: 300,
		minHeight: null,
		maxHeight: null,
		focus: true,
		lang: 'ko-KR',
		placeholder: '불건전한 언어 사용, 타인 비방 및 게시판 운영을 방해하는 행위가 확인되면 서비스 이용이 제한될 수 있습니다.',
		toolbar: [
			['style', ['style']],
			['font', ['bold', 'italic', 'underline', 'clear']],
	        ['fontname', ['fontname']],
	        ['color', ['color']],
	        ['para', ['ul', 'ol', 'paragraph']],
	        ['height', ['height']],
	        ['table', ['table']],
	        ['insert', ['link', 'hr', 'picture']],
	        ['view', ['fullscreen', 'codeview']],
	        ['help', ['help']]
		]
	});
	
	//취소 버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});

function postForm() {
    $('textarea[name="content"]').val($('#summernote').summernote('code'));
}

</script>

<div class="page-header">
	<h3>게시글 작성</h3>
</div>

<div>
<form action="/board/write" method="post" onsubmit="postForm()" enctype="multipart/form-data">
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1">제목</span>
		<input type="text" class="form-control" name="title" style="width:100%"/>
	</div>
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1">아이디</span>
		<label class="form-control">${id }</label>
		<input type="hidden" name="writerId" value="${id }" />
	</div>
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1">닉네임</span>
		<label class="form-control">${nick }</label>
		<input type="hidden" name="writerNick" value="${nick }" />
	</div>

	<textarea name="content" id="content" style="display: none;"></textarea>
	<div id="summernote"></div>

<label>첨부파일 : <input type="file" name="file" /></label>

<div class="text-center">	
	<button type="submit" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

</form>
</div>

