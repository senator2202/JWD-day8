package by.kharitonov.day6.service.parser;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.service.validator.BookValidator;

public class BookParser {
    private static final String DELIMITER = ", ";

    public Book parseBook(String[] tagValues) {
        Book book;
        book = Book.newBuilder()
                .setId(tagValues[BookValidator.ID_INDEX])
                .setTitle(tagValues[BookValidator.TITLE_INDEX])
                .setAuthors(tagValues[BookValidator.AUTHORS_INDEX]
                        .split(DELIMITER))
                .setYear(Integer.parseInt(tagValues[BookValidator.YEAR_INDEX]))
                .setPages(Integer
                        .parseInt(tagValues[BookValidator.PAGES_INDEX]))
                .setPublishingHouse
                        (tagValues[BookValidator.PUBLISHING_HOUSE_INDEX])
                .build();
        return book;
    }

    public BookTag parseBookTag(String stringTag) {
        return BookTag.valueOf(stringTag.toUpperCase());
    }
}
