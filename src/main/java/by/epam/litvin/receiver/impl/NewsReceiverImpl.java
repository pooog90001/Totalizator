package by.epam.litvin.receiver.impl;

import by.epam.litvin.bean.NewsEntity;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.dao.CommentDAO;
import by.epam.litvin.dao.NewsDAO;
import by.epam.litvin.dao.TransactionManager;
import by.epam.litvin.exception.DAOException;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.NewsReceiver;
import by.epam.litvin.util.NewsFormatter;

import java.util.List;
import java.util.Map;

import static by.epam.litvin.constant.GeneralConstant.*;

public class NewsReceiverImpl implements NewsReceiver {

    @Override
    public void openAllNewsPage(RequestContent requestContent) throws ReceiverException {

        String[] page = requestContent.getRequestParameters().get("pageNumber");

        int startIndex = (page != null) ? Integer.valueOf(page[0]) : 1;
        startIndex = (startIndex - 1) * 2;

        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAO newsDAO = new NewsDAO();
            handler.beginTransaction(newsDAO);
            List<NewsEntity> newsList = newsDAO.find(startIndex, 2);
            int newsCount = newsDAO.findNewsCount();
            handler.commit();
            handler.endTransaction();

            NewsFormatter newsFormatter = new NewsFormatter();
            newsFormatter.formatNewsforPreview(newsList);

            requestContent.getRequestAttributes().put(NEWS_LIST, newsList);
            requestContent.getRequestAttributes().put("limit", 2);
            requestContent.getRequestAttributes().put("newsCount", newsCount);

        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                    handler.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void openConcreteNewsPage(RequestContent requestContent) throws ReceiverException {
        String[] newsIdString = requestContent.getRequestParameters().get(NEWS_ID);
        String[] invalidText = requestContent.getRequestParameters().get(INVALID_TEXT);
        int newsId = Integer.valueOf(newsIdString[0]);

        if (invalidText != null) {
            requestContent.getRequestAttributes().put(INVALID_TEXT, invalidText[0]);
        }

        TransactionManager handler = null;
        try {
            handler = new TransactionManager();
            NewsDAO newsDAO = new NewsDAO();
            CommentDAO commentDAO = new CommentDAO();
            handler.beginTransaction(newsDAO, commentDAO);
            NewsEntity news = newsDAO.findEntityById(newsId);

            if (news == null) {
                requestContent.getRequestAttributes().put(WRONG_NEWS, true);
                handler.commit();
                handler.endTransaction();
                return;
            }

            List<Map<String, Object>> newsCommentList = commentDAO.findNewsComments(newsId);
            handler.commit();
            handler.endTransaction();

            requestContent.getRequestAttributes().put("newsCommentList", newsCommentList);
            requestContent.getRequestAttributes().put("attrNews", news);

        } catch (DAOException e) {
            if (handler != null) {
                try {
                    handler.rollback();
                    handler.endTransaction();
                } catch (DAOException e1) {
                    throw new ReceiverException("Rollback error", e);
                }
            }
            throw new ReceiverException(e);
        }
    }
}
