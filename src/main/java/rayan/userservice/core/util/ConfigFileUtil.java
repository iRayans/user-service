package rayan.userservice.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigFileUtil {

    private static final Logger LOGGER = LogManager.getLogger(ConfigFileUtil.class.getName());

    private static final String CONFIGURATION_FILENAME = "config.properties";

    private ConfigFileUtil() {
    }

    ;

    public static Properties getPropertiesInstance() {
        try {
            URL configURL = Thread.currentThread().getContextClassLoader().getResource(CONFIGURATION_FILENAME);

            if (configURL == null) {
                throw new FileNotFoundException();
            }

            String configPath = configURL.getPath();
            Properties props = new Properties();
            props.load(new FileInputStream(configPath));
            return props;
        } catch (FileNotFoundException e1) {
            LOGGER.error("Config file not found.");
            return null;
        } catch (IOException e2) {
            LOGGER.error("IOException: {}", e2.getMessage());
            return null;
        }
    }
}
