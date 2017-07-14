package wattaina.bulletin_board.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wattaina.bulletin_board.beans.User;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
//@WebFilter("/LoginCheckFilter")
@WebFilter("/*")
public class LoginCheckFilter implements Filter {


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = ((HttpServletResponse) response);
		String path = req.getServletPath();




		HttpSession session = req.getSession();
		List<String> messages = new ArrayList<String>();
		User users = (User) session.getAttribute("loginUser");
		//Object user = session.getAttribute("loginUser");
		String requestPath = req.getServletPath();

		System.out.println(path);
		if (users == null && !path.equals("/login") && !path.contains("css")){
			messages.add("ログインしてください");
			session.setAttribute("errorMessages", messages);
			res.sendRedirect("login");

			System.out.println("到達");
			return;
		}


			//ユーザーを編集できる権限(本社の人事のみ)
			if(requestPath.equals("/edit")) {


				if(users.getPositionId()!=1){
					messages.add("アクセスする権限がありません");
					session.setAttribute("errorMessages", messages);
					((HttpServletResponse)response).sendRedirect("./");
					return;
				}
			}

				if(requestPath.equals("/signup")) {

					if(users.getPositionId()!=1){
						messages.add("アクセスする権限がありません");
						session.setAttribute("errorMessages", messages);
						((HttpServletResponse)response).sendRedirect("./");
						return;

					}
				}
				if(requestPath.equals("/userlist")) {

					if(users.getPositionId()!=1){
						messages.add("アクセスする権限がありません");
						session.setAttribute("errorMessages", messages);
						((HttpServletResponse)response).sendRedirect("./");
						return;

					}
				}


				// pass the request along the filter chain


			chain.doFilter(request, response);
		}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
