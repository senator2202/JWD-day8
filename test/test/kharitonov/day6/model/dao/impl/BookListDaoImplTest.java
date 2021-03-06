package test.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.request.SelectRequest;
import by.kharitonov.day6.model.request.impl.FindRequestImpl;
import by.kharitonov.day6.model.request.impl.SortRequestImpl;
import by.kharitonov.day6.model.type.BookTag;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookListDaoImplTest {
    private final BookListDao dao = new BookListDaoImpl();

    @Test
    public void testAddBook() throws DaoException {
        int id = dao.addBook(StaticDataProvider.ADDING_BOOK);
        FindRequestImpl findRequest = new FindRequestImpl(BookTag.ID_BOOK,
                String.valueOf(id));
        List<Book> list = dao.findBooks(findRequest);
        boolean flag = list.size() == 1 & list.get(0).getId() == id;
        assertTrue(flag);
    }

    @Test
    public void testRemoveBooks() throws DaoException {
        addBookSeveralTimes(3);
        List<Book> removedBooks =
                dao.removeBooks(StaticDataProvider.ADDING_BOOK);
        boolean flag = true;
        for (Book removedBook : removedBooks) {
            FindRequestImpl findRequest = new FindRequestImpl(BookTag.ID_BOOK,
                    String.valueOf(removedBook.getId()));
            flag &= dao.findBooks(findRequest).isEmpty();
        }
        assertTrue(flag);
    }

    @Test
    public void testRemoveBookById() throws DaoException {
        int id = dao.addBook(StaticDataProvider.ADDING_BOOK);
        Book removedBook = dao.removeBookById(id);
        Book expectedBook = Book.newBuilder()
                .setId(id)
                .setTitle(StaticDataProvider.ADDING_BOOK.getTitle())
                .setAuthors(StaticDataProvider.ADDING_BOOK
                        .getAuthors().split(", "))
                .setPages(StaticDataProvider.ADDING_BOOK.getPages())
                .setYear(StaticDataProvider.ADDING_BOOK.getYear())
                .setPublishingHouse(StaticDataProvider.ADDING_BOOK
                        .getPublisher())
                .build();
        assertEquals(removedBook, expectedBook);
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
                {new FindRequestImpl(BookTag.ID_BOOK, "1"), foundIdList},
                {new FindRequestImpl(BookTag.TITLE,
                        "Harry Potter and the deathly hallows"),
                        foundTitleList},
                {new FindRequestImpl(BookTag.AUTHORS, "Joan Roaling"),
                        foundAuthorsList},
                {new FindRequestImpl(BookTag.YEAR, "2015"), foundYearList},
                {new FindRequestImpl(BookTag.PAGES, "788"), foundPagesList},
                {new FindRequestImpl(BookTag.PUBLISHER, "Vysnova"),
                        foundPublisherList},
                {new SortRequestImpl(BookTag.ID_BOOK), sortedListId},
                {new SortRequestImpl(BookTag.TITLE), sortedListTitle},
                {new SortRequestImpl(BookTag.AUTHORS), sortedListAuthors},
                {new SortRequestImpl(BookTag.YEAR), sortedListYear},
                {new SortRequestImpl(BookTag.PAGES), sortedListPages},
                {new SortRequestImpl(BookTag.PUBLISHER), sortedListPublisher}
        };
    }

    @Test(dataProvider = "dataFindBooks")
    public void testFindBooks(SelectRequest selectRequest,
                              List<Book> expectedList)
            throws DaoException {
        setUpDB();
        List<Book> actualList = dao.findBooks(selectRequest);
        assertEquals(actualList, expectedList);
    }

    private void setUpDB() throws DaoException {
        SortRequestImpl sortRequest = new SortRequestImpl(BookTag.ID_BOOK);
        List<Book> allBooks = dao.findBooks(sortRequest);
        for (Book book : allBooks) {
            if (book.getId() > 4) {
                dao.removeBookById(book.getId());
            }
        }
    }

    private void addBookSeveralTimes(int count) throws DaoException {
        for (int i = 0; i < count; i++) {
            dao.addBook(StaticDataProvider.ADDING_BOOK);
        }
    }
}