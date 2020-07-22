package test.kharitonov.day6.service.validator;

import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.service.validator.BookValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BookValidatorTest {
    private final BookValidator validator = new BookValidator();

    @DataProvider(name = "dataValidateTag")
    @Test
    public Object[][] dataValidateTag() {
        return new Object[][]{
                {null, "value", false},
                {BookTag.ID_BOOK, null, false},
                {BookTag.TITLE, "", false},
                {BookTag.YEAR, "2000.23", false},
                {BookTag.YEAR, "2036", false},
                {BookTag.PAGES, "ulala", false},
                {BookTag.PAGES, "-100", false},
                {BookTag.ID_BOOK, "12345", true},
                {BookTag.TITLE, "Harry Potter", true},
                {BookTag.AUTHORS, "Bunin, Esenin", true},
                {BookTag.YEAR, "1991", true},
                {BookTag.PAGES, "244", true},
                {BookTag.PUBLISHER, "Delibri", true}
        };
    }

    @Parameters({"bookTag", "value", "expectedResult"})
    @Test(dataProvider = "dataValidateTag")
    public void testValidateTag(BookTag bookTag,
                                String value,
                                boolean expectedResult) {
        boolean actualResult = validator.validateTag(bookTag, value);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "dataValidateAllBookTags")
    @Test
    public Object[][] dataValidateAllBookTags() {
        return new Object[][]{
                {false, null},
                {false, "12", "Harry", "Bunin", "1900", "444"},
                {false, "", "Harry", "Bunin", "1900", "444", "Minsk"},
                {false, "12", "Harry", "Bunin", "190.23", "444", "Minsk"},
                {false, "12", "Harry", "Bunin", "1900", "Ups444", "Minsk"},
                {false, "12", "Harry", "Bunin", "190", "444", "Minsk"},
                {false, "12", "Harry", "Bunin", "1990", "-444", "Minsk"},
                {false, "12", "Harry", "Bunin", "190.23", "444", "Minsk"},
                {true, "12", "Harry", "Bunin, Esenin", "2020", "444", "Minsk"}
        };
    }

    @Parameters({"expectedResult", "tagValues"})
    @Test(dataProvider = "dataValidateAllBookTags")
    public void testValidateAllBookTags(boolean expectedResult,
                                        String[] tagValues) {
        boolean actualResult = validator.validateAllBookTags(tagValues);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "dataValidateFindParameters")
    @Test
    public Object[][] dataValidateFindParameters() {
        return new Object[][]{
                {false, null},
                {false, "title"},
                {false, "wrong_tag_value", "22"},
                {false, "id", ""},
                {false, "id", null},
                {false, "title", ""},
                {false, "title", null},
                {false, "authors", ""},
                {false, "authors", null},
                {false, "year", ""},
                {false, "year", null},
                {false, "year", "1923.44"},
                {false, "year", "-123"},
                {false, "pages", ""},
                {false, "pages", null},
                {false, "pages", "223.44"},
                {false, "pages", "30000"},
                {false, "publishing_house", ""},
                {false, "publishing_house", null},
                {true, "id", "123455"},
                {true, "title", "Lord of the rings"},
                {true, "authors", "Joan Roaling"},
                {true, "year", "2008"},
                {true, "pages", "555"},
                {true, "publishing_house", "Minsk"}
        };
    }

    @Parameters({"expectedResult", "tagValues"})
    @Test(dataProvider = "dataValidateFindParameters")
    public void testValidateFindParameters(boolean expectedResult,
                                           String[] tagValues) {
        boolean actualResult = validator.validateFindParameters(tagValues);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "dataValidateSortParameters")
    @Test
    public Object[][] dataValidateSortParameters() {
        return new Object[][]{
                {false, null},
                {false, "title", "extra"},
                {false, "wrong_tag"},
                {true, "id"},
                {true, "title"},
                {true, "authors"},
                {true, "year"},
                {true, "pages"},
                {true, "publishing_house"}
        };
    }

    @Parameters({"expectedResult", "tagValues"})
    @Test(dataProvider = "dataValidateSortParameters")
    public void testValidateSortParameters(boolean expectedResult,
                                           String[] tagValues) {
        boolean actualResult = validator.validateSortParameters(tagValues);
        assertEquals(actualResult, expectedResult);
    }
}