package bsu.rfe.lavshuk.videoArchive.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Connector {

    private static final Logger logger = LoggerFactory.getLogger(Connector.class);
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    private static final String POOL_SIZE = "db.pool.size";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;


    private Connector() {
    }

    static {
        initConnectionPool();
    }

    private static void initConnectionPool() {
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
            logger.error("Error taking pool : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL),
                    PropertiesUtil.get(USER),
                    PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            logger.error("Error getting connection: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
