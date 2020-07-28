package test.kharitonov.day6.data_provider;

import by.kharitonov.day6.model.entity.Book;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StaticDataProvider {
    public static final Book FIRST_BOOK;
    public static final Book SECOND_BOOK;
    public static final Book THIRD_BOOK;
    public static final Book FOURS_BOOK;
    public static final Book ADDING_BOOK;

    static {
        FIRST_BOOK = Book.newBuilder()
                .setId(2)
                .setTitle("Harry Potter and the deathly hallows")
                .setAuthors("Joan Roaling")
                .setYear(2007)
                .setPages(800)
                .setPublishingHouse("Delibri")
                .build();
        SECOND_BOOK = Book.newBuilder()
                .setId(1)
                .setTitle("Lord of the rings")
                .setAuthors("John Tolkien")
                .setYear(2002)
                .setPages(900)
                .setPublishingHouse("Vysnova")
                .build();
        THIRD_BOOK = Book.newBuilder()
                .setId(3)
                .setTitle("The little golden calf")
                .setAuthors("Ilya Ilf", "Eugene Petrov")
                .setYear(2015)
                .setPages(416)
                .setPublishingHouse("Ishi Press")
                .build();
        FOURS_BOOK = Book.newBuilder()
                .setId(4)
                .setTitle("Harry Potter and the goblet of fire")
                .setAuthors("Joan Roaling")
                .setYear(2005)
                .setPages(788)
                .setPublishingHouse("Minsk")
                .build();
        ADDING_BOOK = Book.newBuilder()
                .setTitle("Neznaika na lune")
                .setAuthors("Bunin", "Esenin")
                .setYear(1995)
                .setPages(250)
                .setPublishingHouse("Kapitoshka")
                .build();
    }

    public static String[] parseTags(Book book) {
        return new String[]{
                String.valueOf(book.getId()),
                book.getTitle(),
                book.getAuthors(),
                String.valueOf(book.getYear()),
                String.valueOf(book.getPages()),
                book.getPublisher()
        };
    }

    @DataProvider(name = "invalidBookTags")
    @Test
    public static Object[][] invalidBookTags() {
        return new Object[][]{
                {"321", "Neznaika na lune", "Bunin, Esenin", "-1995", "250",
                        "Kapitoshka"},
                {"322", "Neznaika na lune", "Bunin, Esenin", "1995", "-250",
                        "Kapitoshka"},
                {"323", "Harry", "Joan Roaling", "2007", "800", "Delibri", "222"},
                {"324", "", "Joan Roaling", "2007", "800", "Delibri"}
        };
    }

    @DataProvider(name = "invalidFindParameters")
    @Test
    public Object[][] invalidFindParameters() {
        return new Object[][]{
                {null},
                {"tag", "value", "extra"},
                {"wrong_tag_name", "value"},
                {"id", ""},
                {"title", ""},
                {"authors", ""},
                {"year", ""},
                {"year", "-22"},
                {"pages", ""},
                {"pages", "22.22"},
                {"publishing_house", ""}
        };
    }

    @DataProvider(name = "invalidSortParameters")
    @Test
    public Object[][] invalidSortParameters() {
        return new Object[][]{
                {null},
                {"tag", "extra"},
                {"wrong_tag_name"},
        };
    }

    @DataProvider(name = "foundBooks")
    @Test
    public Object[][] foundBooks() {
        List<Book> listTitle = new ArrayList<>();
        List<Book> listAuthors = new ArrayList<>();
        List<Book> listYear = new ArrayList<>();
        List<Book> listPages = new ArrayList<>();
        listTitle.add(SECOND_BOOK);
        listAuthors.add(FIRST_BOOK);
        listAuthors.add(FOURS_BOOK);
        listYear.add(FIRST_BOOK);
        listPages.add(THIRD_BOOK);
        return new Object[][]{
                {listTitle, "title", "Lord of the rings"},
                {listAuthors, "authors", "Joan Roaling"},
                {listYear, "year", "2007"},
                {listPages, "pages", "416"},
        };
    }

    @DataProvider(name = "sortedBooks")
    @Test
    public Object[][] sortedBooks() {
        List<Book> listTitle = new ArrayList<>();
        List<Book> listAuthors = new ArrayList<>();
        List<Book> listYear = new ArrayList<>();
        List<Book> listPages = new ArrayList<>();
        listTitle.add(FIRST_BOOK);
        listTitle.add(FOURS_BOOK);
        listTitle.add(SECOND_BOOK);
        listTitle.add(THIRD_BOOK);
        listAuthors.add(FIRST_BOOK);
        listAuthors.add(FOURS_BOOK);
        listAuthors.add(SECOND_BOOK);
        listAuthors.add(THIRD_BOOK);
        listYear.add(SECOND_BOOK);
        listYear.add(FOURS_BOOK);
        listYear.add(FIRST_BOOK);
        listYear.add(THIRD_BOOK);
        listPages.add(THIRD_BOOK);
        listPages.add(FOURS_BOOK);
        listPages.add(FIRST_BOOK);
        listPages.add(SECOND_BOOK);
        return new Object[][]{
                {listTitle, "title"},
                {listAuthors, "authors"},
                {listYear, "year"},
                {listPages, "pages"},
        };
    }
}
