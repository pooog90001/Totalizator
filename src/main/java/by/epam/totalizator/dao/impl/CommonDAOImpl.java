package by.epam.totalizator.dao.impl;

import by.epam.totalizator.bean.Entity;
import by.epam.totalizator.constant.GeneralConstant;
import by.epam.totalizator.constant.SQLRequestConstant;
import by.epam.totalizator.dao.CommonDAO;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.type.ExpectResultType;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonDAOImpl extends CommonDAO {

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

    public int findCountBetsOnCompetition(int competitionId, ExpectResultType expectResult) throws DAOException {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.COUNT_BETS_ON_COMPETITION)) {
            statement.setInt(1, competitionId);
            statement.setString(2, expectResult.toString());
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
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.COUNT_BETS_ON_COMPETITOR)) {
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
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.AMOUNT_OF_MONEY_ON_COMPETITOR)) {
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

    public BigDecimal findAmountOfMoneyOnCompetition (int competitionId, ExpectResultType expectResult) throws DAOException {
        BigDecimal amountOfMoney = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.AMOUNT_OF_MONEY_ON_COMPETITION)) {
            statement.setInt(1, competitionId);
            statement.setString(2, expectResult.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                amountOfMoney = resultSet.getBigDecimal(GeneralConstant.CASH);
            }

        } catch (SQLException e) {
            throw new DAOException("find amount of money error", e);
        }
        return amountOfMoney;
    }

    public Map<String, Object> findAdminStatistic() {
        Map<String, Object> statisticMap = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.SELECT_ADMIN_STATISTIC)) {
            ResultSet resultSet = statement.executeQuery();
            statisticMap = new HashMap<>();

            if (resultSet.next()) {
                statisticMap.put(GeneralConstant.COUNT_REGISTERED, resultSet.getInt(GeneralConstant.COUNT_REGISTERED));
                statisticMap.put(GeneralConstant.COUNT_LOCKED, resultSet.getInt(GeneralConstant.COUNT_LOCKED));
                statisticMap.put(GeneralConstant.COUNT_NEWS, resultSet.getInt(GeneralConstant.COUNT_NEWS));
                statisticMap.put(GeneralConstant.COUNT_SPORTS, resultSet.getInt(GeneralConstant.COUNT_SPORTS));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticMap;
    }


}
