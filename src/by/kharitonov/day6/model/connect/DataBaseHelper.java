package by.kharitonov.day6.model.connect;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.request.SelectRequest;
import by.kharitonov.day6.model.request.impl.FindRequestImpl;
import by.kharitonov.day6.model.request.impl.SortRequestImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHelper {
    private static final String SQL_INSERT =
            "INSERT INTO books(title, authors, year, pages, " +
                    "publisher) VALUES(?,?,?,?,?)";
    private static final String SQL_DELETE =
            "DELETE FROM books WHERE title=? AND authors=? AND year=? " +
                    "AND pages=? AND publisher=?";
    private static final String SQL_SELECT =
            "SELECT id_book, title, authors, year, pages, publisher " +
                    "FROM books WHERE title=? AND authors=? AND year=? " +
                    "AND pages=? AND publisher=?";
    private static final String SQL_DELETE_BY_ID =
            "DELETE FROM books WHERE id_book=?";
    private static final String SQL_SELECT_BY_ID =
            "SELECT id_book, title, authors, year, pages, publisher " +
                    "FROM books WHERE id_book=?";
    private static final String SQL_FIND =
            "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE %s=?";
    private static final String SQL_ORDER =
            "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books ORDER BY %s";

    public PreparedStatement prepareStatementAdd(Connection connection,
                                                 Book book)
            throws DaoException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getPages());
            statement.setString(5, book.getPublisher());
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
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getPages());
            statement.setString(5, book.getPublisher());
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
        return statement;
    }

    public PreparedStatement prepareStatementSelect(Connection connection,
                                                    Book book)
            throws DaoException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_SELECT);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getPages());
            statement.setString(5, book.getPublisher());
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
        return statement;
    }

    public PreparedStatement prepareStatementDeleteById(Connection connection,
                                                        int bookId)
            throws DaoException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setInt(1, bookId);
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
        return statement;
    }

    public PreparedStatement prepareStatementSelectById(Connection connection,
                                                        int bookId)
            throws DaoException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, bookId);
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
        return statement;
    }

    public PreparedStatement prepareStatementFind(Connection connection,
                                                  SelectRequest selectRequest)
            throws DaoException {
        PreparedStatement statement;
        try {
            if (selectRequest instanceof SortRequestImpl) {
                String sqlOrder = String.format(SQL_ORDER,
                        selectRequest.getBookTag().name().toLowerCase());
                statement = connection.prepareStatement(sqlOrder);
            } else {
                String sqlFind = String.format(SQL_FIND,
                        selectRequest.getBookTag().name().toLowerCase());
                statement = connection.prepareStatement(sqlFind);
                statement.setString(1,
                        ((FindRequestImpl) selectRequest).getTagValue());
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting statement!", e);
        }
        return statement;
    }
}
