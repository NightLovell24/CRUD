<%@page import="com.example2.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.example2.dao.UserDao"%>
<%@page import="com.example2.dao.impl.UserDaoFactory"%>
<%@page import="com.example2.dao.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User list</title>
</head>
<body>

<header>
<a href = "profile.jsp">Profile</a>
</header>

<% DaoFactory daoFactory = new UserDaoFactory();
UserDao userDao = daoFactory.getUserDao();
List<User> users = userDao.getAll();
request.setAttribute("users", users);
%>

<ul>
<c:forEach var = "user" items = "${users}">
<li>
${user.login}
</li>
</c:forEach>
</ul>
</body>
</html>