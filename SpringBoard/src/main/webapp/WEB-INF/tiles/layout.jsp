<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">
/* 모든 페이지에 적용되는 CSS */

.wrap {
	margin: 10px 10%;
	text-align: center;
}

.header {
	text-align: center;
	font-size: 30px;
}

.footer {
	border: 1px solid blue;
}
</style>

</head>
<body>


<!-- HEADER -->
<tiles:insertAttribute name="header" />

<!-- BODY -->
<div class="wrap">
	<tiles:insertAttribute name="body" />
</div>

<!-- FOOTER -->
<tiles:insertAttribute name="footer" />

</body>
</html>