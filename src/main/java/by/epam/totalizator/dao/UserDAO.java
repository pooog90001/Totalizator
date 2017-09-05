package by.epam.totalizator.dao;

import by.epam.totalizator.entity.UserEntity;
import by.epam.totalizator.exception.DAOException;

import java.util.List;

public abstract class UserDAO extends DAO<UserEntity> {

    /**
     * Find limit users
     *
     * @param startIndex start index
     * @param limit      limit
     * @return users
     * @throws DAOException when sql request error
     */
    public abstract List<UserEntity> findLimit(int startIndex, int limit) throws DAOException;

    /**
     * Find users count
     *
     * @return users count
     * @throws DAOException when sql request error
     */
    public abstract int findUsersCount() throws DAOException;

    /**
     * Find user by email and password
     *
     * @param user user
     * @return user
     * @throws DAOException when sql request error
     */
    public abstract UserEntity findUser(UserEntity user) throws DAOException;

    /**
     * Update user role
     *
     * @param entity user
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateRole(UserEntity entity) throws DAOException;

    /**
     * Update user role
     *
     * @param entity user
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateAvatarPath(UserEntity entity) throws DAOException;

    /**
     * Update user password
     *
     * @param entity user
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updatePassword(UserEntity entity) throws DAOException;

    /**
     * Update user lock
     *
     * @param entity user
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateLock(UserEntity entity) throws DAOException;

    /**
     * Return money for bets
     *
     * @param competitionId competition id
     * @throws DAOException when sql request error
     */
    public abstract void returnMoneyForBets(int competitionId) throws DAOException;

    /**
     * Update user cash
     *
     * @param entity user
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateCash(UserEntity entity) throws DAOException;
}
