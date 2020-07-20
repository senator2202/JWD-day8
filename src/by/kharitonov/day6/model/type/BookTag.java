package by.kharitonov.day6.model.type;

import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public enum BookTag {
    ID {
        {
            BookListDaoImpl dao = new BookListDaoImpl();
            this.findFunction = dao::findBookById;
            this.sortSupplier = dao::sortBooksById;
        }
    },
    TITLE {
        {
            BookListDaoImpl dao = new BookListDaoImpl();
            this.findFunction = dao::findBooksByTitle;
            this.sortSupplier = dao::sortBooksByTitle;
        }
    },
    AUTHORS {
        {
            BookListDaoImpl dao = new BookListDaoImpl();
            this.findFunction = dao::findBooksByAuthor;
            this.sortSupplier = dao::sortBooksByAuthors;
        }
    },
    YEAR {
        {
            BookListDaoImpl dao = new BookListDaoImpl();
            this.findFunction = dao::findBooksByYear;
            this.sortSupplier = dao::sortBooksByYear;
        }
    },
    PAGES {
        {
            BookListDaoImpl dao = new BookListDaoImpl();
            this.findFunction = dao::findBooksByPages;
            this.sortSupplier = dao::sortBooksByPages;
        }
    },
    PUBLISHING_HOUSE {
        {
            BookListDaoImpl dao = new BookListDaoImpl();
            this.findFunction = dao::findBooksByPublishingHouse;
            this.sortSupplier = dao::sortBooksByPublishingHouse;
        }
    };

    Function<String, List<Book>> findFunction;
    Supplier<List<Book>> sortSupplier;

    public Function<String, List<Book>> getFindFunction() {
        return findFunction;
    }

    public Supplier<List<Book>> getSortSupplier() {
        return sortSupplier;
    }
}
