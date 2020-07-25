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
    /*Method takes Book without id, inserts this book into database,
     * returns id of the book.*/
    @Override
    public int addBook(Book book) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        ResultSet resultSet = null;
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statementAdd =
                     helper.prepareStatementAdd(connection, book);
             PreparedStatement statementSelect =
                     helper.prepareStatementSelect(connection, book)) {
            statementAdd.executeUpdate();
            resultSet = statementSelect.executeQuery();
            resultSet.next();
            return new BookCreator().getBookId(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
        }
    }

    /*Method takes book, selects rows in database with the same tags,
     * (excluding id) deletes them through ResultSet and returns list
     * of deleted books.
     * Method throws DaoException if database doesn't have books
     * with the same tags.*/
    @Override
    public List<Book> removeBooks(Book removingBook) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        List<Book> removedBooks = new ArrayList<>();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statementSelect =
                     helper.prepareStatementSelect(connection, removingBook);
             ResultSet resultSet = statementSelect.executeQuery()) {
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.create(resultSet);
                removedBooks.add(book);
                resultSet.deleteRow();
            }
            if (removedBooks.isEmpty()) {
                throw new DaoException("There is no such book in warehouse!");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return removedBooks;
    }

    /*Method takes book id, select row in database with the same id_book,
     * deletes it through ResultSet and returns deleted Book.
     * Methods throws DaoException if there is on book
     * in database with such id.*/
    @Override
    public Book removeBookById(int id) throws DaoException {
        DataBaseHelper helper = new DataBaseHelper();
        Book book = Book.newBuilder().build();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statementSelectById =
                     helper.prepareStatementSelectById(connection, id);
             ResultSet resultSet = statementSelectById.executeQuery()) {
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                book = bookCreator.create(resultSet);
                resultSet.deleteRow();
            }
            if (book.getId() == 0) {
                throw new DaoException("There is no such book in warehouse!");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return book;
    }

    /*Method takes SelectRequest. It could be FindRequest or SortRequest. Than,
     * according to the select request, it returns list of found books
     * (if selectRequest is instance of FindRequest) or list of sorted books
     * (if selectRequest is instance of SortRequest)*/
    @Override
    public List<Book> findBooks(SelectRequest selectRequest)
            throws DaoException {
        List<Book> foundBooks = new ArrayList<>();
        DataBaseHelper helper = new DataBaseHelper();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statement = helper
                     .prepareStatementFind(connection, selectRequest);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.create(resultSet);
                foundBooks.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading database!", e);
        }
        return foundBooks;
    }
}
