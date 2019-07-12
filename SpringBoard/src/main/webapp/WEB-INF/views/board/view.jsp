<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- AJAX 라이브러리 JS 추가 -->
<script type="text/javascript" src="/js/httpRequest.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//목록 버튼 동작
	$('#btnList').click(function() {
// 		console.log("clicked")
		$(location).attr("href", "/board/list");
	});
	
	//수정 버튼 동작
	$('#btnUpdate').click(function() {
		$(location).attr("href", "/board/update?boardno=${board.boardno }");
	});
	
	//삭제 버튼 동작
	$('#btnDelete').click(function() {
		$(location).attr("href", "/board/delete?boardno=${board.boardno }");
	});
	
	if(${isRecommend}){
		$('#btnRecommend').addClass("btn-warning").html('추천 취소');
	} else {
		$('#btnRecommend').addClass("btn-primary").html('추천');
	}
	
	$('#btnRecommend').click(function() {
		var board_no = ${board.boardno };
		var id = $('#id').val();
		
		console.log(board_no)
		console.log(id)
		
		$.ajax({
			type: "get"
			, url: "/board/recommend"
			, data: {"boardno" : board_no}
			, dataType: "json"
			, success: function(data) {
				console.log("성공")
				console.log(data);
				
				if(data.result) { //추천 성공
					$('#btnRecommend').addClass("btn-warning").html('추천 취소');
				} else {
					$('#btnRecommend').addClass("btn-primary").html('추천');	
				}
				
				//추천수 적용
				$('#recommend').html(data.cnt);
			}
			, error: function() {
				console.log("실패");
			}
		});	
	});
	
});
</script>


<style type="text/css">
.info {
	background-color: #5EC75E;
}
</style>


<input type="hidden" id="id" value="${id }" />

<div class="page-header">
	<h3>게시글 조회</h3>
</div>

	
<c:if test="${login }">
	<button id="btnRecommend" class="btn pull-right" ></button>
</c:if>

<table class="table table-bordered">
	<tr>
		<td class="info">글번호</td><td colspan="3">${board.boardno }</td>
	</tr>
	
	<tr>
		<td class="info">제목</td><td colspan="3">${board.title }</td>
	</tr>
	
	<tr>
		<td class="info">아이디</td><td>${board.writerId }</td>
		<td class="info">닉네임</td><td>${board.writerNick }</td>
	</tr>
	
	<tr>
		<td class="info">조회수</td><td>${board.hit }</td>
		<td class="info">추천수</td><td id="recommend">${board.recommend }</td>
	</tr>
	
	<tr>
		<td class="info">작성일</td><td colspan="3"><fmt:formatDate value="${board.writeDate }" pattern="yyyy-MM-dd" /></td>
	</tr>
	
	<tr>
		<td class="info">첨부파일</td>
		<td colspan="3">
			<a href="/board/download?fileno=${file.fileno }">${file.originname }</a>
		</td>
	</tr>
	
	<tr><td class="info" colspan="4">본문</td></tr>
	<tr><td colspan="4">${board.content }</td></tr>
</table>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">목록</button>

	
	<c:if test="${id eq board.writerId }">
		<button id="btnUpdate" class="btn btn-primary">수정</button>
		<button id="btnDelete" class="btn btn-primary">삭제</button>
	</c:if>
</div>