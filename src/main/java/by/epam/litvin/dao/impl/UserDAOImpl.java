package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.dao.DAO;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.type.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.litvin.constant.GeneralConstant.CAN_NOT_DELETE_OR_UPDATE;
import static by.epam.litvin.constant.GeneralConstant.DUPLICATE_UNIQUE_INDEX;


public class UserDAOImpl extends DAO<UserEntity> {

    @Override
    public List<UserEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity findEntityById(int id) {
        throw new UnsupportedOperationException();
    }

    public UserEntity findUser(UserEntity user) throws DAOException {
        UserEntity foundUser = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                foundUser = new UserEntity();
                foundUser.setId(result.getInt(SQLFieldConstant.User.ID));
                foundUser.setName(result.getString(SQLFieldConstant.User.NAME));
                foundUser.setEmail(result.getString(SQLFieldConstant.User.EMAIL));
                foundUser.setPassword(result.getString(SQLFieldConstant.User.PASSWORD));
                foundUser.setConfirmUrl(result.getString(SQLFieldConstant.User.CONFIRM_URL));
                foundUser.setConfirm(result.getBoolean(SQLFieldConstant.User.IS_CONFIRM));
                foundUser.setBlocked(result.getBoolean(SQLFieldConstant.User.IS_BLOCKED));
                foundUser.setBlockedText(result.getString(SQLFieldConstant.User.BLOCKED_TEXT));
                foundUser.setCash(result.getBigDecimal(SQLFieldConstant.User.CASH));
                foundUser.setAvatarURL(result.getString(SQLFieldConstant.User.AVATAR_URL));
                String userType = result.getString(SQLFieldConstant.User.TYPE);
                foundUser.setType(UserType.valueOf(userType));
            }

        } catch (SQLException e) {
            throw new DAOException("Find user error ", e);
        }

        return foundUser;
    }

    public List<UserEntity> findLimit(int startIndex, int limit) throws DAOException {
        List<UserEntity> userList;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_LIMIT_USERS)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);
            ResultSet result = statement.executeQuery();
            userList = new ArrayList<>();

            while (result.next()) {
                UserEntity foundUser = new UserEntity();
                foundUser.setId(result.getInt(SQLFieldConstant.User.ID));
                foundUser.setName(result.getString(SQLFieldConstant.User.NAME));
                foundUser.setEmail(result.getString(SQLFieldConstant.User.EMAIL));
                foundUser.setPassword(result.getString(SQLFieldConstant.User.PASSWORD));
                foundUser.setConfirmUrl(result.getString(SQLFieldConstant.User.CONFIRM_URL));
                foundUser.setConfirm(result.getBoolean(SQLFieldConstant.User.IS_CONFIRM));
                foundUser.setBlocked(result.getBoolean(SQLFieldConstant.User.IS_BLOCKED));
                foundUser.setBlockedText(result.getString(SQLFieldConstant.User.BLOCKED_TEXT));
                foundUser.setCash(result.getBigDecimal(SQLFieldConstant.User.CASH));
                foundUser.setAvatarURL(result.getString(SQLFieldConstant.User.AVATAR_URL));
                String userType = result.getString(SQLFieldConstant.User.TYPE);
                foundUser.setType(UserType.valueOf(userType));
                userList.add(foundUser);
            }

        } catch (SQLException e) {
            throw new DAOException("Find user error ", e);
        }

        return userList;
    }

    public int findUsersCount() throws DAOException {
        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_USERS_COUNT)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            throw new DAOException("Find count news error ", e);
        }

        return count;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(UserEntity entity) throws DAOException {
        boolean isCreated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_USER)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getConfirmUrl());

            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create user error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    public boolean updateRole(UserEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.UPDATE_USER_ROLE)) {

            String type = entity.getType().toString();
            statement.setString(1, type);
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;


        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Create user error ", e);
            }
        }

        return isUpdated;
    }

    public boolean updateLock(UserEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.UPDATE_USER_LOCK)) {

            statement.setBoolean(1, entity.getIsBlocked());
            statement.setString(2, entity.getBlockedText());
            statement.setInt(3, entity.getId());

            isUpdated = statement.executeUpdate() == 1;


        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Create user error ", e);
            }
        }

        return isUpdated;
    }


    public void returnMoneyForBets(int competitionId) throws DAOException {

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.RETURN_MONEY_FOR_BETS)) {
            statement.setInt(1, competitionId);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Ruturn money for bets error ", e);
        }
    }
}
