package wattaina.bulletin_board.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wattaina.bulletin_board.beans.Branch;
import wattaina.bulletin_board.beans.Comment;
import wattaina.bulletin_board.beans.Position;
import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.beans.UserMessage;
import wattaina.bulletin_board.service.BranchService;
import wattaina.bulletin_board.service.CommentService;
import wattaina.bulletin_board.service.PositionService;
import wattaina.bulletin_board.service.UserMessageService;

@WebServlet(urlPatterns = {"/index.jsp"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {



		HashSet<String> categories = new UserMessageService().getCategory();
		request.setAttribute("categories", categories);

		String category = request.getParameter("category");
		String start = request.getParameter("start");
		String end = request.getParameter("end");


		System.out.println(category);

		if ( start == null || start.isEmpty()) {
			start = "2017-07-01";
		}

		if (end == null || end.isEmpty()) {
			Date date = new Date();
			end = new SimpleDateFormat("yyyy-MM-dd").format(date);
			System.out.println(end);
		}

		//		System.out.println(start);
		//		System.out.println(end);

		//カテゴリーが指定されていなかったら通常通りメッセージを表示する

		//投稿の表示
		List<UserMessage> userMessage = new UserMessageService().getUserMessage(start, end, category);
		request.setAttribute("userMessage", userMessage);

		//コメントの表示
		List<Comment> userComments = new CommentService().getComment();
		request.setAttribute("userComments", userComments);

		//削除権限
		List<Branch> branches = new BranchService().getBranches();
		request.setAttribute("branches", branches);
		List<Position> positions = new PositionService().getPositions();
		request.setAttribute("positions", positions);


		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		request.setAttribute("user", user);


		request.getRequestDispatcher("home.jsp").forward(request, response);


	}

}