<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {

	$('#btnWrite').click(function() {
		$(location).attr("href","/board/write");
	});
	
});
</script>


<style type="text/css">
table, th {
	text-align: center;
}

</style>

<div class="page-header">
	<h3>게시판 목록</h3>
</div>

<table class="table table-striped table-hover table-condensed">
	<tr>
		<th style="width: 5%">글번호</th>
		<th style="width: 20%">제목</th>
		<th style="width: 10%">작성자</th>
		<th style="width: 10%">닉네임</th>
		<th style="width: 5%">조회수</th>
		<th style="width: 20%">작성일</th>
	</tr>
	<c:forEach items="${boardList }" var="i">
	<tr>
		<td>${i.boardno }</td>
		<td><a href="/board/view?boardno=${i.boardno }">${i.title }</a></td>
		<td>${i.writerId }</td>
		<td>${i.writerNick }</td>
		<td>${i.hit }</td>
		<td><fmt:formatDate value="${i.writeDate }" pattern="yyyy-MM-dd" /></td>
	</tr>
	</c:forEach>
</table>

<div class="text-center">
	<ul class="pagination pagination-sm">
<!-- 		<!-- 처음으로 가기 -->
<%-- 		<c:if test="${paging.curPage ne 1 }"> --%>
<!-- 		<li> -->
<!-- 			<a href="/board/list"><span>&larr;처음</span></a> -->
<!-- 		</li> -->
<%-- 		</c:if> --%>

		<!-- 이전 페이지 -->
		<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
		<c:if test="${paging.curPage eq 1 }">
		<li class="disabled">
			<span>&laquo;</span>
		</li>
		</c:if>

		<c:if test="${paging.curPage ne 1 }">
		<li>
			<a href="/board/list?curPage=${paging.curPage-1 }"><span>&laquo;</span></a>
	    </li>
	    </c:if>

		<!-- 페이징 리스트 -->
		<c:forEach
	     begin="${paging.startPage }" end="${paging.endPage }"
	     var="i">
	
			<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
			<c:if test="${paging.curPage eq i}">
			<li class="active">
				<a href="/board/list?curPage=${i }">${i }</a>
			</li>
			</c:if>
		
			<c:if test="${paging.curPage ne i}">
			<li>
				<a href="/board/list?curPage=${i }">${i }</a>
			</li>
			</c:if>
			
	    </c:forEach>
		
		<!-- 다음 페이지 -->
		<c:if test="${paging.curPage eq paging.totalPage }">
		<li class="disabled">
			<span>&raquo;</span>
		</li>
		</c:if>

		<c:if test="${paging.curPage ne paging.totalPage }">
		<li>
			<a href="/board/list?curPage=${paging.curPage+1 }">
			<span>&raquo;</span>
		</a>
		</li>
		</c:if>
	</ul>
</div>

<button id="btnWrite" class="btn btn-primary">글쓰기</button>

