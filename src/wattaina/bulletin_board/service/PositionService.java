package wattaina.bulletin_board.service;


import static wattaina.bulletin_board.utils.CloseableUtil.*;
import static wattaina.bulletin_board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import wattaina.bulletin_board.beans.Position;
import wattaina.bulletin_board.dao.PositionDao;



public class PositionService {

	public List<Position> getPositions() {

		Connection connection = null;
		try {
			connection = getConnection();

			PositionDao positionDao = new PositionDao();
			List<Position> position = positionDao.getPositions(connection);

			commit(connection);

			return position;
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
