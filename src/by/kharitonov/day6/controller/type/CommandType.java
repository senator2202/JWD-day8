package by.kharitonov.day6.controller.type;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.impl.*;

public enum CommandType {
    ADD_COMMAND {
        {
            this.command = new AddCommand();
        }
    },
    REMOVE_COMMAND {
        {
            this.command = new RemoveCommand();
        }
    },
    FIND_COMMAND {
        {
            this.command = new FindCommand();
        }
    },
    SORT_COMMAND {
        {
            this.command = new SortCommand();
        }
    },
    EMPTY_COMMAND {
        {
            this.command = new EmptyCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
