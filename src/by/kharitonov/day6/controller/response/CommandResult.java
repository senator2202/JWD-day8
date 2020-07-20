package by.kharitonov.day6.controller.response;

import by.kharitonov.day6.model.entity.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CommandResult {
    private final Exception exception;
    private final List<Book> bookList;

    public CommandResult() {
        exception = null;
        bookList = new ArrayList<>();
    }

    public CommandResult(List<Book> booksList, Exception exception) {
        this.bookList = booksList;
        this.exception = exception;
    }

    public Optional<Exception> getException() {
        return exception != null ? Optional.of(exception) : Optional.empty();
    }

    public List<Book> getBookList() {
        return Collections.unmodifiableList(bookList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandResult that = (CommandResult) o;
        boolean flag;
        if (exception == null && that.exception != null) {
            flag = false;
        } else if (that.exception == null && exception != null) {
            flag = false;
        } else if (exception == null) {
            flag = true;
        } else {
            flag = exception.getMessage()
                    .contains(that.exception.getMessage()) ||
                    that.exception.getMessage()
                            .contains(exception.getMessage());
        }
        return bookList.equals(that.bookList) && flag;
    }

    @Override
    public int hashCode() {
        int result = exception != null ? exception.hashCode() : 0;
        result = 31 * result + bookList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommandResult{");
        sb.append("exception=").append(exception);
        sb.append(", bookList: size = ").append(bookList.size());
        sb.append('}');
        return sb.toString();
    }
}
