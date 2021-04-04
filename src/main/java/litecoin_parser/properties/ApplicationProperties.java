package litecoin_parser.properties;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
@UtilityClass
public class ApplicationProperties {

    private static final String SERVER_PROPERTIES_FILE = "/application.properties";

    private static Properties properties;

    static {
        initProperties();
    }

    @Nullable
    public static synchronized String getProperty(@Nonnull String key) {
        return properties.getProperty(key);
    }

    private static void initProperties() {
        properties = new Properties();
        try (InputStream inputStream = ApplicationProperties.class.getResourceAsStream(SERVER_PROPERTIES_FILE);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            properties.load(streamReader);
        } catch (IOException e) {
            log.error("Error loading properties file " + SERVER_PROPERTIES_FILE, e);
        }
    }
}
