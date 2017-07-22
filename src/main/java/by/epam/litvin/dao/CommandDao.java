package by.epam.litvin.dao;

import by.epam.litvin.bean.Command;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.litvin.constant.GeneralConstant.DUPLICATE_UNIQUE_INDEX;

/**
 * Created by max on 22.07.17.
 */
public class CommandDao extends AbstractDAO<Command> {

    @Override
    public List<Command> findAll() throws DAOException {
        List<Command> commandList = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_ALL_COMMAND)) {
            ResultSet resultSet = statement.executeQuery();
            commandList = new ArrayList<>();

            while (resultSet.next()) {
                Command command = new Command();
                command.setId(resultSet.getInt(SQLFieldConstant.Command.ID));
                command.setName(resultSet.getString(SQLFieldConstant.Command.NAME));
                command.setKindOfSport(resultSet.getString(SQLFieldConstant.Command.KIND_OF_SPORT_ID));
                commandList.add(command);
            }

        } catch (SQLException e) {
            throw new DAOException("Find all commands ERROR", e);
        }

        return commandList;
    }

    @Override
    public Command findEntityById(int id) throws DAOException {
        Command command = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_COMMAND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                command = new Command();
                command.setId(resultSet.getInt(SQLFieldConstant.Command.ID));
                command.setName(resultSet.getString(SQLFieldConstant.Command.NAME));
                command.setKindOfSport(resultSet.getString(SQLFieldConstant.Command.KIND_OF_SPORT_ID));
            }

        } catch (SQLException e) {
            throw new DAOException("Find command by id ERROR!", e);
        }
        return command;
    }

    @Override
    public void delete(int id) throws DAOException {
        try(PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_COMMAND_BY_ID)) {
            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("DELETE command ERROR");
        }
    }

    @Override
    public void delete(Command entity) throws DAOException {
        try(PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_COMMAND_BY_ID)) {
            statement.setInt(1, entity.getId());
            statement.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("DELETE command ERROR");
        }
    }

    @Override
    public boolean create(Command entity) throws DAOException {
        try(PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.INSERT_COMMAND)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getKindOfSport());
            statement.executeQuery();

        } catch (SQLException e) {
            if (DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                return false;
            }
            throw new DAOException("INSERT command ERROR");
        }
        return true;
    }

    @Override
    public boolean update(Command entity) throws DAOException {
        try(PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.UPDATE_COMMAND_BY_ID)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getKindOfSport());
            statement.executeQuery();

        } catch (SQLException e) {
            if (DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                return false;
            }
            throw new DAOException("UPDATE command ERROR");
        }
        return true;
    }

    @Override
    public void setConnection(ProxyConnection connection) {
        super.setConnection(connection);
    }
}
