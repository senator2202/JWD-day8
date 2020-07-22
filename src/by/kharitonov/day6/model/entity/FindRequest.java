package by.kharitonov.day6.model.entity;

import by.kharitonov.day6.model.type.BookTag;

public class FindRequest extends SelectRequest {
    private BookTag findTag;
    private String tagValue;

    public FindRequest(BookTag findTag, String tagValue) {
        this.findTag = findTag;
        this.tagValue = tagValue;
    }

    @Override
    public BookTag getBookTag() {
        return findTag;
    }

    public String getTagValue() {
        return tagValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FindRequest{");
        sb.append("findTag=").append(findTag);
        sb.append(", tagValue='").append(tagValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
