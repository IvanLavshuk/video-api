package bsu.rfe.lavshuk.videoArchive.db;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Connector {

    private static final Logger logger = Logger.getLogger(Connector.class.getSimpleName());
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    private static final String POOL_SIZE = "db.pool.size";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;


    private Connector() {
    }

    static {
        try {
            initConnectionPool();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initConnectionPool() throws ClassNotFoundException {
        String poolSize = PropertiesUtil.get(POOL_SIZE);
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            Connection connection = openConnection();

            var proxyConnection = (Connection) Proxy.newProxyInstance(Connector.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy) :
                            method.invoke(connection, args));

            pool.add(proxyConnection);
        }

    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            logger.info("Error executing " +" errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static Connection openConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL),
                    PropertiesUtil.get(USER),
                    PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            logger.info("Error executing " +" errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
