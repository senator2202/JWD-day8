package by.kharitonov.day6.model.entity;

import by.kharitonov.day6.model.type.BookTag;

public class FindRequest {
    private BookTag findTag;
    private String tagValue;
    private BookTag sortTag;

    public FindRequest(BookTag findTag, String tagValue, BookTag sortTag) {
        this.findTag = findTag != null ? findTag : BookTag.NONE;
        this.tagValue = tagValue != null ? tagValue : new String();
        this.sortTag = sortTag != null ? sortTag : BookTag.NONE;
    }

    public BookTag getFindTag() {
        return findTag;
    }

    public String getTagValue() {
        return tagValue;
    }

    public BookTag getSortTag() {
        return sortTag;
    }
}
