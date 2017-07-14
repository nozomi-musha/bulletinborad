package wattaina.bulletin_board.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.service.UserService;

@WebServlet(urlPatterns = { "/isstopped" })
public class IsStoppedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int userId = Integer.parseInt(request.getParameter("userId"));

		User user = new UserService().getUser(userId);

		request.setAttribute("user", user);

		request.getRequestDispatcher("userlist.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int stopped = Integer.parseInt(request.getParameter("isStopped"));
		System.out.println(stopped);

		if (stopped == 0) {
			stopped = 1;
		} else if(stopped == 1) {
			stopped = 0;
		}

		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("userId")));
		user.setIsStopped(stopped);




		System.out.println(request.getParameter("isStopped"));
		new UserService().isStopped(user);

		response.sendRedirect("userlist");

	}
}
