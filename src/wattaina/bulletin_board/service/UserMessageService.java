package wattaina.bulletin_board.service;



import static wattaina.bulletin_board.utils.CloseableUtil.*;
import static wattaina.bulletin_board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;

import wattaina.bulletin_board.beans.UserMessage;
import wattaina.bulletin_board.dao.UserMessageDao;



public class UserMessageService {

	public List<UserMessage> getUserMessage(String start, String end, String category) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao userMessageDao = new UserMessageDao();
			List<UserMessage> userMessage = userMessageDao.getUserMessage(connection, start, end, category);

			commit(connection);

			return userMessage;
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



//  一番古いメッセージ
//
//	public List<UserMessage> oldMessage() {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserMessageDao userMessageDao = new UserMessageDao();
//			List<UserMessage> userMessage = userMessageDao.oldMessage(connection);
//
//			commit(connection);
//
//			return userMessage;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}

//	カテゴリー取得
	public HashSet<String> getCategory() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao userMessageDao = new UserMessageDao();
			HashSet<String> userMessage = userMessageDao.getCategory(connection);

			commit(connection);

			return userMessage;
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
