package by.epam.litvin.dao;

import by.epam.litvin.bean.Entity;
import by.epam.litvin.bean.News;
import by.epam.litvin.constant.SQLRequestConstant;
import by.epam.litvin.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO extends AbstractDAO<News> {
    @Override
    public List<News> findAll() {
        throw new UnsupportedOperationException();
    }

    public List<News> find(int limit) throws DAOException {
        List<News> newsList;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_LIMIT_NEWS)) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            newsList = new ArrayList<>();

            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getInt("news_id"));
                news.setText(resultSet.getString("news_text"));
                news.setTitle(resultSet.getString("news_title"));
                news.setImageUrl(resultSet.getString("news_image_url"));
                news.setDateCreation(resultSet.getDate("news_date_creation"));
                newsList.add(news);
            }
            return newsList;

        } catch (SQLException e) {
            throw new DAOException("Find news error ", e);
        }
    }

    @Override
    public News findEntityById(int id) throws DAOException {
        News news;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_NEWS_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            news = new News();

            if (resultSet.next()) {

                news.setId(resultSet.getInt("news_id"));
                news.setText(resultSet.getString("news_text"));
                news.setTitle(resultSet.getString("news_title"));
                news.setImageUrl(resultSet.getString("news_image_url"));
                news.setDateCreation(resultSet.getDate("news_date_creation"));
            }
            return news;

        } catch (SQLException e) {
            throw new DAOException("Find news error ", e);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(News entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(News entity) throws DAOException {
        throw new UnsupportedOperationException();    }

    @Override
    public boolean update(News entity) {
        throw new UnsupportedOperationException();
    }
}
