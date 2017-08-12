package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.constant.GeneralConstant;
import by.epam.litvin.exception.DAOException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.epam.litvin.constant.SQLRequestConstant.*;

public class CommonDAO extends AbstractDAO {

    @Override
    public List findAll() throws DAOException {
        return null;
    }

    @Override
    public Entity findEntityById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Entity entity) throws DAOException {
        return false;
    }

    public int findCountBetsOnCompetition(int competitionId, String result) throws DAOException {
        int count = 0;
        try (PreparedStatement statement =  connection.prepareStatement(COUNT_BETS_ON_COMPETITION)) {
            statement.setInt(1, competitionId);
            statement.setString(2, result.toUpperCase());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                count = resultSet.getInt(GeneralConstant.COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("find count of bets error", e);
        }
        return count;
    }

    public int findCountBetsOnCompetitor(int competitorId) throws DAOException {
        int count = 0;
        try (PreparedStatement statement =  connection.prepareStatement(COUNT_BETS_ON_COMPETITOR)) {
            statement.setInt(1, competitorId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                count = resultSet.getInt(GeneralConstant.COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("find count of bets error", e);
        }
        return count;
    }

    public BigDecimal findAmountOfMoneyOnCompetitor (int competitorId) throws DAOException {
        BigDecimal amountOfMoney = null;
        try (PreparedStatement statement =  connection.prepareStatement(AMOUNT_OF_MONEY_ON_COMPETITOR)) {
            statement.setInt(1, competitorId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                amountOfMoney = resultSet.getBigDecimal(GeneralConstant.CASH);
            }

        } catch (SQLException e) {
            throw new DAOException("find amount of money error", e);
        }
        return amountOfMoney;
    }

    public BigDecimal findAmountOfMoneyOnCompetition (int competitionId, String result) throws DAOException {
        BigDecimal amountOfMoney = null;
        try (PreparedStatement statement =  connection.prepareStatement(AMOUNT_OF_MONEY_ON_COMPETITION)) {
            statement.setInt(1, competitionId);
            statement.setString(2, result.toUpperCase());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                amountOfMoney = resultSet.getBigDecimal(GeneralConstant.CASH);
            }

        } catch (SQLException e) {
            throw new DAOException("find amount of money error", e);
        }
        return amountOfMoney;
    }
}
