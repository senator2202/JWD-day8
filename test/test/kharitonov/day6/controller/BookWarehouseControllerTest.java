package test.kharitonov.day6.controller;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.controller.exception.CommandException;
import by.kharitonov.day6.controller.response.CommandResult;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.service.exception.ServiceException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class BookWarehouseControllerTest {
    private final BookWarehouseController controller =
            BookWarehouseController.getInstance();

    @Test
    public void testProcessRequestAddViewChange() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.ADDING_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        CommandResult actualResult;
        addList.add(StaticDataProvider.ADDING_BOOK);
        expectedResult = new CommandResult(addList, null);
        actualResult = controller.processRequest("add", bookTags);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters("tagValues")
    @Test(dataProvider = "invalidBookTags",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestAddException(String[] tagValues) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        CommandResult actualResult;
        ServiceException exception =
                new ServiceException("Invalid book parameters!");
        expectedResult = new CommandResult(addList, exception);
        actualResult = controller.processRequest("add", tagValues);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testProcessRequestAddExisting() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.FIRST_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        CommandResult actualResult;
        ServiceException exception =
                new ServiceException("This book already exists!");
        expectedResult = new CommandResult(addList, exception);
        actualResult = controller.processRequest("add", bookTags);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testProcessRequestRemoveViewChange() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.SECOND_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        CommandResult actualResult;
        addList.add(StaticDataProvider.SECOND_BOOK);
        expectedResult = new CommandResult(addList, null);
        actualResult = controller.processRequest("remove", bookTags);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters("tagValues")
    @Test(dataProvider = "invalidBookTags",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestRemoveException(String[] tagValues) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        CommandResult actualResult;
        ServiceException exception =
                new ServiceException("Invalid book parameters!");
        expectedResult = new CommandResult(addList, exception);
        actualResult = controller.processRequest("remove", tagValues);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testProcessRequestRemoveNotExisting() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.ADDING_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        CommandResult actualResult;
        ServiceException exception =
                new ServiceException("Such book doesn't exist!");
        expectedResult = new CommandResult(addList, exception);
        actualResult = controller.processRequest("remove", bookTags);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "foundBooks",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestFind(List<Book> expectedList,
                                       String[] parameters) {
        CommandResult expectedResult;
        CommandResult actualResult;
        expectedResult = new CommandResult(expectedList, null);
        actualResult = controller.processRequest("find", parameters);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters("parameters")
    @Test(dataProvider = "invalidFindParameters",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestFindException(String[] parameters) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        CommandResult actualResult;
        ServiceException exception =
                new ServiceException("Invalid tag data!");
        expectedResult = new CommandResult(addList, exception);
        actualResult = controller.processRequest("find", parameters);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "sortedBooks",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestSort(List<Book> expectedList,
                                       String[] parameters) {
        CommandResult expectedResult;
        CommandResult actualResult;
        expectedResult = new CommandResult(expectedList, null);
        actualResult = controller.processRequest("sort", parameters);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters("parameters")
    @Test(dataProvider = "invalidSortParameters",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestSortException(String[] parameters) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        CommandResult actualResult;
        ServiceException exception =
                new ServiceException("Invalid tag data!");
        expectedResult = new CommandResult(addList, exception);
        actualResult = controller.processRequest("sort", parameters);
        assertEquals(actualResult, expectedResult);
    }

    @Test()
    public void testProcessRequestCommandException() {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        CommandResult actualResult;
        CommandException exception =
                new CommandException("Invalid command!");
        expectedResult = new CommandResult(addList, exception);
        actualResult = controller.processRequest("wrong_command", "no matter what value");
        assertEquals(actualResult, expectedResult);
    }
}