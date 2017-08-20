package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.KindOfSportEntity;
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
import static by.epam.litvin.constant.SQLRequestConstant.FIND_ALL_KINDS_OF_SPORT;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_LIVE_GAMES_KINDS_OF_SPORT;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_USING_KINDS_OF_SPORT;

public class KindOfSportDAOImpl extends DAO<KindOfSportEntity> {


    public List<Map<String, Object>> findUsingKindsOfSport() throws DAOException {
        List<Map<String, Object>> kindOfSportList = null;

        try (PreparedStatement statement = connection.prepareStatement(FIND_USING_KINDS_OF_SPORT)) {
            ResultSet resultSet = statement.executeQuery();
            kindOfSportList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> kindOfSport = new HashMap<>();
                kindOfSport.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                kindOfSport.put(SQLFieldConstant.CompetitionType.ID,
                        resultSet.getInt(SQLFieldConstant.CompetitionType.ID));
                kindOfSport.put(SQLFieldConstant.CompetitionType.NAME,
                        resultSet.getString(SQLFieldConstant.CompetitionType.NAME));
                kindOfSportList.add(kindOfSport);
            }

        } catch (SQLException e) {
            throw new DAOException("find using kinds of sport error", e);
        }
        return kindOfSportList;
    }


    public List<KindOfSportEntity> findLiveGamesKindsOfSport() throws DAOException {
        List<KindOfSportEntity> kindOfSportList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIVE_GAMES_KINDS_OF_SPORT)) {
            ResultSet resultSet = statement.executeQuery();
            kindOfSportList = extractKindsOfSport(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find live games kinds of sport error", e);
        }

        return kindOfSportList;
    }

    @Override
    public List<KindOfSportEntity> findAll() throws DAOException {
        List<KindOfSportEntity> kindOfSportList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_KINDS_OF_SPORT)) {
            ResultSet resultSet = statement.executeQuery();
            kindOfSportList = extractKindsOfSport(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find all kinds of sport error", e);
        }

        return kindOfSportList;
    }

    private List<KindOfSportEntity> extractKindsOfSport(ResultSet resultSet) throws SQLException {
        List<KindOfSportEntity> kindOfSportList = new ArrayList<>();

        while (resultSet.next()) {
            KindOfSportEntity kindOfSport = new KindOfSportEntity();
            kindOfSport.setId(resultSet.getInt(SQLFieldConstant.KindOfSport.ID));
            kindOfSport.setName(resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
            kindOfSport.setCompetitorCount(resultSet.getInt(SQLFieldConstant.KindOfSport.COMPETITOR_COUNT));
            kindOfSportList.add(kindOfSport);
        }

        return kindOfSportList;
    }

    @Override
    public KindOfSportEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        boolean isDeleted = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_KIND_OF_SPORT_BY_ID)) {
            statement.setInt(1, id);

            isDeleted = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Delete kind of sport error ", e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean delete(KindOfSportEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(KindOfSportEntity entity) throws DAOException {
        boolean isCreated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_KIND_OF_SPORT)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getCompetitorCount());

            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create kind of sport error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(KindOfSportEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.UPDATE_KIND_OF_SPORT_BY_ID)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Update kind of sport error ", e);
            }
        }
        return isUpdated;
    }

}
