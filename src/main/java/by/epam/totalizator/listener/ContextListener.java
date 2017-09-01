package by.epam.totalizator.listener;

import by.epam.totalizator.exception.ConnectionPoolException;
import by.epam.totalizator.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class ContextListener implements ServletContextListener {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().destroyPool();
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't deregister driver", e);

        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Destroy pool error", e);
        }
    }
}
