<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="page-header">
	<h3>로그인 페이지</h3>
</div>


<div style="text-align: center">
	<form action="/member/login" method="post">
		<label>아이디 <input type="text" name="id" /></label><br>
		<label>비밀번호 <input type="text" name="pw" /></label><br><br>
		
		<button>로그인</button>
	</form>
</div>
