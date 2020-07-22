package by.kharitonov.day6.model.connect;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.FindRequest;
import by.kharitonov.day6.model.entity.SelectRequest;
import by.kharitonov.day6.model.entity.SortRequest;
import by.kharitonov.day6.model.exception.DaoException;

import java.sql.*;

public class DataBaseHelper {
    private static final String SQL_FIND =
            "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE %s=?";
    private static final String SQL_ORDER =
            "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books ORDER BY %s";
    private static final String SQL_INSERT =
            "INSERT INTO books(title, authors, year, pages, " +
                    "publisher) VALUES(?,?,?,?,?)";
    private static final String SQL_DELETE =
            "DELETE FROM books WHERE title=? AND authors=? AND year=? " +
                    "AND pages=? AND publisher=?";

    public PreparedStatement prepareStatementFind(Connection connection,
                                                  SelectRequest selectRequest)
            throws DaoException {
        PreparedStatement statement;
        try {
            if (selectRequest instanceof SortRequest) {
                String sqlOrder = String.format(SQL_ORDER,
                        selectRequest.getBookTag().name().toLowerCase());
                statement = connection.prepareStatement(sqlOrder);
            } else {
                String sqlFind = String.format(SQL_FIND,
                        selectRequest.getBookTag().name().toLowerCase());
                statement = connection.prepareStatement(sqlFind);
                statement.setString(1,
                        ((FindRequest) selectRequest).getTagValue());
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting statement!", e);
        }
        return statement;
    }

    public PreparedStatement prepareStatementAdd(Connection connection,
                                                 Book book)
            throws DaoException {
        PreparedStatement statement;
        try {
            statement=connection.prepareStatement(SQL_INSERT);
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthors());
            statement.setInt(3,book.getYear());
            statement.setInt(4,book.getPages());
            statement.setString(5,book.getPublisher());
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
        return statement;
    }

    public PreparedStatement prepareStatementDelete(Connection connection,
                                                    Book book)
            throws DaoException {
        PreparedStatement statement;
        try {
            statement=connection.prepareStatement(SQL_DELETE);
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthors());
            statement.setInt(3,book.getYear());
            statement.setInt(4,book.getPages());
            statement.setString(5,book.getPublisher());
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
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
