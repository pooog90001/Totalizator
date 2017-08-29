package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.pool.ProxyConnection;
import by.epam.litvin.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DAO<T extends Entity> {
    protected final static Logger LOGGER = LogManager.getLogger();

    protected ProxyConnection connection;

    /**
     * Find all entities.
     *
     * @return
     * @throws DAOException
     */
    public abstract List<T> findAll() throws DAOException;

    /**
     * Find entity by ID.
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public abstract T findEntityById(int id) throws DAOException;

    /**
     * Delete entity by ID.
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public abstract boolean delete(int id) throws DAOException;

    /**
     * Delete entity.
     *
     * @param entity
     * @return
     * @throws DAOException
     */
    public abstract boolean delete(T entity) throws DAOException;

    /**
     * Create entity.
     *
     * @param entity
     * @return
     * @throws DAOException
     */
    public abstract boolean create(T entity) throws DAOException;

    /**
     * Update entity.
     *
     * @param entity
     * @return
     * @throws DAOException
     */
    public abstract boolean update(T entity) throws DAOException;

    /**
     * Set connection.
     *
     * @param connection
     */
    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

}
