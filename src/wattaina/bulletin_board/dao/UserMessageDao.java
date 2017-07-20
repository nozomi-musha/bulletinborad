package wattaina.bulletin_board.dao;


import static wattaina.bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import wattaina.bulletin_board.beans.UserMessage;
import wattaina.bulletin_board.exception.SQLRuntimeException;


public class  UserMessageDao {



	public List<UserMessage> getUserMessage(Connection connection, String start, String end, String category) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM user_message  where created_at between ? and ? ";
			if (!(category == null || category.isEmpty())) {
				sql += "and category = ?";
			}

			ps = connection.prepareStatement(sql);

			ps.setString(1, start + " 00:00:00");
			ps.setString(2, end  + " 23:59:59");

			if (!(category == null || category.isEmpty())) {
				ps.setString(3, category);
			}

			ResultSet rs = ps.executeQuery();

			System.out.println(ps.toString());

			List<UserMessage> userMessageList = toUserMessageList(rs);
			return userMessageList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<UserMessage> toUserMessageList(ResultSet rs) throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {

				int messageId = rs.getInt("message_id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String text = rs.getString("text");
				Date createdAt = rs.getDate("created_at");
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");


				UserMessage userMessage = new UserMessage();
				userMessage.setMessageId(messageId);
				userMessage.setTitle(title);
				userMessage.setCategory(category);
				userMessage.setText(text);
				userMessage.setCreatedAt(createdAt);
				userMessage.setUserId(userId);
				userMessage.setName(name);
				userMessage.setBranchId(branchId);


				ret.add(userMessage);
			}
			return ret;
		} finally {
			close(rs);
		}

	}
	//一番古いメッセージを取得
	public List<UserMessage> oldMessage(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT created_at FROM messages LIMIT 1";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<UserMessage> userMessageList = toMessageList(rs);
			return userMessageList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<UserMessage> toMessageList(ResultSet rs) throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {

				Date createdAt = rs.getDate("created_at");

				UserMessage userMessage = new UserMessage();

				userMessage.setCreatedAt(createdAt);

				ret.add(userMessage);
			}
			return ret;
		} finally {
			close(rs);
		}

	}

	//カテゴリーの取得

	public HashSet<String> getCategory(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT category FROM user_message";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			HashSet<String> userMessageList = getCategoryList(rs);
			return userMessageList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private HashSet<String> getCategoryList(ResultSet rs) throws SQLException {

		HashSet<String> hs = new HashSet<String>();
		try {
			while (rs.next()) {

				String category = rs.getString("category");

				hs.add(category);
			}
			return hs;
		} finally {
			close(rs);
		}

	}
}
