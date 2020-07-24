package by.kharitonov.day6.model.dao;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.request.SelectRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BookListDao {
    Book addBook(Book book) throws DaoException;

    List<Book> removeBookByTags(Book book) throws DaoException;

    Book removeBookById(int id) throws DaoException;

    List<Book> findBooks(SelectRequest selectRequest) throws DaoException;

    default void close(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException("Error during closing result set!");
            }
        }
    }
}
