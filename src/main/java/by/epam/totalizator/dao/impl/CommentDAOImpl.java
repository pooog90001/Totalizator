package by.epam.totalizator.dao.impl;

import by.epam.totalizator.bean.CommentEntity;
import by.epam.totalizator.constant.SQLFieldConstant;
import by.epam.totalizator.constant.SQLRequestConstant;
import by.epam.totalizator.dao.DAO;
import by.epam.totalizator.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDAOImpl extends DAO<CommentEntity> {
    @Override
    public List<CommentEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();

    }

    @Override
    public CommentEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();

    }

    public List<Map<String, Object>> findCommentsByNewsId(int newsId) throws DAOException {
        List<Map<String, Object>> newsCommentList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.FIND_NEWS_COMMENTS)) {
            statement.setInt(1, newsId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> newsComment = new HashMap<>();
                newsComment.put(SQLFieldConstant.Comment.ID,
                        resultSet.getInt(SQLFieldConstant.Comment.ID));
                newsComment.put(SQLFieldConstant.Comment.IS_BLOCKED,
                        resultSet.getBoolean(SQLFieldConstant.Comment.IS_BLOCKED));
                newsComment.put(SQLFieldConstant.Comment.POST_DATE,
                        resultSet.getDate(SQLFieldConstant.Comment.POST_DATE));
                newsComment.put(SQLFieldConstant.Comment.TEXT,
                        resultSet.getString(SQLFieldConstant.Comment.TEXT));
                newsComment.put(SQLFieldConstant.User.AVATAR_URL,
                        resultSet.getString(SQLFieldConstant.User.AVATAR_URL));
                newsComment.put(SQLFieldConstant.User.NAME,
                        resultSet.getString(SQLFieldConstant.User.NAME));
                newsCommentList.add(newsComment);
            }

        } catch (SQLException e) {
            throw new DAOException("Error find news comments ",e);
        }

        return newsCommentList;
    }

    @Override
    public boolean create(CommentEntity entity) throws DAOException {
        boolean isCreated;

        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CREATE_COMMENT)) {
            statement.setString(1, entity.getText());
            statement.setInt(2, entity.getNewsId());
            statement.setInt(3, entity.getUserId());
            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Error create comment",e);
        }

        return isCreated;
    }

    public void changeLockCommentById(int idComment, boolean changeValue) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.CHANGE_LOCK_COMMENT)) {
            statement.setBoolean(1, changeValue);
            statement.setInt(2, idComment);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error change comment lock ",e);
        }
    }

    public boolean deleteByNewsId(int newsId) throws DAOException {
        boolean isDeleted;
        try (PreparedStatement statement = connection.prepareStatement(SQLRequestConstant.DELETE_COMMENT_BY_NEWS_ID)) {
            statement.setInt(1,newsId);
            isDeleted = !statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Error delete comment",e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(CommentEntity entity) throws DAOException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean update(CommentEntity entity) throws DAOException {
        throw new UnsupportedOperationException();

    }
}
