<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
	
});
</script>


<style type="text/css">
.info {
	background-color: #5EC75E;
}
</style>



<div class="page-header">
	<h3>게시글 조회</h3>
</div>

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
		<td class="info">추천수</td><td id="recommend">[ 추후 추가 ]</td>
	</tr>
	
	<tr>
		<td class="info">작성일</td><td colspan="3"><fmt:formatDate value="${board.writeDate }" pattern="yyyy-MM-dd" /></td>
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