package by.kharitonov.day6.model.request.impl;

import by.kharitonov.day6.model.request.SelectRequest;
import by.kharitonov.day6.model.type.BookTag;

public class SortRequestImpl implements SelectRequest {
    private final BookTag sortTag;

    public SortRequestImpl(BookTag sortTag) {
        this.sortTag = sortTag;
    }

    @Override
    public BookTag getBookTag() {
        return sortTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortRequestImpl that = (SortRequestImpl) o;
        return sortTag == that.sortTag;
    }

    @Override
    public int hashCode() {
        return sortTag.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SortRequest{");
        sb.append("sortTag=").append(sortTag);
        sb.append('}');
        return sb.toString();
    }
}
