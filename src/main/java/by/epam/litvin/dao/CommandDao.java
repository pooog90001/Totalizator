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
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Command entity) {
        return false;
    }

    @Override
    public boolean create(Command entity) throws DAOException {
        return false;
    }

    @Override
    public Command update(Command entity) {
        return null;
    }

    @Override
    public void setConnection(ProxyConnection connection) {
        super.setConnection(connection);
    }
}
