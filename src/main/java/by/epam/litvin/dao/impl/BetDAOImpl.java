package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.BetEntity;
import by.epam.litvin.bean.CompetitionEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.BetDAO;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.type.ExpectResultType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> findPastBetsByUserId(int userId) throws DAOException {
        List<Map<String, Object>> betsAndGames;

        try (PreparedStatement statement = connection.prepareStatement(FIND_PAST_BETS_WITH_GAME_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            betsAndGames = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> betAndGame = new HashMap<>();
                BetEntity bet = new BetEntity();
                CompetitionEntity competition = new CompetitionEntity();
                competition.setId(resultSet.getInt(SQLFieldConstant.Competition.ID));
                competition.setTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
                competition.setLessTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
                competition.setMoreTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
                competition.setStandoff(resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
                competition.setDateStart(resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_START));
                competition.setDateFinish(resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_FINISH));
                competition.setName(resultSet.getString(SQLFieldConstant.Competition.NAME));

                bet.setId(resultSet.getInt(SQLFieldConstant.Bet.ID));
                bet.setCompetitorId(resultSet.getInt(SQLFieldConstant.Competitor.ID));
                bet.setUserId(resultSet.getInt(SQLFieldConstant.User.ID));
                bet.setWin(resultSet.getBoolean(SQLFieldConstant.Bet.IS_WIN));
                bet.setActive(resultSet.getBoolean(SQLFieldConstant.Bet.IS_ACTIVE));
                bet.setCash(resultSet.getBigDecimal(SQLFieldConstant.Bet.CASH));
                String expectedResult = resultSet.getString(SQLFieldConstant.Bet.TOTAL);
                bet.setExpectedResult(expectedResult != null ?
                        ExpectResultType.valueOf(expectedResult) : null);

                betAndGame.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                betAndGame.put(SQLFieldConstant.CompetitionType.NAME,
                        resultSet.getString(SQLFieldConstant.CompetitionType.NAME));
                betAndGame.put("bet", bet);
                betAndGame.put("competition", competition);
                betsAndGames.add(betAndGame);
            }

        } catch (SQLException e) {
            throw new DAOException("Find past bets error", e);
        }

        return betsAndGames;
    }

    public List<Map<String, Object>> findUpcomingBetsByUserId(int userId) throws DAOException {
        List<Map<String, Object>> betsAndGames;

        try (PreparedStatement statement = connection.prepareStatement(FIND_UPCOMING_BETS_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            betsAndGames = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> betAndGame = new HashMap<>();
                BetEntity bet = new BetEntity();
                CompetitionEntity competition = new CompetitionEntity();
                competition.setId(resultSet.getInt(SQLFieldConstant.Competition.ID));
                competition.setTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
                competition.setLessTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
                competition.setMoreTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
                competition.setStandoff(resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
                competition.setDateStart(resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_START));
                competition.setDateFinish(resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_FINISH));
                competition.setName(resultSet.getString(SQLFieldConstant.Competition.NAME));

                bet.setId(resultSet.getInt(SQLFieldConstant.Bet.ID));
                bet.setCompetitorId(resultSet.getInt(SQLFieldConstant.Competitor.ID));
                bet.setUserId(resultSet.getInt(SQLFieldConstant.User.ID));
                bet.setActive(resultSet.getBoolean(SQLFieldConstant.Bet.IS_ACTIVE));
                bet.setCash(resultSet.getBigDecimal(SQLFieldConstant.Bet.CASH));
                String expectedResult = resultSet.getString(SQLFieldConstant.Bet.TOTAL);
                bet.setExpectedResult(expectedResult != null ?
                        ExpectResultType.valueOf(expectedResult) : null);

                betAndGame.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                betAndGame.put(SQLFieldConstant.CompetitionType.NAME,
                        resultSet.getString(SQLFieldConstant.CompetitionType.NAME));
                betAndGame.put("bet", bet);
                betAndGame.put("competition", competition);
                betsAndGames.add(betAndGame);
            }
        } catch (SQLException e) {
            throw new DAOException("Find upcoming bets error", e);
        }

        return betsAndGames;
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

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_GAME_RESULT_AND_PAY_MONEY)) {
            statement.setInt(1, competitionId);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Update competition reuslt and pay money bets error", e);
        }
    }

}
