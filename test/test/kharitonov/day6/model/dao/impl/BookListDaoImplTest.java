package test.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.FindRequest;
import by.kharitonov.day6.model.entity.SelectRequest;
import by.kharitonov.day6.model.entity.SortRequest;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.type.BookTag;
import org.mockito.InjectMocks;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class BookListDaoImplTest {
    private final BookListDao dao = new BookListDaoImpl();

    @Test
    public void setUp() throws Exception {
    }

    @Test
    public void testAddBook() throws DaoException {
            dao.addBook(StaticDataProvider.ADDING_BOOK);
    }

    @Test
    public void testRemoveBook() throws DaoException {
        dao.removeBook(StaticDataProvider.ADDING_BOOK);
    }

    @DataProvider(name = "dataFindBooks")
    @Test
    public Object[][] dataFindBooks() {
        List<Book> foundIdList = new ArrayList<>();
        List<Book> foundTitleList = new ArrayList<>();
        List<Book> foundAuthorsList = new ArrayList<>();
        List<Book> foundYearList = new ArrayList<>();
        List<Book> foundPagesList = new ArrayList<>();
        List<Book> foundPublisherList = new ArrayList<>();
        List<Book> sortedListId = new ArrayList<>();
        List<Book> sortedListTitle = new ArrayList<>();
        List<Book> sortedListAuthors = new ArrayList<>();
        List<Book> sortedListYear = new ArrayList<>();
        List<Book> sortedListPages = new ArrayList<>();
        List<Book> sortedListPublisher = new ArrayList<>();
        sortedListId.add(StaticDataProvider.SECOND_BOOK);
        sortedListId.add(StaticDataProvider.FIRST_BOOK);
        sortedListId.add(StaticDataProvider.THIRD_BOOK);
        sortedListId.add(StaticDataProvider.FOURS_BOOK);
        sortedListTitle.add(StaticDataProvider.FIRST_BOOK);
        sortedListTitle.add(StaticDataProvider.FOURS_BOOK);
        sortedListTitle.add(StaticDataProvider.SECOND_BOOK);
        sortedListTitle.add(StaticDataProvider.THIRD_BOOK);
        sortedListAuthors.add(StaticDataProvider.THIRD_BOOK);
        sortedListAuthors.add(StaticDataProvider.FIRST_BOOK);
        sortedListAuthors.add(StaticDataProvider.FOURS_BOOK);
        sortedListAuthors.add(StaticDataProvider.SECOND_BOOK);
        sortedListYear.add(StaticDataProvider.SECOND_BOOK);
        sortedListYear.add(StaticDataProvider.FOURS_BOOK);
        sortedListYear.add(StaticDataProvider.FIRST_BOOK);
        sortedListYear.add(StaticDataProvider.THIRD_BOOK);
        sortedListPages.add(StaticDataProvider.THIRD_BOOK);
        sortedListPages.add(StaticDataProvider.FOURS_BOOK);
        sortedListPages.add(StaticDataProvider.FIRST_BOOK);
        sortedListPages.add(StaticDataProvider.SECOND_BOOK);
        sortedListPublisher.add(StaticDataProvider.FIRST_BOOK);
        sortedListPublisher.add(StaticDataProvider.THIRD_BOOK);
        sortedListPublisher.add(StaticDataProvider.FOURS_BOOK);
        sortedListPublisher.add(StaticDataProvider.SECOND_BOOK);
        foundIdList.add(StaticDataProvider.SECOND_BOOK);
        foundTitleList.add(StaticDataProvider.FIRST_BOOK);
        foundAuthorsList.add(StaticDataProvider.FIRST_BOOK);
        foundAuthorsList.add(StaticDataProvider.FOURS_BOOK);
        foundYearList.add(StaticDataProvider.THIRD_BOOK);
        foundPagesList.add(StaticDataProvider.FOURS_BOOK);
        foundPublisherList.add(StaticDataProvider.SECOND_BOOK);
        return new Object[][]{
                {new FindRequest(BookTag.ID_BOOK, "1"), foundIdList},
                {new FindRequest(BookTag.TITLE,
                        "Harry Potter and the deathly hallows"),
                        foundTitleList},
                {new FindRequest(BookTag.AUTHORS, "Joan Roaling"),
                        foundAuthorsList},
                {new FindRequest(BookTag.YEAR, "2015"), foundYearList},
                {new FindRequest(BookTag.PAGES, "788"), foundPagesList},
                {new FindRequest(BookTag.PUBLISHER, "Vysnova"),
                        foundPublisherList},
                {new SortRequest(BookTag.ID_BOOK), sortedListId},
                {new SortRequest(BookTag.TITLE), sortedListTitle},
                {new SortRequest(BookTag.AUTHORS), sortedListAuthors},
                {new SortRequest(BookTag.YEAR), sortedListYear},
                {new SortRequest(BookTag.PAGES), sortedListPages},
                {new SortRequest(BookTag.PUBLISHER), sortedListPublisher}
        };
    }

    @Test(dataProvider = "dataFindBooks")
    public void testFindBooks(SelectRequest selectRequest,
                              List<Book> expectedList)
            throws DaoException {
        List<Book> actualList = dao.findBooks(selectRequest);
        assertEquals(actualList, expectedList);
    }
}