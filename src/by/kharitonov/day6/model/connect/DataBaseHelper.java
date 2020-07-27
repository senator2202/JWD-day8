package by.kharitonov.day6.model.connect;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.request.SelectRequest;
import by.kharitonov.day6.model.request.impl.FindRequestImpl;
import by.kharitonov.day6.model.request.impl.SortRequestImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseHelper {
    private static final String SQL_INSERT =
            "INSERT INTO books(title, authors, year, pages, " +
                    "publisher) VALUES(?,?,?,?,?)";
    private static final String SQL_SELECT =
            "SELECT id_book, title, authors, year, pages, publisher " +
                    "FROM books WHERE title=? AND authors=? AND year=? " +
                    "AND pages=? AND publisher=?";
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
        try {
            PreparedStatement statement = connection
                .prepareStatement(SQL_INSERT);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getPages());
            statement.setString(5, book.getPublisher());
            return statement;
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
    }

    public PreparedStatement prepareStatementSelect(Connection connection,
                                                    Book book)
            throws DaoException {
        try{
            PreparedStatement statement =
                    connection.prepareStatement(SQL_SELECT,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getPages());
            statement.setString(5, book.getPublisher());
            return statement;
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
    }

    public PreparedStatement prepareStatementSelectById(Connection connection,
                                                        int bookId)
            throws DaoException {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SQL_SELECT_BY_ID,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, bookId);
            return statement;
        } catch (SQLException e) {
            throw new DaoException("Error, while getting statement!", e);
        }
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
