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
            String browser = System.getenv("BROWSER");

            if (email != null) properties.setProperty("valid.email", email);
            if (password != null) properties.setProperty("valid.password", password);
            if (baseUrl != null) properties.setProperty("base.url", baseUrl);
            if (browser != null) properties.setProperty("browser", browser);

            // 2️⃣ Load local config.properties (for local runs)
            InputStream input = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input != null) {
                Properties fileProps = new Properties();
                fileProps.load(input);

                // Merge file properties (only if not already set by env vars)
                for (String key : fileProps.stringPropertyNames()) {
                    if (!properties.containsKey(key)) {
                        properties.setProperty(key, fileProps.getProperty(key));
                    }
                }
            }

            // 3️⃣ Set defaults if nothing was loaded
            if (properties.getProperty("browser") == null) {
                properties.setProperty("browser", "chrome");
                System.out.println("No browser configured, defaulting to Chrome");
            }

            if (properties.getProperty("base.url") == null) {
                properties.setProperty("base.url", "https://laguna.rs/");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration!", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}