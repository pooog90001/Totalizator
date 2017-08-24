package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CommentEntity;
import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.impl.CommentDAOImpl;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommentReceiver;
import by.epam.litvin.validator.impl.CommentValidatorImpl;
import by.epam.litvin.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;


public class CommentReceiverImpl implements CommentReceiver {

    @Override
    public void changeLockComment(RequestContent content) throws ReceiverException {
        UserValidatorImpl userValidator = new UserValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] commentIdString = content.getRequestParameters().get(COMMENT_ID);
        String[] isLockedCommentString = content.getRequestParameters().get(IS_BLOCKED);

        int commentId = Integer.valueOf(commentIdString[0]);
        boolean isLockedComment = Boolean.valueOf(isLockedCommentString[0]);

        if (!userValidator.isAdmin(user) && !userValidator.isBookmaker(user)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            manager.beginTransaction(commentDAO);
            commentDAO.changeLockCommentById(commentId, !isLockedComment);
            manager.commit();
            manager.endTransaction();

            content.setAjaxSuccess(true);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Change block comment error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createComment(RequestContent content) throws ReceiverException {
        CommentValidatorImpl commentValidator = new CommentValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] newsIdString = content.getRequestParameters().get(NEWS_ID);
        String commentText = content.getRequestParameters().get(TEXT)[0].trim();
        int newsId = Integer.valueOf(newsIdString[0]);

        if (user == null) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        if (!commentValidator.isCommentTextValid(commentText)) {
            content.setAjaxSuccess(false);
            return;
        }

        CommentEntity comment = new CommentEntity();
        comment.setUserId(user.getId());
        comment.setNewsId(newsId);
        comment.setText(commentText);

        TransactionManager manager = new TransactionManager();
        try {
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            manager.beginTransaction(commentDAO);
            commentDAO.create(comment);
            manager.commit();
            manager.endTransaction();
            content.setAjaxSuccess(true);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Create comment rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
