package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.BetEntity;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.BetDAO;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import static by.epam.litvin.constant.SQLRequestConstant.*;

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
        boolean isCreated;

        try(PreparedStatement statement = connection.prepareStatement(CREATE_BET)) {
            statement.setInt(1, entity.getUserId());
            statement.setBigDecimal(2, entity.getCash());
            statement.setInt(3, entity.getCompetitorId());

            if (entity.getExpectedResult() != null) {
                statement.setString(4, entity.getExpectedResult().toString());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Create bets error", e);
        }
        return isCreated;
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

    public void updateCompetitorResultAndPayMoney(int competitionId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_COMPETITOR_RESULT_AND_PAY_MONEY)) {
            statement.setInt(1, competitionId);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Update competitor reuslt and pay money bets error", e);
        }
    }

    public void updateGameResultAndPayMoney
            (int competitionId, int competitorResult1, int competitorResult2) throws DAOException {


        try (PreparedStatement statement = connection.prepareStatement(SET_VAR_FIRST_COMPETITOR_RESULT)) {
            statement.setInt(1, competitorResult1);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Set first competitor result variable  error", e);
        }

        try (PreparedStatement statement = connection.prepareStatement(SET_VAR_SECOND_COMPETITOR_RESULT)) {
            statement.setInt(1, competitorResult2);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Set second competitor result variable  error", e);
        }

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BET_RESULT_AND_PAY_MONEY)) {
            statement.setInt(1, competitionId);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Update competition reuslt and pay money bets error", e);
        }
    }

}
