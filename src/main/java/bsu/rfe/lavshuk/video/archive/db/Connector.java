package bsu.rfe.lavshuk.video.archive.db;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.PropertiesUtil;

public class Connector {
    private static final Logger logger = LoggerFactory.getLogger(Connector.class);
    private static final String URL = PropertiesUtil.get("db.url");
    private static final String USER = PropertiesUtil.get("db.user");
    private static final String PASSWORD = PropertiesUtil.get("db.password");
    private static final String POOL_SIZE = PropertiesUtil.get("db.pool.size");

    private static BlockingQueue<Connection> pool;


    private Connector() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            initConnectionPool();
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load JDBC driver", e);
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    private static void initConnectionPool() throws ClassNotFoundException {
       int poolSize = Integer.parseInt(POOL_SIZE);
        pool = new ArrayBlockingQueue<>(poolSize );
        for (int i = 0; i < poolSize  ; i++) {
            Connection connection = openConnection();
            var proxyConnection =
                    (Connection) Proxy.newProxyInstance(Connector.class.getClassLoader(), new Class[]{Connection.class},
                    (proxy, method, args) ->
                            method.getName().equals("close") ?
                                    pool.add((Connection) proxy) : method.invoke(connection, args));
            pool.add(proxyConnection);
        }

    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            logger.error("Error executing " + " errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Connection openConnection() throws ClassNotFoundException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("Error executing " + " errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
