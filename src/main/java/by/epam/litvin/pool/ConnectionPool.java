package by.epam.litvin.pool;


import by.epam.litvin.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 */
public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger();
    private ArrayBlockingQueue<ProxyConnection> availableConns;
    private ArrayBlockingQueue<ProxyConnection> usedConns;
    private ConnectionPoolConfig config;

    private static Lock locker = new ReentrantLock();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;

    private ConnectionPool() {
        config = new ConnectionPoolConfig();
        availableConns = new ArrayBlockingQueue<>(config.getPoolCapacity());
        usedConns = new ArrayBlockingQueue<>(config.getPoolCapacity());
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                isCreated.set(true);
                LOGGER.log(Level.INFO, "Connection pool successfully created");
            }
            locker.unlock();
        }
        return instance;
    }


    private ProxyConnection createConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(config.getUrl(), config.getProperties());
        LOGGER.log(Level.INFO, "Connection created");
        return new ProxyConnection(connection);
    }

    public ProxyConnection retrieveConnection() throws ConnectionPoolException {
        ProxyConnection newConn;
        try {
            newConn = ((availableConns.size() + usedConns.size()) < config.getPoolCapacity()) ?
                    createConnection() :
                    availableConns.take();

        } catch (SQLException e) {
            throw new ConnectionPoolException("Connect to data base error. ", e);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Connection wait error. ", e);
        }
        return usedConns.offer(newConn) ? newConn : null;
    }

    public void putbackConnection(ProxyConnection connection) {
        if (connection != null) {
            if (usedConns.remove(connection)) {
                availableConns.offer(connection);
            }
        }
    }

    public int getAvailableConnsCnt() {
        int result;

        if ((availableConns.size() + usedConns.size()) < config.getPoolCapacity()) {
            int notCreated = config.getPoolCapacity() - (availableConns.size() + usedConns.size());
            result = notCreated + availableConns.size();

        } else {
            result = availableConns.size();
        }
        return result;
    }

    public void closeConnection(ProxyConnection connection) throws ConnectionPoolException {
        try {
            connection.realClose();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection close error. ", e);
        }
    }

    public void destroyPool() throws ConnectionPoolException {
        try {
            for (ProxyConnection availableConn : availableConns) {
                if (!availableConn.isClosed()) {
                    availableConn.realClose();
                }
            }
            availableConns = new ArrayBlockingQueue<>(config.getPoolCapacity());

            for (ProxyConnection usedConn : usedConns) {
                if (!usedConn.isClosed()) {
                    usedConn.realClose();
                }
            }
            usedConns = new ArrayBlockingQueue<>(config.getPoolCapacity());
        } catch (SQLException e) {
            throw new ConnectionPoolException("Destroy pool error. ", e);
        }
    }
}
