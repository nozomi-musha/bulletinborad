package wattaina.bulletin_board.service;

import static wattaina.bulletin_board.utils.CloseableUtil.*;
import static wattaina.bulletin_board.utils.DBUtil.*;

import java.sql.Connection;

import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.dao.UserDao;
import wattaina.bulletin_board.utils.CipherUtil;

public class LoginService {

	public User login(String accountOrEmail, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao
					.getUser(connection, accountOrEmail, encPassword);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
