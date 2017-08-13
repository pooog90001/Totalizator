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
        throw new UnsupportedOperationException();    }

    @Override
    public boolean update(NewsEntity entity) {
        throw new UnsupportedOperationException();
    }
}
