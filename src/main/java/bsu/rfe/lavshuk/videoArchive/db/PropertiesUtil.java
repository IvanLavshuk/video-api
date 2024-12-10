package bsu.rfe.lavshuk.videoArchive.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(Connector.class);
    private static final Properties prop = new Properties();

    private PropertiesUtil() {
    }

    static {
        loadProperties();
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    private static void loadProperties() {
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            logger.info(prop.toString());
            throw new RuntimeException(e);
        }
    }


}
