<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<style>
      <%@ include file="css/styles.css"%>
</style>
</head>
<body>
<header>
<a href = "start.jsp">Start page</a>
</header>
<div class = "form-style-6">
<form action="registration" method="POST">
		Login : <input type = "text" name = "login" value = "Login"> <br>
		Password : <input type = "password" name = "password" > <br>
		
		<input type = "submit" value = "Registration">
		
	</form>
	</div>
</body>
</html>