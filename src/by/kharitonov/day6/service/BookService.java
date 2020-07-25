package by.kharitonov.day6.service;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.request.SelectRequest;
import by.kharitonov.day6.model.request.impl.FindRequestImpl;
import by.kharitonov.day6.model.request.impl.SortRequestImpl;
import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.service.exception.ServiceException;
import by.kharitonov.day6.service.parser.BookParser;
import by.kharitonov.day6.service.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    public List<Book> addBook(String[] tagValues) throws ServiceException {
        if (!new BookValidator().validateAddParameters(tagValues)) {
            throw new ServiceException("Invalid book parameters!");
        }
        BookParser parser = new BookParser();
        Book book = parser.parseBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        List<Book> resultList = new ArrayList<>();
        try {
            int id = dao.addBook(book);
            Book addedBook = Book.newBuilder()
                    .setId(id)
                    .setTitle(book.getTitle())
                    .setAuthors(book.getAuthors())
                    .setYear(book.getYear())
                    .setPages(book.getPages())
                    .setPublishingHouse(book.getPublisher())
                    .build();
            resultList.add(addedBook);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    public List<Book> removeBook(String[] tagValues)
            throws ServiceException {
        if (!new BookValidator().validateRemoveParameters(tagValues)) {
            throw new ServiceException("Invalid book parameters!");
        }
        boolean removingById = tagValues.length == 1;
        List<Book> resultList;
        resultList = removingById
                ? removeBookById(tagValues)
                : removeBookByTags(tagValues);
        return resultList;
    }

    private List<Book> removeBookById(String[] tagValues)
            throws ServiceException {
        List<Book> resultList = new ArrayList<>();
        try {
            BookListDao dao = new BookListDaoImpl();
            int id = Integer.parseInt(tagValues[BookValidator.ID_INDEX]);
            Book book = dao.removeBookById(id);
            resultList.add(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    private List<Book> removeBookByTags(String[] tagValues)
            throws ServiceException {
        List<Book> resultList;
        try {
            BookListDao dao = new BookListDaoImpl();
            BookParser parser = new BookParser();
            Book book = parser.parseBook(tagValues);
            resultList = dao.removeBooks(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultList;
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
        FindRequestImpl findRequest =
                new FindRequestImpl(bookTag, tagValue);
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
        SortRequestImpl sortRequest = new SortRequestImpl(bookTag);
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
