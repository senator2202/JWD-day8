package by.kharitonov.day6.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookWarehouse {
    private static final int CAPACITY = 100;
    private static BookWarehouse bookWarehouseInstance;
    private List<Book> books;

    private BookWarehouse() {
        books = new ArrayList<>();
    }

    public static BookWarehouse getInstance() {
        if (bookWarehouseInstance == null) {
            bookWarehouseInstance = new BookWarehouse();
        }
        return bookWarehouseInstance;
    }

    public void add(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public boolean isFull() {
        return books.size() >= CAPACITY;
    }

    public void removeAll() {
        books = new ArrayList<>();
    }

    public int indexOf(Book book) {
        return books.indexOf(book);
    }

    public List<Book> findAll() {
        return Collections.unmodifiableList(books);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookWarehouse that = (BookWarehouse) o;
        return books.equals(that.books);
    }

    @Override
    public int hashCode() {
        int result = books.hashCode();
        result = 31 * result + ((Integer) CAPACITY).hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookWarehouse{");
        for (Book book : books) {
            sb.append("\n").append(book);
        }
        sb.append("\n").append('}');
        return sb.toString();
    }
}
