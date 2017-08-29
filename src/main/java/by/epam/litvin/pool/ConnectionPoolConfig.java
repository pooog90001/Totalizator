package by.epam.litvin.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import static by.epam.litvin.constant.GeneralConstant.ConnectionPool.CAPACITY;
import static by.epam.litvin.constant.GeneralConstant.DataBase.*;
import static by.epam.litvin.constant.GeneralConstant.DB_PROPERTIES;


class ConnectionPoolConfig {

    private final static Logger LOGGER = LogManager.getLogger();
    private final Properties properties;
    private String url;

    /**
     * Default constructor.
     */
    ConnectionPoolConfig() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTIES);
            properties = new Properties();
            Class.forName(resourceBundle.getString(DRIVER));
            url = resourceBundle.getString(URL);
            properties.put(USER,resourceBundle.getString(USER));
            properties.put(PASSWORD, resourceBundle.getString(PASSWORD));
            properties.put(USE_UNICODE, resourceBundle.getString(USE_UNICODE));
            properties.put(CHARACTER_ENCODING, resourceBundle.getString(CHARACTER_ENCODING));
            properties.put(AUTO_RECONNECT, resourceBundle.getString(AUTO_RECONNECT));
            properties.put(CAPACITY, resourceBundle.getString(CAPACITY));

        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.FATAL, "Connection pool will nowhere create. ", e);
            throw new RuntimeException("Driver not found. ", e);

        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, "Connection pool will nowhere create. ", e);
            throw new RuntimeException("Data base configuration file not found. ", e);

        }
    }

    /**
     * `get URL.
     *
     * @return
     */
    String getUrl() {
        return url;
    }

    /**
     * Get properties.
     *
     * @return
     */
    Properties getProperties() {
        return properties;
    }

    /**
     * Get pool capacity.
     *
     * @return
     */
    int getPoolCapacity() {
        return Integer.valueOf(properties.getProperty(CAPACITY)); //question about this one
    }

}
