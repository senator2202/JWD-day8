package by.kharitonov.day6.model.request.impl;

import by.kharitonov.day6.model.request.SelectRequest;
import by.kharitonov.day6.model.type.BookTag;

public class FindRequestImpl implements SelectRequest {
    private final BookTag findTag;
    private final String tagValue;

    public FindRequestImpl(BookTag findTag, String tagValue) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindRequestImpl that = (FindRequestImpl) o;
        if (findTag != that.findTag) {
            return false;
        }
        return tagValue.equals(that.tagValue);
    }

    @Override
    public int hashCode() {
        int result = findTag.hashCode();
        result = 31 * result + tagValue.hashCode();
        return result;
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
