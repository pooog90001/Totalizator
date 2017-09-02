package by.epam.totalizator.dao;

import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.type.ExpectResultType;

import java.math.BigDecimal;
import java.util.Map;

public abstract class CommonDAO extends DAO {

    /**
     * Number of bets on the competition
     *
     * @param competitionId competition id
     * @param expectResult  expected result
     * @return number of bets
     * @throws DAOException when sql request error
     * @see ExpectResultType
     */
    public abstract int findCountBetsOnCompetition(int competitionId, ExpectResultType expectResult) throws DAOException;

    /**
     * Number of bets on the competition
     *
     * @param competitorId competitor id
     * @return number of bets
     * @throws DAOException when sql request error
     * @see ExpectResultType
     */
    public abstract int findCountBetsOnCompetitor(int competitorId) throws DAOException;

    /**
     * Amount of money for the competitors
     *
     * @param competitorId competitor id
     * @return amount of money
     * @throws DAOException when sql request error
     */
    public abstract BigDecimal findAmountOfMoneyOnCompetitor(int competitorId) throws DAOException;

    /**
     * Amount of money for the competition
     *
     * @param competitionId competition id
     * @param expectResult  expected result
     * @return amount of money
     * @throws DAOException when sql request error
     * @see ExpectResultType
     */
    public abstract BigDecimal findAmountOfMoneyOnCompetition(int competitionId, ExpectResultType expectResult) throws DAOException;

    /**
     * Statistic for admin
     *
     * @return registered people count, locked people count, news count, sports count
     */
    public abstract Map<String, Object> findAdminStatistic();
}
