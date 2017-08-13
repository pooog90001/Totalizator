package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.CompetitionEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.DAO;
import by.epam.litvin.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.SQLRequestConstant.*;

public class CompetitionDAOImpl extends DAO<CompetitionEntity> {

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
                        resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_START));
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

    public List<Map<String, Object>> findActivatedUpcomingCompetitions() throws DAOException {
        List<Map<String, Object>> upcommingGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ACTIVATED_UPCOMING_GAMES_FOR_SETTINGS)) {
            ResultSet resultSet = statement.executeQuery();
            upcommingGamesList = extractUpcomingCompetitions(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find upcoming games error", e);
        }
        return upcommingGamesList;
    }

    public List<Map<String, Object>> findDeactivatedUpcomingCompetitions() throws DAOException {
        List<Map<String, Object>> upcommingGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_DEACTIVATED_UPCOMING_GAMES_FOR_SETTINGS)) {
            ResultSet resultSet = statement.executeQuery();
            upcommingGamesList = extractUpcomingCompetitions(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find upcoming games error", e);
        }
        return upcommingGamesList;
    }

    private List<Map<String, Object>> extractUpcomingCompetitions(ResultSet resultSet)
            throws SQLException {
        List<Map<String, Object>> upcommingGamesList = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> upcommingGame = new HashMap<>();
            upcommingGame.put(SQLFieldConstant.Competition.ID,
                    resultSet.getInt(SQLFieldConstant.Competition.ID));
            upcommingGame.put(SQLFieldConstant.CompetitionType.NAME,
                    resultSet.getString(SQLFieldConstant.CompetitionType.NAME));
            upcommingGame.put(SQLFieldConstant.KindOfSport.NAME,
                    resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
            upcommingGame.put(SQLFieldConstant.Competition.TOTAL,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
            upcommingGame.put(SQLFieldConstant.Competition.LESS_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
            upcommingGame.put(SQLFieldConstant.Competition.MORE_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
            upcommingGame.put(SQLFieldConstant.Competition.STANDOFF_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
            upcommingGame.put(SQLFieldConstant.Competition.DATE_START,
                    resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_START));
            upcommingGame.put(SQLFieldConstant.Competition.DATE_FINISH,
                    resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_FINISH));
            upcommingGame.put(SQLFieldConstant.Competition.NAME,
                    resultSet.getString(SQLFieldConstant.Competition.NAME));
            upcommingGamesList.add(upcommingGame);

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
                        resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_START));
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
    public List<CompetitionEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompetitionEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        boolean isDeleted;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_COMPETITION_BY_ID)) {
            statement.setInt(1, id);
            isDeleted = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Delete competition error", e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(CompetitionEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(CompetitionEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(CompetitionEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    public int createAndGetId(CompetitionEntity entity) throws DAOException {
        int competitionId = 0;

        try (PreparedStatement statement = connection.prepareStatement(INSERT_COMPETITION, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());

            statement.setTimestamp(2, new Timestamp(entity.getDateStart().getTime()));
            statement.setTimestamp(3, new Timestamp(entity.getDateFinish().getTime()));
            statement.setBoolean(4, entity.getActive());
            statement.setInt(5, entity.getTypeId());
            statement.setBigDecimal(6, entity.getTotal());
            statement.setBigDecimal(7, entity.getMoreTotal());
            statement.setBigDecimal(8, entity.getLessTotal());
            statement.setBigDecimal(9, entity.getStandoff());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                competitionId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new DAOException("Create competition error", e);
        }

        return competitionId;
    }


    public boolean updateUpcomingActivatedCoeffs(CompetitionEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_UPCOMING_ACTIVATED_COMPETITION_COEFFS)) {
            statement.setBigDecimal(1, entity.getMoreTotal());
            statement.setBigDecimal(2, entity.getLessTotal());
            statement.setBigDecimal(3, entity.getStandoff());
            statement.setInt(4, entity.getId());
            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update competition error", e);
        }

        return isUpdated;
    }

    public boolean updateUpcomingDeactivatedCoeffs(CompetitionEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_UPCOMING_DEACTIVATED_COMPETITION_COEFFS)) {
            statement.setBigDecimal(1, entity.getMoreTotal());
            statement.setBigDecimal(2, entity.getLessTotal());
            statement.setBigDecimal(3, entity.getStandoff());
            statement.setBigDecimal(4, entity.getTotal());
            statement.setInt(5, entity.getId());
            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update competition error", e);
        }

        return isUpdated;
    }

    public boolean updateName(CompetitionEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_COMPETITION_NAME)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update competition error", e);
        }

        return isUpdated;
    }

    public boolean changeActiveState(int competitionId, boolean state) throws DAOException {
        boolean isChanged;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CHANGE_COMPETITION_ACTIVE_STATE)) {
            statement.setBoolean(1, state);
            statement.setInt(2, competitionId);

            isChanged = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Change active state error", e);
        }

        return isChanged;
    }

}
