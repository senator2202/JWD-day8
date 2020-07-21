package by.kharitonov.day6.model.type;

public enum BookTag {
    ID {
        {
            this.findQuery = "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE id_book=() VALUES(?)";
            this.orderQuery = "SELECT id_book, title, authors, year, " +
                    "pages, publisher FROM books";
        }
    },
    TITLE {
        {
            this.findQuery = "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE title=() VALUES(?)";
            this.orderQuery = "SELECT id_book, title, authors, year, " +
                    "pages, publisher FROM books ORDER BY title";
        }
    },
    AUTHORS {
        {
            this.findQuery = "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE authors=() VALUES(?)";
            this.orderQuery = "SELECT id_book, title, authors, year, " +
                    "pages, publisher FROM books ORDER BY authors";
        }
    },
    YEAR {
        {
            this.findQuery = "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE year=() VALUES(?)";
            this.orderQuery = "SELECT id_book, title, authors, year, " +
                    "pages, publisher FROM books ORDER BY year";
        }
    },
    PAGES {
        {
            this.findQuery = "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE pages=() VALUES(?)";
            this.orderQuery = "SELECT id_book, title, authors, year, " +
                    "pages, publisher FROM books ORDER BY pages";
        }
    },
    PUBLISHER {
        {
            this.findQuery = "SELECT id_book, title, authors, year, pages, " +
                    "publisher FROM books WHERE publisher=() VALUES(?)";
            this.orderQuery = "SELECT id_book, title, authors, year, " +
                    "pages, publisher FROM books ORDER BY publisher";
        }
    },
    NONE {
        {
            this.findQuery = "";
            this.orderQuery = "";
        }
    };

    String findQuery;
    String orderQuery;

    public String getFindQuery() {
        return findQuery;
    }

    public String getOrderQuery() {
        return orderQuery;
    }
}
