package example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
//вынести в проперти или делать инициализаци и инит методе сервлета
    private static final String URL = "jdbc:mysql://localhost:3306/videolibrary";
    private static final String USER = "root";
    private static final String PASSWORD = "ROOT";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            //log.error("Error getting connection: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
