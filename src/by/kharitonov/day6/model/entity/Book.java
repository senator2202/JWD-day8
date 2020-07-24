package by.kharitonov.day6.model.entity;

import java.util.Arrays;

public class Book {
    private int id;
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

    public int getId() {
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
        if (id != book.id ||
                year != book.year ||
                pages != book.pages ||
                !title.equals(book.title) ||
                !Arrays.equals(authors, book.authors) ||
                !publisher.equals(book.publisher)) {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = id;
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

    public class Builder {
        private Builder() {
            Book.this.title = "";
            Book.this.authors = new String[0];
            Book.this.publisher = "";
        }

        public Builder setId(int id) {
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
            return Book.this;
        }
    }
}
