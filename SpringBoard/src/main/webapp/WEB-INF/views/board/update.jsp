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
	
	
	//취소버튼 동작
	$('#btnCancel').click(function() {
		history.go(-1);
	});
});

// function postForm() {
//     $('textarea[name="content"]').val($('#summernote').summernote('code'));
// }

</script>

<div class="page-header">
	<h3>게시글 수정</h3>
</div>

<div>
<form action="/board/update" method="post" >
	<input type="hidden" name="boardno" value="${board.boardno }" />
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1">제목</span>
		<input type="text" class="form-control" name="title" style="width:100%" value="${board.title }"/>
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

	<textarea name="content" id="summernote" style="display: none;">${board.content }</textarea>

<label>첨부파일 : <input type="file" name="file" /></label>

<div class="text-center">	
	<button class="btn btn-info">수정</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

<!-- <div id="attachfile" > -->
<%-- 	<label>첨부 파일 : <a href="/board/file/download?fileno=${boardfile.fileno }">${boardfile.originname }</a></label> --%>
<!-- </div> -->
</form>

</div>
