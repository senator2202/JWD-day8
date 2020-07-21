package by.kharitonov.day6.model.connect;

import by.kharitonov.day6.model.entity.FindRequest;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.type.BookTag;

import java.sql.*;

public class DataBaseHelper {
    private static final String SQL_FIND_ALL =
            "SELECT id_book, title, authors, year, pages, publisher FROM books";

    public PreparedStatement getPreparedStatementFindAll(Connection connection)
            throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
        } catch (SQLException e) {
            throw new DaoException("Error while getting statement!");
        }
        return statement;
    }

    public PreparedStatement getPreparedStatementFind(Connection connection,
                                                      FindRequest findRequest)
            throws DaoException {
        PreparedStatement statement = null;
        try {
            if (findRequest.getFindTag() == BookTag.NONE) {
                statement = connection.prepareStatement(findRequest.getSortTag().getOrderQuery());
            } else {
                statement = connection.prepareStatement(findRequest.getFindTag().getFindQuery());
                statement.setString(1, findRequest.getTagValue());
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting statement!");
        }
        return statement;
    }

    public void close(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Сonnection close error! ", e);
            }
        }
    }

    public void close(Statement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Statement close error! ", e);
            }
        }
    }

    public void close(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException("ResultSet close error! ", e);
            }
        }
    }
}
