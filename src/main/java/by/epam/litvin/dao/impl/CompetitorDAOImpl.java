package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.CompetitorEntity;
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

import static by.epam.litvin.constant.GeneralConstant.DUPLICATE_UNIQUE_INDEX;
import static by.epam.litvin.constant.SQLRequestConstant.*;

public class CompetitorDAOImpl extends DAO<CompetitorEntity> {
    @Override
    public List<CompetitorEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }

    public List<Map<String, Object>> findWithCommandByCompetitionId(int competitionId) throws DAOException {
        List<Map<String, Object>> competitorList;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_COMPETITORS_WITH_COMMAND_BY_COMPETITION_ID)) {
            statement.setInt(1, competitionId);
            ResultSet resultSet = statement.executeQuery();
            competitorList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> competitor = new HashMap<>();
                competitor.put(SQLFieldConstant.Competitor.ID,
                        resultSet.getInt(SQLFieldConstant.Competitor.ID));
                competitor.put(SQLFieldConstant.Competitor.WIN_COEFF,
                        resultSet.getBigDecimal(SQLFieldConstant.Competitor.WIN_COEFF));
                competitor.put(SQLFieldConstant.Command.NAME,
                        resultSet.getString(SQLFieldConstant.Command.NAME));
                competitor.put(SQLFieldConstant.Competitor.RESULT,
                        resultSet.getInt(SQLFieldConstant.Competitor.RESULT));
                competitor.put(SQLFieldConstant.Competitor.IS_WIN,
                        resultSet.getBoolean(SQLFieldConstant.Competitor.IS_WIN));
                competitorList.add(competitor);
            }
        } catch (SQLException e) {
            throw new DAOException("find all competitors whith command error", e);
        }
        return competitorList;
    }

    @Override
    public CompetitorEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(CompetitorEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(CompetitorEntity entity) throws DAOException {
        boolean isCreated = false;

        try (PreparedStatement statement = connection.prepareStatement(INSERT_COMPETITOR)) {
            statement.setInt(1, entity.getCommandId());
            statement.setInt(2, entity.getCompetitionId());
            statement.setBigDecimal(3, entity.getWinCoeff());
            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create competitor error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(CompetitorEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_COMPETITOR_COEFFS)) {
            statement.setBigDecimal(1, entity.getWinCoeff());
            statement.setInt(2, entity.getId());
            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update competitor error", e);
        }

        return isUpdated;
    }

    public List<Integer> findIdsByCompetitionId(int competitionId) throws DAOException {
        List<Integer> idList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_COMPETITOR_IDS_BY_COMPETITION_ID)) {
            statement.setInt(1, competitionId);
            ResultSet resultSet = statement.executeQuery();
            idList = new ArrayList<>();

            while (resultSet.next()) {
                idList.add(resultSet.getInt(SQLFieldConstant.Competitor.ID));
            }

        } catch (SQLException e) {
            throw new DAOException("Find competitor id error", e);
        }

        return idList;
    }

    public void deleteByCompetitionId(int competitionId) throws DAOException {

        try (PreparedStatement statement = connection.prepareStatement(DELETE_COMPETITORS_BY_COMPETITION_ID)) {
            statement.setInt(1, competitionId);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Delete competitors error", e);
        }
    }

    public boolean updateResult(CompetitorEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_COMPETITOR_RESULT)) {
            statement.setBoolean(1, entity.getWin());
            statement.setInt(2, entity.getResult());
            statement.setInt(3, entity.getId());

            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update competitor result error", e);
        }

        return isUpdated;
    }


}
