package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookListDaoImpl implements BookListDao {
    private static final String DELIMITER = ", ";
    private static final String ID_TAG = "id_book";
    private static final String TITLE_TAG = "title";
    private static final String AUTHORS_TAG = "authors";
    private static final String YEAR_TAG = "year";
    private static final String PAGES_TAG = "pages";
    private static final String PUBLISHER_TAG = "publisher";
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "admin";
    private static final String HOST =
            "jdbc:mysql://localhost:3306/book_warehouse" +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=UTC";
    private static final String FIND_ALL_QUERY = "SELECT * FROM books";
    private static final String SORT_ID_QUERY = FIND_ALL_QUERY;

    @Override
    public void addBook(Book book) throws DaoException {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        if (allBooks.contains(book)) {
            throw new DaoException("This book already exists!");
        }
        if (warehouse.isFull()) {
            throw new DaoException("Warehouse is full!");
        }
        warehouse.add(book);
    }

    @Override
    public void removeBook(Book removingBook)
            throws DaoException {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = new ArrayList<>(warehouse.findAll());
        boolean result = false;
        for (Book book : allBooks) {
            if (isSimilar(book, removingBook)) {
                warehouse.remove(book);
                result = true;
            }
        }
        if (!result) {
            throw new DaoException("Such book doesn't exist!");
        }
    }

    private boolean isSimilar(Book book, Book removingBook) {
        return (book.getTitle().equals(removingBook.getTitle()) &&
                book.getAuthors().equals(removingBook.getAuthors()) &&
                book.getYear() == removingBook.getYear() &&
                book.getPages() == removingBook.getPages() &&
                book.getPublishingHouse()
                        .equals(removingBook.getPublishingHouse()));
    }

    @Override
    public List<Book> findBookById(String id) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> searchedBook = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                searchedBook.add(book);
                break;
            }
        }
        return searchedBook;
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getTitle().equals(title)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthors().contains(author)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByYear(String year) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getYear()).equals(year)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByPages(String pages) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getPages()).equals(pages)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByPublishingHouse(String publishingHouse) {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getPublishingHouse().equals(publishingHouse)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> sortBooksById(){
        List<Book> allBooks = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()) {
                Book book = parseBook(resultSet);
                allBooks.add(book);
            }
        } catch (SQLException e) {
        }
        return allBooks;
    }

    @Override
    public List<Book> sortBooksByTitle() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookTitleComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByAuthors() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookAuthorsComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByYear() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookYearComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPages() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookPagesComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPublishingHouse() {
        BookWarehouse warehouse = BookWarehouse.getInstance();
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookPublishingHouseComparator());
        return sortedList;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> allBooks = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()) {
                Book book = parseBook(resultSet);
                allBooks.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading database!", e);
        }
        return allBooks;
    }

    private ResultSet executeQuery(String sqlQuery) throws SQLException {
        Connection connection = DriverManager
                .getConnection(HOST, USER_NAME, USER_PASSWORD);
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }

    private Book parseBook(ResultSet resultSet) throws SQLException {
        String idBook = resultSet.getString(ID_TAG);
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
