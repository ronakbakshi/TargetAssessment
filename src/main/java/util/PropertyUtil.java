package util;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private final Properties properties = new Properties();

    public PropertyUtil() {

        //Load land.properties
        try {
            this.properties.load(this.getClass().getClassLoader().getResourceAsStream("land.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load land's default properties file");
        }
    }

    public String getProperty(final String name) {
        return properties.getProperty(name);
    }
}
