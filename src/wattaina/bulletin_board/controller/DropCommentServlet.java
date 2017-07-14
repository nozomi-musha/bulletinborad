package wattaina.bulletin_board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wattaina.bulletin_board.beans.Comment;
import wattaina.bulletin_board.service.CommentService;

@WebServlet(urlPatterns = {"/dropcomment"})
public class DropCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		Comment comment = new Comment();

		comment.setId(Integer.parseInt(request.getParameter("commentId")));

		new CommentService().dropComment(comment);

		response.sendRedirect("./");

	}
}