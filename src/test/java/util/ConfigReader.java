package util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                throw new RuntimeException("config.properties not found in classpath!");
            }

            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Could not load config.properties file!", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
