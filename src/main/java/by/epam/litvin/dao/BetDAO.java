package by.epam.litvin.dao;

import by.epam.litvin.bean.BetEntity;
import by.epam.litvin.bean.Entity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.type.ExpectResultType;

import javax.mail.MethodNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public boolean update(Entity entity) {
        throw new UnsupportedOperationException();
    }

    public void deleteByCompetitionId(int competitionId) throws DAOException {

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_BETS_BY_COMPETITION_ID)) {
            statement.setInt(1, competitionId);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Delete bets error", e);
        }
    }

}
