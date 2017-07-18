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

import org.apache.commons.lang.StringUtils;

import wattaina.bulletin_board.beans.Message;
import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.service.MessageService;

@WebServlet(urlPatterns = { "/message" })
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();


		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {


			User user = (User) session.getAttribute("loginUser");
			//			System.out.println(request.getParameter("name"));

			System.out.println(user.getId());

			Message message = new Message();
			message.setTitle(request.getParameter("title"));
			message.setCategory(request.getParameter("category"));
			message.setText(request.getParameter("text"));
			message.setUserId(user.getId());

			new MessageService().register(message);

			response.sendRedirect("./");

		} else {
			//			String s = request.getParameter("name");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("message");
			//			request.setAttribute("user", user);
			//			request.getRequestDispatcher("signup.jsp").forward(request, response);

		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");




		if (StringUtils.isEmpty(title) == true) {
			messages.add("タイトルを入力してください");
		}
		if (!(title.length() <= 30)) {
			messages.add("タイトルは30文字以下で入力してください。");
		}

		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを入力してください");
		}
		if (!(category.length() <= 10)) {
			messages.add("カテゴリーは10文字以下で入力してください。");
		}

		if (StringUtils.isEmpty(text) == true) {
			messages.add("テキストを入力してください");
		}

		if (!(text.length() <= 1000)) {
			messages.add("本文は1000文字以下で入力してください。");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}
}
