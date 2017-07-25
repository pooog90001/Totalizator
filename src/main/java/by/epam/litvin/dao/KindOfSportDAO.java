package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.SQLRequestConstant.FIND_USING_KIND_OF_SPORTS;

public class KindOfSportDAO extends AbstractDAO{


    public List<Map<String, Object>> findUsingKindsOfSport() throws DAOException {
        List<Map<String, Object>> kindOfSportList = null;

        try (PreparedStatement statement = connection.prepareStatement(FIND_USING_KIND_OF_SPORTS)) {
            ResultSet resultSet = statement.executeQuery();
            kindOfSportList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> kindOfSport = new HashMap<>();
                kindOfSport.put("kind_of_sport_name",
                        resultSet.getString("kind_of_sport_name"));
                kindOfSport.put("competition_type_id",
                        resultSet.getInt("competition_type_id"));
                kindOfSport.put("competition_type_name",
                        resultSet.getString("competition_type_name"));
                kindOfSportList.add(kindOfSport);
            }

        } catch (SQLException e) {
            throw new DAOException("find using linds of sport error", e);
        }
        return kindOfSportList;
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
