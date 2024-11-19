package example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static final String URL="jdbc:mysql://localhost:3306/videolibrary";
    private static final String USER="root";
    private  static final String PASSWORD="ROOT";

    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
