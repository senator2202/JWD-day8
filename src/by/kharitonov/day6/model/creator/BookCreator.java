package by.kharitonov.day6.model.creator;

import by.kharitonov.day6.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCreator {
    private static final String DELIMITER = ", ";
    private static final String ID_COLUMN = "id_book";
    private static final String TITLE_COLUMN = "title";
    private static final String AUTHORS_COLUMN = "authors";
    private static final String YEAR_COLUMN = "year";
    private static final String PAGES_COLUMN = "pages";
    private static final String PUBLISHER_COLUMN = "publisher";

    public Book create(ResultSet resultSet) throws SQLException {
        int idBook = resultSet.getInt(ID_COLUMN);
        String title = resultSet.getString(TITLE_COLUMN);
        String[] authors = resultSet.getString(AUTHORS_COLUMN).split(DELIMITER);
        int year = resultSet.getInt(YEAR_COLUMN);
        int pages = resultSet.getInt(PAGES_COLUMN);
        String publisher = resultSet.getString(PUBLISHER_COLUMN);
        return Book.newBuilder()
                .setId(idBook)
                .setTitle(title)
                .setAuthors(authors)
                .setYear(year)
                .setPages(pages)
                .setPublishingHouse(publisher)
                .build();
    }

    public int getBookId(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(ID_COLUMN);
    }
}
