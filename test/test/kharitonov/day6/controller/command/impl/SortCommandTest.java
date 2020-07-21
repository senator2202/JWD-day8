package test.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.impl.SortCommand;
import by.kharitonov.day6.controller.response.CommandResult;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.service.exception.ServiceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SortCommandTest {
    private final SortCommand command = new SortCommand();

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "sortedBooks",
            dataProviderClass = StaticDataProvider.class)
    public void testExecute(List<Book> expectedList, String[] parameters) {
        CommandResult actualResult = command.execute(parameters);
        CommandResult expectedResult = new CommandResult(expectedList, null);
        assertEquals(actualResult, expectedResult);
    }

    @Parameters("parameters")
    @Test(dataProvider = "invalidSortParameters",
            dataProviderClass = StaticDataProvider.class)
    public void testExecuteFindInvalidParameters(String[] parameters) {
        List<Book> expectedList = new ArrayList<>();
        ServiceException exception =
                new ServiceException("Invalid tag data!");
        CommandResult expectedResult =
                new CommandResult(expectedList, exception);
        CommandResult actualResult = command.execute(parameters);
        assertEquals(actualResult, expectedResult);
    }
}