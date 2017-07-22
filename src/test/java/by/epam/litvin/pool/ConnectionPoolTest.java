package by.epam.litvin.pool;

import by.epam.litvin.exception.ConnectionPoolException;
import org.junit.Test;

public class ConnectionPoolTest {
    @Test
    public void getPool() throws ConnectionPoolException {
      ProxyConnection connection = ConnectionPool.getInstance().retrieveConnection();
    }

    @Test
    public void retrieve() {
    }

    @Test
    public void putback() {
    }

    @Test
    public void getAvailableConnsCnt() {
    }

    @Test
    public void closeConnection() {
    }

    @Test
    public void destroy() {
    }

}