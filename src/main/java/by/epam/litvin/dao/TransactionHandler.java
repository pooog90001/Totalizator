package by.epam.litvin.dao;

import by.epam.litvin.exception.ConnectionPoolException;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.pool.ConnectionPool;
import by.epam.litvin.pool.ProxyConnection;

import java.sql.SQLException;

public class TransactionHandler implements AutoCloseable {
    private ProxyConnection connection;

    public TransactionHandler() throws ConnectionPoolException {
        connection = ConnectionPool.getPool().retrieveConnection();
    }

    public void beginTransaction(AbstractDAO dao, AbstractDAO ... daos) throws DAOException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("Set auto commit 'false' error", e);
        }
        dao.setConnection(connection);
        for (AbstractDAO d : daos) {
            d.setConnection(connection);
        }
    }

    public void endTransaction() throws DAOException {
        try {
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DAOException("Set auto commit 'true' error", e);
        }
        ConnectionPool.getPool().putbackConnection(connection);
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

    @Override
    public void close() throws DAOException {
        rollback();
        endTransaction();
    }
}
