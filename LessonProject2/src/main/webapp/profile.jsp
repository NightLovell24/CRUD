<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
	<header>
		<a href="leave">Leave</a> <a href="users_list.jsp">List of users</a> <a
			href="profile_options.jsp">Options</a>
	</header>
	<%
	String login = (String) request.getSession().getAttribute("user");
	request.setAttribute("login", login);
	%>
	<h1 style="color:${cookie.color.value};">Hello, ${login}</h1>

	
	<form action="setcolor" method="POST">
	<label for="color">Choose a color:</label>
		<select name="color" id="color">
			<option value="red">Red</option>
			<option value="green">Green</option>
			<option value="blue">Blue</option>
			<option value="purple">Purple</option>
			<option value="yellow">Yellow</option>
		</select>
		<input type = "submit" value = "Set color">
	</form>

</body>
</html>