package bsu.rfe.lavshuk.video.archive.db;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public final class PropertiesUtil {

    private static final Logger logger = Logger.getLogger(Connector.class.getSimpleName());
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
