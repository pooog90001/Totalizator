package by.epam.litvin.dao;

import by.epam.litvin.dao.DAO;
import by.epam.litvin.exception.ConnectionPoolException;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.pool.ConnectionPool;
import by.epam.litvin.pool.ProxyConnection;

import java.sql.SQLException;

public class TransactionManager {
    private ProxyConnection connection;

    public TransactionManager() throws DAOException {
        try {
            connection = ConnectionPool.getInstance().retrieveConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    public void beginTransaction(DAO dao, DAO... daos) throws DAOException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("Set auto commit 'false' error", e);
        }
        dao.setConnection(connection);
        for (DAO d : daos) {
            d.setConnection(connection);
        }
    }

    public void endTransaction() throws DAOException {
        try {
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DAOException("Set auto commit 'true' error", e);
        }
        ConnectionPool.getInstance().putbackConnection(connection);
    }

    public void commit() throws DAOException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Commit error", e);
        }
    }

    public void rollback() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException("Rollback error", e);
        }
    }

}
