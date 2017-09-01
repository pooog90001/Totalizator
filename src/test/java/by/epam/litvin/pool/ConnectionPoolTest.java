package by.epam.litvin.pool;

import by.epam.totalizator.exception.ConnectionPoolException;
import by.epam.totalizator.pool.ConnectionPool;
import by.epam.totalizator.pool.ProxyConnection;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

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
        Set<String> setH = new HashSet<>();
        TreeSet<String> setT = new TreeSet<>();
        LinkedHashSet<String> setS = new LinkedHashSet<>();
        setH.add(null);
        setH.add(null);
        setS.add(null);
        setS.add(null);
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