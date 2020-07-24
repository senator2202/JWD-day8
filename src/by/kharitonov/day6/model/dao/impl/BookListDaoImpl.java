package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.connect.DataBaseHelper;
import by.kharitonov.day6.model.connect.SqlConnector;
import by.kharitonov.day6.model.creator.BookCreator;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.request.SelectRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookListDaoImpl implements BookListDao {
    /*Method takes Book without id, inserts it into database,
     * returns the same book with id.*/
    @Override
    public Book addBook(Book book) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        Book addedBook = Book.newBuilder().build();
        ResultSet resultSet = null;
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statementAdd =
                     helper.prepareStatementAdd(connection, book);
             PreparedStatement statementSelect =
                     helper.prepareStatementSelect(connection, book)) {
            statementAdd.executeUpdate();
            resultSet = statementSelect.executeQuery();
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                addedBook = bookCreator.create(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
        }
        return addedBook;
    }

    /*Method takes book, deletes rows in database with the same tags,
     * excluding id, and returns list of deleted books.
     * Method throws DaoException if database doesn't have books
     * with the same tags.*/
    @Override
    public List<Book> removeBookByTags(Book removingBook) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        List<Book> removedBooks = new ArrayList<>();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statementSelect =
                     helper.prepareStatementSelect(connection, removingBook);
             ResultSet resultSet = statementSelect.executeQuery();
             PreparedStatement statementDelete =
                     helper.prepareStatementDelete(connection, removingBook)) {
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.create(resultSet);
                removedBooks.add(book);
            }
            int result = statementDelete.executeUpdate();
            if (result == 0) {
                throw new DaoException("There is no such book in warehouse!");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return removedBooks;
    }

    /*Method takes book id, deletes from database row with the same id and
     * returns deleted Book*/
    @Override
    public Book removeBookById(int id) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        Book book = Book.newBuilder().build();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statementSelectById =
                     helper.prepareStatementSelectById(connection, id);
             PreparedStatement statementDeleteById =
                     helper.prepareStatementDeleteById(connection, id)) {
            ResultSet resultSet = statementSelectById.executeQuery();
            int result;
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                book = bookCreator.create(resultSet);
            }
            result = statementDeleteById.executeUpdate();
            if (result == 0) {
                throw new DaoException("There is no such book in warehouse!");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return book;
    }

    /*Metohod takes SelectRequest. It could be FindRequest or SortRequest. Than,
     * according to the select request, it returns list of found books
     * (if selectRequest is instance of FindRequest) or list of sorted books
     * (if selectRequest is instance of SortRequest)*/
    @Override
    public List<Book> findBooks(SelectRequest selectRequest)
            throws DaoException {
        List<Book> allBooks = new ArrayList<>();
        DataBaseHelper helper = new DataBaseHelper();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statement = helper
                     .prepareStatementFind(connection, selectRequest);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.create(resultSet);
                allBooks.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading database!", e);
        }
        return allBooks;
    }
}
