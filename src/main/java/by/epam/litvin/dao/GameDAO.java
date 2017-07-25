package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.SQLRequestConstant.FIND_LIMIT_LIVE_GAMES;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_LIMIT_PAST_GAMES;
import static by.epam.litvin.constant.SQLRequestConstant.FIND_LIMIT_UPCOMMING_GAMES;

public class GameDAO extends AbstractDAO {

    public List<Map<String, Object>> findLiveGames(int limit) throws DAOException {
        List<Map<String, Object>> liveGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_LIVE_GAMES)) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            liveGamesList = extractData(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find live games error", e);
        }
        return liveGamesList;
    }

    public List<Map<String, Object>> findUpcommingGames(int limit) throws DAOException {
        List<Map<String, Object>> upcommingGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_UPCOMMING_GAMES)) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            upcommingGamesList = extractData(resultSet);

        } catch (SQLException e) {
            throw new DAOException("find upcomming games error", e);
        }
        return upcommingGamesList;
    }

    private List<Map<String, Object>> extractData(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> upcommingGame = new HashMap<>();
            upcommingGame.put(SQLFieldConstant.Competition.ID,
                    resultSet.getInt(SQLFieldConstant.Competition.ID));
            upcommingGame.put(SQLFieldConstant.KindOfSport.NAME,
                    resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
            upcommingGame.put(SQLFieldConstant.Command.NAME,
                    resultSet.getString(SQLFieldConstant.Command.NAME));
            upcommingGame.put(SQLFieldConstant.Competition.TOTAL,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.TOTAL));
            upcommingGame.put(SQLFieldConstant.Competition.LESS_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.LESS_TOTAL_COEFF));
            upcommingGame.put(SQLFieldConstant.Competition.MORE_TOTAL_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.MORE_TOTAL_COEFF));
            upcommingGame.put(SQLFieldConstant.Competition.STANDOFF_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.Competition.STANDOFF_COEFF));
            upcommingGame.put(SQLFieldConstant.CommandM2MCompetition.WIN_COEFF,
                    resultSet.getBigDecimal(SQLFieldConstant.CommandM2MCompetition.WIN_COEFF));
            resultList.add(upcommingGame);
        }

        return resultList;
    }

    public List<Map<String, Object>> findPastGames(int limit) throws DAOException {
        List<Map<String, Object>> pastGamesList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_PAST_GAMES)) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            pastGamesList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> pastGame = new HashMap<>();
                pastGame.put(SQLFieldConstant.Competition.ID,
                        resultSet.getInt(SQLFieldConstant.Competition.ID));
                pastGame.put(SQLFieldConstant.KindOfSport.NAME,
                        resultSet.getString(SQLFieldConstant.KindOfSport.NAME));
                pastGame.put(SQLFieldConstant.Command.NAME,
                        resultSet.getString(SQLFieldConstant.Command.NAME));
                pastGame.put(SQLFieldConstant.Competition.DATE_START,
                        resultSet.getDate(SQLFieldConstant.Competition.DATE_START));
                pastGame.put(SQLFieldConstant.CommandM2MCompetition.RESULT,
                        resultSet.getInt(SQLFieldConstant.CommandM2MCompetition.RESULT));
                pastGamesList.add(pastGame);
            }

        } catch (SQLException e) {
            throw new DAOException("find past games error", e);
        }
        return pastGamesList;
    }


    @Override
    public List findAll() throws DAOException {
        return null;
    }

    @Override
    public Entity findEntityById(int id) throws DAOException {
        return null;
    }

    @Override
    public void delete(int id) throws DAOException {

    }

    @Override
    public void delete(Entity entity) throws DAOException {

    }

    @Override
    public boolean create(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Entity entity) throws DAOException {
        return false;
    }
}
