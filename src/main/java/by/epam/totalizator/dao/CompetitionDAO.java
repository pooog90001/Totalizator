package by.epam.totalizator.dao;

import by.epam.totalizator.bean.CompetitionEntity;
import by.epam.totalizator.exception.DAOException;

import java.util.List;
import java.util.Map;

public abstract class CompetitionDAO extends DAO<CompetitionEntity> {

    /**
     * Update upcoming activated competition coefficients
     *
     * @param entity competition
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateUpcomingActivatedCoeffs(CompetitionEntity entity) throws DAOException;

    /**
     * Update upcoming deactivated competition coefficients
     *
     * @param entity competition
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateUpcomingDeactivatedCoeffs(CompetitionEntity entity) throws DAOException;

    /**
     * Update competition name
     *
     * @param entity competition
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateName(CompetitionEntity entity) throws DAOException;

    /**
     * Update competition active state
     *
     * @param competitionId competition id
     * @param activeState   active state
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateActiveState(int competitionId, boolean activeState) throws DAOException;

    /**
     * Update competition fill state
     *
     * @param competitionId competition id
     * @param fillState     fill state
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateResultFillState(int competitionId, boolean fillState) throws DAOException;

    /**
     * Find competitions by user id
     *
     * @param userId     user id
     * @param isUpcoming Is competition upcoming?
     * @return competitions
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findGamesByUserId(int userId,
                                                                boolean isUpcoming) throws DAOException;

    /**
     * Find upcoming competitions count
     *
     * @param isActivated activated state
     * @return upcoming competition count
     * @throws DAOException when sql request error
     */
    public abstract int findUpcomingGamesCount(boolean isActivated) throws DAOException;

    /**
     * Find past competitions count
     *
     * @param isResultFilled competition result filled state
     * @param isActivated    competition activated state
     * @return past competition count
     * @throws DAOException when sql request error
     */
    public abstract int findPastGamesCount(boolean isResultFilled, boolean isActivated) throws DAOException;

    /**
     * Find all current competitions
     *
     * @param isActivated competition activated state
     * @return competitions with competition type name
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findAllNowGames(boolean isActivated) throws DAOException;

    /**
     * Find all past competitions
     *
     * @param isActivated    competition activated state
     * @param isResultFilled competition result filled state
     * @return competitions with competition type name
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findAllPastGames(boolean isResultFilled,
                                                               boolean isActivated) throws DAOException;

    /**
     * Find limit past competitions
     *
     * @param startIndex     start index
     * @param limit          limit
     * @param isResultFilled competition result filled state
     * @param isActivated    competition activated state
     * @return competitions with competition type name
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findLimitPastGames(int startIndex, int limit,
                                                                 boolean isResultFilled,
                                                                 boolean isActivated) throws DAOException;

    /**
     * Find limit upcoming competitions
     *
     * @param startIndex  start index
     * @param limit       limit
     * @param isActivated competition activated state
     * @return competitions with competition type name
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findLimitUpcomingGames(int startIndex,
                                                                     int limit, boolean isActivated) throws DAOException;

    /**
     * Find all upcoming competitions
     *
     * @param isActivated competition activated state
     * @return competitions with competition type name
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findAllUpcomingGames(boolean isActivated) throws DAOException;

}

