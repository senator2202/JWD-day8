package by.kharitonov.day6.controller.parser;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.type.CommandType;

public class CommandParser {
    private static final String COMMAND_POSTFIX = "_command";

    public ActionCommand defineCommand(String request) {
        ActionCommand command;
        try {
            command = CommandType
                    .valueOf((request + COMMAND_POSTFIX).toUpperCase())
                    .getCurrentCommand();
        } catch (IllegalArgumentException e) {
            command = CommandType.EMPTY_COMMAND.getCurrentCommand();
        }
        return command;
    }
}
