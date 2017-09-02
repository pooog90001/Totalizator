package by.epam.totalizator.pool;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConnectionPoolTest {
    private static ConnectionPool connectionPool;
    private static Object d;

    @BeforeClass
    public static void setUp() throws Exception {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    public void getInstance() {
        assertEquals(connectionPool, ConnectionPool.getInstance());
    }

    @Test
    public void retrieveConnection() throws Exception {
        ProxyConnection connection = connectionPool.retrieveConnection();
        assertNotNull(connection);
        connectionPool.putbackConnection(connection);
    }

    @Test
    public void putbackConnection() throws Exception {
        ProxyConnection connection = connectionPool.retrieveConnection();
        connectionPool.putbackConnection(connection);
    }

    @Test
    public void destroyPool() throws Exception {
        connectionPool.destroyPool();
    }

}