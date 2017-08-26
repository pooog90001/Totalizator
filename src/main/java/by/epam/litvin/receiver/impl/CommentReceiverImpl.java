package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CommentEntity;
import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.dao.impl.CommentDAOImpl;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommentReceiver;
import by.epam.litvin.validator.impl.CommentValidatorImpl;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import by.epam.litvin.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;

import static by.epam.litvin.constant.GeneralConstant.*;


public class CommentReceiverImpl implements CommentReceiver {

    @Override
    public void changeLockComment(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringCommentId = content.getRequestParameters().get(COMMENT_ID);
        String[] isLockedCommentString = content.getRequestParameters().get(IS_BLOCKED);
        boolean isLockedComment = Boolean.valueOf(isLockedCommentString[0]);

        if (!userValidator.isAdmin(user) && !userValidator.isBookmaker(user)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        if (!commonValidator.isVarExist(stringCommentId) || !commonValidator.isInteger(stringCommentId[0])) {
            content.setAjaxSuccess(false);
            return;
        }

        int commentId = Integer.valueOf(stringCommentId[0]);
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
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        CommentValidatorImpl commentValidator = new CommentValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(USER);
        String[] stringNewsId = content.getRequestParameters().get(NEWS_ID);
        String[] commentTextArr = content.getRequestParameters().get(TEXT);


        if (user == null) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        if (!commonValidator.isVarExist(commentTextArr) ||
                !commonValidator.isVarExist(stringNewsId) ||
                !commonValidator.isInteger(stringNewsId[0]) ||
                !commentValidator.isCommentTextValid(commentTextArr[0].trim())) {
            content.setAjaxSuccess(false);
            return;
        }

        CommentEntity comment = new CommentEntity();
        comment.setUserId(user.getId());
        comment.setNewsId(Integer.valueOf(stringNewsId[0]));
        comment.setText(commentTextArr[0].trim());

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
