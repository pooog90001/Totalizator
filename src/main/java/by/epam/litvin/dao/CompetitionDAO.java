package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.SQLRequestConstant.*;

public class CompetitionDAO extends AbstractDAO {

    public List<Map<String, Object>> findLiveCompetitions(int startIndex, int limit) throws DAOException {
        List<Map<String, Object>> liveGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_LIVE_GAMES)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            liveGamesList = extractLiveCompetitions(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find live games error", e);
        }
        return liveGamesList;
    }

    public List<Map<String, Object>> findAllLiveCompetitions() throws DAOException {
        List<Map<String, Object>> liveGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_LIVE_GAMES)) {
            ResultSet resultSet = statement.executeQuery();
            liveGamesList = extractLiveCompetitions(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find live games error", e);
        }
        return liveGamesList;
    }

    public List<Map<String, Object>> findFilteredLiveCompetitions(int kindOfSportId) throws DAOException {
        List<Map<String, Object>> liveGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_FILTERED_LIVE_GAMES)) {
            statement.setInt(1, kindOfSportId);
            ResultSet resultSet = statement.executeQuery();
            liveGamesList = extractLiveCompetitions(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find live games error", e);
        }
        return liveGamesList;
    }


    private List<Map<String, Object>> extractLiveCompetitions(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> liveGamesList = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> liveGame = new HashMap<>();
            liveGame.put(SQLFieldConstant.Competition.ID,
                    resultSet.getInt(SQLFieldConstant.Competition.ID));
            liveGame.put(SQLFieldConstant.KindOfSport.NAME,
                    resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
            liveGame.put(SQLFieldConstant.Command.NAME,
                    resultSet.getString(SQLFieldConstant.Command.NAME));
            liveGame.put(SQLFieldConstant.Competition.TOTAL,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
            liveGame.put(SQLFieldConstant.Competition.LESS_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
            liveGame.put(SQLFieldConstant.Competition.MORE_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
            liveGame.put(SQLFieldConstant.Competition.STANDOFF_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
            liveGame.put(SQLFieldConstant.Competitor.WIN_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competitor.WIN_COEFF));
            liveGame.put(SQLFieldConstant.Competitor.ID,
                    resultSet.getInt(SQLFieldConstant.Competitor.ID));
            liveGame.put(SQLFieldConstant.Competition.NAME,
                    resultSet.getString(SQLFieldConstant.Competition.NAME));
            liveGamesList.add(liveGame);
        }

        return liveGamesList;
    }

    public List<Map<String, Object>> findUpcomingCompetitions(int startIndex, int limit) throws DAOException {
        List<Map<String, Object>> upcommingGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_UPCOMING_GAMES)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            upcommingGamesList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> upcommingGame = new HashMap<>();
                upcommingGame.put(SQLFieldConstant.Competition.ID,
                        resultSet.getInt(SQLFieldConstant.Competition.ID));
                upcommingGame.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                upcommingGame.put(SQLFieldConstant.Command.NAME,
                        resultSet.getString(SQLFieldConstant.Command.NAME));
                upcommingGame.put(SQLFieldConstant.Competition.TOTAL,
                        resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
                upcommingGame.put(SQLFieldConstant.Competition.LESS_TOTAL_COEFF,
                        resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
                upcommingGame.put(SQLFieldConstant.Competition.MORE_TOTAL_COEFF,
                        resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
                upcommingGame.put(SQLFieldConstant.Competition.STANDOFF_COEFF,
                        resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
                upcommingGame.put(SQLFieldConstant.Competitor.WIN_COEFF,
                        resultSet.getBigDecimal(SQLFieldConstant.Competitor.WIN_COEFF));
                upcommingGame.put(SQLFieldConstant.Competition.DATE_START,
                        resultSet.getBigDecimal(SQLFieldConstant.Competition.DATE_START));
                upcommingGame.put(SQLFieldConstant.Competitor.ID,
                        resultSet.getInt(SQLFieldConstant.Competitor.ID));
                upcommingGame.put(SQLFieldConstant.Competition.NAME,
                        resultSet.getString(SQLFieldConstant.Competition.NAME));
                upcommingGamesList.add(upcommingGame);

            }

        } catch (SQLException e) {
            throw new DAOException("find upcoming games error", e);
        }
        return upcommingGamesList;
    }


    public List<Map<String, Object>> findPastCompetitions(int startIndex, int limit) throws DAOException {
        List<Map<String, Object>> pastGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_PAST_GAMES)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            pastGamesList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> pastGame = new HashMap<>();
                pastGame.put(SQLFieldConstant.Competition.ID,
                        resultSet.getInt(SQLFieldConstant.Competition.ID));
                pastGame.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                pastGame.put(SQLFieldConstant.Command.NAME,
                        resultSet.getString(SQLFieldConstant.Command.NAME));
                pastGame.put(SQLFieldConstant.Competition.DATE_START,
                        resultSet.getDate(SQLFieldConstant.Competition.DATE_START));
                pastGame.put(SQLFieldConstant.Competitor.RESULT,
                        resultSet.getInt(SQLFieldConstant.Competitor.RESULT));
                pastGame.put(SQLFieldConstant.Competition.NAME,
                        resultSet.getString(SQLFieldConstant.Competition.NAME));
                pastGamesList.add(pastGame);
            }

        } catch (SQLException e) {
            throw new DAOException("find past games error", e);
        }
        return pastGamesList;
    }

    public int findLiveGamesCount() throws DAOException {
        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIVE_GAMES_COUNT)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            throw new DAOException("find live games count error", e);
        }
        return count;
    }


    @Override
    public List findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Entity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Entity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Entity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }
}
