package by.epam.totalizator.dao;

import by.epam.totalizator.entity.Entity;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class DAO<T extends Entity> {
    protected final static Logger LOGGER = LogManager.getLogger();

    protected ProxyConnection connection;

    public abstract List<T> findAll() throws DAOException;

    public abstract T findEntityById(int id) throws DAOException;

    public abstract boolean delete(int id) throws DAOException;

    public abstract boolean delete(T entity) throws DAOException;

    public abstract boolean create(T entity) throws DAOException;

    public abstract boolean update(T entity) throws DAOException;

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

}
