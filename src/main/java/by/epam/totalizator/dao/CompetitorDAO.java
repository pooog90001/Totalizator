package by.epam.totalizator.dao;

import by.epam.totalizator.bean.CompetitorEntity;
import by.epam.totalizator.exception.DAOException;

import java.util.List;

public abstract class CompetitorDAO extends DAO<CompetitorEntity> {

    /**
     * Find competitors by competition id
     *
     * @param competitionId competition id
     * @return competitors
     * @throws DAOException when sql request error
     */
    public abstract List<CompetitorEntity> findByGameId(int competitionId) throws DAOException;

    /**
     * Delete competitors by competition id
     *
     * @param competitionId competition id
     * @throws DAOException when sql request error
     */
    public abstract void deleteByCompetitionId(int competitionId) throws DAOException;

    /**
     * Update competitor result
     *
     * @param entity competitor
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateResult(CompetitorEntity entity) throws DAOException;
}
