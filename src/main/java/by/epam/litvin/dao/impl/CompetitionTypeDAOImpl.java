package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.CompetitionTypeEntity;
import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.DAO;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.litvin.constant.GeneralConstant.CAN_NOT_DELETE_OR_UPDATE;
import static by.epam.litvin.constant.GeneralConstant.DUPLICATE_UNIQUE_INDEX;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_ALL_COMPETITION_TYPES;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_COMPETITION_TYPE_BY_ID;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_KIND_OF_SPORT_BY_ID;

public class CompetitionTypeDAOImpl extends DAO<CompetitionTypeEntity> {
    @Override
    public List<CompetitionTypeEntity> findAll() throws DAOException {
        List<CompetitionTypeEntity> competitionTypeList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_COMPETITION_TYPES)) {
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

        try (PreparedStatement statement = connection.prepareStatement(FIND_COMPETITION_TYPE_BY_ID)) {
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
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
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
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
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
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Update type of competition error ", e);
            }
        }
        return isUpdated;
    }
}
