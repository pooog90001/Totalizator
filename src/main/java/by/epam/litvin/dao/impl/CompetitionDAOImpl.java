package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.CompetitionEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.DAO;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.COUNT;
import static by.epam.litvin.constant.SQLRequestConstant.*;

public class CompetitionDAOImpl extends DAO<CompetitionEntity> {


    /**
     * Find limit upcoming competitions
     *
     * @param startIndex
     * @param limit
     * @param isActivated
     * @return List with upcoming games
     * @throws DAOException
     */
    public List<Map<String, Object>> findLimitUpcomingGames(int startIndex,
                                                            int limit, boolean isActivated) throws DAOException {
        List<Map<String, Object>> upcomingGames;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_UPCOMING_GAMES)) {
            statement.setBoolean(1, isActivated);
            statement.setInt(2, startIndex);
            statement.setInt(3, limit);
            ResultSet resultSet = statement.executeQuery();

            upcomingGames = extractGames(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find upcoming games error", e);
        }
        return upcomingGames;
    }

    /**
     * Find all upcoming competitions, activated or deactivated
     *
     * @param isActivated Bets are accepted for this competition or not
     * @return List with upcoming games and competition type
     * @throws DAOException if database error.
     */
    public List<Map<String, Object>> findAllUpcomingGames(boolean isActivated) throws DAOException {
        List<Map<String, Object>> upcomingGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_UPCOMING_GAMES)) {
            statement.setBoolean(1, isActivated);
            ResultSet resultSet = statement.executeQuery();
            upcomingGamesList = extractGames(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find upcoming games error", e);
        }
        return upcomingGamesList;
    }

    public int findUpcomingGamesCount(boolean isActivated) throws DAOException {
        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement(FIND_UPCOMING_GAMES_COUNT)) {
            statement.setBoolean(1, isActivated);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("find upcoming games count error", e);
        }

        return count;
    }

    public int findPastGamesCount(boolean isResultFilled, boolean isActivated) throws DAOException {
        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement(FIND_PAST_GAMES_COUNT)) {
            statement.setBoolean(1, isResultFilled);
            statement.setBoolean(2, isActivated);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("find upcoming games count error", e);
        }

        return count;
    }

    public List<Map<String, Object>> findAllNowGames(boolean isActivated) throws DAOException {
        List<Map<String, Object>> nowGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_NOW_GAMES)) {
            statement.setBoolean(1, isActivated);
            ResultSet resultSet = statement.executeQuery();
            nowGamesList = extractGames(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find now games error", e);
        }
        return nowGamesList;
    }

    public List<Map<String, Object>> findAllPastGames(boolean isResultFilled,
                                                      boolean isActivated) throws DAOException {
        List<Map<String, Object>> pastGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_PAST_GAMES)) {
            statement.setBoolean(1, isResultFilled);
            statement.setBoolean(2, isActivated);
            ResultSet resultSet = statement.executeQuery();
            pastGamesList = extractGames(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find all past games error", e);
        }
        return pastGamesList;
    }



    public List<Map<String, Object>> findLimitPastGames(int startIndex, int limit,
                                                        boolean isResultFilled,
                                                        boolean isActivated) throws DAOException {
        List<Map<String, Object>> pastGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_PAST_GAMES)) {
            statement.setBoolean(1, isResultFilled);
            statement.setBoolean(2, isActivated);
            statement.setInt(3, startIndex);
            statement.setInt(4, limit);
            ResultSet resultSet = statement.executeQuery();

            pastGamesList = extractGames(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find past games error", e);
        }
        return pastGamesList;
    }


    @Override
    public List<CompetitionEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompetitionEntity findEntityById(int id) throws DAOException {
        CompetitionEntity competition = null;

        try (PreparedStatement statement = connection.prepareStatement(FIND_GAME_BY_ID)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                competition = new CompetitionEntity();
                competition.setId(resultSet.getInt(SQLFieldConstant.Competition.ID));
                competition.setName(resultSet.getString(SQLFieldConstant.Competition.NAME));
                competition.setActive(resultSet.getBoolean(SQLFieldConstant.Competition.IS_ACTIVE));
                competition.setResultFilled(resultSet.getBoolean(SQLFieldConstant.Competition.IS_RESULT_FILLED));
                competition.setTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
                competition.setLessTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
                competition.setMoreTotal(resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
                competition.setStandoff(resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
                competition.setDateStart(resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_START));
                competition.setDateFinish(resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_FINISH));
            }

        } catch (SQLException e) {
            throw new DAOException("Find competition error", e);
        }
        return competition;
    }

    public Map<String, Object> findActiveUpcomingGameById(int id) throws DAOException {
        Map<String, Object> competition;

        try (PreparedStatement statement = connection.prepareStatement(FIND_UPCOMING_GAME_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<Map<String, Object>> competitions = extractGames(resultSet);
            competition = competitions.size() == 1 ? competitions.get(0) : null;

        } catch (SQLException e) {
            throw new DAOException("Find concrete upcoming competition error", e);
        }
        return competition;
    }

    public List<Map<String, Object>> findUpcomingGamesByType(int sportId, int typeId,
                                                             boolean isActive) throws DAOException {
        List<Map<String, Object>> competitions;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_UPCOMING_GAMES_BY_TYPE_ID)) {
            statement.setInt(1, typeId);
            statement.setInt(2, sportId);
            statement.setBoolean(3, isActive);
            ResultSet resultSet = statement.executeQuery();

            competitions = extractGames(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Find past competitions by type error", e);
        }
        return competitions;
    }

    public List<Map<String, Object>> findPastGamesByType(int sportId, int typeId, boolean isActive,
                                                         boolean isFilled) throws DAOException {
        List<Map<String, Object>> competitions;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_PAST_BY_TYPE_ID)) {
            statement.setInt(1, typeId);
            statement.setInt(2, sportId);
            statement.setBoolean(3, isActive);
            statement.setBoolean(4, isFilled);
            ResultSet resultSet = statement.executeQuery();

            competitions = extractGames(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Find past competitions by type error", e);
        }
        return competitions;
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

    public boolean updateActiveState(int competitionId, boolean state) throws DAOException {
        boolean isChanged;

        try (PreparedStatement statement = connection.prepareStatement(CHANGE_COMPETITION_ACTIVE_STATE)) {
            statement.setBoolean(1, state);
            statement.setInt(2, competitionId);

            isChanged = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Change active state error", e);
        }

        return isChanged;
    }

    public boolean updateResultFillState(int competitionId, boolean state) throws DAOException {
        boolean isChanged;

        try (PreparedStatement statement = connection.prepareStatement(CHANGE_COMPETITION_RESULT_FILL_STATE)) {
            statement.setBoolean(1, state);
            statement.setInt(2, competitionId);

            isChanged = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Change result fill state error", e);
        }

        return isChanged;
    }

    public List<Map<String, Object>> findGamesByUserId(int userId,
                                                       boolean isUpcoming) throws DAOException {
        List<Map<String, Object>> gamesList;

        try (PreparedStatement statement = connection.prepareStatement("s")) {
            statement.setBoolean(1, isUpcoming);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            gamesList = new ArrayList<>();


        } catch (SQLException e) {
            throw new DAOException("Find competitions by user id error", e);
        }

        return gamesList;
    }

    private List<Map<String, Object>> extractGames(ResultSet resultSet)
            throws SQLException {
        List<Map<String, Object>> gamesList = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> game = new HashMap<>();
            game.put(SQLFieldConstant.Competition.ID,
                    resultSet.getInt(SQLFieldConstant.Competition.ID));
            game.put(SQLFieldConstant.CompetitionType.NAME,
                    resultSet.getString(SQLFieldConstant.CompetitionType.NAME));
            game.put(SQLFieldConstant.KindOfSport.NAME,
                    resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
            game.put(SQLFieldConstant.Competition.TOTAL,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
            game.put(SQLFieldConstant.Competition.LESS_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
            game.put(SQLFieldConstant.Competition.MORE_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
            game.put(SQLFieldConstant.Competition.STANDOFF_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
            game.put(SQLFieldConstant.Competition.DATE_START,
                    resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_START));
            game.put(SQLFieldConstant.Competition.DATE_FINISH,
                    resultSet.getTimestamp(SQLFieldConstant.Competition.DATE_FINISH));
            game.put(SQLFieldConstant.Competition.NAME,
                    resultSet.getString(SQLFieldConstant.Competition.NAME));
            gamesList.add(game);
        }

        return gamesList;
    }


}
