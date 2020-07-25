package test.kharitonov.day6.service.parser;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.service.parser.BookParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import static org.testng.Assert.assertEquals;

public class BookParserTest {
    private final BookParser parser = new BookParser();

    @Test
    public void testPrepareBook() {
        Book book = StaticDataProvider.FIRST_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        Book actualBook = parser.parseBook(bookTags);
        assertEquals(actualBook, StaticDataProvider.FIRST_BOOK);
    }

    @DataProvider(name = "dataParseBookTag")
    @Test
    public Object[][] dataParseBookTag() {
        return new Object[][]{
                {"id_book", BookTag.ID_BOOK},
                {"title", BookTag.TITLE},
                {"authors", BookTag.AUTHORS},
                {"year", BookTag.YEAR},
                {"pages", BookTag.PAGES},
                {"publisher", BookTag.PUBLISHER}
        };
    }

    @Parameters({"stringTag", "expectedBookTag"})
    @Test(dataProvider = "dataParseBookTag")
    public void testParseBookTag(String stringTag, BookTag expectedBookTag) {
        BookTag actualBookTag = parser.parseBookTag(stringTag);
        assertEquals(actualBookTag, expectedBookTag);
    }
}