package by.epam.totalizator.dao.impl;

import by.epam.totalizator.constant.GeneralConstant;
import by.epam.totalizator.constant.SQLFieldConstant;
import by.epam.totalizator.constant.SQLRequestConstant;
import by.epam.totalizator.dao.CompetitionTypeDAO;
import by.epam.totalizator.entity.CompetitionTypeEntity;
import by.epam.totalizator.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionTypeDAOImpl extends CompetitionTypeDAO {
    @Override
    public List<CompetitionTypeEntity> findAll() throws DAOException {
        List<CompetitionTypeEntity> competitionTypeList;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_ALL_COMPETITION_TYPES)) {
            ResultSet resultSet = statement.executeQuery();
            competitionTypeList = new ArrayList<>();

            while (resultSet.next()) {
                CompetitionTypeEntity type = new CompetitionTypeEntity();
                type.setId(resultSet.getInt(SQLFieldConstant.CompetitionType.ID));
                type.setName(resultSet.getString(SQLFieldConstant.CompetitionType.NAME));
                competitionTypeList.add(type);
            }

        } catch (SQLException e) {
            throw new DAOException("find all types of competition error", e);
        }

        return competitionTypeList;
    }

    @Override
    public CompetitionTypeEntity findEntityById(int id) throws DAOException {
        CompetitionTypeEntity type = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_COMPETITION_TYPE_BY_ID)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                type = new CompetitionTypeEntity();
                type.setId(resultSet.getInt(SQLFieldConstant.CompetitionType.ID));
                type.setName(resultSet.getString(SQLFieldConstant.CompetitionType.NAME));
            }

        } catch (SQLException e) {
            throw new DAOException("Find type of competition error ", e);
        }

        return type;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        boolean isDeleted = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_COMPETITION_TYPE)) {
            statement.setInt(1, id);

            isDeleted = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!GeneralConstant.CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Create type of competition error ", e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean delete(CompetitionTypeEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(CompetitionTypeEntity entity) throws DAOException {
        boolean isCreated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_COMPETITION_TYPE)) {
            statement.setString(1, entity.getName());

            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create type of competition error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(CompetitionTypeEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.UPDATE_COMPETITION_TYPE)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Update type of competition error ", e);
            }
        }
        return isUpdated;
    }
}
