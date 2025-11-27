package util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            // 1️⃣ Load environment variables (GitHub Actions)
            loadEnvironmentVariable("VALID_EMAIL", "valid.email");
            loadEnvironmentVariable("VALID_PASSWORD", "valid.password");
            loadEnvironmentVariable("INVALID_EMAIL", "invalid.email");
            loadEnvironmentVariable("INVALID_PASSWORD", "invalid.password");
            loadEnvironmentVariable("BASE_URL", "base.url");
            loadEnvironmentVariable("BROWSER", "browser");
            loadEnvironmentVariable("SEARCH_INPUT", "search.input");
            loadEnvironmentVariable("PRODUCT_TITLE", "product.title");
            loadEnvironmentVariable("BUYER_NAME", "buyer.name");
            loadEnvironmentVariable("BUYER_EMAIL", "buyer.email");
            loadEnvironmentVariable("BUYER_COUNTRY", "buyer.country");
            loadEnvironmentVariable("BUYER_PHONE", "buyer.phone");
            loadEnvironmentVariable("BUYER_STREET", "buyer.street");
            loadEnvironmentVariable("BUYER_STREET_NUMBER", "buyer.streetNumber");
            loadEnvironmentVariable("BUYER_CITY", "buyer.city");

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
                input.close();
            }

            // 3️⃣ Set defaults if nothing was loaded
            if (properties.getProperty("browser") == null) {
                properties.setProperty("browser", "chrome");
            }

            if (properties.getProperty("base.url") == null) {
                properties.setProperty("base.url", "https://laguna.rs/");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration!", e);
        }
    }

    private static void loadEnvironmentVariable(String envVar, String propertyKey) {
        String value = System.getenv(envVar);
        if (value != null && !value.isEmpty()) {
            properties.setProperty(propertyKey, value);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println("WARNING: Property '" + key + "' is null!");
        }
        return value;
    }
}