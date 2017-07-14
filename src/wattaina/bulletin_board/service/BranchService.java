package wattaina.bulletin_board.service;

import static wattaina.bulletin_board.utils.CloseableUtil.*;
import static wattaina.bulletin_board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import wattaina.bulletin_board.beans.Branch;
import wattaina.bulletin_board.dao.BranchDao;



public class BranchService {

	public List<Branch> getBranches() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> branch = branchDao.getBranches(connection);

			commit(connection);

			return branch;
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
