package test.kharitonov.day6.controller.parser;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.impl.*;
import by.kharitonov.day6.controller.parser.CommandParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CommandParserTest {
    private final CommandParser parser = new CommandParser();

    @DataProvider(name = "dataDefineCommand")
    @Test
    public Object[][] dataDefineCommand() {
        return new Object[][]{
                {"add", new AddCommand()},
                {"remove", new RemoveCommand()},
                {"find", new FindCommand()},
                {"sort", new SortCommand()},
                {"empty", new EmptyCommand()},
                {"no matter what", new EmptyCommand()}
        };
    }

    @Test(dataProvider = "dataDefineCommand")
    public void testDefineCommand(String request,
                                  ActionCommand expectedCommand) {
        ActionCommand actualCommand = parser.defineCommand(request);
        assertEquals(actualCommand.getClass(), expectedCommand.getClass());
    }
}