package wattaina.bulletin_board.dao;

import static wattaina.bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wattaina.bulletin_board.beans.Position;
import wattaina.bulletin_board.exception.SQLRuntimeException;


public class  PositionDao {


	//branches table情報の取得

	public List<Position> getPositions(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM positions";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Position> positionList = toPositionList(rs);
			return positionList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<Position> toPositionList(ResultSet rs) throws SQLException {

		List<Position> ret = new ArrayList<Position>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");

				Position position = new Position();
				position.setId(id);
				position.setName(name);
				ret.add(position);
			}
			return ret;
		} finally {
			close(rs);
		}

	}

}