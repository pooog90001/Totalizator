package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.CommentEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.CommentDAO;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.receiver.CommentReceiver;

public class CommentReceiverImpl implements CommentReceiver {

    @Override
    public void blockComment(RequestContent requestContent) {

    }

    @Override
    public void postComment(RequestContent requestContent) {
        String[] userIdString = requestContent.getRequestParameters().get("userId");
        String[] newsIdString = requestContent.getRequestParameters().get("newsId");
        String commentText = requestContent.getRequestParameters().get("text")[0];
        int userId = Integer.valueOf(userIdString[0]);
        int newsId = Integer.valueOf(newsIdString[0]);

        CommentEntity comment = new CommentEntity();
        comment.setUserId(userId);
        comment.setNewsId(newsId);
        comment.setText(commentText);

        TransactionManager manager = null;
        try {
            manager = new TransactionManager();
            CommentDAO commentDAO = new CommentDAO();
            manager.beginTransaction(commentDAO);
            commentDAO.create(comment);
            manager.commit();


        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
