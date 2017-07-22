package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.exception.DAOException;

import javax.mail.MethodNotSupportedException;
import java.util.List;

public class BetDao extends AbstractDAO {


    @Override
    public List findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity findEntityById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Entity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Entity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity update(Entity entity) {
        throw new UnsupportedOperationException();
    }
}
