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

import java.util.HashMap;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;


public class CommentReceiverImpl implements CommentReceiver {

    @Override
    public void changeLockComment(RequestContent requestContent) throws ReceiverException {
        UserValidatorImpl userValidator = new UserValidatorImpl();
        UserEntity user = (UserEntity) requestContent.getSessionAttributes().get(USER);
        String[] newsIdString = requestContent.getRequestParameters().get(NEWS_ID);
        String[] commentIdString = requestContent.getRequestParameters().get(COMMENT_ID);
        String[] isLockedCommentString = requestContent.getRequestParameters().get(IS_BLOCKED);
        int newsId = Integer.valueOf(newsIdString[0]);
        int commentId = Integer.valueOf(commentIdString[0]);
        boolean isLockedComment = Boolean.valueOf(isLockedCommentString[0]);
        Map<String, Object> data = new HashMap<>();

        if (userValidator.isUser(user)) {
            data.put(ACCESS_DENIED, true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
            return;
        }


        TransactionManager manager = new TransactionManager();
        try {
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            manager.beginTransaction(commentDAO);
            commentDAO.changeLockCommentById(commentId, !isLockedComment);
            manager.commit();
            manager.endTransaction();

            data.put(NEWS_ID, newsId);
            requestContent.getSessionAttributes().put(TEMPORARY, data);


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
    public void createComment(RequestContent requestContent) throws ReceiverException {
        CommentValidatorImpl commentValidator = new CommentValidatorImpl();
        UserEntity user = (UserEntity) requestContent.getSessionAttributes().get(USER);
        String[] newsIdString = requestContent.getRequestParameters().get(NEWS_ID);
        String commentText = requestContent.getRequestParameters().get(TEXT)[0].trim();
        int newsId = Integer.valueOf(newsIdString[0]);
        Map<String, Object> data = new HashMap<>();

        if (user == null) {
            requestContent.getRequestAttributes().put(ACCESS_DENIED, true);
            return;
        }
        if (!commentValidator.isCommentTextValid(commentText)) {
            data.put(INVALID_TEXT, true);
            requestContent.getSessionAttributes().put(TEMPORARY, data);
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
            data.put(NEWS_ID, newsId);
            requestContent.getSessionAttributes().put(TEMPORARY, data);



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
