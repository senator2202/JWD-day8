package by.kharitonov.day6.service;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.FindRequest;
import by.kharitonov.day6.model.entity.SelectRequest;
import by.kharitonov.day6.model.entity.SortRequest;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.service.exception.ServiceException;
import by.kharitonov.day6.service.parser.BookParser;
import by.kharitonov.day6.service.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    public List<Book> addBook(String[] tagValues) throws ServiceException {
        if (!new BookValidator().validateAllBookTags(tagValues)) {
            throw new ServiceException("Invalid book parameters!");
        }
        BookParser parser = new BookParser();
        Book book = parser.parseBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        List<Book> resultList = new ArrayList<>();
        try {
            dao.addBook(book);
            resultList.add(book);
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Book> removeBook(String[] tagValues)
            throws ServiceException {
        if (!new BookValidator().validateAllBookTags(tagValues)) {
            throw new ServiceException("Invalid book parameters!");
        }
        BookParser parser = new BookParser();
        Book book = parser.parseBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        List<Book> resultList = new ArrayList<>();
        try {
            dao.removeBook(book);
            resultList.add(book);
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Book> findBooks(String[] parameters)
            throws ServiceException {
        if (!new BookValidator().validateFindParameters(parameters)) {
            throw new ServiceException("Invalid tag data!");
        }
        BookParser parser = new BookParser();
        BookTag bookTag =
                parser.parseBookTag(parameters[BookValidator.BOOK_TAG_INDEX]);
        String tagValue = parameters[BookValidator.TAG_VALUE_INDEX];
        FindRequest findRequest =
                new FindRequest(bookTag, tagValue);
        return executeFind(findRequest);
    }

    public List<Book> sortBooks(String[] parameters)
            throws ServiceException {
        if (!new BookValidator().validateSortParameters(parameters)) {
            throw new ServiceException("Invalid tag data!");
        }
        BookParser parser = new BookParser();
        BookTag bookTag =
                parser.parseBookTag(parameters[BookValidator.BOOK_TAG_INDEX]);
        SortRequest sortRequest = new SortRequest(bookTag);
        return executeFind(sortRequest);
    }

    private List<Book> executeFind(SelectRequest selectRequest)
            throws ServiceException {
        BookListDao dao = new BookListDaoImpl();
        try {
            return dao.findBooks(selectRequest);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
