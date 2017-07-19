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

import wattaina.bulletin_board.beans.Comment;
import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.service.CommentService;



@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();


		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {


			//			System.out.println(request.getParameter("name"));

			//			System.out.println(comment);

			//ここでユーザー情報ゲットする？？？id.position_id.branch_id

			User user = (User) session.getAttribute("loginUser");

			Comment comments = new Comment();
			comments.setText(request.getParameter("comment"));
			comments.setUserId(user.getId());
			comments.setUserName(user.getName());
			comments.setMessageId(Integer.parseInt(request.getParameter("messageId")));
			comments.setPositionId(user.getPositionId());
			comments.setBranchId(user.getBranchId());


			new CommentService().register(comments);

			response.sendRedirect("./");

		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String comment = request.getParameter("comment");


		if (StringUtils.isEmpty(comment) == true) {
			messages.add("コメントを入力してください");

		}

		if (!(comment.length() <= 500)) {
			messages.add("コメントは500文字以下で入力してください。");

		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}
}
