package az.academy.turing.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static Connection getConnection() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5433/postgres";
        final String username = "postgres";
        final String password = "postgres.2025";
        return DriverManager.getConnection(url, username, password);
}
}
