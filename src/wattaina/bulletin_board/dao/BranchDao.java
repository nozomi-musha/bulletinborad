package wattaina.bulletin_board.dao;

import static wattaina.bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wattaina.bulletin_board.beans.Branch;
import wattaina.bulletin_board.exception.SQLRuntimeException;


public class  BranchDao {


	//branches table情報の取得

	public List<Branch> getBranches(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM branches";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Branch> branchList = toBranchList(rs);
			return branchList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<Branch> toBranchList(ResultSet rs) throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");

				Branch branch = new Branch();
				branch.setId(id);
				branch.setName(name);
				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}

	}

}