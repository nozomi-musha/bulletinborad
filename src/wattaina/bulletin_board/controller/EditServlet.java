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

import wattaina.bulletin_board.beans.Branch;
import wattaina.bulletin_board.beans.Position;
import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.service.BranchService;
import wattaina.bulletin_board.service.PositionService;
import wattaina.bulletin_board.service.UserService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String userId = request.getParameter("userId");

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		//urlで不正なページに飛ばさない
		if (userId == null) {
			messages.add("不正なパラメーターです");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("userlist").forward(request, response);
		}

		if (!(userId.matches("^\\d+$"))){
			messages.add("不正なパラメーターです");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("userlist").forward(request, response);
		}

		User user = new UserService().getUser(userId);

		if (user == null) {
			messages.add("不正なパラメーターです");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("userlist").forward(request, response);
		}

		request.setAttribute("user", user);

		List<Branch> branches = new BranchService().getBranches();
		request.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositions();
		request.setAttribute("positions", positions);

		request.getRequestDispatcher("edit.jsp").forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();


		HttpSession session = request.getSession();

		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("userId")));
		user.setLoginId(request.getParameter("loginId"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		user.setPositionId(Integer.parseInt(request.getParameter("positionId")));

		if (isValid(request, messages) == true) {
			System.out.println(request.getParameter("userId"));
			new UserService().update(user);

			response.sendRedirect("./");

		} else {
			System.out.println("test");
			//			String s = request.getParameter("name");
			session.setAttribute("errorMessages", messages);
			request.setAttribute("user", user);
			List<Branch> branches = new BranchService().getBranches();
			request.setAttribute("branches", branches);

			List<Position> positions = new PositionService().getPositions();
			request.setAttribute("positions", positions);

			request.getRequestDispatcher("edit.jsp").forward(request, response);
			//			request.setAttribute("user", user);
			//			request.getRequestDispatcher("signup.jsp").forward(request, response);

		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmation = request.getParameter("confirmation");
		String branchId = request.getParameter("branchId");
		String positionId = request.getParameter("positionId");

		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}

		if (!password.isEmpty()) {
			if (password.matches("\\w{6,20}")){
				messages.add("パスワードは記号を含む半角英数字6～20字で入力してください1");
			}else if(password.matches("^[ -/:-@\\[-\\`\\{-\\~]+${6,20}")) {
				messages.add("パスワードは記号を含む半角英数字6～20字で入力してください2");
			}
		}else if(StringUtils.isEmpty(confirmation) == true) {
			messages.add("パスワードの確認を入力してください");
		}else if (!(password == confirmation) == true) {
			messages.add("確認パスワードが一致しません");
		}



	if (StringUtils.isEmpty(branchId) == true) {
		messages.add("支店名を選択してください");
	}

	if (StringUtils.isEmpty(positionId) == true) {
		messages.add("役職を選択してください");
	}

	//branchとpositionありえない組み合わせは登録できない

	if (!(branchId.equals("1") && (positionId.equals("1") || positionId.equals("2")))) {
		messages.add("ありえない組み合わせです");
	}


	//既存のIDと被っていたら登録できない
	UserService userService = new UserService();
	User  user = userService.getUser(loginId);

	if (user != null) {
		messages.add("既に登録されているIDです");
	}




	if (messages.size() == 0) {
		return true;
	} else {
		return false;
	}
}
}
