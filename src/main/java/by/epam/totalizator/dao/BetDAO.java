package by.epam.totalizator.dao;

import by.epam.totalizator.bean.BetEntity;
import by.epam.totalizator.exception.DAOException;

public abstract class BetDAO extends DAO<BetEntity> {
    public abstract void deleteByCompetitionId(int competitionId) throws DAOException;
}
