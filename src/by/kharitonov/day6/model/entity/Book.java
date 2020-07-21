package by.kharitonov.day6.model.entity;

import by.kharitonov.day6.model.util.IdGenerator;

import java.util.Arrays;
import java.util.Comparator;

public class Book {
    private String id;
    private String title;
    private String[] authors;
    private int year;
    private int pages;
    private String publisher;

    private Book() {
    }

    public static Builder newBuilder() {
        return new Book().new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        String delimiter = ", ";
        return String.join(delimiter, authors);
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getPublisher() {
        return publisher;
    }

    private String getMainAuthor() {
        return authors.length == 0 ? "" : authors[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        boolean result = true;
        if (year != book.year ||
                pages != book.pages ||
                !id.equals(book.id) ||
                !title.equals(book.title) ||
                !Arrays.equals(authors, book.authors) ||
                !publisher.equals(book.publisher)) {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + year;
        result = 31 * result + pages;
        result = 31 * result + publisher.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id:\"").append(id).append('\"');
        sb.append(", title:\"").append(title).append('\"');
        sb.append(", authors:").append(Arrays.toString(authors));
        sb.append(", year:").append(year);
        sb.append(", pages:").append(pages);
        sb.append(", publishingHouse:\"").append(publisher).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public static class BookAuthorsComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1.authors.length == o2.authors.length) {
                return o1.getMainAuthor().compareTo(o2.getMainAuthor());
            }
            return o1.authors.length - o2.authors.length;
        }
    }

    public static class BookIdComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    public static class BookPagesComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPages() - o2.getPages();
        }
    }

    public static class BookPublishingHouseComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPublisher().compareTo(o2.getPublisher());
        }
    }

    public static class BookTitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    public static class BookYearComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getYear() - o2.getYear();
        }
    }

    public class Builder {
        private Builder() {
            Book.this.id = "";
            Book.this.title = "";
            Book.this.authors = new String[0];
            Book.this.publisher = "";
        }

        public Builder setId(String id) {
            Book.this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            Book.this.title = title;
            return this;
        }

        public Builder setAuthors(String... authors) {
            Book.this.authors = authors;
            return this;
        }

        public Builder setYear(int year) {
            Book.this.year = year;
            return this;
        }

        public Builder setPages(int pages) {
            Book.this.pages = pages;
            return this;
        }

        public Builder setPublishingHouse(String publishingHouse) {
            Book.this.publisher = publishingHouse;
            return this;
        }

        public Book build() {
            if (Book.this.id.isEmpty()) {
                Book.this.id = IdGenerator.generateId();
            }
            return Book.this;
        }
    }
}
