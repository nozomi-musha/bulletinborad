package wattaina.bulletin_board.dao;

import static wattaina.bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wattaina.bulletin_board.beans.Comment;
import wattaina.bulletin_board.beans.User;
import wattaina.bulletin_board.exception.SQLRuntimeException;

public class UserDao {

	/**
	 * ログインするときの参照
	 * @param connection
	 * @param login_id
	 * @param password
	 * @return
	 */
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



	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(") VALUES (");
			sql.append(" ?"); // login_id
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // position_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getPositionId());



			System.out.println(ps.toString());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}



	public User getUser(Connection connection, String loginId ) {
		// 新規登録するとき既存のIDと被っていないか確認

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, loginId);
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



	public List<User> getUsers(Connection connection) {

		// ログインするとき既存のIDと被っていないか確認

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}






	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);
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



	public void update(Connection connection, User user) {

		PreparedStatement ps = null;

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("update users set");
			sql.append(" login_id = ?");
			sql.append(", name= ?");
			sql.append(", branch_id = ?");
			sql.append(", position_id = ?");
			if(!user.getPassword().isEmpty()) {
				sql.append(", password = ?");
			}

			sql.append(" where id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString (1, user.getLoginId());
			ps.setString (2, user.getName());
			ps.setInt(3, user.getBranchId());
			ps.setInt(4, user.getPositionId());
			if(!user.getPassword().isEmpty()) {
				ps.setString (5, user.getPassword());
				ps.setInt(6, user.getId());

				System.out.println("passあり");

				System.out.println(ps.toString());

			} else {
				ps.setInt(5, user.getId());
				System.out.println(ps.toString());

			}


			ps.executeUpdate();


		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}


	public void isStopped(Connection connection, User user) {

		PreparedStatement ps = null;

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("update users set");
			sql.append(" is_stopped= ?");
			sql.append(" where id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt (1, user.getIsStopped());
			ps.setInt (2, user.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}
	public void dropComment(Connection connection, Comment id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM comments ");

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

}