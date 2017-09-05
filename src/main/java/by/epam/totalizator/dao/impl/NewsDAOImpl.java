package by.epam.totalizator.dao.impl;

import by.epam.totalizator.constant.SQLFieldConstant;
import by.epam.totalizator.constant.SQLRequestConstant;
import by.epam.totalizator.dao.NewsDAO;
import by.epam.totalizator.entity.NewsEntity;
import by.epam.totalizator.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.totalizator.constant.GeneralConstant.CAN_NOT_DELETE_OR_UPDATE;
import static by.epam.totalizator.constant.GeneralConstant.COUNT;
import static by.epam.totalizator.constant.SQLRequestConstant.UPDATE_IMAGE_PATH_NEWS;

public class NewsDAOImpl extends NewsDAO {

    @Override
    public List<NewsEntity> findAll() throws DAOException {
        List<NewsEntity> newsList;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_ALL_NEWS)) {
            ResultSet resultSet = statement.executeQuery();
            newsList = extractNewsData(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Find all news error ", e);
        }

        return newsList;
    }

    public List<NewsEntity> findLimit(int startIndex, int limit) throws DAOException {
        List<NewsEntity> newsList;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_LIMIT_NEWS)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);

            ResultSet resultSet = statement.executeQuery();
            newsList = extractNewsData(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Find limit news error ", e);
        }

        return newsList;
    }

    private List<NewsEntity> extractNewsData(ResultSet resultSet) throws SQLException {
        List<NewsEntity> newsList = new ArrayList<>();

        while (resultSet.next()) {
            NewsEntity news = new NewsEntity();
            news.setId(resultSet.getInt(SQLFieldConstant.News.ID));
            news.setText(resultSet.getString(SQLFieldConstant.News.TEXT));
            news.setTitle(resultSet.getString(SQLFieldConstant.News.TITLE));
            news.setImageUrl(resultSet.getString(SQLFieldConstant.News.IMAGE_URL));
            news.setDateCreation(resultSet.getTimestamp(SQLFieldConstant.News.DATE_CREATION));
            newsList.add(news);
        }

        return newsList;
    }

    public int createAndGetId(NewsEntity entity) throws DAOException {
        int newsId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_NEWS,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getText());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                newsId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Create news error ", e);
        }

        return newsId;
    }

    public int findNewsCount() throws DAOException {
        int countNews = 0;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_NEWS_COUNT)) {

            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                countNews = resultSet.getInt(COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("Find count news error ", e);
        }

        return countNews;
    }

    public boolean updateImagePath(NewsEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_IMAGE_PATH_NEWS)) {

            statement.setString(1, entity.getImageUrl());
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update news error ", e);
        }

        return isUpdated;
    }

    @Override
    public NewsEntity findEntityById(int id) throws DAOException {
        NewsEntity news;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_NEWS_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            news = new NewsEntity();

            if (resultSet.next()) {
                news.setId(resultSet.getInt(SQLFieldConstant.News.ID));
                news.setText(resultSet.getString(SQLFieldConstant.News.TEXT));
                news.setTitle(resultSet.getString(SQLFieldConstant.News.TITLE));
                news.setImageUrl(resultSet.getString(SQLFieldConstant.News.IMAGE_URL));
                news.setDateCreation(resultSet.getDate(SQLFieldConstant.News.DATE_CREATION));
            }

        } catch (SQLException e) {
            throw new DAOException("Find news error ", e);
        }

        return news;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        boolean isDeleted = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_NEWS_BY_ID)) {
            statement.setInt(1, id);

            isDeleted = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Delete news error ", e);
            }
        }
        return isDeleted;
    }


    @Override
    public boolean delete(NewsEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(NewsEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(NewsEntity entity) {
        throw new UnsupportedOperationException();
    }
}
