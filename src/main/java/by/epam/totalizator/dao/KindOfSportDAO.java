package by.epam.totalizator.dao;

import by.epam.totalizator.bean.KindOfSportEntity;
import by.epam.totalizator.exception.DAOException;

import java.util.List;
import java.util.Map;

public abstract class KindOfSportDAO extends DAO<KindOfSportEntity> {

    /**
     * Find using kinds of sport
     *
     * @return kinds of sport with competition types
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findUsingKindsOfSport() throws DAOException;

}
