package by.epam.litvin.dao;

import by.epam.litvin.bean.CommandEntity;
import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.CAN_NOT_DELETE_OR_UPDATE;
import static by.epam.litvin.constant.GeneralConstant.DUPLICATE_UNIQUE_INDEX;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_ALL_COMMAND_WITH_KIND_OF_SPORT;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_ALL_KINDS_OF_SPORT;

public class CommandDAO extends AbstractDAO<CommandEntity> {
    @Override
    public List<CommandEntity> findAll() throws DAOException {
        List<CommandEntity> commandList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_KINDS_OF_SPORT)) {
            ResultSet resultSet = statement.executeQuery();
            commandList = new ArrayList<>();

            while (resultSet.next()) {
                CommandEntity command = new CommandEntity();
                command.setId(resultSet.getInt(SQLFieldConstant.Command.ID));
                command.setName(resultSet.getString(SQLFieldConstant.Command.NAME));
                command.setId(resultSet.getInt(SQLFieldConstant.Command.KIND_OF_SPORT_ID));
                commandList.add(command);
            }

        } catch (SQLException e) {
            throw new DAOException("find all kinds of sport error", e);
        }

        return commandList;
    }

    public List<Map<String, Object>> findAllWithKindOfSport() throws DAOException {
        List<Map<String, Object>> commandList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_COMMAND_WITH_KIND_OF_SPORT)) {
            ResultSet resultSet = statement.executeQuery();
            commandList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> command = new HashMap<>();
                command.put(SQLFieldConstant.Command.ID,
                        resultSet.getInt(SQLFieldConstant.Command.ID));
                command.put(SQLFieldConstant.Command.NAME,
                        resultSet.getString(SQLFieldConstant.Command.NAME));
                command.put(SQLFieldConstant.KindOfSport.ID,
                        resultSet.getInt(SQLFieldConstant.KindOfSport.ID));
                command.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                commandList.add(command);
            }

        } catch (SQLException e) {
            throw new DAOException("find all kinds of sport error", e);
        }

        return commandList;
    }


    @Override
    public CommandEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        boolean isDeleted = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_COMMAND_BY_ID)) {
            statement.setInt(1, id);

            isDeleted = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Delete command error ", e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean delete(CommandEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(CommandEntity entity) throws DAOException {
        boolean isCreated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_COMMAND)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getKindOfSportId());

            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create command error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(CommandEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.UPDATE_COMMAND_BY_ID)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Update command error ", e);
            }
        }
        return isUpdated;
    }
}
