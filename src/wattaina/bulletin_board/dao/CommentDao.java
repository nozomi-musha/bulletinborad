package wattaina.bulletin_board.dao;



import static wattaina.bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wattaina.bulletin_board.beans.Comment;
import wattaina.bulletin_board.exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;


		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append(" text");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(",created_at");
			sql.append(",user_id");
			sql.append(",message_id");
			sql.append(",user_name");

			sql.append(") VALUES (");
			sql.append(" ?"); // text
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // position_id
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(", ?"); // user_id
			sql.append(", ?"); // message_id
			sql.append(", ?"); // user_name

			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getBranchId());
			ps.setInt(3, comment.getPositionId());
			ps.setInt(4, comment.getUserId());
			ps.setInt(5, comment.getMessageId());
			ps.setString(6, comment.getUserName());



//			System.out.println(ps.toString());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	//コメントされたものをリスト化し、（home.jspに渡すため）

	public List<Comment> getComment(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM comments";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Comment> commentList = toCommentList(rs);
			return commentList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<Comment> toCommentList(ResultSet rs) throws SQLException {

		List<Comment> ret = new ArrayList<Comment>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				String text = rs.getString("text");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
				Date createdAt = rs.getDate("created_at");
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				int messageId = rs.getInt("message_id");


				Comment comment = new Comment();
				comment.setId(id);
				comment.setText(text);
				comment.setBranchId(branchId);
				comment.setPositionId(positionId);
				comment.setCreatedAt(createdAt);
				comment.setUserId(userId);
				comment.setUserName(userName);
				comment.setMessageId(messageId);


				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
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