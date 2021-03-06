package wattaina.bulletin_board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.service.LoginService;


@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		//		System.out.println(request.getParameter("name"));

		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		User user = loginService.login(loginId, password);

		HttpSession session = request.getSession();

		if (user != null) {
			int isStopped = user.getIsStopped();

			if (isStopped == 0) {
				session.setAttribute("loginUser", user);
				//request.getRequestDispatcher("/index.jsp").forward(request, response);
				response.sendRedirect("./");
			} else if (isStopped == 1){
				List<String> messages = new ArrayList<String>();
				messages.add("このアカウントは使用できません。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("login");
			}

		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("login");


		}
	}

}
