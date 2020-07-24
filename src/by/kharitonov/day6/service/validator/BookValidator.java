package by.kharitonov.day6.service.validator;

import by.kharitonov.day6.model.type.BookTag;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class BookValidator {
    public static final int BOOK_TAG_INDEX = 0;
    public static final int TAG_VALUE_INDEX = 1;
    public static final int ID_INDEX = 0;
    public static final int TITLE_INDEX = 1;
    public static final int AUTHORS_INDEX = 2;
    public static final int YEAR_INDEX = 3;
    public static final int PAGES_INDEX = 4;
    public static final int PUBLISHING_HOUSE_INDEX = 5;
    public static final int TAG_NUMBERS = 6;
    private static final int FIND_PARAMETERS_NUMBER = 2;
    private static final int SORT_PARAMETERS_NUMBER = 1;
    private static final int MIN_YEAR = 1800;
    private static final int MAX_YEAR;
    private static final int MAX_PAGES = 2000;
    private static final int MAX_STRING_LENGTH = 45;

    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                Locale.getDefault());
        MAX_YEAR = calendar.get(Calendar.YEAR);
    }

    private boolean validateYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    private boolean validatePages(int pages) {
        return pages > 0 && pages <= MAX_PAGES;
    }

    private boolean validateId(int id) {
        String stringId = String.valueOf(id);
        int intId = Integer.parseInt(stringId);
        return intId > 0 && stringId.length() <= 11;
    }

    public boolean validateTag(BookTag bookTag, String stringValue) {
        if (bookTag == null || stringValue == null || stringValue.isEmpty()) {
            return false;
        }
        if (bookTag != BookTag.PAGES && bookTag != BookTag.YEAR) {
            return stringValue.length() < MAX_STRING_LENGTH;
        }
        boolean result;
        try {
            int tagValue = Integer.parseInt(stringValue);
            result = bookTag == BookTag.YEAR
                    ? validateYear(tagValue)
                    : validatePages(tagValue);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean validateAddParameters(String[] tagValues) {
        if (tagValues == null || tagValues.length != TAG_NUMBERS) {
            return false;
        }
        boolean result = true;
        for (String tagValue : tagValues) {
            if (tagValue.isEmpty()) {
                return false;
            }
        }
        try {
            int year = Integer.parseInt(tagValues[YEAR_INDEX]);
            int pages = Integer.parseInt(tagValues[PAGES_INDEX]);
            if (!validateYear(year) || !validatePages(pages)) {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean validateRemoveParameters(String[] parameters) {
        boolean result;
        if (parameters.length == TAG_NUMBERS) {
            result = validateAddParameters(parameters);
        } else {
            try {
                int id = Integer.parseInt(parameters[ID_INDEX]);
                result = validateId(id);
            } catch (NumberFormatException e) {
                result = false;
            }
        }
        return result;
    }

    public boolean validateFindParameters(String[] parameters) {
        if (parameters == null || parameters.length != FIND_PARAMETERS_NUMBER) {
            return false;
        }
        boolean result;
        try {
            BookTag bookTag =
                    BookTag.valueOf(parameters[BOOK_TAG_INDEX].toUpperCase());
            result = validateTag(bookTag, parameters[TAG_VALUE_INDEX]);
        } catch (IllegalArgumentException e) {
            result = false;
        }
        return result;
    }

    public boolean validateSortParameters(String[] parameters) {
        if (parameters == null || parameters.length != SORT_PARAMETERS_NUMBER) {
            return false;
        }
        boolean result;
        try {
            BookTag.valueOf(parameters[BOOK_TAG_INDEX].toUpperCase());
            result = true;
        } catch (IllegalArgumentException e) {
            result = false;
        }
        return result;
    }
}
