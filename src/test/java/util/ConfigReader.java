package util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            // 1️⃣ Load environment variables (GitHub Actions)
            String email = System.getenv("VALID_EMAIL");
            String password = System.getenv("VALID_PASSWORD");
            String baseUrl = System.getenv("BASE_URL");

            if (email != null) properties.setProperty("email", email);
            if (password != null) properties.setProperty("password", password);
            if (baseUrl != null) properties.setProperty("baseUrl", baseUrl);

            // 2️⃣ Load local config.properties (for local runs)
            InputStream input = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input != null) {
                properties.load(input);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration!", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
