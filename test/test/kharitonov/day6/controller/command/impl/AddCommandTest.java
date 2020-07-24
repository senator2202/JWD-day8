package test.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.impl.AddCommand;
import by.kharitonov.day6.controller.response.CommandResult;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.service.exception.ServiceException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AddCommandTest {
    private final AddCommand command = new AddCommand();

    @Test
    public void testExecute() {
        List<Book> expectedList = new ArrayList<>();
        Book book = StaticDataProvider.ADDING_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        expectedList.add(StaticDataProvider.ADDING_BOOK);
        CommandResult actualResult = command.execute(bookTags);
        CommandResult expectedResult = new CommandResult(expectedList, null);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters("content")
    @Test(dataProvider = "invalidBookTags",
            dataProviderClass = StaticDataProvider.class)
    public void testExecuteCatchTagsException(String[] content) {
        List<Book> expectedList = new ArrayList<>();
        ServiceException exception =
                new ServiceException("Invalid book parameters!");
        CommandResult expectedResult =
                new CommandResult(expectedList, exception);
        CommandResult actualResult = command.execute(content);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void dataExecuteCatchExistingException() {
        List<Book> expectedList = new ArrayList<>();
        Book book = StaticDataProvider.FIRST_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        ServiceException exception =
                new ServiceException("This book already exists!");
        CommandResult expectedResult =
                new CommandResult(expectedList, exception);
        CommandResult actualResult =
                command.execute(bookTags);
        assertEquals(actualResult, expectedResult);
    }
}