package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.parser.CommandParser;
import by.kharitonov.day6.controller.response.CommandResult;

public class BookWarehouseController {
    private static BookWarehouseController bookWarehouseControllerInstance;

    private BookWarehouseController() {
    }

    public static BookWarehouseController getInstance() {
        if (bookWarehouseControllerInstance == null) {
            bookWarehouseControllerInstance = new BookWarehouseController();
        }
        return bookWarehouseControllerInstance;
    }

    public CommandResult processRequest(String request, String... content) {
        CommandParser parser = new CommandParser();
        ActionCommand command = parser.defineCommand(request);
        return command.execute(content);
    }
}
