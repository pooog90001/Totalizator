package by.epam.totalizator.dao;

import by.epam.totalizator.bean.BetEntity;
import by.epam.totalizator.exception.DAOException;

import java.util.List;
import java.util.Map;

public abstract class BetDAO extends DAO<BetEntity> {

    /**
     * Delete bets by competition id
     *
     * @param competitionId competition id
     * @throws DAOException when sql error
     */
    public abstract void deleteByCompetitionId(int competitionId) throws DAOException;

    /**
     * Find past bets by user id
     *
     * @param userId user id
     * @throws DAOException when sql error
     */
    public abstract List<Map<String, Object>> findPastBetsByUserId(int userId) throws DAOException;

    /**
     * Find upcoming bets by user id
     *
     * @param userId user id
     * @throws DAOException when sql error
     */
    public abstract List<Map<String, Object>> findUpcomingBetsByUserId(int userId) throws DAOException;

    /**
     * Update competitor result and pay money winners
     *
     * @param competitionId competition id
     * @throws DAOException when sql error
     */
    public abstract void updateCompetitorResultAndPayMoney(int competitionId) throws DAOException;

    /**
     * Update game result and pay money winners
     *
     * @param competitionId     competition id
     * @param competitorResult1 first competitor result
     * @param competitorResult2 second competitor result
     * @throws DAOException when sql error
     */
    public abstract void updateGameResultAndPayMoney
    (int competitionId, int competitorResult1, int competitorResult2) throws DAOException;
}
