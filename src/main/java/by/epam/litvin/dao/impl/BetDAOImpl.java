package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.BetEntity;
import by.epam.litvin.bean.Entity;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.BetDAO;
import by.epam.litvin.dao.DAO;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BetDAOImpl extends BetDAO {


    @Override
    public List<BetEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BetEntity findEntityById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(BetEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(BetEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(BetEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteByCompetitionId(int competitionId) throws DAOException {

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_BETS_BY_COMPETITION_ID)) {
            statement.setInt(1, competitionId);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Delete bets error", e);
        }
    }

}
