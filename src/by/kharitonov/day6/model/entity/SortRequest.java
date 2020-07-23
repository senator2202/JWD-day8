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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortRequest that = (SortRequest) o;
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
