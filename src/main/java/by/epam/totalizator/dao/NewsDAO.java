package by.epam.totalizator.dao;

import by.epam.totalizator.bean.NewsEntity;
import by.epam.totalizator.exception.DAOException;

import java.util.List;

public abstract class NewsDAO extends DAO<NewsEntity> {

    /**
     * Find limit news
     *
     * @param startIndex start index
     * @param limit      limit
     * @return news
     * @throws DAOException when sql request error
     */
    public abstract List<NewsEntity> findLimit(int startIndex, int limit) throws DAOException;

    /**
     * Create news and get created news id
     *
     * @param entity news
     * @return news id
     * @throws DAOException when sql request error
     */
    public abstract int createAndGetId(NewsEntity entity) throws DAOException;

    /**
     * Find news count
     *
     * @return news count
     * @throws DAOException when sql request error
     */
    public abstract int findNewsCount() throws DAOException;

    /**
     * Update image path
     *
     * @param entity news
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateImagePath(NewsEntity entity) throws DAOException;
}
