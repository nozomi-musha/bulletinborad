package wattaina.bulletin_board.dao;

import static wattaina.bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String accountOrEmail,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, accountOrEmail);
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
				int login_id = rs.getInt("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int position_id = rs.getInt("position_id");
				int is_stopped = rs.getInt("is_stopped");


				User user = new User();

				user.setId(id);
				user.setLogin_id(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setBranch_id(branch_id);
				user.setPositionId(position_id);
				user.setIsStopped(is_stopped);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}

	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO user ( ");
			sql.append("id");
			sql.append(", login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(", is_stopped");
			sql.append(") VALUES (");
			sql.append("null "); // id
			sql.append(", ?"); // login_id
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // position_id
			sql.append(", ?"); // is_stopped
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getPositionId());
			ps.setInt(6, user.getIsStopped());


		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public User getUser(Connection connection, int userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	public void update(Connection connection, User user) {
		// TODO 自動生成されたメソッド・スタブ

	}
}

//UPDATEの処理がこの後入ると思われる
