package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.connect.DataBaseHelper;
import by.kharitonov.day6.model.connect.SqlConnector;
import by.kharitonov.day6.model.creator.BookCreator;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.SelectRequest;
import by.kharitonov.day6.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookListDaoImpl implements BookListDao {

    @Override
    public void addBook(Book book) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statement =
                     helper.prepareStatementAdd(connection, book)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeBook(Book removingBook) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statement =
                     helper.prepareStatementDelete(connection, removingBook)) {
            int result = statement.executeUpdate();
            if (result == 0) {
                throw new DaoException("There is no such book in warehouse!");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findBooks(SelectRequest selectRequest) throws DaoException {
        List<Book> allBooks = new ArrayList<>();
        DataBaseHelper helper = new DataBaseHelper();
        Connection connection = null;
        try {
            connection = SqlConnector.connect();
            PreparedStatement statement = null;
            try {
                statement = helper
                        .prepareStatementFind(connection, selectRequest);
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
}
