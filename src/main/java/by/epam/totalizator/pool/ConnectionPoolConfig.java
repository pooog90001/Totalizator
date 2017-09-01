package by.epam.totalizator.pool;

import by.epam.totalizator.constant.GeneralConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;


class ConnectionPoolConfig {

    private final static Logger LOGGER = LogManager.getLogger();
    private final Properties properties;
    private String url;

    /**
     * Default constructor.
     */
    ConnectionPoolConfig() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(GeneralConstant.DB_PROPERTIES);
            properties = new Properties();
            Class.forName(resourceBundle.getString(GeneralConstant.DataBase.DRIVER));
            url = resourceBundle.getString(GeneralConstant.DataBase.URL);
            properties.put(GeneralConstant.DataBase.USER, resourceBundle.getString(GeneralConstant.DataBase.USER));
            properties.put(GeneralConstant.DataBase.PASSWORD, resourceBundle.getString(GeneralConstant.DataBase.PASSWORD));
            properties.put(GeneralConstant.DataBase.USE_UNICODE, resourceBundle.getString(GeneralConstant.DataBase.USE_UNICODE));
            properties.put(GeneralConstant.DataBase.CHARACTER_ENCODING, resourceBundle.getString(GeneralConstant.DataBase.CHARACTER_ENCODING));
            properties.put(GeneralConstant.DataBase.AUTO_RECONNECT, resourceBundle.getString(GeneralConstant.DataBase.AUTO_RECONNECT));
            properties.put(GeneralConstant.ConnectionPool.CAPACITY, resourceBundle.getString(GeneralConstant.ConnectionPool.CAPACITY));

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
        return Integer.valueOf(properties.getProperty(GeneralConstant.ConnectionPool.CAPACITY)); //question about this one
    }

}
