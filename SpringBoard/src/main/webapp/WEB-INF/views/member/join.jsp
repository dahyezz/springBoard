<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="page-header">
	<h3>회원가입</h3>
</div>


<div style="text-align: center;">
<form action="/member/join" method="post">
	<label>아이디 <input type="text" name="id" /></label><br>
	<label>패스워드 <input type="text" name="pw" /></label><br>
	<label>닉네임 <input type="text" name="nick" /></label><br><br>
	
	<button>회원가입</button>
</form>
</div>

