package com.example2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/*" })
public class LogFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (httpServletRequest.getSession() == null || httpServletRequest.getSession().getAttribute("user") == null) {
			String path = httpServletRequest.getServletPath();
			if (path.equalsIgnoreCase("/registration.jsp") || path.equalsIgnoreCase("/login.jsp")
					|| path.equalsIgnoreCase("/registration") || path.equalsIgnoreCase("/login")) {
				chain.doFilter(request, response);
			} else {
				httpServletRequest.getRequestDispatcher("start.jsp").forward(httpServletRequest, httpServletResponse);
			}
		} else {
			String path = httpServletRequest.getServletPath();
			if (path.equalsIgnoreCase("/registration.jsp") || path.equalsIgnoreCase("/login.jsp")
					|| path.equalsIgnoreCase("/registration") || path.equalsIgnoreCase("/login")) {
				httpServletRequest.getRequestDispatcher("profile.jsp").forward(httpServletRequest, httpServletResponse);
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
