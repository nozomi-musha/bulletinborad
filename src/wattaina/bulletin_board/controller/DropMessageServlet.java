package wattaina.bulletin_board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wattaina.bulletin_board.beans.Message;
import wattaina.bulletin_board.service.MessageService;

@WebServlet(urlPatterns = {"/dropmessage"})
public class DropMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		Message message = new Message();

		message.setId(Integer.parseInt(request.getParameter("messageId")));

		new MessageService().dropMessage(message);

		response.sendRedirect("./");

	}
}