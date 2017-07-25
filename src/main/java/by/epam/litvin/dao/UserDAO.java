package by.epam.litvin.dao;

import by.epam.litvin.bean.User;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.type.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.epam.litvin.constant.GeneralConstant.DUPLICATE_UNIQUE_INDEX;


public class UserDAO extends AbstractDAO<User> {

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findEntityById(int id) {
        throw new UnsupportedOperationException();
    }

    public User findUser(User user) throws DAOException {
        User foundUser = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                foundUser = new User();
                foundUser.setId(result.getInt(SQLFieldConstant.User.ID));
                foundUser.setName(result.getString(SQLFieldConstant.User.NAME));
                foundUser.setEmail(result.getString(SQLFieldConstant.User.EMAIL));
                foundUser.setPassword(result.getString(SQLFieldConstant.User.PASSWORD));
                foundUser.setConfirmUrl(result.getString(SQLFieldConstant.User.CONFIRM_URL));
                foundUser.setConfirm(result.getBoolean(SQLFieldConstant.User.IS_CONFIRM));
                foundUser.setBlocked(result.getBoolean(SQLFieldConstant.User.IS_BLOCKED));
                foundUser.setBlockedText(result.getString(SQLFieldConstant.User.BLOCKED_TEXT));
                foundUser.setCash(result.getBigDecimal(SQLFieldConstant.User.CASH));
                String userType = result.getString(SQLFieldConstant.User.TYPE);
                foundUser.setType(UserType.valueOf(userType));
            }

        } catch (SQLException e) {
            throw new DAOException("Find user error ", e);
        }

        return foundUser;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(User entity) throws DAOException {

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_USER)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getConfirmUrl());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                return false;
            }
            throw new DAOException("Create user error ", e);
        }

    }

    @Override
    public boolean update(User entity) {
        throw new UnsupportedOperationException();
    }
}
