package by.epam.litvin.dao;

import by.epam.litvin.bean.BetEntity;
import by.epam.litvin.exception.DAOException;

public abstract class BetDAO extends DAO<BetEntity> {
    public abstract void deleteByCompetitionId(int competitionId) throws DAOException;
}
