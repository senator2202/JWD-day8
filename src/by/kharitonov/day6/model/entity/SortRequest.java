package by.kharitonov.day6.model.entity;

import by.kharitonov.day6.model.type.BookTag;

public class SortRequest extends SelectRequest {
    private BookTag sortTag;

    public SortRequest(BookTag sortTag) {
        this.sortTag = sortTag;
    }

    @Override
    public BookTag getBookTag() {
        return sortTag;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SortRequest{");
        sb.append("sortTag=").append(sortTag);
        sb.append('}');
        return sb.toString();
    }
}
