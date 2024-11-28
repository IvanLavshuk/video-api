package example.db;

import example.dao.ActorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final Logger LOG = LoggerFactory.getLogger(Connector.class);
    private static final String URL = "jdbc:mysql://localhost:3306/videolibrary";
    private static final String USER = "root";
    private static final String PASSWORD = "ROOT";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            LOG.error("Error getting connection: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
