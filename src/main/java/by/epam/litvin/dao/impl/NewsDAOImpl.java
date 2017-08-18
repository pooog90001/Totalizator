package by.epam.litvin.dao.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.constant.SQLFieldConstant;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.dao.DAO;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.litvin.constant.SQLRequestConstant.UPDATE_IMAGE_PATH_NEWS;

public class NewsDAOImpl extends DAO<NewsEntity> {

    @Override
    public List<NewsEntity> findAll() throws DAOException {
        List<NewsEntity> newsList;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_ALL_NEWS)) {
            ResultSet resultSet = statement.executeQuery();
            newsList = extractNewsData(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Find news error ", e);
        }

        return newsList;
    }

    public List<NewsEntity> find(int startIndex, int limit) throws DAOException {
        List<NewsEntity> newsList;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_LIMIT_NEWS)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);

            ResultSet resultSet = statement.executeQuery();
            newsList = extractNewsData(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Find news error ", e);
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
            news.setDateCreation(resultSet.getDate(SQLFieldConstant.News.DATE_CREATION));
            newsList.add(news);
        }

        return newsList;
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

    public int findNewsCount() throws DAOException {
        int countNews = 0;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_NEWS_COUNT)) {

            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                countNews = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            throw new DAOException("Find count news error ", e);
        }

        return countNews;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(NewsEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(NewsEntity entity) throws DAOException {
        throw new UnsupportedOperationException();
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

    public void updateImagePath(NewsEntity entity) throws DAOException {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_IMAGE_PATH_NEWS)) {

            statement.setString(1, entity.getImageUrl());
            statement.setInt(2, entity.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Update news error ", e);
        }

    }

    @Override
    public boolean update(NewsEntity entity) {
        throw new UnsupportedOperationException();
    }
}