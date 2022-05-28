package com.example2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example2.dao.DaoFactory;
import com.example2.dao.UserDao;
import com.example2.dao.impl.UserDaoFactory;

import com.example2.service.UserService;

@WebServlet("/")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public UserController() {
		DaoFactory daoFactory = new UserDaoFactory();
		UserDao userDao = daoFactory.getUserDao();
		userService = new UserService(userDao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();

		switch (path) {
		case "/registration":
			registerUser(request, response);
			break;
		case "/leave":
			leaveUser(request, response);
			break;
		case "/login":
			loginUser(request, response);
			break;
		case "/delete":
			deleteUser(request, response);
			break;
		case "/change_password":
			changePassword(request, response);
			break;
		case "/setcolor":
			setColor(request, response);
			break;
		case "/":
			forwardUser(request, response);
			break;

		default:

			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private void setColor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String color = request.getParameter("color");

		Cookie cookie = new Cookie("color", color);
		response.addCookie(cookie);

		response.sendRedirect("profile.jsp");
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		String login = (String) request.getSession().getAttribute("user");

		boolean passwordChanged = userService.changePassword(login, old_password, new_password);
		if (passwordChanged) {
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("profile_options.jsp").forward(request, response);
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = (String) request.getSession().getAttribute("user");
		userService.deleteUser(login);
		request.getSession().invalidate();
		request.getRequestDispatcher("start.jsp").forward(request, response);

	}

	private void leaveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession(false).invalidate();
		request.getRequestDispatcher("start.jsp").forward(request, response);

	}

	private void forwardUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession() == null || request.getSession().getAttribute("user") == null) {
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean logged = userService.loginUser(login, password);
		if (logged) {
			request.getSession().setAttribute("user", login);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		} else {
			request.setAttribute("last_login", login);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		boolean registered = userService.registerUser(login, password);

		if (registered) {
			HttpSession session = request.getSession();
			session.setAttribute("user", login);
			request.getRequestDispatcher("profile.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		}

	}

}
