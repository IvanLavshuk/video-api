package bsu.rfe.lavshuk.video.archive.db;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private static final Properties properties = new Properties();

    private PropertiesUtil() {
    }

    static {
        loadProperties();
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("File db.properties not found in classpath");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(properties.toString());
            throw new RuntimeException(e);
        }
    }


}
