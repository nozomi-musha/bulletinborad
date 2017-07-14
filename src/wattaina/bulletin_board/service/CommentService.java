package wattaina.bulletin_board.service;

import static wattaina.bulletin_board.utils.CloseableUtil.*;
import static wattaina.bulletin_board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import wattaina.bulletin_board.beans.Comment;
import wattaina.bulletin_board.dao.CommentDao;


public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();


			CommentDao userDao = new CommentDao();
			userDao.insert(connection, comment);

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





	public List<Comment> getComment() {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao CommentDao = new CommentDao();
			List<Comment> comments = CommentDao.getComment(connection);

			commit(connection);

			return comments;
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

	public void dropComment(Comment id) {

		Connection connection = null;
		try {
			connection = getConnection();


			CommentDao commentDao = new CommentDao();
			commentDao.dropComment(connection, id);

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