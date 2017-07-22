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

public abstract class AbstractDAO<T extends Entity> {
    private final static Logger LOGGER = LogManager.getLogger();

    protected ProxyConnection connection;

    public abstract List<T> findAll() throws DAOException;

    public abstract T findEntityById(int id) throws DAOException;

    public abstract void delete(int id) throws DAOException;

    public abstract void delete(T entity) throws DAOException;

    public abstract boolean create(T entity) throws DAOException;

    public abstract boolean update(T entity) throws DAOException;

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

}
