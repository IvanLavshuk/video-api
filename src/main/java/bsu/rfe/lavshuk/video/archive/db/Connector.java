package bsu.rfe.lavshuk.video.archive.db;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Connector {
    private static final Logger logger = Logger.getLogger(Connector.class.getSimpleName());
    private static final String URL = "jdbc:mysql://localhost:3306/videolibrary";
    private static final String USER = "root";
    private static final String PASSWORD = "ROOT";
    private static final String POOL_SIZE = "8";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;


    private Connector() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            initConnectionPool();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initConnectionPool() throws ClassNotFoundException {
        //String poolSize = PropertiesUtil.get(POOL_SIZE); не работает
        String poolSize = POOL_SIZE;
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            Connection connection = openConnection();
            var proxyConnection = (Connection) Proxy.newProxyInstance(Connector.class.getClassLoader(), new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy) : method.invoke(connection, args));
            pool.add(proxyConnection);
        }

    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            logger.info("Error executing " + " errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Connection openConnection() throws ClassNotFoundException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.info("Error executing " + " errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
