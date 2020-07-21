package by.kharitonov.day6.model.connect;

import java.sql.*;

public class SqlConnector {
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

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(HOST, USER_NAME, USER_PASSWORD);
    }
}
