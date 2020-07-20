package by.kharitonov.day6.controller.command;

import by.kharitonov.day6.controller.response.CommandResult;

public interface ActionCommand {
    CommandResult execute(String... content);
}
