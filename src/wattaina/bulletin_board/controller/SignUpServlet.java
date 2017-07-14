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

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {



		//DBから支店名のデータを呼び出すためにリスト化したもの セレクトボックス

		List<Branch> branches = new BranchService().getBranches();
		request.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositions();
		request.setAttribute("positions", positions);


		request.getRequestDispatcher("signup.jsp").forward(request, response);



	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();


		HttpSession session = request.getSession();
		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		user.setPositionId(Integer.parseInt(request.getParameter("positionId")));

		if (isValid(request, messages) == true) {

			new UserService().register(user);

			response.sendRedirect("./");

		} else {
			//			String s = request.getParameter("name");
			session.setAttribute("errorMessages", messages);

			request.setAttribute("user", user);

			List<Branch> branches = new BranchService().getBranches();
			request.setAttribute("branches", branches);

			List<Position> positions = new PositionService().getPositions();
			request.setAttribute("positions", positions);

			request.getRequestDispatcher("signup.jsp").forward(request, response);
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

		} else if (!(loginId.matches("[a-zA-Z0-9]{6,20}"))) {
			messages.add("ログインIDは半角英数6文字以上20文字以下で入力してください");
		}


		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");

		} else if (!(name.length() <= 10)) {
			messages.add("名前は10文字以下で入力してください。");
		}


		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
//		else if (!(password.matches("^[a-zA-Z0-9]+${6,20}"))) {
//			messages.add("パスワードは記号を含む半角英数字6～20字で入力してください");

//		} else if (!(password.matches("^[ -/:-@\\[-\\`\\{-\\~]+$"))){
//			messages.add("パスワードは記号を含む半角英数字6～20字で入力してください");
//		}


		if (StringUtils.isEmpty(confirmation) == true) {
			messages.add("パスワードの確認を入力してください");
		} else if (!(password.equals(confirmation))) {
			messages.add("確認パスワードが一致しません");
		}


		if (StringUtils.isEmpty(branchId) == true) {
			messages.add("支店名を選択してください");
		}


		if (StringUtils.isEmpty(positionId) == true) {
			messages.add("役職を選択してください");
		}

		// branchとpositionありえない組み合わせは登録できない

		if ((!branchId.equals("1") && (positionId.equals("1") || positionId.equals("2")))) {
			messages.add("ありえない組み合わせです");
		}

		if  (branchId.equals("1") && (!(positionId.equals("1") || positionId.equals("2")))) {
			messages.add("ありえない組み合わせです");

		}

		//既存のIDと被っていたら登録できない

		UserService userService = new UserService();
		User  user = userService.getUser(loginId);

		if (user != null) {
			messages.add("既存のIDは使用できません");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
