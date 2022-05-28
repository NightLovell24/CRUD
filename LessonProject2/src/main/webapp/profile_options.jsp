<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Options</title>
<style>
      <%@ include file="css/styles.css"%>
</style>
</head>
<body>

<header>
<a href = "delete">Delete account</a>
<a href = "profile.jsp">Profile</a>
</header>


<div class = "form-style-6">
<form action="change_password" method="POST">
		Old password : <input type = "password" name = "old_password" > <br>
		New password : <input type = "password" name = "new_password" > <br>
		
		<input type = "submit" value = "Change password">
		
	</form>
	</div>
	
	

</body>
</html>