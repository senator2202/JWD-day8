package test.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.model.exception.DaoException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookListDaoImplTest {
    private final BookListDao dao = new BookListDaoImpl();
    private final BookWarehouse warehouse = BookWarehouse.getInstance();

    @BeforeClass
    @BeforeMethod
    private void setUp() {
        warehouse.removeAll();
        warehouse.add(StaticDataProvider.FIRST_BOOK);
        warehouse.add(StaticDataProvider.SECOND_BOOK);
        warehouse.add(StaticDataProvider.THIRD_BOOK);
        warehouse.add(StaticDataProvider.FOURS_BOOK);
    }

    @Test
    public void testAddBook() throws DaoException {
        boolean flag;
        dao.addBook(StaticDataProvider.ADDING_BOOK);
        flag = warehouse.indexOf(StaticDataProvider.ADDING_BOOK) == 4;
        assertTrue(flag);
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "This book already exists!")
    public void testAddExistingBook() throws DaoException {
        dao.addBook(StaticDataProvider.FIRST_BOOK);
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "Warehouse is full!")
    public void testAddBookCapacityException() throws DaoException {
        for (int i = 0; i < 96; i++) {
            warehouse.add(StaticDataProvider.FIRST_BOOK);
        }
        dao.addBook(StaticDataProvider.ADDING_BOOK);
    }

    @Test
    public void testRemoveBook() throws DaoException {
        boolean flag = warehouse.indexOf(StaticDataProvider.THIRD_BOOK) == 2;
        dao.removeBook(StaticDataProvider.THIRD_BOOK);
        assertTrue(warehouse.indexOf(StaticDataProvider.THIRD_BOOK) == -1 &&
                flag);
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "Such book doesn't exist!")
    public void testRemoveBookException() throws DaoException {
        dao.removeBook(StaticDataProvider.ADDING_BOOK);
    }

    @Test
    public void testFindBookById() {
        List<Book> actualList = dao.findBookById("3");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testFindBooksByTitle() {
        List<Book> actualList = dao.findBooksByTitle("Lord of the rings");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testFindBooksByAuthor() {
        List<Book> actualList = dao.findBooksByAuthor("Joan Roaling");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testFindBooksByYear() {
        List<Book> actualList = dao.findBooksByYear("2005");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testFindBooksByPages() {
        List<Book> actualList = dao.findBooksByPages("800");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testFindBooksByPublishingHouse() {
        List<Book> actualList =
                dao.findBooksByPublishingHouse("Ishi Press");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testSortBooksById() throws DaoException {
        List<Book> actualList = dao.sortBooksById();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testSortBooksByTitle() {
        List<Book> actualList = dao.sortBooksByTitle();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testSortBooksByAuthors() {
        List<Book> actualList = dao.sortBooksByAuthors();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testSortBooksByYear() {
        List<Book> actualList = dao.sortBooksByYear();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testSortBooksByPages() {
        List<Book> actualList = dao.sortBooksByPages();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testSortBooksByPublishingHouse() {
        List<Book> actualList = dao.sortBooksByPublishingHouse();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testFindAll() throws DaoException {
        List<Book> actualList = dao.findAll();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.SECOND_BOOK);
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        expectedList.add(StaticDataProvider.THIRD_BOOK);
        expectedList.add(StaticDataProvider.FOURS_BOOK);
        assertEquals(actualList,expectedList);
    }
}