package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.response.CommandResult;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.service.BookService;
import by.kharitonov.day6.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class RemoveCommand implements ActionCommand {
    @Override
    public CommandResult execute(String... content) {
        BookService service = new BookService();
        CommandResult commandResult;
        try {
            List<Book> bookList = service.removeBook(content);
            commandResult = new CommandResult(bookList, null);
        } catch (ServiceException e) {
            List<Book> bookList = new ArrayList<>();
            commandResult = new CommandResult(bookList, e);
        }
        return commandResult;
    }
}
