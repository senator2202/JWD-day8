package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.exception.CommandException;
import by.kharitonov.day6.controller.response.CommandResult;
import by.kharitonov.day6.model.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class EmptyCommand implements ActionCommand {
    @Override
    public CommandResult execute(String... content) {
        List<Book> list = new ArrayList<>();
        CommandException exception = new CommandException("Invalid command!");
        return new CommandResult(list, exception);
    }
}
