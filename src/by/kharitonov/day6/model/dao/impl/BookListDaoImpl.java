package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.connect.DataBaseHelper;
import by.kharitonov.day6.model.connect.SqlConnector;
import by.kharitonov.day6.model.creator.BookCreator;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.FindRequest;
import by.kharitonov.day6.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookListDaoImpl implements BookListDao {

    @Override
    public void addBook(Book book) throws DaoException {
    }

    @Override
    public void removeBook(Book removingBook) throws DaoException {
    }

    @Override
    public List<Book> findBooks(FindRequest findRequest) throws DaoException {
        List<Book> allBooks = new ArrayList<>();
        DataBaseHelper helper = new DataBaseHelper();
        Connection connection = null;
        try {
            connection = SqlConnector.connect();
            PreparedStatement statement = null;
            try {
                statement = helper
                        .getPreparedStatementFind(connection, findRequest);
                ResultSet resultSet = null;
                try {
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        BookCreator bookCreator = new BookCreator();
                        Book book = bookCreator.create(resultSet);
                        allBooks.add(book);
                    }
                } finally {
                    helper.close(resultSet);
                }
            } finally {
                helper.close(statement);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading database!", e);
        } finally {
            helper.close(connection);
        }
        return allBooks;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> allBooks = new ArrayList<>();
        DataBaseHelper helper = new DataBaseHelper();
        Connection connection = null;
        try {
            connection = SqlConnector.connect();
            PreparedStatement statement = null;
            try {
                statement = helper.getPreparedStatementFindAll(connection);
                ResultSet resultSet = null;
                try {
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        BookCreator bookCreator = new BookCreator();
                        Book book = bookCreator.create(resultSet);
                        allBooks.add(book);
                    }
                } finally {
                    helper.close(resultSet);
                }
            } finally {
                helper.close(statement);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading database!", e);
        } finally {
            helper.close(connection);
        }
        return allBooks;
    }

    private void close(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Сonnection close error! ", e);
            }
        }
    }

    private void close(Statement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Statement close error! ", e);
            }
        }
    }

    private void close(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException("ResultSet close error! ", e);
            }
        }
    }
}
