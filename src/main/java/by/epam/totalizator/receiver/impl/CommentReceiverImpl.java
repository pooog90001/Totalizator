package by.epam.totalizator.receiver.impl;

import by.epam.totalizator.constant.GeneralConstant;
import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.dao.TransactionManager;
import by.epam.totalizator.dao.impl.CommentDAOImpl;
import by.epam.totalizator.entity.CommentEntity;
import by.epam.totalizator.entity.UserEntity;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;
import by.epam.totalizator.receiver.CommentReceiver;
import by.epam.totalizator.validator.impl.CommentValidatorImpl;
import by.epam.totalizator.validator.impl.CommonValidatorImpl;
import by.epam.totalizator.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;


public class CommentReceiverImpl implements CommentReceiver {

    @Override
    public void changeLockComment(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(GeneralConstant.USER);
        String[] stringCommentId = content.getRequestParameters().get(GeneralConstant.COMMENT_ID);
        String[] isLockedCommentString = content.getRequestParameters().get(GeneralConstant.IS_BLOCKED);
        boolean isLockedComment = Boolean.valueOf(isLockedCommentString[0]);

        if (!userValidator.isAdmin(user) && !userValidator.isBookmaker(user)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(GeneralConstant.ACCESS_DENIED, new Gson().toJsonTree(true));
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
        UserEntity user = (UserEntity) content.getSessionAttributes().get(GeneralConstant.USER);
        String[] stringNewsId = content.getRequestParameters().get(GeneralConstant.NEWS_ID);
        String[] commentTextArr = content.getRequestParameters().get(GeneralConstant.TEXT);


        if (user == null) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(GeneralConstant.ACCESS_DENIED, new Gson().toJsonTree(true));
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
