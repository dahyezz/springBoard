<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-header">
	<h3>메인 페이지</h3>
</div>


<%-- 비로그인 상태 --%>
<c:if test="${empty login }">
<div align="center">
	<strong>로그인이 필요합니다</strong><br>
	<button class="btn btn-info" onclick="location.href='/member/login';" >로그인</button>
	<button class="btn btn-info" onclick="location.href='/member/join';">회원가입</button>
</div>
</c:if>

<%-- 로그인 상태 --%>
<c:if test="${login }">
<div align="center">
	${id }님, 접속을 환영합니다<br>
	<button class="btn btn-info" onclick="location.href='/board/list';" >게시판 목록</button>
	<button class="btn btn-info" onclick="location.href='/member/logout';">로그아웃</button>
</div>
</c:if>
