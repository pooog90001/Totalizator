package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.exception.DAOException;

import javax.mail.MethodNotSupportedException;
import java.util.List;
import java.util.Map;

public class BetDAO extends AbstractDAO {


    @Override
    public List findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity findEntityById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Entity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Entity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Entity entity) {
        throw new UnsupportedOperationException();
    }

}
