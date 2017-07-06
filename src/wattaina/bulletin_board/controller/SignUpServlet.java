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



		//DBから支店名のデータを呼び出すためにリスト化したもの　セレクトボックス

		List<Branch> branches = new BranchService().getBranches();
		request.setAttribute("branhces", branches);

		//System.out.println(branches.get(0).getName());


		List<Position> positions = new PositionService().getPositions();
		request.setAttribute("positions", positions);

		//System.out.println(positions.get(0).getName());


		request.getRequestDispatcher("signup.jsp").forward(request, response);



	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();


		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {


			System.out.println(request.getParameter("name"));

			User user = new User();
			user.setLoginId(request.getParameter("login_id"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setPositionId(Integer.parseInt(request.getParameter("position_id")));

			new UserService().register(user);

			response.sendRedirect("./");

		} else {
			//			String s = request.getParameter("name");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
			//			request.setAttribute("user", user);
			//			request.getRequestDispatcher("signup.jsp").forward(request, response);

		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmation = request.getParameter("confirmation");
		String branch_id = request.getParameter("branch_id");
		String position_id = request.getParameter("position_id");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");

		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");


		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");

		}

		if (StringUtils.isEmpty(confirmation) == true) {
			messages.add("パスワードの確認を入力してください");
		}

			if (!(password == confirmation) == true) {

				messages.add("確認パスワードが一致しません");

		}

		if (StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店名を選択してください");

		}


		if (StringUtils.isEmpty(position_id) == true) {
			messages.add("役職を選択してください");

		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要





		//パスワードの再確認　最初に入力したものと等しいか




		//既存のIDと被っていたら登録できない

		UserService userService = new UserService();
		User  user = userService.getUser(login_id);

		if (user != null) {
			messages.add("既に登録されているIDです");
		}




		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	//支店名選択



}