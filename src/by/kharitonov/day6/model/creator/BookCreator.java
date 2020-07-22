package by.kharitonov.day6.model.creator;

import by.kharitonov.day6.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCreator {
    private static final String DELIMITER = ", ";
    private static final String ID_TAG = "id_book";
    private static final String TITLE_TAG = "title";
    private static final String AUTHORS_TAG = "authors";
    private static final String YEAR_TAG = "year";
    private static final String PAGES_TAG = "pages";
    private static final String PUBLISHER_TAG = "publisher";

    public Book create(ResultSet resultSet) throws SQLException {
        int idBook = resultSet.getInt(ID_TAG);
        String title = resultSet.getString(TITLE_TAG);
        String[] authors = resultSet.getString(AUTHORS_TAG).split(DELIMITER);
        int year = resultSet.getInt(YEAR_TAG);
        int pages = resultSet.getInt(PAGES_TAG);
        String publisher = resultSet.getString(PUBLISHER_TAG);
        return Book.newBuilder()
                .setId(idBook)
                .setTitle(title)
                .setAuthors(authors)
                .setYear(year)
                .setPages(pages)
                .setPublishingHouse(publisher)
                .build();
    }
}
