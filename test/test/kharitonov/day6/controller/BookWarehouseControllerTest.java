package test.kharitonov.day6.controller;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.controller.exception.CommandException;
import by.kharitonov.day6.controller.response.CommandResult;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.service.exception.ServiceException;
import by.kharitonov.day6.view.ViewEmulator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookWarehouseControllerTest {
    private final BookWarehouseController controller =
            BookWarehouseController.getInstance();

    @Test
    public void testProcessRequestAddViewChange() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.ADDING_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        addList.add(StaticDataProvider.ADDING_BOOK);
        expectedResult = new CommandResult(addList, null);
        controller.processRequest("add", bookTags);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Parameters("tagValues")
    @Test(dataProvider = "invalidBookTags",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestAddException(String[] tagValues) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        ServiceException exception =
                new ServiceException("Invalid book parameters!");
        expectedResult = new CommandResult(addList, exception);
        controller.processRequest("add", tagValues);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Test
    public void testProcessRequestAddExisting() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.FIRST_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        ServiceException exception =
                new ServiceException("This book already exists!");
        expectedResult = new CommandResult(addList, exception);
        controller.processRequest("add", bookTags);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Test
    public void testProcessRequestRemoveViewChange() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.SECOND_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        addList.add(StaticDataProvider.SECOND_BOOK);
        expectedResult = new CommandResult(addList, null);
        controller.processRequest("remove", bookTags);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Parameters("tagValues")
    @Test(dataProvider = "invalidBookTags",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestRemoveException(String[] tagValues) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        ServiceException exception =
                new ServiceException("Invalid book parameters!");
        expectedResult = new CommandResult(addList, exception);
        controller.processRequest("remove", tagValues);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Test
    public void testProcessRequestRemoveNotExisting() {
        List<Book> addList = new ArrayList<>();
        Book book = StaticDataProvider.ADDING_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        CommandResult expectedResult;
        ServiceException exception =
                new ServiceException("Such book doesn't exist!");
        expectedResult = new CommandResult(addList, exception);
        controller.processRequest("remove", bookTags);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "foundBooks",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestFind(List<Book> expectedList,
                                       String[] parameters) {
        CommandResult expectedResult;
        expectedResult = new CommandResult(expectedList, null);
        controller.processRequest("find", parameters);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Parameters("parameters")
    @Test(dataProvider = "invalidFindParameters",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestFindException(String[] parameters) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        ServiceException exception =
                new ServiceException("Invalid tag data!");
        expectedResult = new CommandResult(addList, exception);
        controller.processRequest("find", parameters);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "sortedBooks",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestSort(List<Book> expectedList,
                                       String[] parameters) {
        CommandResult expectedResult;
        expectedResult = new CommandResult(expectedList, null);
        controller.processRequest("sort", parameters);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Parameters("parameters")
    @Test(dataProvider = "invalidSortParameters",
            dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestSortException(String[] parameters) {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        ServiceException exception =
                new ServiceException("Invalid tag data!");
        expectedResult = new CommandResult(addList, exception);
        controller.processRequest("sort", parameters);
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }

    @Test()
    public void testProcessRequestCommandException() {
        List<Book> addList = new ArrayList<>();
        CommandResult expectedResult;
        CommandException exception =
                new CommandException("Invalid command!");
        expectedResult = new CommandResult(addList, exception);
        controller.processRequest("wrong_command", "no matter what value");
        assertEquals(ViewEmulator.getCommandResult(), expectedResult);
    }
}