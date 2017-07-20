package wattaina.bulletin_board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wattaina.bulletin_board.beans.Branch;
import wattaina.bulletin_board.beans.Position;
import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.service.BranchService;
import wattaina.bulletin_board.service.PositionService;
import wattaina.bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/userlist"})
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		request.setAttribute("loginUser", user);

		List<User> users = new UserService().getUsers();

		request.setAttribute("users", users);

		List<Branch> branches = new BranchService().getBranches();
		request.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositions();
		request.setAttribute("positions", positions);


		request.getRequestDispatcher("userlist.jsp").forward(request, response);
	}
}

