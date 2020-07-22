package by.kharitonov.day6.model.dao;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.SelectRequest;
import by.kharitonov.day6.model.exception.DaoException;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws DaoException;

    void removeBook(Book book) throws DaoException;
    
    List<Book> findBooks(SelectRequest selectRequest) throws DaoException;
}
