package by.epam.litvin.dao;

import by.epam.litvin.bean.User;
import by.epam.litvin.pool.ProxyConnection;
import by.epam.litvin.constant.SQLCommand;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findEntityById(int id) {
        return null;
    }

    public User findUser(User user) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQLCommand.CREATE_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet result = statement.executeQuery();
            User foundUser = null;

            if (result.next()) {
                foundUser = new User();
                foundUser.setId(result.getInt("user_id"));
                foundUser.setName(result.getString("user_name"));
                foundUser.setEmail(result.getString("user_email"));
                foundUser.setPassword(result.getString("user_password"));
                foundUser.setConfirmUrl(result.getString("user_confirm_url"));
                foundUser.setConfirm(result.getBoolean("user_is_confirm"));
                foundUser.setBlocked(result.getBoolean("user_is_bloked"));
                foundUser.setCash(result.getBigDecimal("user_cash"));
            }
            return foundUser;

        } catch (SQLException e) {
            throw new DAOException("Find user error ", e);
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) throws DAOException {

        try (PreparedStatement statement = connection.prepareStatement(SQLCommand.CREATE_USER)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getConfirmUrl());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                return false;
            }
            throw new DAOException("Create user error ", e);
        }

    }

    @Override
    public User update(User entity) {
        return null;
    }
}
