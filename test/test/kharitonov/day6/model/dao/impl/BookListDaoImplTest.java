package test.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.FindRequest;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.type.BookTag;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class BookListDaoImplTest {
    private final BookListDao dao = new BookListDaoImpl();

    @Test
    public void testAddBook() throws DaoException {
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "This book already exists!")
    public void testAddExistingBook() throws DaoException {
        dao.addBook(StaticDataProvider.FIRST_BOOK);
    }

    @Test
    public void testRemoveBook() throws DaoException {
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "Such book doesn't exist!")
    public void testRemoveBookException() throws DaoException {
        dao.removeBook(StaticDataProvider.ADDING_BOOK);
    }

    @Test
    public void testFindBooks() throws DaoException {
        FindRequest findRequest =
                new FindRequest(BookTag.NONE, "", BookTag.YEAR);
        dao.findBooks(findRequest);
    }

    @Test
    public void testFindAll() throws DaoException {
        List<Book> actualList = dao.findAll();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        assertEquals(actualList, expectedList);
    }
}