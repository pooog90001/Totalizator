package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.TeamEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.DAO;
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
import static by.epam.litvin.constant.SQLRequestConstant.*;

public class TeamDAOImpl extends DAO<TeamEntity> {
    @Override
    public List<TeamEntity> findAll() throws DAOException {
        List<TeamEntity> teamList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_TEAMS)) {
            ResultSet resultSet = statement.executeQuery();
            teamList = new ArrayList<>();

            while (resultSet.next()) {
                TeamEntity team = new TeamEntity();
                team.setId(resultSet.getInt(SQLFieldConstant.TEAM.ID));
                team.setName(resultSet.getString(SQLFieldConstant.TEAM.NAME));
                team.setId(resultSet.getInt(SQLFieldConstant.TEAM.KIND_OF_SPORT_ID));
                teamList.add(team);
            }

        } catch (SQLException e) {
            throw new DAOException("find all teams error", e);
        }

        return teamList;
    }

    public List<TeamEntity> findBySportId(int sportId) throws DAOException {
        List<TeamEntity> teamList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_TEAMS_BY_SPORT_ID)) {
            statement.setInt(1, sportId);
            ResultSet resultSet = statement.executeQuery();
            teamList = new ArrayList<>();

            while (resultSet.next()) {
                TeamEntity team = new TeamEntity();
                team.setId(resultSet.getInt(SQLFieldConstant.TEAM.ID));
                team.setName(resultSet.getString(SQLFieldConstant.TEAM.NAME));
                team.setKindOfSportId(resultSet.getInt(SQLFieldConstant.TEAM.KIND_OF_SPORT_ID));
                teamList.add(team);
            }

        } catch (SQLException e) {
            throw new DAOException("find all teams error", e);
        }

        return teamList;
    }

    public List<Map<String, Object>> findAllWithKindOfSport() throws DAOException {
        List<Map<String, Object>> teamList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_TEAMS_WITH_KIND_OF_SPORT)) {
            ResultSet resultSet = statement.executeQuery();
            teamList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> team = new HashMap<>();
                team.put(SQLFieldConstant.TEAM.ID,
                        resultSet.getInt(SQLFieldConstant.TEAM.ID));
                team.put(SQLFieldConstant.TEAM.NAME,
                        resultSet.getString(SQLFieldConstant.TEAM.NAME));
                team.put(SQLFieldConstant.KindOfSport.ID,
                        resultSet.getInt(SQLFieldConstant.KindOfSport.ID));
                team.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                teamList.add(team);
            }

        } catch (SQLException e) {
            throw new DAOException("find all teams error", e);
        }

        return teamList;
    }


    @Override
    public TeamEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        boolean isDeleted = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_TEAM_BY_ID)) {
            statement.setInt(1, id);

            isDeleted = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Delete team error ", e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean delete(TeamEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(TeamEntity entity) throws DAOException {
        boolean isCreated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_TEAM)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getKindOfSportId());

            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create team error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(TeamEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.UPDATE_TEAM_BY_ID)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Update team error ", e);
            }
        }
        return isUpdated;
    }
}
