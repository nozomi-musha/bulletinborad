package wattaina.bulletin_board.service;

import static wattaina.bulletin_board.utils.CloseableUtil.*;
import static wattaina.bulletin_board.utils.DBUtil.*;

import java.sql.Connection;

import wattaina.bulletin_board.beans.Message;
import wattaina.bulletin_board.dao. MessageDao;


public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try {
			connection = getConnection();


			MessageDao userDao = new MessageDao();
			userDao.insert(connection, message);

			commit(connection);
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

	public void dropMessage(Message id) {

		Connection connection = null;
		try {
			connection = getConnection();


			MessageDao messageDao = new MessageDao();
			messageDao.dropMessage(connection, id);

			commit(connection);
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