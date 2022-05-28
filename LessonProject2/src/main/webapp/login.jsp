<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
<style>
      <%@ include file="css/styles.css"%>
</style>
</head>
<body>
<header>
<a href = "start.jsp">Start page</a>
</header>

<% String last_login =(String) request.getAttribute("last_login");
if (last_login == null)
{
	last_login = "Login";
}
%>




<div class = "form-style-6">
<form action="login" method="POST">
		Your login : <input type = "text" name = "login" value = "<%=last_login%>"> <br>
		Your password : <input type = "password" name = "password" > <br>
		
		<input type = "submit" value = "Log in">
		
	</form>
	</div>
</body>
</html>