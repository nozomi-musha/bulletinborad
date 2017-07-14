package wattaina.bulletin_board.dao;

import static wattaina.bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wattaina.bulletin_board.beans.Message;
import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append(" title");
			sql.append(", category");
			sql.append(", text");
			sql.append(",created_at");
			sql.append(",user_id");
			sql.append(") VALUES (");
			sql.append(" ?"); // title
			sql.append(", ?"); // category
			sql.append(", ?"); // text
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(", ?"); // user_id

			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getTitle());
			ps.setString(2,message.getCategory());
			ps.setString(3, message.getText());
			ps.setInt(4, message.getUserId());

			//			System.out.println(ps.toString());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void dropMessage(Connection connection, Message id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM messages ");

			sql.append(" where id = ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id.getId());

			//			System.out.println(ps.toString());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String login_id , String password) {

		// ログインするとき既存のIDと被っていないか確認

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, login_id);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");

			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int position_id = rs.getInt("position_id");
				int is_stopped = rs.getInt("is_stopped");

				User user = new User();

				user.setId(id);
				user.setLoginId(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branch_id);
				user.setPositionId(position_id);
				user.setIsStopped(is_stopped);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}

	}

}