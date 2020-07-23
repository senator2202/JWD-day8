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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindRequest that = (FindRequest) o;
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
